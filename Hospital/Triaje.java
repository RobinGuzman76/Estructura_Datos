package Hospital;

public class Triaje {
    private String nombrePaciente;
    private String sintomasPaciente;
    private String prioridadAtencion;
    private String fechaHoraIngreso;

    // Constructor crea un nuevo Triaje con los detalles del paciente
    public Triaje(String nombrePaciente, String sintomasPaciente, String prioridadAtencion, String fechaHoraIngreso) {
        this.nombrePaciente = nombrePaciente;
        this.sintomasPaciente = sintomasPaciente;
        this.prioridadAtencion = prioridadAtencion;
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    // Getters y Setters para acceder y modificar los atributos de forma controlada
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getSintomasPaciente() {
        return sintomasPaciente;
    }

    public void setSintomasPaciente(String sintomasPaciente) {
        this.sintomasPaciente = sintomasPaciente;
    }

    public String getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(String prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public String getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(String fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }


    // toString: representación legible del objeto Triaje para impresión en consola
    public String toString() {
        return "Nombre del Paciente: " + nombrePaciente + ", Síntomas: " + sintomasPaciente +
               ", Prioridad de Atención: " + prioridadAtencion + ", Fecha y Hora de Ingreso: " + fechaHoraIngreso;
    }
}
