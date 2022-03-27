import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uwu.narumi.argparser.ArgumentParser;
import uwu.narumi.argparser.option.Options;
import uwu.narumi.argparser.rules.Rules;

/*
    Really idk if i do it properly :kekw:
 */
public class SimpleTest {

    ArgumentParser argumentParser = ArgumentParser.builder().build();

    @Test
    public void simpleTestA() {
        Options options = argumentParser.parse(
                Rules.builder()
                        .add("a", true, false)
                        .add("comment", false, true)
                        .add("int", true, true)
                        .build(),
                "+a -comment hi q w e r t y -int 69"
        );
        Assertions.assertEquals(69, options.asInt("int").orElse(1));
        Assertions.assertEquals(69, options.asInt("comment").orElse(69));
        Assertions.assertEquals("hi q w e r t y", options.asString("comment").orElse(""));
        Assertions.assertTrue(options.asBoolean("a").orElse(false));
    }

    @Test
    public void simpleTestB() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> argumentParser.parse(
                Rules.builder()
                        .add("a", false, false)
                        .add("comment", false, true)
                        .add("int", true, true)
                        .build(),
                "-comment hi q w e r t y -int 69"
        ));
    }
}
