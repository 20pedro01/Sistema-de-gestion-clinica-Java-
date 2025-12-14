import java.sql.*;
import java.util.Scanner;

public class CrudPacientes {

    // Configuración de la conexión (Igual que en TestConnection)
    static final String URL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "20pedro01A"; // Cambiar a "" si es necesario para Wamp

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("--- CONEXIÓN EXITOSA ---");
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 5) {
                System.out.println("\n--- MENU CRUD PACIENTES ---");
                System.out.println("1. Listar Pacientes (READ)");
                System.out.println("2. Insertar Paciente (CREATE)");
                System.out.println("3. Actualizar Paciente (UPDATE)");
                System.out.println("4. Eliminar Paciente (DELETE)");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                } else {
                    scanner.next(); // Limpiar entrada inválida
                    continue;
                }

                switch (opcion) {
                    case 1:
                        listarPacientes(conn);
                        break;
                    case 2:
                        insertarPaciente(conn, scanner);
                        break;
                    case 3:
                        actualizarPaciente(conn, scanner);
                        break;
                    case 4:
                        eliminarPaciente(conn, scanner);
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
    private static void listarPacientes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM pacientes";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- LISTA DE PACIENTES ---");
            System.out.printf("%-5s %-20s %-20s %-15s %-30s%n", "ID", "NOMBRES", "APELLIDOS", "TELEFONO",
                    "OBSERVACIONES");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombres");
                String ape = rs.getString("apellidos");
                String tel = rs.getString("telefono");
                String obs = rs.getString("observaciones");
                System.out.printf("%-5d %-20s %-20s %-15s %-30s%n", id, nom, ape, tel, obs);
            }
        }
    }

    // --- CREATE ---
    private static void insertarPaciente(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- NUEVO PACIENTE ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();

        String sql = "INSERT INTO pacientes (nombres, apellidos, telefono, observaciones) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombres);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, telefono);
            pstmt.setString(4, observaciones);

            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("¡Paciente insertado con éxito!");
        }
    }

    // --- UPDATE ---
    private static void actualizarPaciente(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- ACTUALIZAR PACIENTE ---");
        System.out.print("ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir enter

        System.out.println("¿Qué dato deseas actualizar?");
        System.out.println("1. Nombres");
        System.out.println("2. Apellidos");
        System.out.println("3. Teléfono");
        System.out.println("4. Observaciones");
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
                sql = "UPDATE pacientes SET nombres = ? WHERE id = ?";
                campo = "Nombres";
                break;
            case 2:
                System.out.print("Nuevos Apellidos: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE pacientes SET apellidos = ? WHERE id = ?";
                campo = "Apellidos";
                break;
            case 3:
                System.out.print("Nuevo Teléfono: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE pacientes SET telefono = ? WHERE id = ?";
                campo = "Teléfono";
                break;
            case 4:
                System.out.print("Nuevas Observaciones: ");
                nuevoValor = scanner.nextLine();
                sql = "UPDATE pacientes SET observaciones = ? WHERE id = ?";
                campo = "Observaciones";
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
                System.out.println("¡" + campo + " del paciente actualizado con éxito!");
            else
                System.out.println("No se encontró paciente con ese ID.");
        }
    }

    // --- DELETE ---
    private static void eliminarPaciente(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("\n--- ELIMINAR PACIENTE ---");
        System.out.print("ID del paciente a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("¡Paciente eliminado con éxito!");
            else
                System.out.println("No se encontró paciente con ese ID.");
        }
    }
}
