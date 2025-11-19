package BibliotecaDepartamental;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de la gestión de los libros: agregar, mostrar,
 * actualizar, eliminar, buscar y cambiar disponibilidad.
 *
 * Incluye integración con la clase `Accion` para poder deshacer
 * operaciones transaccionales (agregar, actualizar, eliminar).
 */
public class GestionLibros {
    // Lista interna que almacena los libros registrados
    private ArrayList<Libro> listaLibros;
    // Scanner para leer entrada desde consola
    private Scanner scanner;
    // Gestor de acciones que permite deshacer operaciones
    private Accion acciones;

    /**
     * Constructor por defecto (sin soporte de deshacer).
     */
    public GestionLibros() {
        listaLibros = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Constructor que recibe un objeto `Accion` para habilitar
     * el registro de operaciones que se puedan deshacer.
     *
     * @param acciones gestor de acciones para deshacer
     */
    public GestionLibros(Accion acciones) {
        this.listaLibros = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.acciones = acciones;
    }

    /**
     * Agrega un libro pidiendo datos por consola.
     * Registra una acción de deshacer que elimina el libro agregado.
     */
    public void agregarLibro() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();

        // Crear el libro con los datos ingresados
        Libro nuevoLibro = new Libro(titulo, autor, isbn);
        listaLibros.add(nuevoLibro);
        System.out.println("Libro agregado correctamente.");

        // Si hay un gestor de acciones, registrar la operación para poder deshacerla
        if (acciones != null) {
            final String isbnGuardado = isbn;
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    // Al deshacer, buscar el libro por ISBN y eliminarlo
                    Libro l = buscarLibroPorIsbn(isbnGuardado);
                    if (l != null) listaLibros.remove(l);
                }

                @Override
                public String getDescripcion() {
                    return "Agregar libro (deshacer) ISBN: " + isbnGuardado;
                }
            });
        }
    }

    /**
     * Muestra por consola todos los libros registrados.
     */
    public void mostrarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        for (Libro libro : listaLibros) {
            System.out.println(libro);
        }
    }

    /**
     * Actualiza los datos (título y autor) de un libro identificado por ISBN.
     * Guarda el estado anterior para permitir deshacer la actualización.
     */
    public void actualizarLibro() {
        System.out.print("Ingrese el ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();

        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró un libro con ese ISBN.");
            return;
        }

        // Guardar estado previo para permitir deshacer
        final String viejoTitulo = libro.getTitulo();
        final String viejoAutor = libro.getAutor();

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

        System.out.println("Libro actualizado correctamente.");

        // Registrar acción de deshacer que restaura los valores anteriores
        if (acciones != null) {
            final Libro libroRef = libro;
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    libroRef.setTitulo(viejoTitulo);
                    libroRef.setAutor(viejoAutor);
                }

                @Override
                public String getDescripcion() {
                    return "Actualizar libro (deshacer) ISBN: " + libroRef.getIsbn();
                }
            });
        }
    }

    /**
     * Elimina un libro por ISBN. Registra la operación para poder restaurarlo
     * si se solicita deshacer.
     */
    public void eliminarLibro() {
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();

        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró un libro con ese ISBN.");
            return;
        }

        // Guardar referencia para poder restaurarlo
        final Libro libroEliminado = libro;
        listaLibros.remove(libro);
        System.out.println("Libro eliminado correctamente.");

        if (acciones != null) {
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    // Al deshacer, volver a agregar el libro eliminado
                    listaLibros.add(libroEliminado);
                }

                @Override
                public String getDescripcion() {
                    return "Eliminar libro (deshacer) ISBN: " + libroEliminado.getIsbn();
                }
            });
        }
    }

    /**
     * Buscar y mostrar libros por título (coincidencia exacta, case-insensitive).
     */
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

    /**
     * Buscar y mostrar libros por autor (coincidencia exacta, case-insensitive).
     */
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

    /**
     * Búsqueda interna por ISBN. Devuelve el libro o null si no existe.
     * Método privado para uso dentro de esta clase.
     *
     * @param isbn ISBN a buscar
     * @return Libro encontrado o null
     */
    private Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Versión pública de búsqueda por ISBN (útil para otras clases).
     *
     * @param isbnBuscado ISBN a buscar
     * @return Libro encontrado o null
     */
    public Libro buscarLibroPorIsbn(String isbnBuscado) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbnBuscado)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Cambia el estado de disponibilidad de un libro (disponible <-> prestado).
     * Solicita el ISBN por consola y actualiza el libro si existe.
     */
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