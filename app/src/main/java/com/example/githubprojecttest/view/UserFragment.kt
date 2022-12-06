package com.example.githubprojecttest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.FragmentListGitHubUsersBinding
import com.example.githubprojecttest.databinding.FragmentSettingsBinding
import com.example.githubprojecttest.databinding.FragmentUserBinding
import com.example.githubprojecttest.model.GitHubUserModel


class UserFragment : Fragment() {
    private var _binding: FragmentListGitHubUsersBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<GitHubUserModel>(CURRENTUSER)
        Toast.makeText(requireContext(), user?.login.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        const val CURRENTUSER = "userCurrentList"
        fun newInstance(user: GitHubUserModel) = UserFragment().apply {
            arguments = Bundle().apply { putParcelable(CURRENTUSER, user) }
        }

    }
}