package com.catalog.moviecatalogapp.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catalog.moviecatalogapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionPageAdapter = SectionPageAdapter(this,supportFragmentManager)
        binding.viewPager.adapter = sectionPageAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }

}