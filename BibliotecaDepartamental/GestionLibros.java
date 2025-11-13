package BibliotecaDepartamental;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionLibros {
    private ArrayList<Libro> listaLibros;
    private Scanner scanner;

    public GestionLibros() {
        listaLibros = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Crear libro (Agregar)
    public void agregarLibro() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();

        System.out.print("Ingrese la categoría del libro: ");
        String categoria = scanner.nextLine();

        Libro nuevoLibro = new Libro(titulo, autor, isbn, categoria);
        listaLibros.add(nuevoLibro);
        System.out.println("Libro agregado correctamente.");
    }

    // Leer (Mostrar todos los libros)
    public void mostrarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        for (Libro libro : listaLibros) {
            System.out.println(libro);
        }
    }

    // Actualizar libro
    public void actualizarLibro() {
        System.out.print("Ingrese el ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();

        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró un libro con ese ISBN.");
            return;
        }

        System.out.print("Nuevo título (dejar vacío para no cambiar): ");
        String nuevoTitulo = scanner.nextLine();
        if (!nuevoTitulo.isEmpty()) {
            libro.setTitulo(nuevoTitulo);
        }

        System.out.print("Nuevo autor (dejar vacío para no cambiar): ");
        String nuevoAutor = scanner.nextLine();
        if (!nuevoAutor.isEmpty()) {
            libro.setAutor(nuevoAutor);
        }

        System.out.print("Nueva categoría (dejar vacío para no cambiar): ");
        String nuevaCategoria = scanner.nextLine();
        if (!nuevaCategoria.isEmpty()) {
            libro.setCategoria(nuevaCategoria);
        }

        System.out.println("Libro actualizado correctamente.");
    }

    // Eliminar libro
    public void eliminarLibro() {
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();

        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró un libro con ese ISBN.");
            return;
        }

        listaLibros.remove(libro);
        System.out.println("Libro eliminado correctamente.");
    }

    // Buscar por título
    public void buscarPorTitulo() {
        System.out.print("Ingrese el título a buscar: ");
        String titulo = scanner.nextLine();

        boolean encontrado = false;
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(libro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún libro con ese título.");
        }
    }

    // Buscar por autor
    public void buscarPorAutor() {
        System.out.print("Ingrese el autor a buscar: ");
        String autor = scanner.nextLine();

        boolean encontrado = false;
        for (Libro libro : listaLibros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                System.out.println(libro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún libro de ese autor.");
        }
    }

    // Buscar por ISBN (uso interno)
    private Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarLibroPorIsbn(String isbnBuscado) {
    for (Libro libro : listaLibros) {
        if (libro.getIsbn().equalsIgnoreCase(isbnBuscado)) {
            return libro;
        }
    }
    return null;
}

    // Control de disponibilidad
    public void cambiarDisponibilidad() {
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();

        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró el libro.");
            return;
        }

        libro.setDisponible(!libro.isDisponible());
        System.out.println("Disponibilidad actualizada: " +
                (libro.isDisponible() ? "Disponible" : "No disponible"));
    }
}