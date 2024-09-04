package ru.homyakin.iuliia;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Transliterates Cyrillic string into Latin according to the rules of the {@link Schemas} set in the constructor.
 *
 * @author Homyakin
 */
public class Translator {

    private final static Pattern separator = Pattern.compile("(?U:\\b)");
    private final Schema schema;

    /**
     * Creates instance of {@link Translator}.
     * Each resource file is loaded only once for each instance.
     *
     * @param schema The scheme according to which the translation will take place
     * @throws IllegalStateException if schema was corrupted
     */
    public Translator(Schemas schema) {
        try {
            this.schema = schema.getSchema();
        } catch (IOException e) {
            throw new IllegalStateException("Resources were corrupted");
        }
    }

    /**
     * Transliterates given Cyrillic string into Latin according to the rules of the {@link Schemas} set in the constructor.
     *
     * @param str the string to transliterate
     * @return transliterated string
     */
    public String translate(String str) {
        if (str == null) return null;
        final var words = separator.split(str);
        final var translated = new StringBuilder();
        for (final var word : words) {
            translated.append(translateWord(word));
        }
        return translated.toString();
    }

    private String translateWord(String word) {
        final var splitWord = splitWord(word);
        final var translatedEnding = schema.translateEnding(splitWord.ending);
        return translatedEnding
            .map(s -> translateLetters(splitWord.stem) + s)
            .orElseGet(() -> translateLetters(word));
    }

    private String translateLetters(String word) {
        var prev = "";
        var curr = "";
        var next = "";
        final int length = word.length();
        final var translated = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            if (!curr.equals("")) {
                prev = curr;
            }
            if (next.equals("")) {
                curr = String.valueOf(word.charAt(i));
            } else {
                curr = next;
            }
            if (i < length - 1) {
                next = String.valueOf(word.charAt(i + 1));
            } else {
                next = "";
            }
            translated.append(schema.translateLetter(prev, curr, next));
        }
        return translated.toString();
    }

    private SplitWord splitWord(String word) {
        if (word.length() > WORD_ENDING_LENGTH) {
            int separateIndex = word.length() - WORD_ENDING_LENGTH;
            return new SplitWord(word.substring(0, separateIndex), word.substring(separateIndex));
        } else {
            return new SplitWord(word, "");
        }
    }

    private record SplitWord(String stem, String ending) {
    }

    private static final int WORD_ENDING_LENGTH = 2;
}
