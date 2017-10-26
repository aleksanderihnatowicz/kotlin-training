package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable
import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Repository
import kotlin.reflect.KClass

abstract class MemoryRepository<Entity : Identifiable<Id>, Id> : Repository<Entity, Id> {
    protected val entities = HashMap<Id, Entity>()

    override fun save(entity: Entity) {
        val id = entity.id ?: throw InvalidEntityIdException(entity::class)
        entities[id] = entity
    }

    override fun findById(id: Id): Entity? = entities[id]

    override fun findAll(): List<Entity> = entities.values.toList()

    override fun contains(id: Id): Boolean = id in entities
}

class InvalidEntityIdException(entityClass: KClass<*>) : RuntimeException("Entity of type ${entityClass.simpleName} has no id.")
