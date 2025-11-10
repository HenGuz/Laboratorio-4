public class Articulo extends Contenido implements Editable {
    private String texto;
    private String resumen;

    public Articulo() { super(); }

    public Articulo(int id, String titulo, String autor, Categoria categoria, String fechaPublicacion, String texto) {
        super(id, titulo, autor, categoria, fechaPublicacion);
        this.texto = texto;
        this.resumen = texto != null && texto.length() > 60 ? texto.substring(0,60) + "..." : texto;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: publica el articulo (estadoPublicacion = true)
     */
    @Override
    public void publicar() {
        this.estadoPublicacion = true;
        System.out.println("Artículo publicado: " + titulo);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: muestra el texto del artículo por consola
     */
    @Override
    public void visualizar() {
        System.out.println("=== Artículo ===");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Texto: " + (texto != null ? texto : "(vacío)"));
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Map<String,Object> cambios)
     * @return  void: edita campos permitidos del artículo
     */
    @Override
    public void editar(java.util.Map<String, Object> cambios) {
        if (cambios.containsKey("titulo")) this.titulo = (String) cambios.get("titulo");
        if (cambios.containsKey("texto")) {
            this.texto = (String) cambios.get("texto");
            this.resumen = texto != null && texto.length() > 60 ? texto.substring(0,60) + "..." : texto;
        }
        if (cambios.containsKey("categoria")) this.categoria = (Categoria) cambios.get("categoria");
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: eliminar marca como no publicado y borra texto (simulación)
     */
    @Override
    public void eliminar() {
        this.estadoPublicacion = false;
        this.texto = null;
        System.out.println("Artículo eliminado (simulación): " + titulo);
    }

    // Getters/Setters
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getResumenArticulo() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
}
