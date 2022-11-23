package com.example.githubprojecttest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.adapters.ListUsersAdapter
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.databinding.FragmentListGitHubUsersBinding
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.viewModels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListGitHubUsersFragment : Fragment() {
    private lateinit var adapter: ListUsersAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by activityViewModels()
    var userName = "krishmasand"
    private var _binding: FragmentListGitHubUsersBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListGitHubUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadUsers()
    }

    private fun loadUsers() {
        viewModel.inputUserName.observe(requireContext() as LifecycleOwner, Observer { userName = it })
        val listUsers = GitHubApiService.retrofitApi().loadFollowers(userName)
        listUsers.enqueue(object : Callback<List<GitHubUserModel>> {
            override fun onResponse(
                call: Call<List<GitHubUserModel>>,
                response: Response<List<GitHubUserModel>>
            ) {
                val list = response.body()
                list?.let {
                    adapter = ListUsersAdapter(list)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<GitHubUserModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ListGitHubUsersFragment()
    }
}

