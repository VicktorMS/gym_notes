package br.edu.infnet.myapplication.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.nav(id: Int){
    findNavController().navigate(id)
}

fun Fragment.navUp(){
    findNavController().navigateUp()
}

fun Fragment.getTextInput(editText: EditText): String {
    return editText.text.toString()
}


fun Fragment.getIntInput(editText: EditText): Int {
    return editText.text.toString().toInt()
}

fun Fragment.getFloatInput(editText: EditText): Float {
    return editText.text.toString().toFloat()
}

fun Fragment.toast(msg: String){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}