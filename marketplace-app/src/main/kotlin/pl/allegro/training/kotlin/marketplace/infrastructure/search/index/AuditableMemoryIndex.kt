package pl.allegro.training.kotlin.marketplace.infrastructure.search.index

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.logger

// @Konrad
// 4 cześć

class AuditableMemoryIndex(private val delegate: MemoryIndex): Index by delegate {
    override fun addTokenOccurrence(occurrence: Pair<String, DocumentId>) {
        logger.debug("Adding token {} with document {}", occurrence.first, occurrence.second)
        delegate.addTokenOccurrence(occurrence)
    }

    companion object {
        private val logger by logger()
    }
}