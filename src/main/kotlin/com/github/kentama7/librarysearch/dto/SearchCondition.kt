package com.github.kentama7.librarysearch.dto

import com.github.kentama7.librarysearch.data.Pref

data class SearchCondition(val pref: Pref,
                           val city: String?,
                           val name: String?)

