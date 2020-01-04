package com.eko.moviesapp.view.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eko.moviesapp.R
import com.eko.moviesapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setSupportActionBar(toolbar_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pages = listOf(FavoriteMovieFragment(), FavoriteTvShowFragment())
        vp_favorite.adapter = ViewPagerAdapter(supportFragmentManager, this, pages)
        tabs_fvorite.setupWithViewPager(vp_favorite)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
