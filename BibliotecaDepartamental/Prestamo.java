package BibliotecaDepartamental;

public class Prestamo {
    private String idUsuario;
    private String isbnLibro;

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

    @Override
    public String toString() {
        return "Usuario ID: " + idUsuario + " | Libro ISBN: " + isbnLibro;
    }
}
