# `Iuliia`

> Transliterate Cyrillic → Latin in every possible way

Transliteration means representing Cyrillic data (mainly names and geographic locations) with Latin letters. It is used for international passports, visas, green cards, driving licenses, mail and goods delivery etc.

## Why use `Iuliia`

-   [20 transliteration schemas](https://github.com/nalgeon/iuliia/blob/master/README.md#supported-schemas) (rule sets), including all main international and Russian standards.
-   Correctly implements not only the base mapping, but all the special rules for letter combinations and word endings (AFAIK, Iuliia is the only library which does so).
-   Simple API.

For schema details and other information, see <https://dangry.ru/iuliia> (in Russian).

## Installation

### Java 17 or higher (recommended)
Maven dependency
```xml
<dependency>
    <groupId>ru.homyakin</groupId>
    <artifactId>iuliia-java</artifactId>
    <version>2.0.0</version>
</dependency>
```

Gradle
```gradle
implementation 'ru.homyakin:iuliia-java:2.0.0'
```

### Java 11 or higher
Latest version for java 11 is [1.8](https://mvnrepository.com/artifact/ru.homyakin/iuliia-java/1.8)

## Usage

Transliterate using specified schema:

```java
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

public class Clazz {
    public static void test() {        
        final var translator = new Translator(Schemas.ICAO_DOC_9303);
        translator.translate("Юлия"); //Iuliia
    }
}
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Make sure to add or update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
