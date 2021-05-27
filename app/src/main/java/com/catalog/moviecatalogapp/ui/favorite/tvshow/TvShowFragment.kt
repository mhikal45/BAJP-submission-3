package com.catalog.moviecatalogapp.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalog.moviecatalogapp.catalog.factory.ViewModelFactory
import com.catalog.moviecatalogapp.databinding.FragmentTvShowFavoriteBinding

class TvShowFragment : Fragment() {

    private var binding: FragmentTvShowFavoriteBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowFavoriteBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.progressBar?.visibility = View.VISIBLE
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[TvShowViewModel::class.java]

            val showAdapter = TvShowAdapter()

            viewModel.getShow().observe(viewLifecycleOwner, {show ->
                if (show != null) {
                    showAdapter.submitList(show)
                    showAdapter.notifyDataSetChanged()
                }
            })

            with(binding?.rvTvshow) {
                this?.layoutManager = LinearLayoutManager(activity)
                this?.adapter = showAdapter
                binding?.progressBar?.visibility = View.INVISIBLE
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}