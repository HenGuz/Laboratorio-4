import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Editor extends Usuario {

    private boolean permisoEditar;

    public Editor() {
        this.tipo = "Editor";
        this.permisoEditar = true;
    }

    public Editor(int idUsuario, String nombre, String email, String password) {
        super(idUsuario, nombre, email, password, "Editor");
        this.permisoEditar = true;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipoContenido, Map<String,Object> datos)
     * @return  Contenido: crea un contenido del tipo solicitado (similar a Administrador)
     */
    @Override
    public Contenido crearContenido(String tipoContenido, Map<String, Object> datos) {
        // implementación similar a Administrador pero sin permisos especiales
        if ("Articulo".equalsIgnoreCase(tipoContenido)) {
            Articulo a = new Articulo(
                (int) datos.getOrDefault("id", 0),
                (String) datos.getOrDefault("titulo", "Sin título"),
                this.nombre,
                (Categoria) datos.getOrDefault("categoria", null),
                (String) datos.getOrDefault("fechaPublicacion", ""),
                (String) datos.getOrDefault("texto", "")
            );
            return a;
        } else if ("Video".equalsIgnoreCase(tipoContenido)) {
            Video v = new Video(
                (int) datos.getOrDefault("id", 0),
                (String) datos.getOrDefault("titulo", "Sin título"),
                this.nombre,
                (Categoria) datos.getOrDefault("categoria", null),
                (String) datos.getOrDefault("fechaPublicacion", ""),
                (String) datos.getOrDefault("urlVideo", ""),
                (int) datos.getOrDefault("duracion", 0),
                (String) datos.getOrDefault("formato", "mp4")
            );
            return v;
        } else if ("Imagen".equalsIgnoreCase(tipoContenido)) {
            Imagen im = new Imagen(
                (int) datos.getOrDefault("id", 0),
                (String) datos.getOrDefault("titulo", "Sin título"),
                this.nombre,
                (Categoria) datos.getOrDefault("categoria", null),
                (String) datos.getOrDefault("fechaPublicacion", ""),
                (String) datos.getOrDefault("rutaImagen", ""),
                (String) datos.getOrDefault("resolucion", ""),
                (int) datos.getOrDefault("tamanoKB", 0)
            );
            return im;
        }
        return null;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Contenido c, Map<String,Object> cambios)
     * @return  void: edita un contenido si tiene permiso y es autor
     */
    public void editarContenido(Contenido c, Map<String, Object> cambios) {
        if (!permisoEditar) {
            System.out.println("No tiene permiso para editar.");
            return;
        }
        if (c == null) {
            System.out.println("Contenido no encontrado.");
            return;
        }
        // En una app real se verificaría autor; aquí se permite editar
        if (c instanceof Editable) {
            ((Editable) c).editar(cambios);
            System.out.println("Contenido editado por " + nombre);
        } else {
            System.out.println("Contenido no editable.");
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  List<Contenido>: lista vacía de ejemplo (en real devolvería del controlador)
     */
    public List<Contenido> listarMisContenidos() {
        return new ArrayList<>();
    }
}
