package pl.allegro.training.kotlin.marketplace.adapter.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// @Konrad
// 9a. o tego zaczynamy cześć 4

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