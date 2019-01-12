package com.example.diksha.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.annotation.NonNull
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.MenuItem
import android.support.v4.view.ViewPager
import android.util.Log
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> viewpager.currentItem = 0
            R.id.navigation_dashboard -> viewpager.currentItem = 1
            R.id.navigation_notifications -> viewpager.currentItem = 2
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setupViewPager()
    }

    private fun setupViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment(), "home")
        adapter.addFragment(DashboardFragment(), "Dashboard")
        adapter.addFragment(NotificationsFragment(), "Notifications")

        viewpager.adapter = adapter

    }

    internal inner class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
        private val fragmentList = ArrayList<Fragment>()
        private val titleFragmentList = ArrayList<String>()

        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleFragmentList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleFragmentList[position]
        }
    }
}
