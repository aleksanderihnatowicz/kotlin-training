package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.domain.account.AccountNotFoundException
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator

class OrderService(
        private val idGenerator: IdGenerator,
        private val orderRepository: OrderRepository,
        private val accountRepository: AccountRepository,
        private val orderValidator: OrderValidator
) {

    fun createOrder(order: Order): Order {
        orderValidator.validate(order)
        val persisted = order.copy(id = idGenerator.getNextId())
        orderRepository.save(persisted)
        return persisted
    }

    fun getOrdersByBuyer(buyerAccountId: String): List<Order> {
        if (buyerAccountId !in accountRepository) throw AccountNotFoundException(buyerAccountId)
        return orderRepository.findByBuyerId(buyerAccountId)
    }

    fun addPayment(orderId: String, payment: Payment): Order {
        val order = orderRepository.findById(orderId) ?: throw OrderNotFoundException(orderId)
        val orderWithPayment = order.copy(payment = payment, status = OrderStatus.PENDING)
        orderRepository.save(orderWithPayment)
        return orderWithPayment
    }
}