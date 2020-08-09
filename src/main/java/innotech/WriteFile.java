package innotech;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {

    public static void write(String fileName, List<String> list) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false)))
        {
            for (String variant : list) {
                writer.write(variant);
            }
            writer.flush();
        } catch (IOException e) {
            System.out.printf("Ошибка при записи");
        }
    }
}
