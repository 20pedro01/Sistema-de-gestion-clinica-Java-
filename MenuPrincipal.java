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
                scanner.nextLine(); // Consumir nueva línea
            } else {
                scanner.next(); // Limpiar entrada inválida
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Llamamos al main de CrudExample (Pacientes)
                    // Pasamos argumentos vacíos ya que CrudExample no los usa
                    System.out.println("\n>>> Accediendo a Gestión de Pacientes...");
                    CrudPacientes.main(new String[] {});
                    break;
                case 2:
                    // Llamamos al main de CrudEspecialistas (Especialistas)
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
