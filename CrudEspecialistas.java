import java.sql.*;
import java.util.Scanner;

public class CrudEspecialistas {

    // Configuración de la conexión
    static final String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = ""; // Cambiar si es necesario para Wamp

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("--- CONECTADO A ESPECIALISTAS ---");
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 5) {
                System.out.println("\n--- MENU CRUD ESPECIALISTAS ---");
                System.out.println("1. Listar Especialistas (READ)");
                System.out.println("2. Insertar Especialista (CREATE)");
                System.out.println("3. Actualizar Especialista (UPDATE)");
                System.out.println("4. Eliminar Especialista (DELETE)");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); 
                } else {
                    scanner.next(); 
                    continue;
                }

                switch (opcion) {
                    case 1:
                        listarEspecialistas(conn);
                        break;
                    case 2:
                        insertarEspecialista(conn, scanner);
                        break;
                    case 3:
                        actualizarEspecialista(conn, scanner);
                        break;
                    case 4:
                        eliminarEspecialista(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- READ ---
    private static void listarEspecialistas(Connection conn) throws SQLException {
        String sql = "SELECT * FROM especialistas";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- LISTA DE ESPECIALISTAS ---");
            System.out.printf("%-5s %-20s %-20s %-20s %-15s%n", "ID", "NOMBRES", "APELLIDOS", "ESPECIALIDAD",
                    "TELEFONO");
            System.out.println(
                    "-----------------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombres");
                String ape = rs.getString("apellidos");
                String esp = rs.getString("especialidad");
                String tel = rs.getString("telefono");
                System.out.printf("%-5d %-20s %-20s %-20s %-15s%n", id, nom, ape, esp, tel);
            }
        }
    }

    // --- CREATE ---
    private static void insertarEspecialista(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- NUEVO ESPECIALISTA ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Especialidad (Psicología/Psiquiatría): ");
        String especialidad = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        String sql = "INSERT INTO especialistas (nombres, apellidos, especialidad, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombres);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, especialidad);
            pstmt.setString(4, telefono);

            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("¡Especialista insertado con éxito!");
        }
    }

    // --- UPDATE ---
    private static void actualizarEspecialista(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- ACTUALIZAR ESPECIALISTA ---");
        System.out.print("ID del especialista a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir enter

        System.out.println("¿Qué dato deseas actualizar?");
        System.out.println("1. Nombres");
        System.out.println("2. Apellidos");
        System.out.println("3. Especialidad");
        System.out.println("4. Teléfono");
        System.out.println("5. Cancelar");
        System.out.print("Elige una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir enter

        String sql = "";
        String nuevoValor = "";
        String campo = "";

        switch (opcion) {
            case 1:
                System.out.print("Nuevos Nombres: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE especialistas SET nombres = ? WHERE id = ?";
                campo = "Nombres";
                break;
            case 2:
                System.out.print("Nuevos Apellidos: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE especialistas SET apellidos = ? WHERE id = ?";
                campo = "Apellidos";
                break;
            case 3:
                System.out.print("Nueva Especialidad: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE especialistas SET especialidad = ? WHERE id = ?";
                campo = "Especialidad";
                break;
            case 4:
                System.out.print("Nuevo Teléfono: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE especialistas SET telefono = ? WHERE id = ?";
                campo = "Teléfono";
                break;
            case 5:
                System.out.println("Operación cancelada.");
                return;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoValor);
            pstmt.setInt(2, id);

            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("¡" + campo + " del especialista actualizado con éxito!");
            else
                System.out.println("No se encontró especialista con ese ID.");
        }
    }

    // --- DELETE ---
    private static void eliminarEspecialista(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- ELIMINAR ESPECIALISTA ---");
        System.out.print("ID del especialista a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM especialistas WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("¡Especialista eliminado con éxito!");
            else
                System.out.println("No se encontró especialista con ese ID.");
        }
    }
}

