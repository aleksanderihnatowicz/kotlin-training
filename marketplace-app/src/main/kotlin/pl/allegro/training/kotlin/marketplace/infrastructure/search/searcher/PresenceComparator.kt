package pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId

class PresenceComparator(private val ids: Set<DocumentId>) : Comparator<DocumentId> {

    override fun compare(a: DocumentId?, b: DocumentId?): Int {
        val aPresence = if (a in ids) 1 else 0
        val bPresence = if (b in ids) 1 else 0

        return aPresence.compareTo(bPresence)
    }

}