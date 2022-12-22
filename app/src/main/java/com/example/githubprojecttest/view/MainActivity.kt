package com.example.githubprojecttest.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubprojecttest.R
import com.example.githubprojecttest.adapters.ListUsersAdapter
import com.example.githubprojecttest.databinding.ActivityMainBinding
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.preferense.PreferenseHelper
import com.example.githubprojecttest.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity(), Navigation {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme()
        if (savedInstanceState == null) {
            showRepos()
            // showCurrentGitHubUser()
        }
        clickBottomBar()
    }

    private fun clickBottomBar() {
        binding.bottomNavigationView.setOnItemSelectedListener {
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
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun showGitHubListFragment() {
        replaceFragment(ListGitHubUsersFragment.newInstance())
    }

    override fun showInputNameGitHubUserFragment() {
        replaceFragment(InputNameGitHubUserFragment.newInstance())
    }

    override fun showCurrentGitHubUser() {
        replaceFragment(InputNameGitHubUserFragment.newInstance())
    }

    override fun showSettings() {
        replaceFragment(SettingsFragment.newInstance())
    }

    override fun showRepos() {
        replaceFragment(GitHubRepoListFragment.newInstance())
    }

    override fun showCurrentUser(user: GitHubUserModel) {
        replaceFragment(UserFragment.newInstance(user = user))
    }


    private fun setTheme() {
        val pref = PreferenseHelper(this)
        when (pref.getTheme()) {
            "Base" -> {
                setTheme(R.style.Light_Theme_GitHubProjectTest)
            }
            "Dark" -> {
                setTheme(R.style.Dark_Theme_GitHubProjectTest)
            }
        }
        viewModel.theme.observe(this) { changeTheme ->
            if (pref.getTheme() != changeTheme) {
                when (changeTheme) {
                    "Base" -> {
                        pref.saveTheme("Base")
                    }
                    "Dark" -> {
                        pref.saveTheme("Dark")
                    }
                }
                recreate()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}