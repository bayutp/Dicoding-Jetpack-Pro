package com.bayuspace.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.ItemsModuleListCustomBinding

class ModuleListAdapter(private val listener: (Int, ModuleEntity) -> Unit) :
    RecyclerView.Adapter<ModuleListAdapter.ViewHolder>() {

    private val listModules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>) {
        listModules.clear()
        listModules.addAll(modules)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemsModuleListCustomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
            itemView.setOnClickListener {
                listener(adapterPosition, module)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listModules[position])
    }

    override fun getItemCount(): Int {
        return listModules.size
    }
}