import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UnicodeWrite {
    // Java 11 adds Charset to FileWriter
    public static void writeUnicodeJava11(String fileName, String toWrite) {

        try (FileWriter fw = new FileWriter(new File(fileName), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(fw)) {
            writer.append(toWrite);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}