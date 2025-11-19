package BibliotecaDepartamental;

/**
 * Clase que representa un libro en la biblioteca.
 *
 * Esta clase almacena los datos básicos de un libro:
 * - título
 * - autor
 * - ISBN
 * - disponibilidad (si está prestado o no)
 *
 * Se eliminó la categoría para mantener el ejemplo simple y centrado en lo esencial.
 */
public class Libro {
    // Atributos del libro: datos privados accesibles mediante getters/setters.
    private String titulo;      // Título del libro
    private String autor;       // Autor del libro
    private String isbn;        // Identificador único del libro
    private boolean disponible; // true = disponible, false = prestado

    /**
     * Constructor: crea un libro nuevo, por defecto disponible.
     *
     * @param titulo Título del libro
     * @param autor  Autor del libro
     * @param isbn   Código ISBN (identificador)
     */
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        // Los libros nuevos se consideran disponibles por defecto.
        this.disponible = true;
    }

    // -------- Getters y setters --------
    // Se usan para acceder y modificar los atributos de forma controlada.

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Indica si el libro está disponible para préstamo.
     *
     * @return true si está disponible, false si está prestado
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Cambia el estado de disponibilidad del libro.
     *
     * @param disponible true = disponible, false = prestado
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Representación en texto del libro. Útil para mostrar en pantalla.
     *
     * @return línea con los datos principales del libro
     */
    public String toString() {
        return "Título: " + titulo +
               " | Autor: " + autor +
               " | ISBN: " + isbn +
               " | Disponible: " + (disponible ? "Sí" : "No");
    }
}