package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.edu.infnet.myapplication.R
import br.edu.infnet.myapplication.databinding.FragmentUserBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.utils.navUp


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
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}