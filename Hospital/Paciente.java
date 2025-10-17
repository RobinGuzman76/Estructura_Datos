package Hospital;

public class Paciente {
    private String nombre;
    private int edad;
    private String genero;
    private String direccion;
    private String telefono;

    // Constructor: crea un nuevo Paciente con nombre, edad, género, dirección y teléfono
    public Paciente(String nombre, int edad, String genero, String direccion, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y setters: permiten acceder y modificar los atributos de forma controlada
    // Devuelve el nombre del paciente
    public String getNombre() {
        return nombre;
    }

    // Actualiza el nombre del paciente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve la edad del paciente
    public int getEdad() {
        return edad;
    }

    // Actualiza la edad del paciente
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Devuelve el género del paciente
    public String getGenero() {
        return genero;
    }

    // Actualiza el género del paciente
    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Devuelve la dirección del paciente
    public String getDireccion() {
        return direccion;
    }

    // Actualiza la dirección del paciente
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Devuelve el teléfono del paciente
    public String getTelefono() {
        return telefono;
    }

    // Actualiza el teléfono del paciente
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // toString: representación legible del objeto Paciente para impresión en consola
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Género: " + genero +
               ", Dirección: " + direccion + ", Teléfono: " + telefono;
    }
    
}
