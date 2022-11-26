package com.example.githubprojecttest.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.FragmentInputNameGitHubUserBinding
import com.example.githubprojecttest.databinding.FragmentSettingsBinding
import com.example.githubprojecttest.viewModels.MainActivityViewModel

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lightTvButton.setOnClickListener {
            viewModel.theme.postValue("Base")
        }

        binding.darkTvButton.setOnClickListener {
            viewModel.theme.postValue("Dark")//устанавливаю значение theme в LiveData
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }
}
