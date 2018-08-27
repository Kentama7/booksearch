package com.github.kentama7.librarysearch.app

import com.github.kentama7.librarysearch.data.Pref
import com.github.kentama7.librarysearch.dto.Library
import com.github.kentama7.librarysearch.dto.SearchCondition
import com.github.kentama7.librarysearch.service.SearchService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@RunWith(SpringRunner::class)
@WebMvcTest(SearchController::class)
class SearchControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var helper: SearchHelper

    @MockBean
    private lateinit var service: SearchService

    @Test
    fun 初期表示() {
        // act and assert
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("searchForm"))
    }

    @Test
    fun 検索結果を表示する() {
        // arrange
        val form = SearchForm()
        val condition = SearchCondition(Pref.北海道, "", "")
        val libraries = listOf(Library("札幌市中央図書館",
                "北海道札幌市中央区南22条西13丁目",
                "011-512-7320",
                "http://www.city.sapporo.jp/toshokan/"))
        Mockito.`when`(helper.createSearchCondition(form)).thenReturn(condition)
        Mockito.`when`(service.findLibraries(condition)).thenReturn(libraries)
        // act and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/search")
                .requestAttr("form", form))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("searchForm", "libraries"))
    }
}
