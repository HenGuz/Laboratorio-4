import java.util.Map;

public abstract class Usuario {
    protected int idUsuario;
    protected String nombre;
    protected String email;
    private String password;
    protected String tipo; // "Administrador" o "Editor"

    public Usuario() { }

    public Usuario(int idUsuario, String nombre, String email, String password, String tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipo, Map<String,Object> datos)
     * @return  Contenido: el objeto contenido creado
     */
    public abstract Contenido crearContenido(String tipoContenido, Map<String, Object> datos);

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String password)
     * @return  boolean: true si coincide la contraseña, false si no
     */
    public boolean autenticar(String password) {
        if (this.password == null) return false;
        return this.password.equals(password);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: cerrar sesion (simulación)
     */
    public void cerrarSesion() {
        System.out.println(nombre + " ha cerrado sesión.");
    }

    // Getters y setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
