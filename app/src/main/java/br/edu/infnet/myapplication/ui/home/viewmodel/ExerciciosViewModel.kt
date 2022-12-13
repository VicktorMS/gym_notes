/*
package br.edu.infnet.myapplication.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.models.Exercicio
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.data.repository.GymRepositoy
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject

class ExerciciosViewModel: ViewModel() {

    /// TAG e Referencia Repositório
    val TAG = "ExerciciosViewModel"
    val repository = GymRepositoy.get()



    private val _exerciciosId = MutableLiveData<List<ExercicioId>>()

    val exerciciosId : LiveData<List<ExercicioId>> = _exerciciosId

    fun setExerciciosId(value: List<ExercicioId>) {
        Log.i(TAG, "setExerciciosId" )
        Log.i(TAG, "value = ${value}" )
        _exerciciosId.postValue(value)
    }

    private val _selectedExercicioId = MutableLiveData<ExercicioId>()
    val selectedExercicioId: LiveData<ExercicioId> = _selectedExercicioId
    
    fun setSelectedExercicioId(value: ExercicioId) {
        _selectedExercicioId.postValue(value)
    }




    fun observeExerciciosCollection() {

        repository.getExerciciosCollection()
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                val inputList = mutableListOf<ExercicioId>()

                val removeList = mutableListOf<String>()

                val alterList = mutableListOf<ExercicioId>()

                // Ver alterações entre instantâneos
                // https://firebase.google.com/docs/firestore/query-data/listen?hl=pt&authuser=0#view_changes_between_snapshots
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {

                        // Documento adicionado
                        DocumentChange.Type.ADDED -> {

                            val exercicio = dc.document.toObject<Exercicio>()
                            val id = dc.document.id
                            val exercicioId = exercicioToExercicioId(exercicio, id)

                            Log.i(TAG, "exercicioId: ${exercicioId}")

                            //Colocar a função aqui, quando criar o exercio cria dentro da série
                            addExercicioInSerie(exercicioId)

                            inputList.add(exercicioId)

                        }

                        // Documento modificado
                        DocumentChange.Type.MODIFIED -> {
                            val exercicio = dc.document.toObject<Exercicio>()
                            val id = dc.document.id
                            val exercicioId = exercicioToExercicioId(exercicio, id)

                            Log.i(TAG, "Modificacao - exercicioId: ${exercicioId}")
                            alterList.add(exercicioId)
                        }

                        // Documento removido
                        DocumentChange.Type.REMOVED -> {
                            val id = dc.document.id
//                            Log.i(TAG, "id removido: ${id}")
                            removeList.add(dc.document.id)

                        }
                    }
                }

                addListToExercicioId(inputList)
                removeFromExercicioId(removeList)
                alterInExercicioId(alterList)


            }
    }

    private fun exercicioToExercicioId(exercicio: Exercicio, id: String): ExercicioId {
        return ExercicioId(
            nome = exercicio.nome,
            peso = exercicio.peso,
            repExercicio = exercicio.repExercicio,
            repMove = exercicio.repMov,
            aparelho = exercicio.aparelho,

            id=id
        )
    }

    fun addListToExercicioId(listaInput: List<ExercicioId>) {

        val listaAntiga = exerciciosId.value
        val listaNova = mutableListOf<ExercicioId>()

        listaAntiga?.forEach {
            listaNova.add(it)
        }
        listaInput.forEach {
            listaNova.add(it)
        }
        setExerciciosId(listaNova)
    }

    fun modifyItemInListaExercicioId(itemModificado: ExercicioId) {
        val listaAntiga = exerciciosId.value
        val listaNova = mutableListOf<ExercicioId>()

        listaAntiga?.forEach { itemDaLista ->
            if (itemModificado.id == itemDaLista.id) {
                listaNova.add(itemModificado)
            } else {
                listaNova.add(itemDaLista)
            }
        }
        setExerciciosId(listaNova)
    }

    private fun alterInExercicioId(alterList: List<ExercicioId>) {
        Log.i(TAG, "listaModificacao: ${alterList}")
        if (alterList.isNotEmpty()) {
            for (alterItem in alterList) {
                modifyItemInListaExercicioId(alterItem)
            }
        }
    }

    private fun removeFromExercicioId(removeList: List<String>) {

        val oldList = exerciciosId.value
        val newList = mutableListOf<ExercicioId>()
        Log.i(TAG, "listaRemocao: ${removeList}")

        if (removeList.isNotEmpty()) {
            oldList?.forEach {
                Log.i(TAG, "item da lista Antiga: ${it.id}")
                if (it.id in removeList) {
                    Log.i(TAG, "item ${it.id} está dentro da listaRemocao")

                } else {
                    Log.i(TAG, "item ${it.id} _NÃO_ está dentro da listaRemocao")
                    newList.add(it)
                }
            }
            setExerciciosId(newList)
        }
    }

    fun createExercicio(exercicio: Exercicio): Task<DocumentReference> {
        return repository.createExercicio(exercicio)
    }


    fun updateExercicio(exercicio: Exercicio) {
        repository.updateExercicio(selectedExercicioId.value?.id, exercicio)
    }

    fun deleteExercicio(exercicioId: ExercicioId): Task<Void> {
        return repository.deleteExercicio(exercicioId.id)
    }

    //TODO: Encontrar uma forma de adicionar o Exercicio a série assim que for criado
    //TODO: Encontrar uma forma de se comunicar com outra viewmodel, para pegar o Id da série atual
    //
    fun addExercicioInSerie(exercicioId: ExercicioId){
        repository.addExercicioInSerieId(getSelectedSerieId().value?.id!!, exercicioId
        )
    }

    fun getSelectedSerieId(){

    }


    init {
        observeExerciciosCollection()
    }


}*/
