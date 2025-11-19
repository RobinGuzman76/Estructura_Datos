package BibliotecaDepartamental;

/**
 * Clase simple que representa un préstamo de un libro a un usuario.
 * Contiene la identificación del usuario y el ISBN del libro prestado.
 */
public class Prestamo {
    // ID del usuario que realiza el préstamo
    private String idUsuario;
    // ISBN del libro prestado
    private String isbnLibro;

    /**
     * Constructor del préstamo.
     *
     * @param idUsuario ID del usuario
     * @param isbnLibro ISBN del libro
     */
    public Prestamo(String idUsuario, String isbnLibro) {
        this.idUsuario = idUsuario;
        this.isbnLibro = isbnLibro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    /**
     * Representación en texto del préstamo: muestra usuario y ISBN.
     */
    public String toString() {
        return "Usuario ID: " + idUsuario + " | Libro ISBN: " + isbnLibro;
    }
}
