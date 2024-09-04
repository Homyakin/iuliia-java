package ru.homyakin.iuliia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

public class TranslatorTranslateTest {
    @Test
    public void Given_LetterToNumbers_When_Translate_Then_Success() throws IOException {
        var translator = createTranslator("test_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("243221", word);
    }

    @Test
    public void Given_TwoPrevLettersIntoZeroLetters_When_Translate_Then_RemoveLastLetter() throws IOException {
        var translator = createTranslator("test_prev_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Iulia", word);
    }

    @Test
    public void Given_TwoNextLettersIntoOneLetter_When_Translate_Then_ReplaceFirstLetter() throws IOException {
        var translator = createTranslator("test_next_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Yuliia", word);
    }

    @Test
    public void Given_TwoEndingLettersIntoTwoLetters_When_Translate_Then_ReplaceLastTwoLetters() throws IOException {
        var translator = createTranslator("test_ending_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Iuliyd", word);
    }

    @Test
    public void Given_NoRules_When_Translate_Then_RemainWord() throws IOException {
        var translator = createTranslator("test_empty.json");
        var word = translator.translate("Iu");
        Assertions.assertEquals("Iu", word);
    }

    @Test
    public void Given_EmptyWord_When_Translate_Then_EmptyWord() throws IOException {
        var translator = createTranslator("test_empty.json");
        var word = translator.translate("");
        Assertions.assertEquals("", word);
    }

    private Translator createTranslator(String schemaName) throws IOException {
        var mockedSchemas = Mockito.mock(Schemas.class);
        var schema = getSchema(schemaName);
        Mockito.when(mockedSchemas.getSchema()).thenReturn(schema);
        return new Translator(mockedSchemas);
    }


    private Schema getSchema(String name) throws IOException {
        try (var stream = getJsonStream(name)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stream, Schema.class);
        }
    }

    private InputStream getJsonStream(String fileName) {
        return getClass().getResourceAsStream("/schemas/" + fileName);
    }
}
