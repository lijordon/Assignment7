package edu.temple.assignment7

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel: ViewModel() {
    private val selectedBook = MutableLiveData<Book>()
    private val books = MutableLiveData<BookList>()

    var hasSeenSelection = false

    fun setSelectedBook(bookChosen:Book) {
        hasSeenSelection = false
        Log.d("setSelectedBook", "Book = $bookChosen.title")
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