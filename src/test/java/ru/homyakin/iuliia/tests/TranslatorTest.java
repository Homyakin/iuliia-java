package ru.homyakin.iuliia.tests;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

public class TranslatorTest {

    @Test
    public void iuliiaTest() throws IOException {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        var word = translator.translate("Юлия");
        Assertions.assertEquals("Iuliia", word);
    }
}
