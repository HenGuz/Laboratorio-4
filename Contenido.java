public abstract class Contenido {
    protected int id;
    protected String titulo;
    protected String autor;
    protected Categoria categoria;
    protected String fechaPublicacion;
    protected boolean estadoPublicacion; // true = publicado, false = borrador

    public Contenido() { }

    public Contenido(int id, String titulo, String autor, Categoria categoria, String fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoPublicacion = false;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  lo que devuelve el tipo de dato y su uso
     */
    public abstract void publicar();

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  lo que devuelve el tipo de dato y su uso
     */
    public abstract void visualizar();

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  String: resumen breve del contenido
     */
    public String getResumen() {
        if (titulo == null) return "";
        return titulo + " - " + (categoria != null ? categoria.getNombre() : "Sin categoría");
    }

    // Getters y setters simples

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public boolean isPublicado() { return estadoPublicacion; }
    public void setPublicado(boolean estado) { this.estadoPublicacion = estado; }
}
