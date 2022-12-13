package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.databinding.FragmentEditDeleteSerieBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.getTextInput
import br.edu.infnet.myapplication.utils.nav
import br.edu.infnet.myapplication.utils.navUp

class EditDeleteSerieFragment : Fragment() {


    val viewModel: HomeSerieViewModel by activityViewModels()

    private var _binding: FragmentEditDeleteSerieBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDeleteSerieBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()

        return view
    }

    private fun setup() {
        setupObservers()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.buttonEditDeleteSerie.setOnClickListener{
            updateSerie()
            navToHome()
        }

        binding.imageViewDeleteSerie.setOnClickListener {
           onClickDelete()
        }
    }



    private fun onClickDelete() {
        binding.imageViewDeleteSerie.setOnClickListener {
            viewModel.selectedSerieId.observe(viewLifecycleOwner){
                viewModel.deleteSerie(it.id)
                navToHome()
            }
        }
    }


    private fun updateSerie() {
        val serie = getSerieFromInputs()
        viewModel.updateSerie(serie)
    }

    private fun setupObservers() {
        viewModel.selectedSerieId.observe(viewLifecycleOwner){
            binding.apply {
                editTextEditDeleteNomeSerie.setText(it.nome)
                TextviewEditDeleteTip.text = "Dias Antigos: " + it.dia
            }
        }
    }



    fun getSerieFromInputs(): Serie{
        binding.apply {
            val dias = getDiasFromCheckboxs()
            return Serie(
                nomeSerie = getTextInput(editTextEditDeleteNomeSerie),
                diaSerie = dias
            )
        }
    }

    private fun getDiasFromCheckboxs(): String {
        var diasLista: MutableList<String> = mutableListOf()
        binding.apply {
            if (checkBoxEditDeleteSeg.isChecked){
                diasLista.add("Seg")
            }
            if (checkBoxEditDeleteTer.isChecked){
                diasLista.add("Ter")
            }
            if (checkBoxEditDeleteQua.isChecked){
                diasLista.add("Qua")
            }
            if (checkBoxEditDeleteQui.isChecked){
                diasLista.add("Qui")
            }
            if (checkBoxEditDeleteSex.isChecked){
                diasLista.add("Sex")
            }
            if (checkBoxEditDeleteSab.isChecked){
                diasLista.add("Sab")
            }
            if (checkBoxEditDeleteDom.isChecked){
                diasLista.add("Dom")
            }
        }
        val stringDias = diasLista.joinToString(prefix = "", postfix = "", separator = ", ")
        return stringDias
    }

    private fun navToHome() {
        navUp()
        navUp()
    }


}