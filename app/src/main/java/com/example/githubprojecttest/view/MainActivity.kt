package com.example.githubprojecttest.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.ActivityMainBinding
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.preferense.PreferenseHelper
import com.example.githubprojecttest.viewModels.MainActivityViewModel
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class MainActivity : AppCompatActivity(), Navigation {

    lateinit var binding: ActivityMainBinding
    private lateinit var drawer: Drawer
    private lateinit var header: AccountHeader
    private lateinit var toolbar: Toolbar


    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme()
        if (savedInstanceState == null)
            showCurrentGitHubUser()
        // clickBottomBar()
        viewModel.inputUserName.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            showGitHubListFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

//    private fun clickBottomBar() {
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.item_search -> {
//                    showCurrentGitHubUser()
//                }
//                R.id.item_list -> {
//                    showGitHubListFragment()
//                }
//                R.id.item_settings -> {
//                    showSettings()
//                }
//            }
//            true
//        }
//    }

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

    override fun showCurrentGitHubUser() {
        replaceFragment(InputNameGitHubUserFragment.newInstance())
    }

    override fun showSettings() {
        replaceFragment(SettingsFragment.newInstance())
    }

    override fun showCurrentUser(user: GitHubUserModel) {
        replaceFragment(UserFragment.newInstance(user = user))
    }

    private fun initFields() {
        toolbar = binding.toolbar

    }

    private fun initFunc() {
        setSupportActionBar(toolbar)
        createHeader()
        crateDrawer()
    }

    private fun createHeader() {
        header = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem().withName("Ivan Ivanov")
                    .withEmail("ivanovIvan@gmail.com")
            )
            .build()
    }

    private fun crateDrawer() {
        drawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(header)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Search")
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Followers list")
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Settings")
                    .withSelectable(false)
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        1 -> showCurrentGitHubUser()
                        2 -> showGitHubListFragment()
                        3 -> showSettings()
                    }
                    Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT)
                        .show()
                    return false
                }

            })

            .build()
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
}