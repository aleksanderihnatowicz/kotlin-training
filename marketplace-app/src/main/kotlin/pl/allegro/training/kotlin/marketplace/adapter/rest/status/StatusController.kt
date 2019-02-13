package pl.allegro.training.kotlin.marketplace.adapter.rest.status

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping(path = ["/status"], produces = ["application/json"])
    fun getStatus(): String = """
        {
            "status": "OK"
        }
    """.trimIndent()

}