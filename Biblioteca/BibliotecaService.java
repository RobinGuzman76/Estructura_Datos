package Biblioteca;

import java.util.ArrayList;
import java.util.List;

// Servicio que gestiona la colección de libros en memoria.
// Proporciona operaciones para agregar, listar, buscar, editar y eliminar libros.
public class BibliotecaService {

    // Lista interna que almacena los objetos Libro
    private final List<Libro> libros;

    // Constructor: inicializa la lista y añade algunos libros por defecto
    public BibliotecaService() {
        this.libros = new ArrayList<>();
        // Libros por defecto para tener datos de ejemplo al iniciar la aplicación
        this.libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967));
        this.libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605));
        this.libros.add(new Libro("La ciudad y los perros", "Mario Vargas Llosa", 1963));
        this.libros.add(new Libro("Ficciones", "Jorge Luis Borges", 1944));
        this.libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", 2001));
    }

    // Agrega un libro a la colección
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Devuelve la lista completa de libros (por referencia)
    public List<Libro> obtenerLibros() {
        return libros;
    }

    // Busca libros cuyo título contiene el texto proporcionado (búsqueda no sensible a mayúsculas)
    public List<Libro> buscarPorTitulo(String texto) {
        List<Libro> resultados = new ArrayList<>();
        if (texto == null) {
            // Si el texto es null, devolver lista vacía
            return resultados;
        }
        // Normalizar el texto de búsqueda: eliminar espacios al inicio/fin y pasar a minúsculas
        String textoMinus = texto.trim().toLowerCase();
        if (textoMinus.isEmpty()) {
            // Si la búsqueda está vacía, devolver todos los libros (comportamiento explícito)
            resultados.addAll(libros);
            return resultados;
        }
        // Recorrer todos los libros y añadir los que coincidan en el título
        for (Libro libro : libros) {
            if (libro.getTitulo() != null && libro.getTitulo().toLowerCase().contains(textoMinus)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // Edita el libro en la posición indicada. Devuelve true si el índice es válido y la edición se realiza.
    public boolean editarLibro(int indice, Libro libroActualizado) {
        if (indice >= 0 && indice < libros.size()) {
            libros.set(indice, libroActualizado);
            return true;
        }
        return false; // índice inválido
    }

    // Elimina el libro en la posición indicada. Devuelve true si la eliminación tuvo éxito.
    public boolean eliminarLibro(int indice) {
        if (indice >= 0 && indice < libros.size()) {
            libros.remove(indice);
            return true;
        }
        return false; // índice inválido
    }
}