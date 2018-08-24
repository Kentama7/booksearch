package com.github.kentama7.booksearch

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class SearchController {

    @GetMapping
    fun index() = "index"
}
