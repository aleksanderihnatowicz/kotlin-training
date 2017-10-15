package pl.allegro.training.kotlin.marketplace.domain.search


data class DocumentId(val value: String)

class Document(val id: DocumentId, val text: String)