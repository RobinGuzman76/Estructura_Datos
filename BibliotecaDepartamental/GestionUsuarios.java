package BibliotecaDepartamental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

public class GestionUsuarios {
    private ArrayList<Usuario> listaUsuarios;
    private LinkedList<Prestamo> historialPrestamos;
    private Scanner scanner;

    public GestionUsuarios() {
        listaUsuarios = new ArrayList<>();
        historialPrestamos = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    // Registrar usuario
    public void agregarUsuario() {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la identificación del usuario: ");
        String id = scanner.nextLine();

        Usuario nuevo = new Usuario(nombre, id);
        listaUsuarios.add(nuevo);

        System.out.println("✔ Usuario registrado correctamente.");
    }

    // Mostrar usuarios con Iterator
    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        Iterator<Usuario> it = listaUsuarios.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // Buscar usuario con Iterator
    public Usuario buscarUsuarioPorId(String idBuscado) {
        Iterator<Usuario> it = listaUsuarios.iterator();
        while (it.hasNext()) {
            Usuario u = it.next();
            if (u.getIdentificacion().equalsIgnoreCase(idBuscado)) {
                return u;
            }
        }
        return null;
    }

    public void registrarPrestamo(GestionLibros gestionLibros) {
    System.out.print("Ingrese el ID del usuario: ");
    String idUsuario = scanner.nextLine();

    Usuario usuario = buscarUsuarioPorId(idUsuario);
    if (usuario == null) {
        System.out.println("No se encontró ese usuario.");
        return;
    }

    System.out.print("Ingrese el ISBN del libro prestado: ");
    String isbn = scanner.nextLine();

    // Validar que el libro sí existe
    Libro libro = gestionLibros.buscarLibroPorIsbn(isbn);
    if (libro == null) {
        System.out.println("❌ No existe un libro registrado con ese ISBN.");
        return;
    }

    // Validar disponibilidad
    if (!libro.isDisponible()) {
        System.out.println("❌ El libro no está disponible para préstamo.");
        return;
    }

    // Registrar préstamo
    Prestamo prestamo = new Prestamo(idUsuario, isbn);
    historialPrestamos.add(prestamo);

    // Cambiar disponibilidad del libro
    libro.setDisponible(false);

    System.out.println("✔ Préstamo registrado correctamente.");
}
    // Mostrar historial de préstamos
    public void mostrarHistorialPrestamos() {
        if (historialPrestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        Iterator<Prestamo> it = historialPrestamos.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}