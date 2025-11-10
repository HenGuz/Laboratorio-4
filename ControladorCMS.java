import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorCMS {
    private ArrayList<Contenido> listaContenidos;
    private ArrayList<Usuario> listaUsuarios;
    private Buscador buscador;
    private Reporte reporte;
    private VistaCMS vista;

    public ControladorCMS() {
        this.listaContenidos = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.buscador = new Buscador();
        this.reporte = new Reporte();
    }

    public void setVista(VistaCMS v) { this.vista = v; }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Contenido c)
     * @return  void: agrega contenido a la lista y actualiza buscador
     */
    public void agregarContenido(Contenido c) {
        if (c == null) return;
        listaContenidos.add(c);
        buscador.actualizarReferencia(listaContenidos);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String titulo)
     * @return  boolean: true si se eliminó algún contenido
     */
    public boolean eliminarContenidoPorTitulo(String titulo) {
        Contenido encontrado = null;
        for (Contenido c : listaContenidos) {
            if (c.getTitulo() != null && c.getTitulo().equalsIgnoreCase(titulo)) {
                encontrado = c;
                break;
            }
        }
        if (encontrado != null) {
            if (encontrado instanceof Editable) {
                ((Editable) encontrado).eliminar();
            }
            listaContenidos.remove(encontrado);
            buscador.actualizarReferencia(listaContenidos);
            return true;
        }
        return false;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String categoria)
     * @return  List<Contenido>: lista filtrada por categoria
     */
    public List<Contenido> buscarPorCategoria(String categoria) {
        return buscador.filtrarPorCategoria(categoria);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Class tipo)
     * @return  List<Contenido>: lista filtrada por tipo de clase
     */
    public List<Contenido> buscarPorTipo(Class<?> tipo) {
        return buscador.filtrarPorTipo(tipo);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String nombre)
     * @return  Usuario: usuario encontrado o null
     */
    public Usuario buscarUsuario(String nombre) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre() != null && u.getNombre().equalsIgnoreCase(nombre)) return u;
        }
        return null;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Usuario u)
     * @return  void: agrega usuario a la lista
     */
    public void agregarUsuario(Usuario u) {
        if (u == null) return;
        listaUsuarios.add(u);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String nombre, String password)
     * @return  Usuario: el usuario autenticado o null
     */
    public Usuario autenticarUsuario(String nombre, String password) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre() != null && u.getNombre().equalsIgnoreCase(nombre)) {
                if (u.autenticar(password)) return u;
            }
        }
        return null;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: muestra todos los contenidos por consola (usa vista si existe)
     */
    public void mostrarTodo() {
        if (vista != null) {
            vista.mostrarLista(listaContenidos);
            return;
        }
        for (Contenido c : listaContenidos) {
            c.visualizar();
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String titulo)
     * @return  Contenido: contenido encontrado por titulo o null
     */
    public Contenido obtenerContenidoPorTitulo(String titulo) {
        for (Contenido c : listaContenidos) {
            if (c.getTitulo() != null && c.getTitulo().equalsIgnoreCase(titulo)) return c;
        }
        return null;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipo, String criterio)
     * @return  Reporte: crea y devuelve un reporte con resultados
     */
    public Reporte crearReporte(String tipo, String criterio) {
        Reporte r = new Reporte();
        if ("tipo".equalsIgnoreCase(tipo)) {
            r = r.generarPorTipo(criterio);
            // llenar resultado
            ArrayList<Contenido> res = new ArrayList<>((java.util.Collection<? extends Contenido>) buscarPorTipo(resolveTipo(criterio)));
            r.setResultado(res);
        } else if ("categoria".equalsIgnoreCase(tipo)) {
            r = r.generarPorCategoria(criterio);
            ArrayList<Contenido> res = new ArrayList<>(buscarPorCategoria(criterio));
            r.setResultado(res);
        }
        r.guardar();
        return r;
    }

    // ayudita: resuelve nombre de tipo a la clase
    private Class<?> resolveTipo(String criterio) {
        if ("Articulo".equalsIgnoreCase(criterio)) return Articulo.class;
        if ("Video".equalsIgnoreCase(criterio)) return Video.class;
        if ("Imagen".equalsIgnoreCase(criterio)) return Imagen.class;
        return Contenido.class;
    }
}
