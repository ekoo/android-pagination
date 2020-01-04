package com.eko.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.eko.moviesapp.adapter.ViewPagerAdapter
import com.eko.moviesapp.view.MoviesFragment
import com.eko.moviesapp.view.TvFragment
import com.eko.moviesapp.view.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val pages = listOf(MoviesFragment(), TvFragment())
        vp_main.adapter = ViewPagerAdapter(supportFragmentManager, this, pages)
        tabs_main.setupWithViewPager(vp_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tm_favorite -> {
                startActivity<FavoriteActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
