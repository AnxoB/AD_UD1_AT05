import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Persona p1 = new Persona("Anxo", 21);
        Persona p2 = new Persona("Sergio", 19);

        System.out.println("1. Copiar fichero");
        System.out.println("2. Guarda objetos tipo Persona");
        System.out.println("3. Leer objetos tipo Persona");
        System.out.println("4. Salir");
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();

        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    CopiaFichero cf = new CopiaFichero();
                    cf.copy();
                    System.out.println("1. Copiar fichero");
                    System.out.println("2. Guarda objetos tipo Persona");
                    System.out.println("3. Leer objetos tipo Persona");
                    System.out.println("4. Salir");
                    opcion = entrada.nextInt();
                    break;

                case 2:
                    try (FileOutputStream stream = new FileOutputStream("personas.bin");
                            ObjectOutputStream out = new ObjectOutputStream(stream)) {

                        out.writeObject(p1);
                        out.writeObject(p2);

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println("1. Copiar fichero");
                    System.out.println("2. Guarda objetos tipo Persona");
                    System.out.println("3. Leer objetos tipo Persona");
                    System.out.println("4. Salir");
                    opcion = entrada.nextInt();
                    break;

                case 3:
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("personas.bin"))) {
                        Persona p;
                        while ((p = leerPersona(in)) != null) {
                            System.out.println(p);
                        }

                        System.out.println("Final del archivo");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("1. Copiar fichero");
                    System.out.println("2. Guarda objetos tipo Persona");
                    System.out.println("3. Leer objetos tipo Persona");
                    System.out.println("4. Salir");
                    opcion = entrada.nextInt();
                    break;

                default:
                    break;
            }
        }

        entrada.close();

    }

    private static Persona leerPersona(ObjectInputStream in) {
        try {
            return (Persona) in.readObject();
        } catch (EOFException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
