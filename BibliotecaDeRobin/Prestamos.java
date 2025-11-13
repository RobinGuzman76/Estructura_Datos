package BibliotecaDeRobin;

public class Prestamos {
    private int ID;
    private int ID_Libro;
    private int ID_Usuario;
    private String Fecha_Prestamo;
    private String Fecha_Devolucion;
    private String Estado;

    // Método Getters
    public int getID() {
        return ID;
    }

    public int getID_Libro() {
        return ID_Libro;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public String getFecha_Prestamo() {
        return Fecha_Prestamo;
    }

    public String getFecha_Devolucion() {
        return Fecha_Devolucion;
    }

    public String getEstado() {
        return Estado;
    }

    // Método Setters
    public void setID(int id) {
        this.ID = id;
    }

    public void setID_Libro(int id_libro) {
        this.ID_Libro = id_libro;
    }

    public void setID_Usuario(int id_usuario) {
        this.ID_Usuario = id_usuario;
    }

    public void setFecha_Prestamo(String fecha_prestamo) {
        this.Fecha_Prestamo = fecha_prestamo;
    }

    public void setFecha_Devolucion(String fecha_devolucion) {
        this.Fecha_Devolucion = fecha_devolucion;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    // Constructor
    public Prestamos(int id, int id_libro, int id_usuario, String fecha_prestamo, String fecha_devolucion, String estado) {
        this.ID = id;
        this.ID_Libro = id_libro;
        this.ID_Usuario = id_usuario;
        this.Fecha_Prestamo = fecha_prestamo;
        this.Fecha_Devolucion = fecha_devolucion;
        this.Estado = estado;
    }

    public String toString() {
        return "ID Prestamo: " + ID +
               " | ID Libro: " + ID_Libro +
               " | ID Usuario: " + ID_Usuario +
               " | Fecha Prestamo: " + Fecha_Prestamo +
               " | Fecha Devolucion: " + Fecha_Devolucion +
               " | Estado: " + Estado;
    }
}

    
