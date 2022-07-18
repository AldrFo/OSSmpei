package ru.acediat.core_android

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class RecyclerViewAdapter<DATA : Any, B : ViewBinding>
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder<DATA, B>>(){

    private val items = mutableListOf<DATA>()

    fun addItem(item: DATA) {
        this.items.add(item)
        notifyItemInserted(items.size-1)
    }

    fun addItems(items: List<DATA>) {
        this.items.addAll(items)
        notifyDataSetChanged()//TODO: Выбрать более оптимизированный метод
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder<DATA, B>

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder<DATA, B>, pos: Int) = holder.bind(items[pos], pos)

    class ViewHolder<DATA : Any, B : ViewBinding>(
        private val binding: B,
        private val callbacks: AdapterCallback<DATA, B>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DATA, position: Int) {
            callbacks.bindViews(binding, item, position)
            binding.root.setOnClickListener { callbacks.onViewClicked(binding.root, item) }
        }

    }

}