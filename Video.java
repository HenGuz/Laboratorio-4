public class Video extends Contenido implements Editable {
    private String urlVideo;
    private int duracion; // segundos
    private String formato;

    public Video() { super(); }

    public Video(int id, String titulo, String autor, Categoria categoria, String fechaPublicacion, String urlVideo, int duracion, String formato) {
        super(id, titulo, autor, categoria, fechaPublicacion);
        this.urlVideo = urlVideo;
        this.duracion = duracion;
        this.formato = formato;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: publica el video (estadoPublicacion = true)
     */
    @Override
    public void publicar() {
        this.estadoPublicacion = true;
        System.out.println("Video publicado: " + titulo);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: muestra info del video por consola (simula reproducción)
     */
    @Override
    public void visualizar() {
        System.out.println("=== Video ===");
        System.out.println("Título: " + titulo);
        System.out.println("URL: " + urlVideo);
        System.out.println("Duración (s): " + duracion);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Map<String,Object> cambios)
     * @return  void: edita campos del video
     */
    @Override
    public void editar(java.util.Map<String, Object> cambios) {
        if (cambios.containsKey("titulo")) this.titulo = (String) cambios.get("titulo");
        if (cambios.containsKey("urlVideo")) this.urlVideo = (String) cambios.get("urlVideo");
        if (cambios.containsKey("duracion")) this.duracion = (Integer) cambios.get("duracion");
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
        this.urlVideo = null;
        System.out.println("Video eliminado (simulación): " + titulo);
    }

    // Getters/Setters
    public String getUrlVideo() { return urlVideo; }
    public void setUrlVideo(String urlVideo) { this.urlVideo = urlVideo; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }
}
