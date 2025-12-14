import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        // Configuración de la conexión (Basada en tu configuración local original)
        String url = "jdbc:mysql://localhost:3306/dbtest?useSSL=false&serverTimezone=UTC";
        String usuario = "root";

        // NOTA: WampServer por defecto no tiene contraseña (string vacío "").
        // Pero tu archivo original tenía '20pedro01A'.
        // Si falla la conexión, cambia esto a: String password = "";
        String password = "20pedro01A";

        System.out.println("Intentando conectar a: " + url);
        System.out.println("Usuario: " + usuario);

        try {
            // Cargar el driver (Opcional en versiones nuevas de Java, pero buena práctica
            // para debug)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(url, usuario, password);

            if (conexion != null) {
                System.out.println("-------------------------------------------------");
                System.out.println("¡CONEXIÓN EXITOSA!");
                System.out.println("Se ha conectado correctamente a la base de datos 'dbtest'.");
                System.out.println("-------------------------------------------------");
                conexion.close();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el Driver JDBC.");
            System.err.println(
                    "Asegúrate de tener el archivo 'mysql-connector-j-X.X.X.jar' en la misma carpeta o en el ClassPath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXIÓN SQL:");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código de error: " + e.getErrorCode());

            if (e.getErrorCode() == 1045) {
                System.out.println("\nPISTA: Error de acceso. Verifica tu usuario y contraseña.");
                System.out.println("Si usas WampServer por defecto, prueba poniendo la password vacía en el código.");
            }
        }
    }
}
