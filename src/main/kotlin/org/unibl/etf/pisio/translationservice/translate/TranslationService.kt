package org.unibl.etf.pisio.translationservice.translate

import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import org.springframework.stereotype.Service

@Service
class TranslationService {

    fun translateText(text: String): String {
        val translate = TranslateOptions.getDefaultInstance().service
        val translation = translate.translate(
            text,
            Translate.TranslateOption.sourceLanguage("sr"),
            Translate.TranslateOption.targetLanguage("en")
        )
        return translation.translatedText
    }
}