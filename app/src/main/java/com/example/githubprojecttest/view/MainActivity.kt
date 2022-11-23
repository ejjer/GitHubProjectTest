package com.example.githubprojecttest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.ActivityMainBinding
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity(), Navigation {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.LightThem)
        if (savedInstanceState == null)
            showCurrentGitHubUser()
        clickBottomBar()
        viewModel.inputUserName.observe(this,{
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

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