package br.edu.infnet.myapplication.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.repository.GymRepositoy
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel: ViewModel() {

    val TAG = "ViewModel"
    val repository = GymRepositoy

    val repositoryGet = GymRepositoy.get()

    fun isLoggedIn(): Boolean {
        return repositoryGet.isLoggedIn()
    }

    fun login(email: String, password: String): Task<AuthResult> {
        return GymRepositoy.auth.signInWithEmailAndPassword(email, password)
    }

    fun signOn(email: String, password: String): Task<AuthResult> {
        return repositoryGet.createUsuarioComSenha(email, password)
    }
}