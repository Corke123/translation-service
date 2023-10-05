package org.unibl.etf.pisio.translationservice.translate

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class TranslationRoutes(private val translationService: TranslationService) {

    @Bean
    fun http() = coRouter {
        POST("/api/v1/translate") {
            val request = it.awaitBody<TranslationRequest>()
            val translatedText = translationService.translateText(request.text)
            ServerResponse.ok().bodyValueAndAwait(TranslationResponse(translatedText))
        }
    }
}

data class TranslationRequest(val text: String)
data class TranslationResponse(val text: String)