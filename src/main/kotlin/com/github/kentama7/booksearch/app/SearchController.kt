package com.github.kentama7.booksearch.app.search

import com.github.kentama7.booksearch.service.CalilService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class SearchController(private val helper: SearchHelper,
                       private val service: CalilService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute(SearchForm())
        return "index"
    }

    @PostMapping("search")
    fun search(@Validated form: SearchForm, bindingResult: BindingResult, model: Model): String {
        val condition = helper.createSearchCondition(form)
        println(service.findLibraries(condition))
        model.addAttribute(form)
        return "index"
    }
}
