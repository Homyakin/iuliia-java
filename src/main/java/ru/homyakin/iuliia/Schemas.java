package ru.homyakin.iuliia;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

/**
 * Prepared Schemas of transliteration.
 * Please don't create your own.
 * You can find all descriptions in Russian <a href="https://iuliia.ru/">here</a>
 *
 * @author Homyakin
 */
public enum Schemas {
    /**
     * American Library Association and Library of Congress Romanization Tables for Russian.
     * Universal usage.
     */
    ALA_LC("ala_lc.json"),
    /**
     * Same as {@link #ALA_LC} but without diacritics.
     */
    ALA_LC_ALT("ala_lc_alt.json"),
    /**
     * BGN/PCGN romanization system for Russian.
     * Developed by United States Board on Geographic Names (BGN)
     * and by the Permanent Committee on Geographical Names for British Official Use (PCGN).
     * Universal usage.
     */
    BGN_PCGN("bgn_pcgn.json"),
    /**
     * Same as {@link #BGN_PCGN} but without interpunct.
     */
    BGN_PCGN_ALT("bgn_pcgn_alt.json"),
    /**
     * British Standard 2979:1958.
     * Universal usage.
     */
    BS_2979("bs_2979.json"),
    /**
     * Same as {@link #BS_2979} but without diacritics.
     */
    BS_2979_ALT("bs_2979_alt.json"),
    /**
     * GOST 7.79-2000 (aka ISO 9:1995) transliteration schema.
     * Universal usage.
     */
    GOST_779("gost_779.json"),
    /**
     * Same as {@link #GOST_779} but without diacritics.
     */
    GOST_779_ALT("gost_779_alt.json"),
    /**
     * GOST R 7.0.34-2014 transliteration schema.
     * For libraries and publishers.
     */
    GOST_7034("gost_7034.json"),
    /**
     * GOST 16876-71 (aka GOST 1983) transliteration schema. Deprecated in favor of {@link #GOST_779}.
     */
    GOST_16876("gost_16876.json"),
    /**
     * Same as {@link #GOST_16876} but without diacritics. Deprecated in favor of {@link #GOST_779_ALT}.
     */
    GOST_16876_ALT("gost_16876_alt.json"),
    /**
     * GOST R 52290-2004 transliteration schema.
     * Used for proper names on road signs.
     */
    GOST_52290("gost_52290.json"),
    /**
     * GOST R 52535.1-2006 transliteration schema. Deprecated in favor of {@link #ICAO_DOC_9303}.
     */
    GOST_52535("gost_52535.json"),
    /**
     * ICAO DOC 9303 transliteration schema.
     * Used for full name.
     */
    ICAO_DOC_9303("icao_doc_9303.json"),
    /**
     * ISO/R 9:1954 transliteration schema. Deprecated in favor of {@link #GOST_779}.
     */
    ISO_9_1954("iso_9_1954.json"),
    /**
     * ISO/R 9:1968 transliteration schema. Deprecated in favor of {@link #GOST_779}.
     */
    ISO_9_1968("iso_9_1968.json"),
    /**
     * Same as {@link #ISO_9_1968} but with some different rules. Deprecated in favor of {@link #GOST_779}.
     */
    ISO_9_1968_ALT("iso_9_1968_alt.json"),
    /**
     * Transliteration schema of Moscow Metro. Designed by Artlebedev.
     */
    MOSMETRO("mosmetro.json"),
    /**
     * MVD 310-1997 transliteration schema. Deprecated in favor of {@link #ICAO_DOC_9303}.
     */
    MVD_310("mvd_310.json"),
    /**
     * "French" variant of ${@link #MVD_310}. Deprecated in favor of {@link #ICAO_DOC_9303}.
     */
    MVD_310_FR("mvd_310_fr.json"),
    /**
     * MVD 782-2000 transliteration schema. Deprecated in favor of {@link #ICAO_DOC_9303}.
     */
    MVD_782("mvd_782.json"),
    /**
     * Scientific transliteration schema from 1898. Similar with {@link #GOST_16876}.
     * Used in scientific documents.
     */
    SCIENTIFIC("scientific.json"),
    /**
     * Scheme of the Ministry of Communications for the transliteration of international telegrams.
     */
    TELEGRAM("telegram.json"),
    /**
     * UNGEGN 1987 V/18 transliteration schema from United Nations. Based on ${@link #GOST_16876}.
     */
    UNGEGN_1987("ungegn_1987.json"),
    /**
     * Wikipedia transliteration schema. Based on ${@link #BGN_PCGN} with significant changes.
     */
    WIKIPEDIA("wikipedia.json"),
    /**
     * Yandex.Maps transliteration schema for geographic names.
     */
    YANDEX_MAPS("yandex_maps.json"),
    /**
     * Yandex.Money transliteration schema. Deprecated in favor of {@link #ICAO_DOC_9303}.
     */
    YANDEX_MONEY("yandex_money.json");

    private final String name;

    Schemas(String name) {
        this.name = name;
    }

    /**
     * Returns the schema specified by the {@link #name} field.
     * The schema is read from a JSON file in the classpath.
     * @return the schema
     * @throws IOException if there is an error reading the schema file
     */
    public Schema getSchema() throws IOException {
        try (var stream = getJsonStream(name)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stream, Schema.class);
        }
    }

    /**
     * @return the file name of this schema
     */
    public String getName() {
        return name;
    }

    private InputStream getJsonStream(String fileName) {
        return getClass().getResourceAsStream("/schemas/" + fileName);
    }
}
