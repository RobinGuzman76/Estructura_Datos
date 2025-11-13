package BibliotecaDeRobin;

public class Libros {
    // Atributos
    private int ID;
    private String Titulo;
    private String Autor;
    private int Año;
    private String Isbn;

    // Método Getters
    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public int getAño() {
        return Año;
    }

    public String getIsbn() {
        return Isbn;
    }

    // Método Setters
    public void setID(int id) {
        this.ID = id;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

    public void setAño(int año) {
        this.Año = año;
    }

    public void setIsbn(String isbn) {
        this.Isbn = isbn;
    }

    // Constructor
    public Libros(int id, String titulo, String autor, int año, String isbn) {
        this.ID = id;
        this.Titulo = titulo;
        this.Autor = autor;
        this.Año = año;
        this.Isbn = isbn;
    }

    public String toString() {
        return "ID: " + ID + " | Título: " + Titulo + " | Autor: " + Autor + " | Año: " + Año + " | ISBN: " + Isbn;
    }
}