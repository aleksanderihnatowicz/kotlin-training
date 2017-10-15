package pl.allegro.training.kotlin.marketplace.infrastructure.repository

// invariance
interface Repository<Entity, in Id> {
    fun save(entity: Entity)
    fun findById(id: Id): Entity?
    fun findAll(): List<Entity>
    fun exists(id: Id): Boolean
}