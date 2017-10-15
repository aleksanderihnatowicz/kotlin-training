package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable
import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Repository
import kotlin.reflect.KClass

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
