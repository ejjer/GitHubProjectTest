package com.example.githubprojecttest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.FragmentInputNameGitHubUserBinding


class InputNameGitHubUserFragment : Fragment() {
    private var _binding: FragmentInputNameGitHubUserBinding? = null
    private val binding get() = _binding!!

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
//        val searchEditText = view.findViewById<EditText>(R.id.edit_text_input_name)
//        val searchBtn = view.findViewById<Button>(R.id.button_search)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = InputNameGitHubUserFragment()
    }
}
