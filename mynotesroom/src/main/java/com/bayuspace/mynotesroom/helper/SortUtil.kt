package com.bayuspace.mynotesroom.helper

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtil {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleSqlQuery = StringBuilder().append("SELECT * FROM tbl_note ")
        when (filter) {
            NEWEST -> simpleSqlQuery.append("ORDER BY id DESC")
            OLDEST -> simpleSqlQuery.append("ORDER BY id ASC")
            RANDOM -> simpleSqlQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleSqlQuery.toString())
    }
}