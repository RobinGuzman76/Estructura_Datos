package Biblioteca;

import java.util.List;
import java.util.Scanner;

// Aplicación de consola que permite interactuar con la BibliotecaService
// Proporciona un menú para agregar, ver, buscar, editar y eliminar libros.
public class BibliotecaApp {

    // Scanner para leer la entrada del usuario desde la consola
    private static final Scanner scanner = new Scanner(System.in);
    // Servicio que gestiona los libros en memoria
    private static final BibliotecaService biblioteca = new BibliotecaService();

    // Punto de entrada de la aplicación
    public static void main(String[] args) {
        boolean salir = false;

        // Bucle principal que muestra el menú hasta que el usuario elija salir
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    // Agregar un nuevo libro
                    agregarLibro();
                    break;
                case 2:
                    // Mostrar todos los libros
                    mostrarLibros(biblioteca.obtenerLibros());
                    break;
                case 3:
                    // Buscar libros por título
                    buscarLibro();
                    break;
                case 4:
                    // Editar un libro existente
                    editarLibro();
                    break;
                case 5:
                    // Eliminar un libro
                    eliminarLibro();
                    break;
                case 6:
                    // Salir de la aplicación
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    // Muestra el menú principal en la consola
    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Biblioteca ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Ver libros");
        System.out.println("3. Buscar libro por título");
        System.out.println("4. Editar libro");
        System.out.println("5. Eliminar libro");
        System.out.println("6. Salir");
    }

    // Lee un entero de la consola, validando la entrada
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor ingresa un número válido: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer del scanner después de leer el número
        return num;
    }

    // Solicita los datos de un nuevo libro al usuario y lo agrega a la biblioteca
    private static void agregarLibro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("El título no puede estar vacío.");
            return;
        }

        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();
        if (autor.isEmpty()) {
            System.out.println("El autor no puede estar vacío.");
            return;
        }

        int anio = leerEntero("Año: ");

        Libro libro = new Libro(titulo, autor, anio);
        biblioteca.agregarLibro(libro);

        System.out.println("Libro agregado exitosamente.");
    }

    // Imprime en consola la lista de libros proporcionada
    private static void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            System.out.println("\n--- Lista de libros ---");
            for (int i = 0; i < libros.size(); i++) {
                // Se muestra el índice (1-based) y la representación del libro
                System.out.println((i + 1) + ". " + libros.get(i));
            }
        }
    }

    // Pide un texto y busca libros cuyo título contenga ese texto
    private static void buscarLibro() {
        System.out.print("Ingresa el título o parte del título a buscar: ");
        String texto = scanner.nextLine();

        List<Libro> resultados = biblioteca.buscarPorTitulo(texto);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("--- Resultados de búsqueda ---");
            mostrarLibros(resultados);
        }
    }

    // Permite editar los campos de un libro seleccionado por índice
    private static void editarLibro() {
        List<Libro> libros = biblioteca.obtenerLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros para editar.");
            return;
        }
        mostrarLibros(libros);
        int indice = leerEntero("Elige el número del libro que quieres editar: ") - 1;

        if (indice < 0 || indice >= libros.size()) {
            System.out.println("Número inválido.");
            return;
        }

        // Obtener el libro actual y preguntar por nuevos valores
        Libro libro = libros.get(indice);

        System.out.print("Nuevo título (actual: " + libro.getTitulo() + "): ");
        String nuevoTitulo = scanner.nextLine();
        if (!nuevoTitulo.isBlank()) {
            libro.setTitulo(nuevoTitulo);
        }

        System.out.print("Nuevo autor (actual: " + libro.getAutor() + "): ");
        String nuevoAutor = scanner.nextLine();
        if (!nuevoAutor.isBlank()) {
            libro.setAutor(nuevoAutor);
        }

        System.out.print("Nuevo año (actual: " + libro.getAnio() + "): ");
        String nuevoAnioStr = scanner.nextLine();
        if (!nuevoAnioStr.isBlank()) {
            try {
                int nuevoAnio = Integer.parseInt(nuevoAnioStr);
                libro.setAnio(nuevoAnio);
            } catch (NumberFormatException e) {
                System.out.println("Año inválido, no se cambió.");
            }
        }

        // Guardar cambios usando el servicio (reemplaza el libro en la lista)
        biblioteca.editarLibro(indice, libro);
        System.out.println("Libro actualizado.");
    }

    // Elimina un libro seleccionado por índice después de una confirmación
    private static void eliminarLibro() {
        List<Libro> libros = biblioteca.obtenerLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros para eliminar.");
            return;
        }
        mostrarLibros(libros);
        int indice = leerEntero("Elige el número del libro que quieres eliminar: ") - 1;

        if (indice < 0 || indice >= libros.size()) {
            System.out.println("Número inválido.");
            return;
        }

        System.out.print("¿Seguro que quieres eliminar este libro? (si/no) - (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            boolean ok = biblioteca.eliminarLibro(indice);
            if (ok) {
                System.out.println("Libro eliminado.");
            } else {
                System.out.println("No se pudo eliminar el libro.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
}