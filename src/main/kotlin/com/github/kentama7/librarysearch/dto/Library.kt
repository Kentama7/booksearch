package com.github.kentama7.librarysearch.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Library(@JsonProperty("formal") val name: String
                   , val address: String
                   , val tel: String
                   , @JsonProperty("url_pc") val url: String)
