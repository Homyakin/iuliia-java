package ru.homyakin.iuliia.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.homyakin.iuliia.Schema;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

public class MappingTest {

    @Test
    public void iuliiaTest() throws IOException {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        var word = translator.translate("Юлия");
        Assertions.assertEquals("Iuliia", word);
    }

    @Test
    public void mappingTest() throws IOException {
        var translator = createTranslator("test_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("243221", word);
    }

    @Test
    public void prevMappingTest() throws IOException {
        var translator = createTranslator("test_prev_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Iulia", word);
    }

    @Test
    public void nextMappingTest() throws IOException {
        var translator = createTranslator("test_next_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Yuliia", word);
    }

    @Test
    public void endingMappingTest() throws IOException {
        var translator = createTranslator("test_ending_mapping.json");
        var word = translator.translate("Iuliia");
        Assertions.assertEquals("Iuliya", word);
    }

    @Test
    public void shortWordTest() throws IOException {
        var translator = createTranslator("test_empty.json");
        var word = translator.translate("Iu");
        Assertions.assertEquals("Iu", word);
    }

    @Test
    public void emptyWordTest() throws IOException {
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
        var stream = getJsonStream(name);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stream, Schema.class);
    }

    private InputStream getJsonStream(String fileName) {
        return getClass().getResourceAsStream("/schemas/" + fileName);
    }
}
