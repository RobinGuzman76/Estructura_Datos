package BibliotecaDepartamental;

import java.util.Stack;

/**
 * Gestor sencillo de acciones "deshacer".
 *
 * Mantiene una pila (LIFO) de acciones que implementan la interfaz
 * `DeshacerAction`. Cada acción debe saber cómo deshacerse a sí misma
 * mediante el método `accion()` y también proporcionar una descripción
 * legible para el usuario.
 */
public class Accion {

    /**
     * Interfaz que define una acción que se puede deshacer.
     * - `accion()` ejecuta la lógica para deshacer
     * - `getDescripcion()` devuelve un texto que describe la acción
     */
    public interface DeshacerAction {
        void accion();
        String getDescripcion();
    }

    // Pila que guarda las acciones en el orden en que se pueden deshacer
    private Stack<DeshacerAction> pila;

    /**
     * Constructor: inicializa la pila.
     */
    public Accion() {
        pila = new Stack<>();
    }

    /**
     * Registra una acción para poder deshacerla más tarde.
     *
     * @param accion acción que implementa DeshacerAction
     */
    public void registrarAccion(DeshacerAction accion) {
        pila.push(accion);
    }

    /**
     * Deshace la última acción registrada (si existe).
     * Extrae la acción de la pila y llama a su método `accion()`.
     */
    public void deshacerUltimaAccion() {
        if (pila.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
        DeshacerAction accion = pila.pop();
        try {
            accion.accion();
            System.out.println("Acción deshecha: " + accion.getDescripcion());
        } catch (Exception e) {
            // Capturar errores al deshacer para no detener la aplicación
            System.out.println("Error al deshacer la acción: " + e.getMessage());
        }
    }

    /**
     * Indica si hay acciones disponibles para deshacer.
     *
     * @return true si la pila no está vacía
     */
    public boolean hayAcciones() {
        return !pila.isEmpty();
    }
}
