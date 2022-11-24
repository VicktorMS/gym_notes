package br.edu.infnet.myapplication.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GymRepositoy private constructor(){
    companion object {
        lateinit var auth: FirebaseAuth

        private var INSTANCE: GymRepositoy? = null
        fun initialize() {

            if (INSTANCE == null) {
                INSTANCE = GymRepositoy()
            }
            auth = Firebase.auth

        }
        fun get(): GymRepositoy {
            return INSTANCE
                ?: throw IllegalStateException("GymRepositoy deve ser inicializado.")
        }
    }

    // Auth  ///////////////////////////////////////////////////////////////////////////////////////

    fun getCurrentUser() = auth.currentUser

    fun isLoggedIn(): Boolean {

        if (getCurrentUser() != null) {
            return true
        }
        return false
    }

    // Faça o mesmo que foi feito com o Login
    fun cadastrarUsuarioComSenha(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun login(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun logout() {
        auth.signOut()
    }

}