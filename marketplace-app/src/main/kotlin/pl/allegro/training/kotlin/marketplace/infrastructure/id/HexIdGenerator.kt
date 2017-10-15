package pl.allegro.training.kotlin.marketplace.infrastructure.id

import pl.allegro.training.kotlin.util.hash.SecureHashAlgorithmUtils
import java.util.UUID

class HexIdGenerator : IdGenerator {
    override fun getNextId(): String = SecureHashAlgorithmUtils.sha1(UUID.randomUUID().toString()).take(8)
}