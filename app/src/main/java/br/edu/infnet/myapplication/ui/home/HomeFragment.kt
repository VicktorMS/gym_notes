package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.data.models.SerieComId
import br.edu.infnet.myapplication.data.viewModel.MainViewModel
import br.edu.infnet.myapplication.databinding.FragmentCadastrarSerieBinding
import br.edu.infnet.myapplication.databinding.FragmentHomeBinding
import br.edu.infnet.myapplication.ui.home.adapters.SerieComIdAdapter
import br.edu.infnet.myapplication.ui.home.adapters.SerieComIdListener

class HomeFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
        setupRecycleView()
        setupObservers()
    }

    private fun setupObservers() {



    }

    private fun setupRecycleView() {
        binding.rvSeries.adapter = adapter
        binding.rvSeries.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    private fun setupClickListeners() {

    }

    private fun setupViews() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val adapter = SerieComIdAdapter(
        object : SerieComIdListener{
            override fun onDeleteClick(serie: Serie) {
                TODO("Not yet implemented")
            }

            override fun onEditClick(serie: Serie) {
                TODO("Not yet implemented")
            }
        }
    )

    fun atualizarRecycleView(lista: List<SerieComId>){
        adapter.submitList(lista)
        binding.rvSeries.adapter = adapter
    }
}