package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Repository

interface OrderRepository : Repository<Order, String> {
    fun findByBuyerId(id: String): List<Order>
}