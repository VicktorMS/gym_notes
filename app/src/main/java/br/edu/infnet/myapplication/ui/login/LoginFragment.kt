package br.edu.infnet.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentLoginBinding
import br.edu.infnet.myapplication.ui.home.HomeActivity
import br.edu.infnet.myapplication.ui.login.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    val viewModel by activityViewModels<LoginViewModel>()


    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSignIn.setOnClickListener {
                onSignInClick()
            }
            btnLoginSignOn.setOnClickListener {
                navOnClick()
            }
        }
    }

    private fun navOnClick() {
        findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
    }


    private fun onSignInClick() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        singIn(email, password)
    }

    private fun singIn(email: String, password: String) {
        viewModel.login(email, password)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Logado com Sucesso", Toast.LENGTH_SHORT).show()
                startHomeActivity()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Não foi possivel fazer Login", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun startHomeActivity() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        activity?.finish()
    }

}