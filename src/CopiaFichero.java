import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopiaFichero {
    public void copy() {
        Path path1 = Paths.get("dir\\origen.txt");
        Path path2 = Paths.get("dir\\copia.txt");
        Path path3 = Paths.get("backup\\copia.txt");

        try (BufferedReader reader = Files.newBufferedReader(path1);
                BufferedWriter writer = Files.newBufferedWriter(path2)) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line, 0, line.length());
                writer.newLine();
            }

            Files.createDirectories(path3.getParent());
            Files.copy(path2, path3);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        }
    }

}
