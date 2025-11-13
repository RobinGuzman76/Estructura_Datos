package BibliotecaDeRobin;

public class Usuarios {
    private int ID;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Telefono;

    // Método Getters
    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getEmail() {
        return Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    // Método Setters
    public void setID(int id) {
        this.ID = id;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    // Constructor
    public Usuarios(int id, String nombre, String apellido, String email, String telefono) {
        this.ID = id;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Email = email;
        this.Telefono = telefono;
    }

    public String toString() {
        return "ID: " + ID + ", Nombre: " + Nombre + ", Apellido: " + Apellido +
               ", Email: " + Email + ", Teléfono: " + Telefono;
    }
}
