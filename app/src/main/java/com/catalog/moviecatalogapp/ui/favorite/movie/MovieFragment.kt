package com.catalog.moviecatalogapp.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalog.moviecatalogapp.catalog.factory.ViewModelFactory
import com.catalog.moviecatalogapp.databinding.FragmentMovieFavoriteBinding

class MovieFragment : Fragment() {

    private var binding: FragmentMovieFavoriteBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding?.progressBar?.visibility = View.VISIBLE
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    movieAdapter.submitList(movies)
                    movieAdapter.notifyDataSetChanged()
                }
            })

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(activity)
                this?.adapter = movieAdapter
                binding?.progressBar?.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}