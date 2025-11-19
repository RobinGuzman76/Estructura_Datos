package BibliotecaDepartamental;

import java.util.Scanner;

/**
 * Aplicación principal de la biblioteca.
 * Contiene menús para la gestión de libros y usuarios y funciones de
 * validación de entrada para facilitar la experiencia por consola.
 */
public class BibliotecaApp {

    // Scanner compartido para leer entrada desde consola en toda la app
    private static Scanner sc = new Scanner(System.in);
    // Gestor de acciones para poder deshacer operaciones transaccionales
    private static Accion acciones = new Accion();
    // Instancias de los gestores de dominio (libros y usuarios)
    private static GestionLibros gestionLibros = new GestionLibros(acciones);
    private static GestionUsuarios gestionUsuarios = new GestionUsuarios(acciones);

    /**
     * Punto de entrada de la aplicación: muestra el menú principal.
     */
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de libros");
            System.out.println("2. Gestión de usuarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1:
                    menuLibros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // ---------------- MENÚ DE LIBROS ----------------
    /**
     * Muestra el menú de gestión de libros y delega operaciones a GestionLibros.
     */
    public static void menuLibros() {
        int opcion;
        do {
            System.out.println("\n===== MÓDULO 1: GESTIÓN DE LIBROS =====");
            System.out.println("1. Agregar libro");
            System.out.println("2. Mostrar todos los libros");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Buscar por título");
            System.out.println("6. Buscar por autor");
            System.out.println("7. Cambiar disponibilidad por ISBN");
            System.out.println("8. Deshacer última acción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1:
                    gestionLibros.agregarLibro();
                    break;
                case 2:
                    gestionLibros.mostrarLibros();
                    break;
                case 3:
                    gestionLibros.actualizarLibro();
                    break;
                case 4:
                    gestionLibros.eliminarLibro();
                    break;
                case 5:
                    gestionLibros.buscarPorTitulo();
                    break;
                case 6:
                    gestionLibros.buscarPorAutor();
                    break;
                case 7:
                    gestionLibros.cambiarDisponibilidad();
                    break;
                case 8:
                    acciones.deshacerUltimaAccion();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ---------------- MENÚ DE USUARIOS ----------------
    /**
     * Muestra el menú de gestión de usuarios y delega operaciones a GestionUsuarios.
     */
    public static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n===== MÓDULO 2: GESTIÓN DE USUARIOS =====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar usuarios");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Registrar préstamo");
            System.out.println("6. Devolver libro");
            System.out.println("7. Mostrar historial de préstamos");
            System.out.println("8. Deshacer última acción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1:
                    gestionUsuarios.agregarUsuario();
                    break;
                case 2:
                    gestionUsuarios.mostrarUsuarios();
                    break;
                case 3:
                    gestionUsuarios.actualizarUsuario();
                    break;
                case 4:
                    gestionUsuarios.eliminarUsuario();
                    break;
                case 5:
                    gestionUsuarios.registrarPrestamo(gestionLibros);
                    break;
                case 6:
                    gestionUsuarios.devolverPrestamo(gestionLibros);
                    break;
                case 7:
                    gestionUsuarios.mostrarHistorialPrestamos();
                    break;
                case 8:
                    acciones.deshacerUltimaAccion();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ---------------- VALIDACIONES ----------------

    /**
     * Lee una opción numérica del usuario. Rechaza entradas no numéricas.
     *
     * @return número entero representando la opción seleccionada
     */
    public static int leerOpcionNumerica() {
        String entrada = sc.nextLine().trim();
        while (!entrada.matches("\\d+")) {
            System.out.print("Error: ingrese solo números: ");
            entrada = sc.nextLine().trim();
        }
        return Integer.parseInt(entrada);
    }

    /**
     * Lee una cadena que contenga solo números. Repite hasta recibir
     * una entrada válida.
     *
     * @param mensaje texto que se muestra antes de la lectura
     * @return cadena de dígitos
     */
    public static String leerSoloNumeros(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            if (!entrada.matches("\\d+")) {
                System.out.println("Error: solo se permiten números.");
                entrada = null;
            }
        } while (entrada == null || entrada.isEmpty());
        return entrada;
    }

    /**
     * Lee una cadena que contenga solo letras (y espacios). Repite hasta
     * recibir una entrada válida.
     *
     * @param mensaje texto que se muestra antes de la lectura
     * @return cadena con solo letras y espacios
     */
    public static String leerSoloLetras(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            if (!entrada.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+")) {
                System.out.println("Error: solo se permiten letras.");
                entrada = null;
            }
        } while (entrada == null || entrada.isEmpty());
        return entrada;
    }
}