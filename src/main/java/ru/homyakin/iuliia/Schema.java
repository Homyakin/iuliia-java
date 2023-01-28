package ru.homyakin.iuliia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Optional<String> translateEnding(String ending) {
        if (ending.equals("")) {
            return Optional.of(ending);
        }
        return Optional.ofNullable(endingMapping.getOrDefault(ending, null));
    }

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
