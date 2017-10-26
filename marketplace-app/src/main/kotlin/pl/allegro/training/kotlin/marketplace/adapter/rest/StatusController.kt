package pl.allegro.training.kotlin.marketplace.adapter.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping(path = arrayOf("/status"), produces = arrayOf("application/json"))
    fun getStatus(): String = """
        {
            "status": "OK"
        }
    """.trimIndent()

}