package pl.allegro.training.kotlin.marketplace.domain.offer

import java.math.BigDecimal

sealed class Delivery(val cost: BigDecimal)

object SelfPickup : Delivery(cost = BigDecimal.ZERO)

class CourierService(
    cost: BigDecimal,
    val timeInDays: Int
) : Delivery(cost)