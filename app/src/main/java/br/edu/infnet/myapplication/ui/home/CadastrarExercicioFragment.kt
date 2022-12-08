package br.edu.infnet.myapplication.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentCadastrarExercicioBinding

class CadastrarExercicioFragment : Fragment() {

    private var _binding: FragmentCadastrarExercicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastrarExercicioBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}