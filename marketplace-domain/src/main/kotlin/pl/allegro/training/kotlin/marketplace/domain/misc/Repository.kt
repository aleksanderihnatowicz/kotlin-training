package pl.allegro.training.kotlin.marketplace.domain.misc

// invariance
interface Repository<Entity, in Id> {
    fun save(entity: Entity)
    fun findById(id: Id): Entity?
    fun findAll(): List<Entity>
    fun exists(id: Id): Boolean
}