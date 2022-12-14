package br.edu.infnet.myapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.myapplication.data.models.ExercicioId
import br.edu.infnet.myapplication.databinding.ExerciciosNaSerieRvItemListBinding

class ExercicioInSerieAdapter(val listener: ExercicionInSerieListener) :
    ListAdapter<
            ExercicioId,
            ExercicioInSerieAdapter.ViewHolder
            >(ExercicionInSerieDiffCallback()) {

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
        val binding: ExerciciosNaSerieRvItemListBinding,
        val listener: ExercicionInSerieListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExercicioId, position: Int) {
            binding.apply {
                textViewItemNomeExercicios.text = item.nome
                textViewItemNomeAparelho.text = item.aparelho
                textViewItemPeso.text = item.peso.toString()
                textViewItemRepExercicios.text = item.repExercicio.toString()
                textViewItemRepMovimento.text = item.repMove.toString()

                cardviewItemExerciciosSerie.setOnClickListener{
                    listener.deleteOnClick(item)
                }

            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: ExercicionInSerieListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ExerciciosNaSerieRvItemListBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class ExercicionInSerieDiffCallback : DiffUtil.ItemCallback<ExercicioId>() {

    override fun areItemsTheSame(oldItem: ExercicioId, newItem: ExercicioId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExercicioId, newItem: ExercicioId): Boolean {
        return oldItem == newItem
    }
}


interface ExercicionInSerieListener {
    fun deleteOnClick(exercicio: ExercicioId)
}