package com.github.kentama7.librarysearch.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kentama7.librarysearch.dto.Library
import com.github.kentama7.librarysearch.dto.SearchCondition
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.HttpException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SearchService {

    @Value("\${calil.url}")
    private lateinit var url: String

    @Value("\${calil.app-key}")
    private lateinit var appKey: String

    private val mapper = jacksonObjectMapper()

    fun findLibraries(condition: SearchCondition): List<Library> {
        // カーリルAPIで検索（JSON形式）
        // アプリケーションキーと都道府県は必須、市区町村は任意
        // 詳細 https://calil.jp/doc/api_ref.html
        val triple = Fuel
                .get(url, listOf("appKey" to appKey, "pref" to condition.pref, "city" to condition.city, "format" to "json"))
                .responseString()
        if (triple.second.statusCode != 200) {
            throw HttpException(triple.second.statusCode, "カーリルAPIでエラーが発生")
        }

        // 検索結果を取得し不要な文字列を削除
        // カーリルAPIの戻り値は `callback({JSON});` の形式
        var json = triple.third.component1() ?: return emptyList()
        json = json.replace("callback(", "").replace(");", "")

        // JSON -> Object
        val libraries: List<Library> = mapper.readValue(json)
        // 名称の検索条件があればフィルタリング
        if (condition.name != null) {
            return libraries.filter { it.name.contains(condition.name) }
        }

        return libraries
    }
}
