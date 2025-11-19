package BibliotecaDepartamental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase para gestionar usuarios y préstamos.
 *
 * Funcionalidades principales:
 * - agregar, actualizar, eliminar usuarios
 * - registrar préstamos y devoluciones
 * - mantener un historial de préstamos
 *
 * También registra acciones deshacer si se le pasa un objeto `Accion`.
 */
public class GestionUsuarios {
    // Lista de usuarios registrados
    private ArrayList<Usuario> listaUsuarios;
    // Historial de préstamos (lista enlazada para ejemplos sencillos)
    private LinkedList<Prestamo> historialPrestamos;
    // Scanner para leer desde consola
    private Scanner scanner;
    // Gestor de acciones para permitir deshacer operaciones
    private Accion acciones;

    /**
     * Constructor por defecto (sin soporte de deshacer).
     */
    public GestionUsuarios() {
        listaUsuarios = new ArrayList<>();
        historialPrestamos = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Constructor que acepta un gestor de acciones para registrar
     * operaciones que se puedan deshacer.
     *
     * @param acciones gestor de acciones
     */
    public GestionUsuarios(Accion acciones) {
        this.listaUsuarios = new ArrayList<>();
        this.historialPrestamos = new LinkedList<>();
        this.scanner = new Scanner(System.in);
        this.acciones = acciones;
    }

    /**
     * Registra un usuario pidiendo nombre, identificación y teléfono.
     * Al finalizar, opcionalmente registra la acción para poder deshacerla.
     */
    public void agregarUsuario() {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la identificación del usuario: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el teléfono del usuario: ");
        String telefono = scanner.nextLine();

        Usuario nuevo = new Usuario(nombre, id, telefono);
        listaUsuarios.add(nuevo);

        System.out.println("Usuario registrado correctamente.");

        // Registrar acción de deshacer: eliminar el usuario recién agregado
        if (acciones != null) {
            final String idGuardado = id;
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    Usuario u = buscarUsuarioPorId(idGuardado);
                    if (u != null) listaUsuarios.remove(u);
                }

                @Override
                public String getDescripcion() {
                    return "Agregar usuario (deshacer) ID: " + idGuardado;
                }
            });
        }
    }

    /**
     * Actualiza el nombre y/o teléfono de un usuario identificado por su ID.
     * Guarda el estado anterior para permitir deshacer la operación.
     */
    public void actualizarUsuario() {
        System.out.print("Ingrese la identificación del usuario a actualizar: ");
        String id = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("No se encontró ese usuario.");
            return;
        }

        final String viejoNombre = usuario.getNombre();
        final String viejoTelefono = usuario.getTelefono();

        System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) usuario.setNombre(nuevoNombre);

        System.out.print("Nuevo teléfono (dejar vacío para no cambiar): ");
        String nuevoTelefono = scanner.nextLine();
        if (!nuevoTelefono.isEmpty()) usuario.setTelefono(nuevoTelefono);

        System.out.println("Usuario actualizado correctamente.");

        // Registrar deshacer que restaura los valores anteriores
        if (acciones != null) {
            final Usuario uRef = usuario;
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    uRef.setNombre(viejoNombre);
                    uRef.setTelefono(viejoTelefono);
                }

                @Override
                public String getDescripcion() {
                    return "Actualizar usuario (deshacer) ID: " + uRef.getIdentificacion();
                }
            });
        }
    }

    /**
     * Elimina un usuario por su identificación.
     * Registra una acción de deshacer que vuelve a agregar al usuario eliminado.
     */
    public void eliminarUsuario() {
        System.out.print("Ingrese la identificación del usuario a eliminar: ");
        String id = scanner.nextLine();

        Usuario u = buscarUsuarioPorId(id);
        if (u == null) {
            System.out.println("No se encontró ese usuario.");
            return;
        }

        final Usuario usuarioEliminado = u;
        listaUsuarios.remove(u);
        System.out.println("Usuario eliminado correctamente.");

        if (acciones != null) {
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    listaUsuarios.add(usuarioEliminado);
                }

                @Override
                public String getDescripcion() {
                    return "Eliminar usuario (deshacer) ID: " + usuarioEliminado.getIdentificacion();
                }
            });
        }
    }

    /**
     * Muestra todos los usuarios registrados usando un Iterator.
     */
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

    /**
     * Busca un usuario por su identificación.
     *
     * @param idBuscado identificación a buscar
     * @return Usuario encontrado o null
     */
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

    /**
     * Registra un préstamo: verifica usuario, libro y disponibilidad.
     * Cambia el estado del libro a no disponible y guarda el préstamo en el historial.
     */
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
            System.out.println("No existe un libro registrado con ese ISBN.");
            return;
        }

        // Validar disponibilidad
        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible para préstamo.");
            return;
        }

        // Registrar préstamo
        Prestamo prestamo = new Prestamo(idUsuario, isbn);
        historialPrestamos.add(prestamo);

        // Cambiar disponibilidad del libro a prestado
        libro.setDisponible(false);

        System.out.println("Préstamo registrado correctamente.");

        // Registrar acción de deshacer: eliminar préstamo y marcar libro disponible
        if (acciones != null) {
            final Prestamo prestamoGuardado = prestamo;
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    historialPrestamos.remove(prestamoGuardado);
                    Libro l = gestionLibros.buscarLibroPorIsbn(prestamoGuardado.getIsbnLibro());
                    if (l != null) l.setDisponible(true);
                }

                @Override
                public String getDescripcion() {
                    return "Registrar préstamo (deshacer) Usuario ID: " + prestamoGuardado.getIdUsuario() + " ISBN: " + prestamoGuardado.getIsbnLibro();
                }
            });
        }
    }

    /**
     * Registra la devolución de un libro: busca el préstamo y lo elimina del historial.
     * Si el libro existe, se marca como disponible.
     */
    public void devolverPrestamo(GestionLibros gestionLibros) {
        System.out.print("Ingrese el ID del usuario que devuelve: ");
        String idUsuario = scanner.nextLine();

        System.out.print("Ingrese el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();

        Prestamo encontrado = null;
        for (Prestamo p : historialPrestamos) {
            if (p.getIdUsuario().equalsIgnoreCase(idUsuario) && p.getIsbnLibro().equalsIgnoreCase(isbn)) {
                encontrado = p;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("No se encontró un préstamo con esos datos.");
            return;
        }

        final Prestamo prestamoEliminado = encontrado;
        historialPrestamos.remove(encontrado);

        Libro libro = gestionLibros.buscarLibroPorIsbn(isbn);
        if (libro != null) libro.setDisponible(true);

        System.out.println("Devolución registrada correctamente.");

        // Registrar acción de deshacer: volver a agregar el préstamo y marcar libro como no disponible
        if (acciones != null) {
            acciones.registrarAccion(new Accion.DeshacerAction() {
                @Override
                public void accion() {
                    historialPrestamos.add(prestamoEliminado);
                    Libro l = gestionLibros.buscarLibroPorIsbn(prestamoEliminado.getIsbnLibro());
                    if (l != null) l.setDisponible(false);
                }

                @Override
                public String getDescripcion() {
                    return "Devolver préstamo (deshacer) Usuario ID: " + prestamoEliminado.getIdUsuario() + " ISBN: " + prestamoEliminado.getIsbnLibro();
                }
            });
        }
    }

    /**
     * Muestra el historial de préstamos por consola.
     */
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