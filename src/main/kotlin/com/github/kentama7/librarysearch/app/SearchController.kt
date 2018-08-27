package com.github.kentama7.librarysearch.app

import com.github.kentama7.librarysearch.service.SearchService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class SearchController(private val helper: SearchHelper,
                       private val service: SearchService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("searchForm", SearchForm())
        return "index"
    }

    @PostMapping("search")
    fun search(@Validated form: SearchForm, bindingResult: BindingResult, model: Model): String {
        val condition = helper.createSearchCondition(form)
        val libraries = service.findLibraries(condition)
        model.addAttribute("searchForm", form)
        model.addAttribute("libraries", libraries)
        return "index"
    }
}
