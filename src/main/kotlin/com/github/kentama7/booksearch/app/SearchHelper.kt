package com.github.kentama7.booksearch.app

import com.github.kentama7.booksearch.app.SearchForm
import com.github.kentama7.booksearch.data.Pref
import com.github.kentama7.booksearch.dto.SearchCondition
import org.springframework.stereotype.Component

@Component
class SearchHelper {

    fun createSearchCondition(form: SearchForm): SearchCondition {
        // 改ざんされた場合はエラーとする
        val pref: Pref = form.pref ?: throw IllegalArgumentException("都道府県が指定されていません")
        return SearchCondition(pref, form.city, form.name)
    }
}
