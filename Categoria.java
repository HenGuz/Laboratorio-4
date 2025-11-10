public class Categoria {
    private int idCategoria;
    private String nombre;
    private String descripcion;

    public Categoria() { }

    public Categoria(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Métodos simples

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  String: nombre de la categoria
     */
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Categoria)) return false;
        Categoria c = (Categoria) o;
        return this.idCategoria == c.idCategoria || (this.nombre != null && this.nombre.equalsIgnoreCase(c.nombre));
    }

    @Override
    public String toString() {
        return nombre != null ? nombre : "(sin nombre)";
    }
}
