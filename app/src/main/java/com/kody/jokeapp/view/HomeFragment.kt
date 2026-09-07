package com.kody.jokeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kody.jokeapp.R
import com.kody.jokeapp.data.CategoryRemoteDataSource
import com.kody.jokeapp.model.Category
import com.kody.jokeapp.presentation.HomePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HomeFragment:  Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: HomePresenter
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //SE EU PASSAR DESTA FORMA NO HOMEPRESETE NAO PRECISSO PASSAR NADA NO CONSTRUTOR
        //private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
        //QUER DIZER NAO RECISSO CIRAR ISTO val dataSource = CategoryRemoteDataSource()
        val dataSource = CategoryRemoteDataSource()
        presenter = HomePresenter(this, dataSource)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container,false) //super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        progressBar = view.findViewById(R.id.progress_bar)


        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        if (adapter.itemCount == 0){
            presenter.findAllCategories()

        }

        recyclerView.adapter = adapter


    adapter.setOnItemClickListener { item, view ->
        val bundle = Bundle()
        val categoryName = (item as CategoryItem).category.name
        bundle.putString(JokeFragment.CATEGORY_KEY, categoryName)
        findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
    }







    }
    fun showCategories(categories: List<Category>){
        val categories = categories.map { CategoryItem(it) }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }


    fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress(){
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}