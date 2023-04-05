package edu.temple.assignment7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var books: BookList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hasTwoContainers = true

        // initialises the booklist
        books = BookList()
        val bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val bookTitles = resources.getStringArray(R.array.book_titles)
        val bookAuthors = resources.getStringArray(R.array.book_authors)

        for (i in bookTitles.indices) {
            val book = Book(bookTitles[i], bookAuthors[i])
            books.add(book)
        }

        val fragment1 = BookListFragment()
        bookViewModel.setBooks(books)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView2, fragment1)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }

        if (hasTwoContainers) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, BookFragment())
                .commit()
        }
        else {
            bookViewModel.getSelectedBook().observe(this) {
                if (!bookViewModel.hasSeenSelection && !hasTwoContainers) {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView, BookFragment())
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit()

                    bookViewModel.hasSeenSelection = true
                }

            }
        }
    }
}