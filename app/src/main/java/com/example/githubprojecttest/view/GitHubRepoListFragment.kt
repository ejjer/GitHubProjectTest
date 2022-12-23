package com.example.githubprojecttest.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.R
import com.example.githubprojecttest.adapters.ListRepoAdapter
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.databinding.FragmentGitHubRepoListBinding
import com.example.githubprojecttest.model.GitHubUserRepoModel
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.viewModels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepoListFragment : Fragment(R.layout.fragment_git_hub_repo_list) {
    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var adapter: ListRepoAdapter
    private lateinit var recyclerView:RecyclerView
    private var listener: Navigation? = null


    private var binding: FragmentGitHubRepoListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGitHubRepoListBinding.bind(view)
        binding?.apply { recyclerView = recyclerViewUsersRepo }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.inputUserName.observe(viewLifecycleOwner) { inputUserName ->
            viewModel.loadRepo(inputUserName).observe(viewLifecycleOwner) { list ->
                if (list != null) {
                    adapter = ListRepoAdapter(list)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigation) {
            listener = context
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        fun newInstance() = GitHubRepoListFragment()
    }
}