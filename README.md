# Proyecto Java - Sistema CRUD

Este directorio contiene el código fuente Java para un sistema CRUD de Pacientes y Especialistas.

## Requisitos Previos

1.  **Java Development Kit (JDK)**: Asegúrate de tener instalado Java (versión 8 o superior).
2.  **MySQL Server**: Debes tener un servidor MySQL ejecutándose (por ejemplo, a través de WampServer, XAMPP o una instalación independiente).
3.  **Conector MySQL**: El archivo `mysql-connector-j-9.4.0.jar` ya está incluido en esta carpeta.

## Configuración de la Base de Datos

Antes de ejecutar la aplicación, asegúrate de que tu base de datos esté configurada correctamente:

1.  Crea una base de datos llamada `dbtest`.
2.  La aplicación espera las siguientes credenciales por defecto (puedes cambiarlas en `TestConnection.java` y otras clases si es necesario):
    *   **Usuario**: `root`
    *   **Contraseña**: `` (vacía `""` si usas la configuración por defecto de WampServer)

## Cómo Compilar

Para compilar el proyecto, abre una terminal accediendo a esta carpeta y ejecutando el siguiente comando:

```bash
javac -cp .;mysql-connector-j-9.4.0.jar *.java
```

*Nota: En Linux/Mac, usa dos puntos `:` en lugar de punto y coma `;` como separador en el classpath.*

## Cómo ejecutar

### Probar la Conexión

Para verificar que la conexión a la base de datos funciona correctamente:

```bash
java -cp .;mysql-connector-j-9.4.0.jar TestConnection
```

### Ejecutar el Menú Principal

Para iniciar la aplicación principal:

```bash
java -cp .;mysql-connector-j-9.4.0.jar MenuPrincipal
```

## Estructura de Archivos

*   `MenuPrincipal.java`: Punto de entrada de la aplicación.
*   `TestConnection.java`: Script para probar la conectividad con la base de datos.
*   `CrudPacientes.java`: Lógica CRUD para pacientes.
*   `CrudEspecialistas.java`: Lógica CRUD para especialistas.
*   `mysql-connector-j-9.4.0.jar`: Driver JDBC para MySQL.
