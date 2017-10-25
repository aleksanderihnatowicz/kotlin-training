package pl.allegro.training.kotlin.marketplace.infrastructure.repository

// 4. contains jest napisane bez operatora, wyjasnic słowo kluczowe in i out

// invariance
// dodac dziedziczenie z Identifiable
interface Repository<Entity, in Id> {
    fun save(entity: Entity)
    fun findById(id: Id): Entity?
    fun findAll(): List<Entity>
    operator fun contains(id: Id): Boolean
}