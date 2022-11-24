package br.edu.infnet.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        setupClickListeners()
        return view
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSignIn.setOnClickListener {
                //findNavController().navigate(id)
            }
            btnSignOn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}