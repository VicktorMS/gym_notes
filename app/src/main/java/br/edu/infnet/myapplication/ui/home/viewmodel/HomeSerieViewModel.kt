package br.edu.infnet.myapplication.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.data.models.SerieId
import br.edu.infnet.myapplication.data.repository.GymRepositoy
import com.google.android.gms.tasks.Task
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

    fun deleteTurma(id: String) {
        repository.deleteSerie(id)
    }


    init {
        observeColectionSeries()

    }

}