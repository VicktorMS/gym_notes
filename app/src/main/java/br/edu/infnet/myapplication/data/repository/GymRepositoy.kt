package br.edu.infnet.myapplication.data.repository

import br.edu.infnet.myapplication.data.models.Exercicio
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.data.models.ExercicioInSerie
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

        lateinit var seriesCollection: CollectionReference

        lateinit var exerciciosCollection: CollectionReference

        private var INSTANCE: GymRepositoy? = null
        fun initialize() {

            if (INSTANCE == null) {
                INSTANCE = GymRepositoy()
            }
            auth = Firebase.auth
            db = Firebase.firestore

            seriesCollection = db.collection("series")
            exerciciosCollection = db.collection("exercicios")

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
        return seriesCollection.add(serie)
    }

    fun getSeries(): Task<QuerySnapshot>{
        return seriesCollection.get()
    }

    fun getSeriesColecao(): CollectionReference{
        return seriesCollection
    }

    fun updateSerie(id: String?, serie: Serie){
        if (id != null){
            seriesCollection.document(id).set(serie)
        }
    }

    fun deleteSerie(id: String){
        seriesCollection.document(id).delete()
    }
    //////////////////////////////////////////////////





    fun getExerciciosCollection(): CollectionReference {
        return exerciciosCollection
    }

    fun createExercicio(exercicio: Exercicio): Task<DocumentReference> {
        return exerciciosCollection.add(exercicio)
    }

    fun deleteExercicio(id: String): Task<Void> {
        return exerciciosCollection.document(id).delete()
    }

    fun updateExercicio(id: String?, exercicio: Exercicio) {
        if (id != null) {
            exerciciosCollection.document(id).set(exercicio)
        }
    }

    fun addExercicioInSerieId(idSerie: String, exercicioId: ExercicioId){
        val exercicioInSerie = ExercicioInSerie(
            exercicioNome = exercicioId.nome)
        seriesCollection
            .document(idSerie)
            .collection("exercicios")
            .document(exercicioId.id)
            .set(exercicioInSerie)
    }


    fun getExerciciosInSerieId(idSerie: String): CollectionReference{
        //val exercicioInSerie = ExercicioInSerie(exercicioNome = exercicioId.nome)
        return seriesCollection
            .document(idSerie)
            .collection("exercicios")
    }





}