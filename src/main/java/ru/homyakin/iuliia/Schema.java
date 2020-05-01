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
        this.mapping = createMapping(mapping);
    }

    @JsonProperty("prev_mapping")
    private void unpackPrevMapping(Map<String, String> prevMapping) {
        this.prevMapping = createMapping(prevMapping);
    }

    @JsonProperty("next_mapping")
    private void unpackNextMapping(Map<String, String> nextMapping) {
        this.nextMapping = createMapping(nextMapping);
    }

    @JsonProperty("ending_mapping")
    private void unpackEndingMapping(Map<String, String> endingMapping) {
        this.endingMapping = createMapping(endingMapping);
    }

    private Map<String, String> createMapping(Map<String, String> mapping) {
        if (mapping == null) {
            return new HashMap<>();
        } else {
            var entrySet = new HashSet<>(mapping.entrySet());
            for (var entry : entrySet) {
                mapping.put(capitalize(entry.getKey()), capitalize(entry.getValue()));
            }
            return mapping;
        }
    }

    private String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen)
            .append(Character.toTitleCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
}
