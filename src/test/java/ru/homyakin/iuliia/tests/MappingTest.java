package ru.homyakin.iuliia.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.homyakin.iuliia.Schema;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {Schemas.class})
public class MappingTest {

    @Test
    public void iuliiaTest() throws IOException {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        var word = translator.translate("Юлия");
        Assert.assertEquals("Iuliia", word);
    }

    @Test
    public void mappingTest() throws IOException {
        var translator = createTranslator("test_mapping.json");
        var word = translator.translate("Iuliia");
        Assert.assertEquals("243221", word);
    }

    @Test
    public void prevMappingTest() throws IOException {
        var translator = createTranslator("test_prev_mapping.json");
        var word = translator.translate("Iuliia");
        Assert.assertEquals("Iulia", word);
    }

    @Test
    public void nextMappingTest() throws IOException {
        var translator = createTranslator("test_next_mapping.json");
        var word = translator.translate("Iuliia");
        Assert.assertEquals("Yuliia", word);
    }

    @Test
    public void endingMappingTest() throws IOException {
        var translator = createTranslator("test_ending_mapping.json");
        var word = translator.translate("Iuliia");
        Assert.assertEquals("Iuliya", word);
    }

    @Test
    public void shortWordTest() throws IOException {
        var translator = createTranslator("test_empty.json");
        var word = translator.translate("Iu");
        Assert.assertEquals("Iu", word);
    }

    @Test
    public void emptyWordTest() throws IOException {
        var translator = createTranslator("test_empty.json");
        var word = translator.translate("");
        Assert.assertEquals("", word);
    }

    private Translator createTranslator(String schemaName) throws IOException {
        var mockSchema = PowerMockito.mock(Schemas.class);
        var schema = getSchema(schemaName);
        PowerMockito.when(mockSchema.getSchema()).thenReturn(schema);
        return new Translator(mockSchema);
    }


    private Schema getSchema(String name) throws IOException {
        var stream = getFilePath(name);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stream, Schema.class);
    }

    private InputStream getFilePath(String fileName) {
        return getClass().getResourceAsStream("/schemas/" + fileName);
    }
}
