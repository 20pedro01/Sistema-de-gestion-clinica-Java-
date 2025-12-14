import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n=======================================================");
            System.out.println("   Bienvenido al Sistema de Gestión Clínica Integral");
            System.out.println("=======================================================");
            System.out.println("1. Gestión de Pacientes");
            System.out.println("2. Gestión de Especialistas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.next();
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n>>> Accediendo a Gestión de Pacientes...");
                    CrudPacientes.main(new String[] {});
                    break;
                case 2:
                    System.out.println("\n>>> Accediendo a Gestión de Especialistas...");
                    CrudEspecialistas.main(new String[] {});
                    break;
                case 3:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }
}
