/*
package br.edu.infnet.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.databinding.SerieListItemBinding

class SerieAdapter(val listener: SerieListener) :
    ListAdapter<
            Serie,
            SerieAdapter.ViewHolder
            >(SerieDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    class ViewHolder private constructor(
        val binding: SerieListItemBinding,
        val listener: SerieListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Serie, position: Int) {
            binding.apply {
                serieNome.text = item.nomeSerie
                serieDia.text = item.diaSerie

                ivEdit.setOnClickListener {
                    listener.onEditClick(item)
                }
                ivDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }

            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: SerieListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SerieListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }
}

class SerieDiffCallback : DiffUtil.ItemCallback<Serie>() {

    override fun areItemsTheSame(oldItem: Serie, newItem: Serie): Boolean {
        return oldItem.nomeSerie == newItem.nomeSerie
    }

    override fun areContentsTheSame(oldItem: Serie, newItem: Serie): Boolean {
        return oldItem == newItem
    }
}

interface SerieListener {
    fun onEditClick(serie: Serie)
    fun onDeleteClick(serie: Serie)
}


*/
