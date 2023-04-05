package edu.temple.assignment7

class BookList {
    private val books: ArrayList<Book> = ArrayList()

    fun add(book: Book) {
        books.add(book)
    }

    fun remove(book: Book) {
        books.remove(book)
    }

    operator fun get(index: Int): Book {
        return books[index]
    }

    fun size(): Int {
        return books.size
    }
}
