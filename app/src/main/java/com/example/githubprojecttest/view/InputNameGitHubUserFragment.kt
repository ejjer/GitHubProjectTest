package com.example.githubprojecttest.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.FragmentInputNameGitHubUserBinding
import com.example.githubprojecttest.navigation.Navigation
import com.example.githubprojecttest.viewModels.MainActivityViewModel


class InputNameGitHubUserFragment() : Fragment() {
    private var _binding: FragmentInputNameGitHubUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()
    var userNameStart = "krishmasand"
    private var listener: Navigation? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputNameGitHubUserBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigation) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchEditText = binding.editTextInputName
        val searchBtn = binding.buttonSearch
        val repositoryBtn = binding.inputUserNameRepositoryBtn
        val followersBtn = binding.inputUserNameFollowersBtn
        val userName = binding.userName

        userName.text = userNameStart

        searchBtn.setOnClickListener {
            listener?.showInputNameGitHubUserFragment()
        }


        repositoryBtn.setOnClickListener {
            listener?.showRepos()
            viewModel.inputUserName.postValue(userNameStart)
        }
        followersBtn.setOnClickListener {
           // viewModel.navigation.postValue("FollowersFragment")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = InputNameGitHubUserFragment()
    }
}
