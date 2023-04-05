package edu.temple.assignment7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var books: BookList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialises the booklist
        books = BookList()

        val bookTitles = resources.getStringArray(R.array.book_titles)
        val bookAuthors = resources.getStringArray(R.array.book_authors)

        for (i in bookTitles.indices) {
            val book = Book(bookTitles[i], bookAuthors[i])
            books.add(book)
        }

    }
}