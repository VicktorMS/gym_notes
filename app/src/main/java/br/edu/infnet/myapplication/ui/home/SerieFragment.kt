package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentCadastrarSerieBinding
import br.edu.infnet.myapplication.databinding.FragmentSerieBinding


class SerieFragment : Fragment() {
    private var _binding: FragmentSerieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentSerieBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}