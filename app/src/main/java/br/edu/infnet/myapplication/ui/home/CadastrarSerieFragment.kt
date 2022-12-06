package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.databinding.FragmentCadastrarSerieBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.getTextInput
import br.edu.infnet.myapplication.utils.nav


class CadastrarSerieFragment : Fragment() {
    val TAG = "Cadastro de Séries"

    val viewModel by activityViewModels<HomeSerieViewModel>()

    private var _binding: FragmentCadastrarSerieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastrarSerieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            buttonCreateSerie.setOnClickListener {
                createSerie()
            }
        }
    }

    private fun createSerie() {
        val serie = getSerieFromInputs()
        viewModel.createSerie(serie)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Série cadastrada com sucesso: ${documentReference.id}")
                nav(R.id.action_cadastrarSerieFragment_to_homeFragment)
            }
            .addOnFailureListener {
                Log.d(TAG, "Não foi possível cadastrar a Série")
            }
    }

    fun getDiaSeriefromInputs(): String{
        var dias = ""
        binding.apply {
            if (radioButtonSeg.isChecked){
                dias + "Seg, "
            }
            if (radioButtonTer.isChecked){
                dias + "Ter, "
            }
            if (radioButtonQua.isChecked){
                dias + "Qua, "
            }
            if (radioButtonQui.isChecked){
                dias + "Qui, "
            }
            if (radioButtonSex.isChecked){
                dias + "Sex, "
            }
            if (radioButtonSab.isChecked){
                dias + "Sab, "
            }
            if (radioButtonDom.isChecked){
                dias + "Dom, "
            }
        }

        //TODO: Trocar Botões radio button para checkbox, com radio button só dá pra marcar um
        return dias
    }

    fun getSerieFromInputs(): Serie {
        binding.apply {
            val dias = getDiaSeriefromInputs()
            return Serie(
                nomeSerie = editTextNomeSerie.text.toString(),
                diaSerie = dias
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}