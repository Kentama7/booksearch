package com.github.kentama7.booksearch.dto

import com.github.kentama7.booksearch.data.Pref

data class SearchCondition(val pref: Pref,
                           val city: String?,
                           val name: String?)

