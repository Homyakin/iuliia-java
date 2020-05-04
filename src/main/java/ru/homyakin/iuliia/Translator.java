package ru.homyakin.iuliia;

import java.io.IOException;

public class Translator {

    private final Schema schema;

    /**
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

    public String translate(String str) {
        if (str == null) return null;
        var words = str.split("\\s+");
        var translated = new StringBuilder();
        int length = words.length;
        for (int i = 0; i < length - 1; ++i) {
            translated.append(translateWord(words[i]));
            translated.append(" ");
        }
        translated.append(translateWord(words[length - 1]));
        return translated.toString();
    }

    private String translateWord(String word) {
        var splitWord = splitWord(word);
        var translatedEnding = schema.translateEnding(splitWord.ending);
        return translatedEnding
            .map(s -> translateLetters(splitWord.stem) + s)
            .orElseGet(() -> translateLetters(word));
    }

    private String translateLetters(String word) {
        String prev = "";
        String curr = "";
        String next = "";
        int length = word.length();
        var translated = new StringBuilder();
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
        int endingLength = 2;
        if (word.length() > endingLength) {
            int separateIndex = word.length() - endingLength;
            return new SplitWord(word.substring(0, separateIndex), word.substring(separateIndex));
        } else {
            return new SplitWord(word, "");
        }
    }

    private static class SplitWord {
        private final String stem;
        private final String ending;

        public SplitWord(String stem, String ending) {
            this.stem = stem;
            this.ending = ending;
        }
    }
}
