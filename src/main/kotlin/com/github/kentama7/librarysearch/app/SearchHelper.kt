package com.github.kentama7.librarysearch.app

import com.github.kentama7.librarysearch.data.Pref
import com.github.kentama7.librarysearch.dto.SearchCondition
import com.ibm.icu.text.Transliterator
import org.springframework.stereotype.Component

@Component
class SearchHelper {

    private val transliterator = Transliterator.getInstance("Halfwidth-Fullwidth")

    fun createSearchCondition(form: SearchForm): SearchCondition {
        // 都道府県が未入力の場合はエラーとする
        val pref: Pref = form.pref ?: throw IllegalArgumentException("都道府県が指定されていません")
        // 半角 -> 全角 (市区町村には半角はないため)
        val city: String = transliterator.transliterate(form.city)
        return SearchCondition(pref, city, form.name)
    }
}
