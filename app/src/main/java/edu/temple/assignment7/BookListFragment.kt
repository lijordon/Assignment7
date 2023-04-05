package edu.temple.assignment7

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val BOOK_ARRAY_KEY = "bookarraykey"


class BookListFragment : Fragment() {
    private lateinit var books: BookList
    private lateinit var recyclerView1: RecyclerView

    lateinit var bookViewModel: BookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView1 = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView1.layoutManager = GridLayoutManager(requireContext(), 2)
        bookViewModel.getBooks().observe(requireActivity()) {
            recyclerView1.adapter = CustomRecyclerAdapter(it) {bookChosen: Book ->
                bookViewModel.setSelectedBook(bookChosen)

            }
        }
    }
}



class CustomRecyclerAdapter(private val bookList: BookList, private val callback: (Book)->Unit): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
        val titleTextView = layout.findViewById<TextView>(R.id.titleTextViewRecycler)
        val authorTextView = layout.findViewById<TextView>(R.id.authorTextViewRecycler)

        // WHY DOES IT CRASH HERE?
        // WHY IS TITLETEXTVIEW NULL HERE?
        // LAYOUT IS NOT NULL HERE
        init {
            Log.d("MyViewHolder","MyViewHolder created with layout: $layout")
            Log.d("titleTextView","titleTextView value: $titleTextView")
            titleTextView.setOnClickListener { callback(bookList[adapterPosition]) }
        }

        fun bind(book: Book) {
            titleTextView.text = book.title
            authorTextView.text = book.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookList[position])

    }

    override fun getItemCount() = bookList.size()
}
