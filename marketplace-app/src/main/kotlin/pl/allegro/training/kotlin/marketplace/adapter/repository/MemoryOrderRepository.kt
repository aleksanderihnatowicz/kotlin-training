package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.domain.order.Order
import pl.allegro.training.kotlin.marketplace.domain.order.OrderRepository

class MemoryOrderRepository : MemoryRepository<Order, String>(), OrderRepository {

    override fun findByBuyerId(id: String): List<Order> = entities.values.filter { it.buyerId == id }

}