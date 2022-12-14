package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.SerieId
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.databinding.FragmentHomeBinding
import br.edu.infnet.myapplication.ui.home.adapters.SerieIdAdapter
import br.edu.infnet.myapplication.ui.home.adapters.SerieIdListener
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
        setupRecyclerView()
        setupObservers()
    }

    private fun setOnClickListener() {
        binding.apply {
            floatingActionButtonAddSerie.setOnClickListener {
                nav(R.id.action_homeFragment_to_cadastrarSerieFragment)
            }
            imageViewUserInfo.setOnClickListener {
                nav(R.id.action_homeFragment_to_userFragment)
            }
        }
    }

    val adapter = SerieIdAdapter(
        object : SerieIdListener {
            override fun navOnClick(serie: SerieId) {
                viewModel.setSelectedSerieId(serie)
                nav(R.id.action_homeFragment_to_serieFragment)
            }

        }
    )

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvSeries.adapter = adapter
        binding.rvSeries.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }


    private fun setupObservers() {
        viewModel.seriesId.observe(viewLifecycleOwner) {
            updateRecyclerView(it)
        }
    }

    private fun updateRecyclerView(lista: List<SerieId>) {
        adapter.submitList(lista)
        binding.rvSeries.adapter = adapter

    }


}