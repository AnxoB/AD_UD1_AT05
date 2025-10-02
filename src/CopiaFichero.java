import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopiaFichero {
    public void copy() {
        Path pathOrigen = Paths.get("dir\\origen.txt");
        Path pathCopia = Paths.get("dir\\copia.txt");
        Path pathBackup = Paths.get("backup");
        Path pathBackupFile = Paths.get("backup\\copia.txt");

        try {
            Files.copy(pathOrigen, pathCopia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Contenido copiado de origen.txt a copia.txt");

            if (!Files.exists(pathBackup)) {
                Files.createDirectories(pathBackup);
                System.out.println("Directorio backup creado");
            }

            Files.move(pathCopia, pathBackupFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copia.txt movido al directorio backup");

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
