package pl.allegro.training.kotlin.marketplace.domain.search

data class DocumentId(val value: String)

class Document(val id: DocumentId, val text: String)

interface Tokenizer {
    fun tokenize(text: String): Set<String>
}

class WhitespaceTokenizer : Tokenizer {
    private val whitespaceRegex = Regex("\\s+")

    override fun tokenize(text: String): Set<String> = text.split(whitespaceRegex).toSet()
}

interface Index {
    fun addTokenOccurrence(occurrence: Pair<String, DocumentId>)
    fun getTokenOccurrences(token: String): Set<DocumentId>
}

class MemoryIndex : Index {
    private val store = HashMap<String, Set<DocumentId>>()

    override fun addTokenOccurrence(occurrence: Pair<String, DocumentId>) {
        val (token, docId) = occurrence
        // omitting unwanted arguments with _
        // if one-liner
        store.compute(token) { _, documentIds ->
            if (documentIds == null) mutableSetOf(docId) else documentIds + docId
        }
    }

    override fun getTokenOccurrences(token: String): Set<DocumentId> {
        // emptySet()
        return store.getOrDefault(token, emptySet())
    }
}

class Indexer(private val index: Index, private val tokenizer: Tokenizer) {

    fun add(doc: Document) {
        val tokens = tokenizer.tokenize(doc.text)
        tokens.forEach {
            index.addTokenOccurrence(it to doc.id)
        }
    }
}

class Searcher(private val index: Index) {
    private val parser = QueryParser()

    fun search(queryText: String): List<DocumentId> {
        val query = parser.parse(queryText)
        val validDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.required }.map { it.value })
        val invalidDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.forbidden }.map { it.value })
        val optionalDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.optional }.map { it.value })
        // operation on sets with operators
        return (validDocs - invalidDocs).sortedWith(BoostingComparator(optionalDocs).reversed())
    }

    private fun Index.getDocumentsWithTokens(tokens: List<String>): Set<DocumentId> =
            tokens.flatMap { token -> this.getTokenOccurrences(token) }.toSet()
}

class BoostingComparator(private val boostedIds: Set<DocumentId>) : Comparator<DocumentId> {
    override fun compare(aDocId: DocumentId?, bDocId: DocumentId?): Int =
            boostedIds.contains(aDocId).compareTo(boostedIds.contains(bDocId))
}

// construction possibility blocked by private constructor - must use companion object
class Phrase private constructor(
        val value: String,
        val required: Boolean = false,
        val optional: Boolean = false,
        val forbidden: Boolean = false
) {
    // companion object
    // named parameters
    companion object {
        fun required(value: String) = Phrase(value, required = true)

        fun optional(value: String) = Phrase(value, optional = true)

        fun forbidden(value: String) = Phrase(value, forbidden = true)
    }
}

//sealed class Phrase2(val value: String) {
//    class Required(value: String) : Phrase2(value)
//    class Optional(value: String) : Phrase2(value)
//    class Forbidden(value: String): Phrase2(value)
//}

typealias Query = HashSet<Phrase>

//class Query(phrases: Collection<Phrase>) : HashSet<Phrase>(phrases)

class QueryParser {
    private val tokenizer = WhitespaceTokenizer()

    // using let()'s lambda version - very readable code; alternative - constructor reference
    fun parse(query: String): Query = tokenizer.tokenize(query).map { it.asPhrase() }.let { Query(it) }

    // when expression
    // getting first character of string with [] operator
    private fun String.asPhrase(): Phrase = when (this.firstCharacter) {
        '+'  -> Phrase.required(this.substring(1))
        '-'  -> Phrase.forbidden(this.substring(1))
        else -> Phrase.optional(this)
    }

    // extension property
    private val String.firstCharacter
        get() = this[0]
}

fun main(args: Array<String>) {
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
}