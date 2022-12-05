package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.databinding.FragmentHomeBinding
import br.edu.infnet.myapplication.utils.nav

class HomeFragment : Fragment() {

    val viewModel: HomeSerieViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            floatingActionButtonAddSerie.setOnClickListener {
                nav(R.id.action_homeFragment_to_cadastrarSerieFragment)
            }
        }
    }


}