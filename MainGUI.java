import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * PrincipalGUI: interfaz gráfica principal que integra el CMS.
 */
public class MainGUI {

    private JFrame ventanita;
    private JTextField txtUsuario;
    private JTextField txtPass;
    private JButton btnLogin;
    private JButton btnCancel;

    // Referencia al controlador del sistema
    private ControladorCMS controlador;

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: punto de entrada que crea la ventana en el hilo de eventos
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrincipalGUI window = new PrincipalGUI();
                    window.ventanita.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: constructor que inicializa controlador, datos de prueba e interfaz
     */
    public PrincipalGUI() {
        // Inicializar controlador y datos de prueba
        initControladorConDatosDePrueba();

        // Construir la GUI
        initialize();
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: inicializa el controlador y agrega usuarios/contenidos de ejemplo
     */
    private void initControladorConDatosDePrueba() {
        controlador = new ControladorCMS();

        // Crear usuarios de prueba
        Administrador admin = new Administrador(1, "admin", "admin@cms.com", "1234");
        Editor editor = new Editor(2, "editor", "editor@cms.com", "abcd");

        controlador.agregarUsuario(admin);
        controlador.agregarUsuario(editor);

        // Crear categorias de ejemplo
        Categoria catEdu = new Categoria(1, "Educación", "Contenido educativo");
        Categoria catTec = new Categoria(2, "Tecnología", "Contenido tecnológico");

        // Crear contenidos de prueba (usando administrador)
        Map<String, Object> datos1 = new HashMap<>();
        datos1.put("id", 1);
        datos1.put("titulo", "Introducción a POO");
        datos1.put("texto", "Este es un artículo sobre POO.");
        datos1.put("categoria", catEdu);
        datos1.put("fechaPublicacion", "09/11/2025");
        Contenido art1 = admin.crearContenido("Articulo", datos1);
        controlador.agregarContenido(art1);

        Map<String, Object> datos2 = new HashMap<>();
        datos2.put("id", 2);
        datos2.put("titulo", "Video: Introducción a Java");
        datos2.put("urlVideo", "http://ejemplo.com/video1");
        datos2.put("duracion", 180);
        datos2.put("categoria", catTec);
        datos2.put("fechaPublicacion", "09/11/2025");
        Contenido vid1 = admin.crearContenido("Video", datos2);
        controlador.agregarContenido(vid1);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: construye la ventana de login con los componentes
     */
    private void initialize() {
        Listener sapo = new Listener();

        ventanita = new JFrame();
        ventanita.setTitle("CMS - Login");
        ventanita.setBounds(100, 100, 450, 180);
        ventanita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanita.getContentPane().setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        ventanita.getContentPane().add(lblUsuario);

        txtUsuario = new JTextField();
        ventanita.getContentPane().add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblPass = new JLabel("Contraseña:");
        ventanita.getContentPane().add(lblPass);

        txtPass = new JTextField();
        ventanita.getContentPane().add(txtPass);
        txtPass.setColumns(10);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(sapo);
        ventanita.getContentPane().add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(sapo);
        ventanita.getContentPane().add(btnCancel);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: abre el dashboard con opciones para el usuario autenticado
     */
    private void abrirDashboardParaUsuario(Usuario u) {
        JFrame dash = new JFrame("Dashboard - " + u.getNombre() + " (" + u.getTipo() + ")");
        dash.setBounds(150, 150, 500, 200);
        dash.getContentPane().setLayout(new GridLayout(0, 2, 10, 10));

        // Botón: Listar contenidos
        JButton btnListar = new JButton("Listar contenidos");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar lista en un dialog
                StringBuilder sb = new StringBuilder();
                for (Contenido c : controlador.buscarPorTipo(Contenido.class)) {
                    sb.append("ID: ").append(c.getId())
                      .append(" | Título: ").append(c.getTitulo())
                      .append(" | Categoria: ").append(c.getCategoria() != null ? c.getCategoria().getNombre() : "Sin cat")
                      .append(" | Publicado: ").append(c.isPublicado())
                      .append("\n");
                }
                if (sb.length() == 0) sb.append("(No hay contenidos)");
                javax.swing.JOptionPane.showMessageDialog(dash, sb.toString(), "Listado de contenidos", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        dash.getContentPane().add(btnListar);

        // Botón: Crear artículo (ejemplo simple)
        JButton btnCrear = new JButton("Crear artículo");
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = javax.swing.JOptionPane.showInputDialog(dash, "Título del artículo:");
                if (titulo == null || titulo.trim().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dash, "Título no válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String texto = javax.swing.JOptionPane.showInputDialog(dash, "Texto del artículo:");
                // crear datos
                Map<String, Object> datos = new HashMap<>();
                datos.put("id", controlador.buscarPorTipo(Contenido.class).size() + 1);
                datos.put("titulo", titulo);
                datos.put("texto", texto != null ? texto : "");
                // pedir categoría simple
                String nombreCat = javax.swing.JOptionPane.showInputDialog(dash, "Categoría (ej. Educación):");
                Categoria cat = new Categoria(99, nombreCat != null ? nombreCat : "Sin categoría", "");
                datos.put("categoria", cat);
                datos.put("fechaPublicacion", "09/11/2025");
                Contenido nuevo = u.crearContenido("Articulo", datos);
                if (nuevo != null) {
                    controlador.agregarContenido(nuevo);
                    javax.swing.JOptionPane.showMessageDialog(dash, "Artículo creado con ID " + nuevo.getId(), "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dash, "No se pudo crear el artículo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dash.getContentPane().add(btnCrear);

        // Botón: Buscar por categoría
        JButton btnBuscarCat = new JButton("Buscar por categoría");
        btnBuscarCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoria = javax.swing.JOptionPane.showInputDialog(dash, "Ingrese la categoría a buscar:");
                if (categoria == null) return;
                StringBuilder sb = new StringBuilder();
                for (Contenido c : controlador.buscarPorCategoria(categoria)) {
                    sb.append("ID: ").append(c.getId())
                      .append(" | Título: ").append(c.getTitulo())
                      .append(" | Autor: ").append(c.getAutor())
                      .append("\n");
                }
                if (sb.length() == 0) sb.append("(No se encontraron contenidos)");
                javax.swing.JOptionPane.showMessageDialog(dash, sb.toString(), "Resultados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        dash.getContentPane().add(btnBuscarCat);

        // Botón: Generar reporte por categoría (solo muestra CSV por consola y diálogo)
        JButton btnReporte = new JButton("Generar reporte por categoría");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoria = javax.swing.JOptionPane.showInputDialog(dash, "Ingrese la categoría para el reporte:");
                if (categoria == null) return;
                Reporte r = controlador.crearReporte("categoria", categoria);
                // Exportar (simulado)
                r.exportarCSV("reporte_" + r.getIdReporte() + ".csv");
                javax.swing.JOptionPane.showMessageDialog(dash, "Reporte generado ID=" + r.getIdReporte() + " con " + r.getResultado().size() + " items.", "Reporte", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        dash.getContentPane().add(btnReporte);

        dash.setVisible(true);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: listener para botones Login y Cancel
     */
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLogin) {
                String usuario = txtUsuario.getText();
                String password = txtPass.getText();
                // Autenticar mediante controlador
                Usuario u = controlador.autenticarUsuario(usuario, password);
                if (u == null) {
                    JOptionPane.showMessageDialog(ventanita, "Credenciales inválidas. Usa 'admin/1234' o 'editor/abcd' (demo).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventanita, "Bienvenido " + u.getNombre() + " (" + u.getTipo() + ")", "Login correcto", JOptionPane.INFORMATION_MESSAGE);
                    // deshabilitar login simple y abrir dashboard
                    btnLogin.setEnabled(false);
                    abrirDashboardParaUsuario(u);
                }
            }

            if (e.getSource() == btnCancel) {
                txtUsuario.setText("");
                txtPass.setText("");
            }
        }
    }
}
