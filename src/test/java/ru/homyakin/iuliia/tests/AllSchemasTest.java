package ru.homyakin.iuliia.tests;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

public class AllSchemasTest {

    @Test
    public void alaLcTest() throws IOException {
        var translator = new Translator(Schemas.ALA_LC);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("I͡ulii͡a, sʺeshʹ eshchё ėtikh mi͡agkikh frant͡suzskikh bulok iz Ĭoshkar-Oly, da vypeĭ altaĭskogo chai͡u", sentence);
    }

    @Test
    public void alaLcAltTest() throws IOException {
        var translator = new Translator(Schemas.ALA_LC_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Iuliia, s\"esh' eshche etikh miagkikh frantsuzskikh bulok iz Ioshkar-Oly, da vypei altaiskogo chaiu", sentence);
    }

    @Test
    public void bgnPcgnTest() throws IOException {
        var translator = new Translator(Schemas.BGN_PCGN);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, s”yesh’ yeshchё etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void bgnPcgnAltTest() throws IOException {
        var translator = new Translator(Schemas.BGN_PCGN_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, s”yesh’ yeshchё etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void bs2979Test() throws IOException {
        var translator = new Translator(Schemas.BS_2979);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, sʺeshʹ eshchё étikh myagkikh frantsuzskikh bulok iz Ĭoshkar-Olȳ, da vȳpeĭ altaĭskogo chayu", sentence);
    }

    @Test
    public void bs2979AltTest() throws IOException {
        var translator = new Translator(Schemas.BS_2979_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, s\"esh' eshche etikh myagkikh frantsuzskikh bulok iz Ioshkar-Oly, da vypei altaiskogo chayu", sentence);
    }

    @Test
    public void gost779Test() throws IOException {
        var translator = new Translator(Schemas.GOST_779);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Ûliâ, sʺešʹ eŝё ètih mâgkih francuzskih bulok iz Joškar-Oly, da vypej altajskogo čaû", sentence);
    }

    @Test
    public void gost779AltTest() throws IOException {
        var translator = new Translator(Schemas.GOST_779_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, s``esh` eshhyo e`tix myagkix franczuzskix bulok iz Joshkar-Oly`, da vy`pej altajskogo chayu", sentence);
    }

    @Test
    public void gost7034Test() throws IOException {
        var translator = new Translator(Schemas.GOST_7034);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, s''esh' eshhyo etix myagkix francuzskix bulok iz Joshkar-Oly, da vypej altajskogo chayu", sentence);
    }

    @Test
    public void gost16876Test() throws IOException {
        var translator = new Translator(Schemas.GOST_16876);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Ûliâ, sʺešʹ eŝё ètih mâgkih francuzskih bulok iz Joškar-Oly, da vypej altajskogo čaû", sentence);
    }

    @Test
    public void gost16876AltTest() throws IOException {
        var translator = new Translator(Schemas.GOST_16876_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Julija, s\"esh' eshhjo ehtikh mjagkikh francuzskikh bulok iz Jjoshkar-Oly, da vypejj altajjskogo chaju", sentence);
    }

    @Test
    public void gost52290Test() throws IOException {
        var translator = new Translator(Schemas.GOST_52290);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh' eshche etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void gost52535Test() throws IOException {
        var translator = new Translator(Schemas.GOST_52535);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Iuliia, sesh eshche etikh miagkikh frantcuzskikh bulok iz Ioshkar-Oly, da vypei altaiskogo chaiu", sentence);
    }

    @Test
    public void icaoDoc9303Test() throws IOException {
        var translator = new Translator(Schemas.ICAO_DOC_9303);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Iuliia, sieesh eshche etikh miagkikh frantsuzskikh bulok iz Ioshkar-Oly, da vypei altaiskogo chaiu", sentence);
    }

    @Test
    public void iso91954Test() throws IOException {
        var translator = new Translator(Schemas.ISO_9_1954);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Julija, s\"ešʹ eščë ėtih mjagkih francuzskih bulok iz Joškar-Oly, da vypej altajskogo čaju", sentence);
    }

    @Test
    public void iso91968Test() throws IOException {
        var translator = new Translator(Schemas.ISO_9_1968);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Julija, sʺešʹ eščë ėtih mjagkih francuzskih bulok iz Joškar-Oly, da vypej altajskogo čaju", sentence);
    }

    @Test
    public void iso91968AltTest() throws IOException {
        var translator = new Translator(Schemas.ISO_9_1968_ALT);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yulyya, sʺeshʹ eshchë ėtykh myagkykh frantsuzskykh bulok yz Ĭoshkar-Oly, da vypeĭ altaĭskogo chayu", sentence);
    }

    @Test
    public void mosmetroTest() throws IOException {
        var translator = new Translator(Schemas.MOSMETRO);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh esche etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void mvd310Test() throws IOException {
        var translator = new Translator(Schemas.MVD_310);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh' eshche etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void mvd310FrTest() throws IOException {
        var translator = new Translator(Schemas.MVD_310_FR);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Iouliia, sech echtche etikh miagkikh frantsouzskikh boulok iz Iochkar-Oly, da vypei altaiskogo tchaiou", sentence);
    }

    @Test
    public void mvd782Test() throws IOException {
        var translator = new Translator(Schemas.MVD_782);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh' eshche etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void scientificTest() throws IOException {
        var translator = new Translator(Schemas.SCIENTIFIC);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Julija, sʺešʹ eščё ètix mjagkix francuzskix bulok iz Joškar-Oly, da vypej altajskogo čaju", sentence);
    }

    @Test
    public void telegramTest() throws IOException {
        var translator = new Translator(Schemas.TELEGRAM);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Iuliia, sesh esce etih miagkih francuzskih bulok iz Ioshkar-Oly, da vypei altaiskogo chaiu", sentence);
    }

    @Test
    public void ungegn1987Test() throws IOException {
        var translator = new Translator(Schemas.UNGEGN_1987);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Julija, sʺešʹ eščё ètih mjagkih francuzskih bulok iz Joškar-Oly, da vypej altajskogo čaju", sentence);
    }

    @Test
    public void wikipediaTest() throws IOException {
        var translator = new Translator(Schemas.WIKIPEDIA);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh yeshchyo etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void yandexMapsTest() throws IOException {
        var translator = new Translator(Schemas.YANDEX_MAPS);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, syesh yeschyo etikh myagkikh frantsuzskikh bulok iz Yoshkar-Oly, da vypey altayskogo chayu", sentence);
    }

    @Test
    public void yandexMoneyTest() throws IOException {
        var translator = new Translator(Schemas.YANDEX_MONEY);
        var sentence = translator.translate("Юлия, съешь ещё этих мягких французских булок из Йошкар-Олы, да выпей алтайского чаю");
        Assertions.assertEquals("Yuliya, sesh esche etikh myagkikh frantsuzskikh bulok iz Ioshkar-Oly, da vypei altaiskogo chayu", sentence);
    }
}
