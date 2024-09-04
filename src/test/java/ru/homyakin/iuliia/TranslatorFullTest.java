package ru.homyakin.iuliia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslatorFullTest {
    @Test
    public void Given_RealSchema_When_CreateTranslator_And_Translate_Then_Success() {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        var word = translator.translate("Юлия");
        Assertions.assertEquals("Iuliia", word);
    }
}
