package com.github.kentama7.librarysearch.app

import com.github.kentama7.librarysearch.data.Pref
import com.github.kentama7.librarysearch.dto.SearchCondition
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SearchHelperTest {

    @Autowired
    private lateinit var helper: SearchHelper

    @Test
    fun 検索Formの内容をそのままSearchConditionに設定する() {
        // arrange
        val form = SearchForm()
        form.pref = Pref.北海道
        form.city = "札幌市"
        form.name = "中央図書館"
        // act
        val actual = helper.createSearchCondition(form)
        // assert
        assertThat(actual).isEqualTo(SearchCondition(Pref.北海道, "札幌市", "中央図書館"))
    }

    @Test
    fun 検索Formの市区町村を全角から半角に変換してSearchConditionに設定する() {
        // arrange
        val form = SearchForm()
        form.pref = Pref.北海道
        form.city = "***"
        form.name = "中央図書館"
        // act
        val actual = helper.createSearchCondition(form)
        // assert
        assertThat(actual).isEqualTo(SearchCondition(Pref.北海道, "＊＊＊", "中央図書館"))
    }

    @Test
    fun 検索Formの都道府県がnullの場合に例外が発生する() {
        // arrange
        val form = SearchForm()
        form.pref = null
        form.city = "***"
        form.name = "中央図書館"
        // act
        // assert
        assertThatThrownBy { helper.createSearchCondition(form) }.isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContaining("都道府県が指定されていません")
    }
}
