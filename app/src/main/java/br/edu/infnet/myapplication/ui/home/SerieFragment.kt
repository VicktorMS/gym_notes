package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.Exercicio
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.databinding.FragmentCadastrarSerieBinding
import br.edu.infnet.myapplication.databinding.FragmentSerieBinding
import br.edu.infnet.myapplication.ui.home.adapters.ExercicioInSerieAdapter
import br.edu.infnet.myapplication.ui.home.adapters.ExercicionInSerieListener
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.nav


class SerieFragment : Fragment() {

    val viewModel: HomeSerieViewModel by activityViewModels()
    private var _binding: FragmentSerieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSerieBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            floatingActionButtonAddExercicio.setOnClickListener {
                nav(R.id.action_serieFragment_to_cadastrarExercicioFragment)
            }
            imageViewEditSerie.setOnClickListener{
                nav(R.id.action_serieFragment_to_cadastrarExercicioFragment)
            }
            imageViewEditSerie.setOnClickListener {
                nav(R.id.action_serieFragment_to_editDeleteSerieFragment)
            }
        }
    }

    private fun setupObservers() {
        viewModel.selectedSerieId.observe(viewLifecycleOwner) {
            binding.apply {
                textViewTituloNomeTreino.setText(it.nome)
                textViewDiasTreino.setText("Dias de treino: "+ it.dia)
            }
        }
    }



    val adapter = ExercicioInSerieAdapter(
        object : ExercicionInSerieListener {
            override fun deleteOnClick(exercicio: ExercicioId) {
                viewModel.deleteExercicio(exercicio)
            }
        }
    )

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.recyclerViewExerciciosSerie.adapter = adapter
        binding.recyclerViewExerciciosSerie.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /*private fun setupObservers() {
        val serieId = viewModel.selectedSerieId.toString()
        viewModel.getExerciciosInSerieId(serieId).observe(viewLifecycleOwner) {
            atualizaRecyclerView(it)
        }
    }

    fun atualizaRecyclerView(lista: List<ExercicioId>) {
        adapter.submitList(lista)
        binding.recyclerViewExerciciosSerie.adapter = adapter
    }*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}