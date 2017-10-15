package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.domain.account.AccountNotFoundException
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.misc.IdGenerator
import pl.allegro.training.kotlin.marketplace.domain.misc.Identifiable
import pl.allegro.training.kotlin.marketplace.domain.misc.Repository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferNotFoundException
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository
import java.math.BigDecimal
import kotlin.reflect.KClass

sealed class DeliveryMethod {
    object InStorePickup
    class Courier(val cost: BigDecimal)
    class DroneShipment()
}

sealed class PaymentMethod {
    object CashOnDelivery
    class BankTransfer(val accountNumber: String)
    class Bitcoin(val walletAddress: String)
}

data class Order(
        override val id: String? = null,
        val offerId: String,
        val buyerId: String,
        val status: OrderStatus = OrderStatus.PENDING
) : Identifiable<String>

enum class OrderStatus {
    PENDING, ACCEPTED, DELIVERED
}

class OrderService(
        private val idGenerator: IdGenerator,
        private val orderRepository: OrderRepository,
        private val accountRepository: AccountRepository,
        private val orderValidator: OrderValidator
) {

    fun createOrder(order: Order): Order {
        orderValidator.validate(order)
        val persisted = order.copy(id = idGenerator.getNextId())
        orderRepository.save(order)
        return persisted
    }

    fun getOrdersByBuyer(buyerId: String): List<Order> {
        if (!accountRepository.exists(buyerId)) throw AccountNotFoundException(buyerId)
        return orderRepository.findByBuyerId(buyerId)
    }
}

class OrderValidator(private val offerRepository: OfferRepository, private val accountRepository: AccountRepository) {
    fun validate(order: Order) {
        if (offerRepository.findById(order.offerId) == null) {
            throw OfferNotFoundException(order.offerId)
        }

        if (accountRepository.findById(order.buyerId) == null) {
            throw AccountNotFoundException(order.buyerId)
        }
    }
}

interface OrderRepository : Repository<Order, String> {
    fun findByBuyerId(id: String): List<Order>
}

abstract class MemoryRepository<Entity : Identifiable<Id>, Id> : Repository<Entity, Id> {
    protected val entities = HashMap<Id, Entity>()

    override fun save(entity: Entity) {
        if (entity.id == null) {
            throw InvalidEntityIdException(entity::class)
        }
        // too bad, smart cast not working
        // setting map using [] operator
        entities[entity.id!!] = entity
    }

    // getting from map through get operator
    override fun findById(id: Id): Entity? = entities[id]

    override fun findAll(): List<Entity> = entities.values.toList()

    // in operator for checking if map contains key
    override fun exists(id: Id): Boolean = id in entities
}

class InvalidEntityIdException(entityClass: KClass<*>) : RuntimeException("Entity of type ${entityClass.simpleName} has no id.")

class MemoryOrderRepository : MemoryRepository<Order, String>(), OrderRepository {

    override fun findByBuyerId(id: String): List<Order> = entities.values.filter { it.buyerId == id }

}
