package com.example.githubprojecttest.view

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
import com.example.githubprojecttest.databinding.FragmentInputNameGitHubUserBinding
import com.example.githubprojecttest.viewModels.MainActivityViewModel


class InputNameGitHubUserFragment : Fragment() {
    private var _binding: FragmentInputNameGitHubUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputNameGitHubUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchEditText = binding.editTextInputName
        val searchBtn = binding.buttonSearch
        searchBtn.setOnClickListener {
            // viewModel.inputUserName.postValue(searchEditText.getText())
            viewModel.inputUserName.postValue(searchEditText.getText().toString())
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
