package com.dexler.gachades.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dexler.gachades.R
import com.dexler.gachades.emails.EmailFragment
import com.dexler.gachades.installations.InstallationFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val COUNT = 2
        private val EMAIL_SCREEN = 0
        private val INSTALLATION_SCREEN = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs.setupWithViewPager(viewpager)
        viewpager.adapter = ScheduleAdapter(supportFragmentManager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    /**
     * Adapter that builds a page for each conference day.
     */
    inner class ScheduleAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {

        override fun getCount() = COUNT

        override fun getItem(position: Int): Fragment {
            return when (position) {
                EMAIL_SCREEN -> EmailFragment()
                else -> InstallationFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                EMAIL_SCREEN -> "Emails"
                else -> "Installations"
            }
        }
    }
}
