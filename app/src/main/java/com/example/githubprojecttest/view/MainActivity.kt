package com.example.githubprojecttest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.ActivityMainBinding
import com.example.githubprojecttest.navigation.Navigation

class MainActivity : AppCompatActivity(), Navigation {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null)
            showCurrentGitHubUser()

        clickBottomBar()

    }

    fun clickBottomBar() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_search -> {
                    showCurrentGitHubUser()
                }
                R.id.item_list -> {
                    showGitHubListFragment()
                }
                R.id.item_settings -> {
                    showSettings()
                }
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun showGitHubListFragment() {
        replaceFragment(ListGitHubUsersFragment.newInstance())
    }

    override fun showCurrentGitHubUser() {
        replaceFragment(InputNameGitHubUserFragment.newInstance())
    }

    override fun showSettings() {
        replaceFragment(SettingsFragment.newInstance())
    }
}