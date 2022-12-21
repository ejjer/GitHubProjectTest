package com.example.githubprojecttest.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.githubprojecttest.R
import com.example.githubprojecttest.databinding.FragmentGitHubRepoListBinding
import com.example.githubprojecttest.databinding.FragmentUserBinding
import com.example.githubprojecttest.model.GitHubUserModel
import com.example.githubprojecttest.viewModels.MainActivityViewModel


class UserFragment : Fragment(R.layout.fragment_user) {
    private var binding: FragmentUserBinding? = null
    private val viewModel: MainActivityViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        val user = arguments?.getParcelable<GitHubUserModel>(CURRENTUSER)
        Toast.makeText(requireContext(), user?.login.toString(), Toast.LENGTH_SHORT).show()
        binding?.apply { userName.text = user?.login }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        const val CURRENTUSER = "userCurrentList"
        fun newInstance(user: GitHubUserModel) = UserFragment().apply {
            arguments = Bundle().apply { putParcelable(CURRENTUSER, user) }
        }

    }
}