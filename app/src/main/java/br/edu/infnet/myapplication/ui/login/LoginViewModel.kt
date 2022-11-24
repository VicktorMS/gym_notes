package br.edu.infnet.myapplication.ui.login

import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.repository.GymRepositoy
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel: ViewModel() {

    val TAG = "ViewModel"
    val repository = GymRepositoy

    val repositoryGet = repository.get()

    fun isLoggedIn(): Boolean {
        return repositoryGet.isLoggedIn()
    }

    fun login(email: String, password: String): Task<AuthResult> {
        return repository.auth.signInWithEmailAndPassword(email, password)
    }

    fun signOn(email: String, password: String): Task<AuthResult> {
        return repositoryGet.cadastrarUsuarioComSenha(email, password)
    }
}