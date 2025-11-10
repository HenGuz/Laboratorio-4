import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VistaCMS {
    private ControladorCMS controlador;
    private Scanner scanner;

    public VistaCMS(ControladorCMS controlador) {
        this.controlador = controlador;
        this.controlador.setVista(this);
        this.scanner = new Scanner(System.in);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: muestra el menu principal por consola
     */
    public void mostrarMenu() {
        System.out.println("=== CMS Simple (Consola) ===");
        System.out.println("1. Listar contenidos");
        System.out.println("2. Buscar por categoría");
        System.out.println("3. Buscar por tipo");
        System.out.println("4. Crear contenido (demo para admin/editor)");
        System.out.println("5. Generar reporte por categoría");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");

        String opcion = solicitarEntrada();
        switch (opcion) {
            case "1":
                mostrarLista(controlador.buscarPorTipo(Contenido.class));
                break;
            case "2":
                System.out.print("Ingrese categoría: ");
                String cat = solicitarEntrada();
                mostrarLista(controlador.buscarPorCategoria(cat));
                break;
            case "3":
                System.out.print("Ingrese tipo (Articulo/Video/Imagen): ");
                String t = solicitarEntrada();
                mostrarLista(controlador.buscarPorTipo(resolveTipo(t)));
                break;
            case "4":
                System.out.println("Creando contenido de ejemplo...");
                // en demo creamos un artículo por defecto con un admin de prueba
                Map<String, Object> datos = new HashMap<>();
                datos.put("id", controlador.buscarPorTipo(Contenido.class).size() + 1);
                datos.put("titulo", "Artículo demo");
                datos.put("texto", "Texto de ejemplo");
                datos.put("fechaPublicacion", "09/11/2025");
                Categoria c = new Categoria(1, "Educación", "Contenidos educativos");
                datos.put("categoria", c);
                // buscamos un admin
                Usuario u = controlador.buscarUsuario("admin");
                if (u != null) {
                    Contenido nuevo = u.crearContenido("Articulo", datos);
                    controlador.agregarContenido(nuevo);
                    System.out.println("Contenido creado por " + u.getNombre());
                } else {
                    System.out.println("No hay administrador de prueba (nombre 'admin') registrado.");
                }
                break;
            case "5":
                System.out.print("Ingrese categoría para reporte: ");
                String rc = solicitarEntrada();
                Reporte rp = controlador.crearReporte("categoria", rc);
                System.out.println("Reporte generado id=" + rp.getIdReporte() + " con " + rp.getResultado().size() + " items.");
                rp.exportarCSV("reporte_" + rp.getIdReporte() + ".csv");
                break;
            default:
                System.out.println("Saliendo...");
                break;
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String msg)
     * @return  void: muestra un mensaje por consola
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (List<Contenido> lista)
     * @return  void: muestra la lista de contenidos por consola
     */
    public void mostrarLista(List<Contenido> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("(No hay contenidos para mostrar)");
            return;
        }
        for (Contenido c : lista) {
            System.out.println("ID: " + c.getId() + " | Título: " + c.getTitulo() + " | Categoria: " + (c.getCategoria() != null ? c.getCategoria().getNombre() : "Sin cat") + " | Publicado: " + c.isPublicado());
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipo)
     * @return  Map<String,Object>: datos ingresados por usuario para crear contenido
     */
    public Map<String, Object> pedirDatosContenido(String tipo) {
        Map<String, Object> datos = new HashMap<>();
        System.out.print("Título: "); datos.put("titulo", solicitarEntrada());
        System.out.print("Fecha publicación: "); datos.put("fechaPublicacion", solicitarEntrada());
        return datos;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  String: linea leida desde consola
     */
    public String solicitarEntrada() {
        return scanner.nextLine();
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  Usuario: simula pedir login (no autentica en demo)
     */
    public Usuario solicitarLogin() {
        System.out.print("Usuario (nombre): ");
        String nombre = solicitarEntrada();
        System.out.print("Contraseña: ");
        String pass = solicitarEntrada();
        Usuario u = controlador.autenticarUsuario(nombre, pass);
        if (u == null) System.out.println("Credenciales inválidas.");
        return u;
    }

    private Class<?> resolveTipo(String t) {
        if ("Articulo".equalsIgnoreCase(t)) return Articulo.class;
        if ("Video".equalsIgnoreCase(t)) return Video.class;
        if ("Imagen".equalsIgnoreCase(t)) return Imagen.class;
        return Contenido.class;
    }
}
