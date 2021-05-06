package com.bayuspace.academy.data

import com.bayuspace.academy.data.source.remote.response.ModuleResponse

data class ModuleEntity(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
){
    var contentEntity: ContentEntity? = null
    companion object{
        fun mapToModuleEntity(moduleResponse: ModuleResponse) =
            ModuleEntity(
                moduleResponse.moduleId,
                moduleResponse.courseId,
                moduleResponse.title,
                moduleResponse.position,
                false
            )
    }
}