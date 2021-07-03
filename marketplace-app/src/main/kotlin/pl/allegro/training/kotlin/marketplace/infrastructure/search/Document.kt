package pl.allegro.training.kotlin.marketplace.infrastructure.search

@JvmInline
value class DocumentId(val value: String)
//typealias DocumentId = String

class Document(val id: DocumentId, val text: String)
