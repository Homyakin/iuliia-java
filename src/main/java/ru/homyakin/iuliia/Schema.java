package ru.homyakin.iuliia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a transliteration schema, which defines the rules for converting text from one script to another.
 *
 * @author Homyakin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schema {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    private Map<String, String> mapping;
    private Map<String, String> prevMapping;
    private Map<String, String> nextMapping;
    private Map<String, String> endingMapping;

    /**
     * @return the name of this schema
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of this schema
     */
    public String getDescription() {
        return description;
    }

    /**
     * Transliterates given word ending according to the rules of this schema.
     *
     * @param ending the ending to transliterate
     * @return transliterated ending, or empty string if input was empty, or empty {@link Optional} if no transliteration was found
     */
    public Optional<String> translateEnding(String ending) {
        if (ending.equals("")) {
            return Optional.of(ending);
        }
        return Optional.ofNullable(endingMapping.getOrDefault(ending, null));
    }

    /**
     * Transliterates a single letter according to this schema.
     * <p>
     * The letter is transliterated according to the following rules:
     * <ol>
     * <li>If a mapping for the previous letter and the current letter exists, it is used.
     * <li>If a mapping for the current letter and the next letter exists, it is used.
     * <li>If a mapping for the current letter exists, it is used.
     * <li>The current letter is left as is.
     * </ol>
     *
     * @param prev the previous letter
     * @param curr the current letter
     * @param next the next letter
     * @return the transliterated letter
     */
    public String translateLetter(String prev, String curr, String next) {
        String letter = prevMapping.get(prev + curr);
        if (letter == null) {
            letter = nextMapping.get(curr + next);
        }
        if (letter == null) {
            letter = mapping.getOrDefault(curr, curr);
        }
        return letter;
    }

    @JsonProperty("mapping")
    private void unpackMapping(Map<String, String> mapping) {
        if (mapping == null) {
            this.mapping = new HashMap<>();
        } else {
            var entrySet = new HashSet<>(mapping.entrySet());
            for (var entry : entrySet) {
                mapping.put(capitalize(entry.getKey()), capitalize(entry.getValue()));
            }
            this.mapping = mapping;
        }
    }

    @JsonProperty("prev_mapping")
    private void unpackPrevMapping(Map<String, String> prevMapping) {
        if (prevMapping == null) {
            this.prevMapping = new HashMap<>();
        } else {
            var entrySet = new HashSet<>(prevMapping.entrySet());
            for (var entry : entrySet) {
                prevMapping.put(capitalize(entry.getKey()), entry.getValue());
                prevMapping.put(entry.getKey().toUpperCase(), capitalize(entry.getValue()));
            }
            this.prevMapping = prevMapping;
        }
    }

    @JsonProperty("next_mapping")
    private void unpackNextMapping(Map<String, String> nextMapping) {
        if (nextMapping == null) {
            this.nextMapping = new HashMap<>();
        } else {
            var entrySet = new HashSet<>(nextMapping.entrySet());
            for (var entry : entrySet) {
                nextMapping.put(capitalize(entry.getKey()), capitalize(entry.getValue()));
                nextMapping.put(entry.getKey().toUpperCase(), capitalize(entry.getValue()));
            }
            this.nextMapping = nextMapping;
        }
    }

    @JsonProperty("ending_mapping")
    private void unpackEndingMapping(Map<String, String> endingMapping) {
        if (endingMapping == null) {
            this.endingMapping = new HashMap<>();
        } else {
            var entrySet = new HashSet<>(endingMapping.entrySet());
            for (var entry : entrySet) {
                endingMapping.put(entry.getKey().toUpperCase(), entry.getValue().toUpperCase());
            }
            this.endingMapping = endingMapping;
        }
    }

    private String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }
}
