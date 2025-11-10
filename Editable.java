public interface Editable {
    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (Map<String,Object> cambios)
     * @return  void: modifica atributos del objeto
     */
    void editar(java.util.Map<String, Object> cambios);

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: marca o elimina el objeto
     */
    void eliminar();
}
