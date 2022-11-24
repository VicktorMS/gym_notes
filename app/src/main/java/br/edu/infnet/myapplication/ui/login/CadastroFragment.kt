package br.edu.infnet.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentCadastroBinding


class CadastroFragment : Fragment() {
    val viewModel by activityViewModels<LoginViewModel> ()


    private var _binding: FragmentCadastroBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        val view = binding.root
        onSignOnClick()
        setOnClickListeners()
        return view
    }

    private fun setOnClickListeners() {
        binding.btnSignOn.setOnClickListener {
            onSignOnClick()
        }
    }


    private fun onSignOnClick() {
        binding.apply {
            val email = inputEmail.text.toString()
           // val valEmail = inp.text.toString()
            val password = inputPassword.text.toString()
            val valPassword = inputConfirmPassword.text.toString()

            if (((password == valPassword) && password.length > 5)){
                signOn(email, password)
            }
            else{
                Toast.makeText(requireContext(), "Informações Inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signOn(email: String, password: String) {
        viewModel.signOn(email, password)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Falha no cadastro\n" +
                        "${it.message}", Toast.LENGTH_SHORT).show()
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}