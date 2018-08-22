package com.github.kentama7.booksearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookSearchApplication

fun main(args: Array<String>) {
    runApplication<BookSearchApplication>(*args)
}
