package pl.allegro.training.kotlin.marketplace.domain.order


class OrderNotFoundException(orderId: String) : RuntimeException("Order (id = $orderId) not found.")