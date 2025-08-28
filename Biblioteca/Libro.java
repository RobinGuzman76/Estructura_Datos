package Biblioteca;

// Clase que representa un libro con los campos básicos: título, autor y año
public class Libro {
    // Atributos privados que almacenan la información del libro
    private String titulo;
    private String autor;
    private int anio;

    // Constructor: crea un nuevo Libro con título, autor y año
    public Libro(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    // Getters y setters: permiten acceder y modificar los atributos de forma controlada
    // Devuelve el título del libro
    public String getTitulo() {
        return titulo;
    }

    // Actualiza el título del libro
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Devuelve el autor del libro
    public String getAutor() {
        return autor;
    }

    // Actualiza el autor del libro
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Devuelve el año de publicación
    public int getAnio() {
        return anio;
    }

    // Actualiza el año de publicación
    public void setAnio(int anio) {
        this.anio = anio;
    }

    // toString: representación legible del objeto Libro para impresión en consola
    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Año: " + anio;
    }
}