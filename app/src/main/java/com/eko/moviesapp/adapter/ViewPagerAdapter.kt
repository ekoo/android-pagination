package com.eko.moviesapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eko.moviesapp.R
import com.eko.moviesapp.view.MoviesFragment
import com.eko.moviesapp.view.TvFragment

class ViewPagerAdapter(fm: FragmentManager, context: Context, private val pages: List<Fragment>): FragmentPagerAdapter(fm) {

    private val movieTabTitle: String = context.getString(R.string.movies)
    private val tvShowTabTitle: String = context.getString(R.string.tv_show)

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> movieTabTitle
            else -> tvShowTabTitle
        }
    }



}