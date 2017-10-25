package pl.allegro.training.kotlin.marketplace.infrastructure.search

import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.Tokenizer

val LineTokenizer = object: Tokenizer {
    override fun tokenize(text: String): Set<String> = text.lineSequence().toSet()
}

/*fun main(args: Array<String>) {
    val index = MemoryIndex()
    val indexer = Indexer(index, WhitespaceTokenizer())
    val doc1 = Document(DocumentId("1"), "ala ma kota")
    val doc2 = Document(DocumentId("2"), "ola ma psa")
    // apply eases multiple invocation on receiver object
    //indexer.add(doc1)
    //indexer.add(doc2)
    indexer.apply {
        add(doc1)
        add(doc2)
    }
    //with version
//    with(indexer) {
//        add(doc1)
//        add(doc2)
//    }
    val searcher = Searcher(index)
    val docs = searcher.search("+ma")
    println(docs)
}*/