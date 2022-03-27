import uwu.narumi.argparser.ArgumentParser;
import uwu.narumi.argparser.option.Options;
import uwu.narumi.argparser.rules.Rules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Example {

    private static final ArgumentParser argumentParser = ArgumentParser.builder().build();

    //java -jar application.jar -dir example/dir -fileName example.txt +create
    //java -jar application.jar -dir example/dir -fileName example.txt
    public static void main(String... args) throws IOException {
        Options options = argumentParser.parse(
                Rules.builder()
                        .add("dir", false, true)
                        .add("fileName", false, true)
                        .add("create", true, false)
                        .build(),
                args
        );

        System.out.println(options);
        Path dir = Paths.get(options.asString("dir").get());
        if (!dir.toFile().exists())
            dir.toFile().mkdirs();

        Path file = Paths.get(dir.toString(), options.asString("fileName").get());
        if (options.asBoolean("create").orElse(false) && !file.toFile().exists()) {
            file.toFile().createNewFile();
            System.out.println("Created: " + file);
        }

        System.out.println("Written bytes to: " + Files.write(file, new byte[]{1, 2, 3}));
    }
}
