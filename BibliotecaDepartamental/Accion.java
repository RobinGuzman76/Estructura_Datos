package BibliotecaDepartamental;

import java.util.Stack;

public class Accion {

    public interface UndoableAction {
        void accion();
        String getDescripcion();
    }

    private Stack<UndoableAction> pila;

    public Accion() {
        pila = new Stack<>();
    }

    public void registrarAccion(UndoableAction accion) {
        pila.push(accion);
    }

    public void deshacerUltimaAccion() {
        if (pila.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
        UndoableAction accion = pila.pop();
        try {
            accion.accion();
            System.out.println("Acción deshecha: " + accion.getDescripcion());
        } catch (Exception e) {
            System.out.println("Error al deshacer la acción: " + e.getMessage());
        }
    }

    public boolean hayAcciones() {
        return !pila.isEmpty();
    }
}
