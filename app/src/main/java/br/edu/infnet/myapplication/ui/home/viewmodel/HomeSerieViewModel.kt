package br.edu.infnet.myapplication.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.models.Exercicio
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.data.models.SerieId
import br.edu.infnet.myapplication.data.repository.GymRepositoy
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject

class HomeSerieViewModel: ViewModel() {

    val TAG = "HomeSerieViewModel"
    val repository = GymRepositoy.get()

    fun logout() {
        repository.logout()
    }

    fun createSerie(serie: Serie): Task<DocumentReference> {
        return repository.createSerie(serie)
    }

    fun observeColectionSeries() {
        repository.getSeriesColecao()
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                val inputList = mutableListOf<SerieId>()
                val removeList = mutableListOf<String>()
                val alterList = mutableListOf<SerieId>()


                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {

                        // Documento adicionado
                        DocumentChange.Type.ADDED -> {

                            val serie = dc.document.toObject<Serie>()
                            val id = dc.document.id
                            val serieId = serieToSerieId(serie, id)

                            Log.i(TAG, "SerieId: ${serieId}")
                            inputList.add(serieId)

                        }

                        // Documento modificado
                        DocumentChange.Type.MODIFIED -> {
                            val serie = dc.document.toObject<Serie>()
                            val id = dc.document.id
                            val serieId = serieToSerieId(serie, id)

                            Log.i(TAG, "Modificacao - SerieId: ${serieId}")
                            alterList.add(serieId)
                        }

                        // Documento removido
                        DocumentChange.Type.REMOVED -> {
                            val id = dc.document.id
                            Log.i(TAG, "id removido: ${id}")
                            removeList.add(dc.document.id)

                        }
                    }
                }

                addListToSerieId(inputList)

                removeFromSerieId(removeList)

                alterInSerieId(alterList)
            }
    }

    fun alterItemInSerieIdList(itemModificado: SerieId) {
        val oldList = seriesId.value
        val newList = mutableListOf<SerieId>()

        oldList?.forEach { itemList ->
            if (itemModificado.id == itemList.id) {
                newList.add(itemModificado)
            } else {
                newList.add(itemList)
            }
        }
        setSerieId(newList)
    }


    private fun alterInSerieId(alterList: List<SerieId>) {
        Log.i(TAG, "listaModificacao: ${alterList}")
        if (alterList.isNotEmpty()) {
            for (itemModificado in alterList) {
                alterItemInSerieIdList(itemModificado)
            }
        }
    }



    private fun removeFromSerieId(removeList: List<String>) {
        val oldList = seriesId.value
        val newList = mutableListOf<SerieId>()

        Log.i(TAG, "listaRemocao: ${removeList}")

        if (removeList.isNotEmpty()) {
            oldList?.forEach {
                Log.i(TAG, "item da lista Antiga: ${it.id}")
                if (it.id in removeList) {
                    Log.i(TAG, "item ${it.id} está dentro da listaRemocao")
                    //listaNova.add(it)
                }
                else {
                    Log.i(TAG, "item ${it.id} _NÃO_ está dentro da listaRemocao")
                    newList.add(it)
                }
            }
            setSerieId(newList)
        }


    }

    fun addListToSerieId(listaInput: List<SerieId>) {
        val oldList = seriesId.value
        val newList = mutableListOf<SerieId>()

        oldList?.forEach {
            newList.add(it)
        }
        listaInput.forEach {
            newList.add(it)
        }
        setSerieId(newList)
    }


    /*private val _series = MutableLiveData<List<Serie>>()
    val series: LiveData<List<Serie>> = _series
    fun setSeries(value: List<Serie>) {
        _series.postValue(value)
    }*/

    private val _seriesId = MutableLiveData<List<SerieId>>()
    val seriesId: LiveData<List<SerieId>> = _seriesId

    fun setSerieId(value: List<SerieId>) {
        _seriesId.postValue(value)
    }

    fun serieToSerieId(serie: Serie, id: String): SerieId {
        return SerieId(
           nome = serie.nomeSerie,
           dia = serie.diaSerie,
           id = id)
    }

    private val _selectedSerieId = MutableLiveData<SerieId>()
    val selectedSerieId: LiveData<SerieId> = _selectedSerieId


    fun setSelectedSerieId(value: SerieId) {
        _selectedSerieId.postValue(value)
    }

    fun updateSerie(serie: Serie) {
        repository.updateSerie(selectedSerieId.value?.id, serie)
    }

    fun deleteSerie(id: String) {
        repository.deleteSerie(id)
    }

    /*//TODO: Se essa função pelo menos puder ser chamada na outra view model é um problema resolvido
    @JvmName("getSelectedSerieId1") //Notação Sugerida pelo AS
    fun getSelectedSerieId(): LiveData<SerieId>{
        return selectedSerieId
    }


    fun addExercicioInSerie(exercicioId: ExercicioId){
        repository.addExercicioInSerieId(selectedSerieId.value?.id!!, exercicioId
        )
    }*/

    // Se todo o código tivesse em uma viewModel não teria problema


    ///////////////////////////////////////////////////////////////////////////////////////////////






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
                            if(selectedSerieId.value != null){
                                addExercicioInSerie(exercicioId)
                            }

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

    fun addExercicioInSerie(exercicioId: ExercicioId){
        repository.addExercicioInSerieId(selectedSerieId.value?.id!!, exercicioId)
    }

    fun getExerciciosInSerieId(idSerie: String): CollectionReference {
       return repository.getExerciciosInSerieId(idSerie)
    }

    init {
        observeColectionSeries()
        observeExerciciosCollection()

    }

}