package com.github.kentama7.librarysearch.app

import com.github.kentama7.librarysearch.data.Pref
import com.github.kentama7.librarysearch.dto.SearchCondition
import org.springframework.stereotype.Component

@Component
class SearchHelper {

    fun createSearchCondition(form: SearchForm): SearchCondition {
        // 改ざんされた場合はエラーとする
        val pref: Pref = form.pref ?: throw IllegalArgumentException("都道府県が指定されていません")
        return SearchCondition(pref, form.city, form.name)
    }
}
