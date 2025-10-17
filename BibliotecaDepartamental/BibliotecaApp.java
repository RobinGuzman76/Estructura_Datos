package BibliotecaDepartamental;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Libro> libros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

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
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1:
                    registrarLibro();  // Ya creado
                    break;
                case 2:
                    listarLibros();   // Ya creado
                    break;
                case 3:
                    System.out.println("Función 'Actualizar libro' pendiente por implementar.");
                    break;
                case 4:
                    System.out.println("Función 'Eliminar libro' pendiente por implementar.");
                    break;
                case 5:
                    System.out.println("Función 'Buscar por título' pendiente por implementar.");
                    break;
                case 6:
                    System.out.println("Función 'Buscar por autor' pendiente por implementar.");
                    break;
                case 7:
                    System.out.println("Función 'Cambiar disponibilidad' pendiente por implementar.");
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
    public static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n===== MÓDULO 2: GESTIÓN DE USUARIOS =====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar usuarios");
            System.out.println("3. Registrar préstamo");
            System.out.println("4. Mostrar historial de préstamos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1:
                    registrarUsuario();  // Ya creado
                    break;
                case 2:
                    listarUsuarios();   // Ya creado
                    break;
                case 3:
                    System.out.println("Función 'Registrar préstamo' pendiente por implementar.");
                    break;
                case 4:
                    System.out.println("Función 'Mostrar historial de préstamos' pendiente por implementar.");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ---------------- FUNCIONES EXISTENTES (quedan igual) ----------------

    public static void registrarLibro() {
        System.out.println("\n--- REGISTRO DE LIBRO ---");

        String isbn = leerSoloNumeros("Ingrese el ISBN del libro: ");
        String titulo = leerSoloLetras("Ingrese el título del libro: ");
        String autor = leerSoloLetras("Ingrese el autor del libro: ");

        System.out.print("Ingrese la categoría del libro: ");
        String categoria = sc.nextLine().trim();

        libros.add(new Libro(titulo, autor, isbn, categoria));
        System.out.println("✅ Libro registrado exitosamente.");
    }

    public static void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE LIBROS ---");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public static void registrarUsuario() {
        System.out.println("\n--- REGISTRO DE USUARIO ---");

        String identificacion = leerSoloNumeros("Ingrese la identificación del usuario: ");
        String nombre = leerSoloLetras("Ingrese el nombre del usuario: ");

        usuarios.add(new Usuario(nombre, identificacion));
        System.out.println("✅ Usuario registrado exitosamente.");
    }

    public static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (Usuario u : usuarios) {
            System.out.println("Nombre: " + u.getNombre() + " | Identificación: " + u.getIdentificacion());
        }
    }

    // ---------------- VALIDACIONES ----------------

    public static int leerOpcionNumerica() {
        String entrada = sc.nextLine().trim();
        while (!entrada.matches("\\d+")) {
            System.out.print("Error: ingrese solo números: ");
            entrada = sc.nextLine().trim();
        }
        return Integer.parseInt(entrada);
    }

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