package edu.temple.assignment7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel: ViewModel() {
    private val selectedBook = MutableLiveData<Book>()
    private val books = MutableLiveData<BookList>()

    var hasSeenSelection = false

    fun setSelectedBook(bookChosen:Book) {
        hasSeenSelection = false
        selectedBook.value = bookChosen
    }

    fun getSelectedBook(): LiveData<Book> {
        return selectedBook
    }

    fun setBooks(booksArray:BookList) {
        this.books.value = booksArray
    }

    fun getBooks(): LiveData<BookList> {
        return books
    }
}