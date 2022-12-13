package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.edu.infnet.myapplication.data.models.Exercicio
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.databinding.FragmentCadastrarExercicioBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.*

class CadastrarExercicioFragment : Fragment() {

    val viewModel by activityViewModels<HomeSerieViewModel>()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickOnListeners()
    }

    private fun setupClickOnListeners() {
        binding.apply {
            buttonCreateExercicio.setOnClickListener {
                createExercicioOnClick()

            }
        }
    }

    private fun createExercicioOnClick() {
        val exercicio = getExercicioFromInput()

        viewModel.createExercicio(exercicio)
            .addOnSuccessListener { documentReference ->
                toast("Exercicio cadastrado com sucesso: ${documentReference.id}")
                navUp()
            }
            .addOnFailureListener {
                toast("Falha ao cadastrar")
            }

    }

    private fun getExercicioFromInput(): Exercicio {
        binding.apply {
            return Exercicio(
                nome = getTextInput(editTextNomeExercicio),
                peso = getFloatInput(editTextNomePesoExercicio),
                repExercicio = getIntInput(editTextNomeRepExercicio),
                repMov = getIntInput(editTextNomeRepMovExercicio)
            )
        }
    }





}