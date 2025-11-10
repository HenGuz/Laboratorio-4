import java.util.ArrayList;
import java.util.List;

public class Buscador {
    private java.util.List<Contenido> contenidosRef;

    public Buscador() {
        this.contenidosRef = new ArrayList<>();
    }

    public void actualizarReferencia(java.util.List<Contenido> lista) {
        this.contenidosRef = lista;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String titulo)
     * @return  List<Contenido>: contenidos cuyo titulo contiene la cadena
     */
    public List<Contenido> buscarPorTitulo(String titulo) {
        List<Contenido> res = new ArrayList<>();
        if (titulo == null || contenidosRef == null) return res;
        for (Contenido c : contenidosRef) {
            if (c.getTitulo() != null && c.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String categoria)
     * @return  List<Contenido>: contenidos que pertenecen a la categoría dada
     */
    public List<Contenido> filtrarPorCategoria(String categoria) {
        List<Contenido> res = new ArrayList<>();
        if (categoria == null || contenidosRef == null) return res;
        for (Contenido c : contenidosRef) {
            if (c.getCategoria() != null && categoria.equalsIgnoreCase(c.getCategoria().getNombre())) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Class tipo)
     * @return  List<Contenido>: contenidos del tipo indicado
     */
    public List<Contenido> filtrarPorTipo(Class<?> tipo) {
        List<Contenido> res = new ArrayList<>();
        if (tipo == null || contenidosRef == null) return res;
        for (Contenido c : contenidosRef) {
            if (tipo.isInstance(c)) res.add(c);
        }
        return res;
    }
}
