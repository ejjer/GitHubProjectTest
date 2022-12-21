package com.example.githubprojecttest.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.R
import com.example.githubprojecttest.adapters.ClickListener
import com.example.githubprojecttest.adapters.ListRepoAdapter
import com.example.githubprojecttest.adapters.ListUsersAdapter
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.databinding.FragmentListGitHubUsersBinding
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.viewModels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListGitHubUsersFragment : Fragment(R.layout.fragment_list_git_hub_users), ClickListener {
    private lateinit var adapter: ListUsersAdapter
    private lateinit var recyclerView: RecyclerView

    private val viewModel: MainActivityViewModel by activityViewModels()
    var userName = "krishmasand"


    private var listener: Navigation? = null

    private var binding: FragmentListGitHubUsersBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigation) {
            listener = context
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListGitHubUsersBinding.bind(view)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.inputUserName.observe(viewLifecycleOwner) {
            viewModel.loadFollowers(inputUserName).observe(viewLifecycleOwner) { list ->
                if (list != null) {
                    adapter = ListUsersAdapter(list)
                    recyclerView.adapter = adapter
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = ListGitHubUsersFragment()
    }

    override fun onItemClick(user: GitHubUserModel) {
        listener?.showCurrentUser(user)

    }
}

