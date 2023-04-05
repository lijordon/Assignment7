package edu.temple.assignment7

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val BOOK_TITLE_KEY = "bookTitle"
const val BOOK_AUTHOR_KEY = "bookAuthor"
class BookFragment : Fragment() {

    private var bookTitle: String? = null
    private var bookAuthor: String? = null

    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false).apply {
            titleTextView = findViewById(R.id.titleTextViewRecycler)
            authorTextView = findViewById(R.id.authorTextViewRecycler)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookViewModel.getSelectedBook().observe(requireActivity()){
            changeBook(it)
        }
    }

    fun changeBook(BookChosen: Book) {

        BookChosen.title?.let { titleTextView.text = it }
        BookChosen.author?.let { authorTextView.text = it }
    }

    companion object {
        @JvmStatic
        fun newInstance(book: Book) = BookFragment().apply {
            arguments = Bundle().apply {
                putString(BOOK_TITLE_KEY, book.title)
                putString(BOOK_AUTHOR_KEY, book.author)
            }
        }
    }
}
