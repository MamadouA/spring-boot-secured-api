package com.ascenders.secured_api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping
    fun greeting() = "Hello world!"

    @GetMapping("/about")
    fun about() = "This is a secured api."
}