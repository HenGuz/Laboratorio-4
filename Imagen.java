public class Imagen extends Contenido implements Editable {
    private String rutaImagen;
    private String resolucion;
    private int tamanoKB;

    public Imagen() { super(); }

    public Imagen(int id, String titulo, String autor, Categoria categoria, String fechaPublicacion, String rutaImagen, String resolucion, int tamanoKB) {
        super(id, titulo, autor, categoria, fechaPublicacion);
        this.rutaImagen = rutaImagen;
        this.resolucion = resolucion;
        this.tamanoKB = tamanoKB;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: publica la imagen
     */
    @Override
    public void publicar() {
        this.estadoPublicacion = true;
        System.out.println("Imagen publicada: " + titulo);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: muestra info de la imagen por consola
     */
    @Override
    public void visualizar() {
        System.out.println("=== Imagen ===");
        System.out.println("Título: " + titulo);
        System.out.println("Ruta: " + rutaImagen);
        System.out.println("Resolución: " + resolucion);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Map<String,Object> cambios)
     * @return  void: edita campos de la imagen
     */
    @Override
    public void editar(java.util.Map<String, Object> cambios) {
        if (cambios.containsKey("titulo")) this.titulo = (String) cambios.get("titulo");
        if (cambios.containsKey("rutaImagen")) this.rutaImagen = (String) cambios.get("rutaImagen");
        if (cambios.containsKey("resolucion")) this.resolucion = (String) cambios.get("resolucion");
        if (cambios.containsKey("categoria")) this.categoria = (Categoria) cambios.get("categoria");
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: eliminar (simulación)
     */
    @Override
    public void eliminar() {
        this.estadoPublicacion = false;
        this.rutaImagen = null;
        System.out.println("Imagen eliminada (simulación): " + titulo);
    }

    // Getters/Setters
    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }

    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }

    public int getTamanoKB() { return tamanoKB; }
    public void setTamanoKB(int tamanoKB) { this.tamanoKB = tamanoKB; }
}
