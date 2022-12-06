package com.example.githubprojecttest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprojecttest.R
import com.example.githubprojecttest.adapters.ListRepoAdapter
import com.example.githubprojecttest.api.GitHubApiService
import com.example.githubprojecttest.databinding.FragmentGitHubRepoListBinding
import com.example.githubprojecttest.model.GitHubUserRepoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepoListFragment : Fragment() {
    private lateinit var adapter: ListRepoAdapter
    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentGitHubRepoListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGitHubRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewUsersRepo)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadRepos()
    }

    private fun loadRepos() {
        val listRepository = GitHubApiService.retrofitApi().loadRepos("krishmasand")
        listRepository.enqueue(object : Callback<List<GitHubUserRepoModel>> {
            override fun onResponse(
                call: Call<List<GitHubUserRepoModel>>,
                response: Response<List<GitHubUserRepoModel>>
            ) {
                val list = response.body()
                list?.let {
                    adapter = ListRepoAdapter(list)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<GitHubUserRepoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = GitHubRepoListFragment()
    }
}