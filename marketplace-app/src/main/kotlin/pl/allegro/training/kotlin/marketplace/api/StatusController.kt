package pl.allegro.training.kotlin.marketplace.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class StatusController {

    // use arrayOf() to set annotation properties values
    @GetMapping(path = arrayOf("/status"), produces = arrayOf("application/json"))
    fun getStatus(): String {
        // multi-line string
        return """
            {
                "status": "OK"
            }
        """.trimIndent()
    }

}