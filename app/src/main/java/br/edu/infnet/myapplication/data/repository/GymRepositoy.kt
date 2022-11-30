package br.edu.infnet.myapplication.data.repository

import br.edu.infnet.myapplication.data.models.Serie
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GymRepositoy private constructor(){
    companion object {
        lateinit var auth: FirebaseAuth

        lateinit var db: FirebaseFirestore

        lateinit var colecaoSeries: CollectionReference

        lateinit var colecaoExercicios: CollectionReference

        private var INSTANCE: GymRepositoy? = null
        fun initialize() {

            if (INSTANCE == null) {
                INSTANCE = GymRepositoy()
            }
            auth = Firebase.auth
            db = Firebase.firestore

            colecaoSeries = db.collection("series")
            colecaoExercicios = db.collection("exercicios")

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

    fun createUsuarioComSenha(
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

    // FireStore /////////////////////////////////////////////////////////////////



    //Series
    fun createSerie(serie: Serie): Task<DocumentReference>{
        return colecaoSeries.add(serie)
    }

    fun getSeries(): Task<QuerySnapshot>{
        return colecaoSeries.get()
    }

    fun getSeriesColecao(): CollectionReference{
        return colecaoSeries
    }

    fun updateSerie(id: String?, serie: Serie){
        if (id != null){
            colecaoSeries.document(id).set(serie)
        }
    }

    fun deleteSerie(id: String){
        colecaoSeries.document(id).delete()
    }
    //////////////////////////////////////////////////


}