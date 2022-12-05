package br.edu.infnet.myapplication.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.repository.GymRepositoy

class ExerciciosViewModel: ViewModel() {
    val TAG = "ExerciciosViewModel"
    val repository = GymRepositoy.get()

}