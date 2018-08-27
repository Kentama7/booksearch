package com.github.kentama7.librarysearch.service

import com.github.kentama7.librarysearch.data.Pref
import com.github.kentama7.librarysearch.dto.Library
import com.github.kentama7.librarysearch.dto.SearchCondition
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SearchServiceTest {

    // calil.app-keyが正しく設定されている前提でテストを行う
    @Autowired
    private lateinit var service: SearchService

    @Test
    fun 検索結果が1件の検索をする() {
        // arrange
        val condition = SearchCondition(Pref.北海道, "札幌市", "中央図書館")
        // act
        val actual = service.findLibraries(condition)
        // assert
        val expected = Library("札幌市中央図書館",
                "北海道札幌市中央区南22条西13丁目",
                "011-512-7320",
                "http://www.city.sapporo.jp/toshokan/")
        assertThat(actual).hasSize(1).containsOnly(expected)
    }

    @Test
    fun 検索結果が複数件の検索をする() {
        // arrange
        val condition = SearchCondition(Pref.沖縄県, "", "琉球")
        // act
        val actual = service.findLibraries(condition)
        // assert
        val expected1 = Library("琉球大学附属図書館医学部分館",
                "沖縄県中頭郡西原町字上原207番地",
                "098-895-1052",
                "http://www.lib.u-ryukyu.ac.jp/")
        val expected2 = Library("琉球大学附属図書館",
                "沖縄県西原町字千原1",
                "098-895-8166",
                "http://www.lib.u-ryukyu.ac.jp/")
        assertThat(actual).hasSize(2).containsOnly(expected1, expected2)
    }

    @Test
    fun 検索結果が0件の検索をする() {
        // arrange
        val condition = SearchCondition(Pref.北海道, "千代田区", "hoge図書館")
        // act
        val actual = service.findLibraries(condition)
        // assert
        assertThat(actual).hasSize(0).isEmpty()
    }
}
