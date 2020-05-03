package ru.homyakin.iuliia;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public enum Schemas {
    ALA_LC("ala_lc.json"),
    ALA_LC_ALT("ala_lc_alt.json"),
    BGN_PCGN("bgn_pcgn.json"),
    BGN_PCGN_ALT("bgn_pcgn_alt.json"),
    BS_2979("bgn_2979.json"),
    BS_2979_ALT("bgn_2979_alt.json"),
    GOST_779("gost_779.json"),
    GOST_779_ALT("gost_779_alt.json"),
    GOST_7034("gost_7034.json"),
    GOST_16876("gost_16876.json"),
    GOST_16876_ALT("gost_16876_alt.json"),
    GOST_52290("gost_52290.json"),
    GOST_52535("gost_52535.json"),
    ICAO_DOC_9303("icao_doc_9303.json"),
    ISO_9_1954("iso_9_1954.json"),
    ISO_9_1968("iso_9_1968.json"),
    ISO_9_1968_alt("iso_9_1968_alt.json"),
    MOSMETRO("mosmetro.json"),
    MVD_310("mvd_310.json"),
    MVD_310_FR("mvd_310_fr.json"),
    MVD_782("mvd_782.json"),
    SCIENTIFIC("scientific.json"),
    TELEGRAM("telegram.json"),
    UNGEGN_1987("ungegn_1987.json"),
    WIKIPEDIA("wikipedia.json"),
    YANDEX_MAPS("yandex_maps.json"),
    YANDEX_MONEY("yandex_money.json");

    private final String name;

    Schemas(String name) {
        this.name = name;
    }

    public Schema getSchema() throws IOException {
        var stream = getFilePath(name);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stream, Schema.class);
    }

    public String getName() {
        return name;
    }

    private InputStream getFilePath(String fileName) {
        return getClass().getResourceAsStream("/schemas/" + fileName);
    }


}
