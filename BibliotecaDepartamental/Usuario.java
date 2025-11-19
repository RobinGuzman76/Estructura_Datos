package BibliotecaDepartamental;

/**
 * Representa un usuario del sistema de la biblioteca.
 * Contiene información básica necesaria para la gestión y contacto.
 */
public class Usuario {
    // Nombre completo del usuario
    private String nombre;
    // Identificación única (cedula, pasaporte, etc.) como texto
    private String identificacion;
    // Teléfono de contacto
    private String telefono;

    /**
     * Constructor principal del usuario.
     *
     * @param nombre       Nombre completo
     * @param identificacion Identificación (ID)
     * @param telefono     Teléfono de contacto
     */
    public Usuario(String nombre, String identificacion, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    // -------- Getters (acceder a los datos) --------
    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    // -------- Setters (modificar los datos) --------
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Convierte el usuario a una cadena para mostrarlo en listas.
     * Muestra nombre, identificación y teléfono.
     */
    public String toString() {
        return "Nombre: " + nombre + " | ID: " + identificacion + " | Teléfono: " + telefono;
    }
}
