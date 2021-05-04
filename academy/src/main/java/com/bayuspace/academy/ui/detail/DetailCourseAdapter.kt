package com.bayuspace.academy.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ViewHolder>() {
    private val listModules = ArrayList<ModuleEntity>()

    fun setModule(data: List<ModuleEntity>) {
        listModules.clear()
        listModules.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemsModuleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listModules[position])
    }

    override fun getItemCount(): Int {
        return listModules.size
    }
}