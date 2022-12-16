package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.data.models.Endereco
import br.edu.infnet.myapplication.data.repository.CepApiJson
import br.edu.infnet.myapplication.databinding.FragmentUserBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.navUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment : Fragment() {
    val viewModel: HomeSerieViewModel by activityViewModels()

    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        binding.apply {
            imageViewUserNavback.setOnClickListener {
                navUp()
            }
            textViewUserEmail.text = viewModel.getCurrentUserEmail()

            buttonConsultarCEP.setOnClickListener {
                onConsultaCepClick()
            }
        }
    }

    private fun onConsultaCepClick() {
        lifecycleScope.launch(Dispatchers.Main) {
            val cepFormatado = binding.editTextTextPersonCEP.text.toString()
            val cepSimples = getCepNumberFromFormat(cepFormatado)
            Log.i("API", "cepFormatado: $cepFormatado" )
            Log.i("API", "cepSimples: $cepSimples" )

            fillForm(getCepEndereco(cepSimples))
            //getCepJson(cepSimples)
        }

    }

   /* private fun getCepJson(cep: String) {
        CepApiJson.retrofitService.getCepJson(cep).enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    binding.tvJson.text = "Json: \n${response.body()}"
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    binding.tvJson.text = "Falha: \n${t.message}"
                }
            })


    }*/

    suspend fun getCepEndereco(cep: String): Endereco {
        val enderecoAsync = lifecycleScope
            .async(Dispatchers.IO) {
                return@async viewModel.fetchEnderecoFromCep(cep)
            }
        return enderecoAsync.await()
    }

    private fun fillForm(endereco: Endereco) {
        binding.apply {
            textViewUserCEP.text = "CEP: ${endereco.cep}"
            textViewUserLocal.text = "Cidade: ${endereco.cidade}"
            textViewUserUF.text = "Estado: ${endereco.estado}"
        }
    }

    private fun getCepNumberFromFormat(cep: String): String {
        return cep.replace(".", "").replace("-", "")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}