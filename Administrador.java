import java.util.Map;

public class Administrador extends Usuario {

    private boolean permisoEliminar;
    private boolean permisoPublicar;

    public Administrador() {
        this.tipo = "Administrador";
        this.permisoEliminar = true;
        this.permisoPublicar = true;
    }

    public Administrador(int idUsuario, String nombre, String email, String password) {
        super(idUsuario, nombre, email, password, "Administrador");
        this.permisoEliminar = true;
        this.permisoPublicar = true;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipoContenido, Map<String,Object> datos)
     * @return  Contenido: nuevo contenido creado por administrador
     */
    @Override
    public Contenido crearContenido(String tipoContenido, Map<String, Object> datos) {
        // Creación básica según tipo
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
     * atributos o datos que usan solo su tipo (Contenido c)
     * @return  void: publica el contenido si tiene permiso
     */
    public void publicarContenido(Contenido c) {
        if (permisoPublicar && c != null) {
            c.publicar();
        } else {
            System.out.println("No tiene permiso para publicar.");
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Contenido c)
     * @return  void: elimina contenido si tiene permiso
     */
    public void eliminarContenido(Contenido c) {
        if (permisoEliminar && c != null) {
            if (c instanceof Editable) {
                ((Editable) c).eliminar();
            } else {
                System.out.println("Contenido no editable.");
            }
        } else {
            System.out.println("No tiene permiso para eliminar.");
        }
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipo, String criterio)
     * @return  Reporte: genera un reporte de acuerdo al criterio
     */
    public Reporte generarReporte(String tipo, String criterio) {
        Reporte r = new Reporte();
        if ("tipo".equalsIgnoreCase(tipo)) {
            r = r.generarPorTipo(criterio);
        } else if ("categoria".equalsIgnoreCase(tipo)) {
            r = r.generarPorCategoria(criterio);
        }
        r.guardar();
        return r;
    }

    public void asignarPermisos(Usuario u, String permiso) {
        // implementación simple: asigna tipo o banderas
        if ("publicar".equalsIgnoreCase(permiso)) {
            if (u instanceof Administrador) ((Administrador) u).permisoPublicar = true;
        }
    }
}
