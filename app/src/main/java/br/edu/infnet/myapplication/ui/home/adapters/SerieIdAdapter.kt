package br.edu.infnet.myapplication.ui.home.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//import br.edu.infnet.myapplication.data.models.Serie
import br.edu.infnet.myapplication.data.models.SerieId
import br.edu.infnet.myapplication.databinding.SerieListItemBinding

class SerieIdAdapter(val listener: SerieIdListener) :
    ListAdapter<SerieId, SerieIdAdapter.ViewHolder>(SerieIdDiffCallback()) {

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
        val listener: SerieIdListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SerieId, position: Int) {
            binding.apply {
                serieNome.text = item.nome
                serieDia.text = item.dia

                cardViewSerieItem.setOnClickListener {
                    listener.navOnClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: SerieIdListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SerieListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, listener)
            }
        }
    }
}

class SerieIdDiffCallback : DiffUtil.ItemCallback<SerieId>() {

    override fun areItemsTheSame(oldItem: SerieId, newItem: SerieId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SerieId, newItem: SerieId): Boolean {
        return oldItem == newItem
    }
}

interface SerieIdListener {
    fun navOnClick(serie: SerieId)
}
