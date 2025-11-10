import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ControladorCMS controlador = new ControladorCMS();

        // Crear vista
        VistaCMS vista = new VistaCMS(controlador);

        // Crear usuarios de prueba
        Administrador admin = new Administrador(1, "admin", "admin@cms.com", "1234");
        Editor editor = new Editor(2, "editor", "editor@cms.com", "abcd");

        controlador.agregarUsuario(admin);
        controlador.agregarUsuario(editor);

        // Crear categorias
        Categoria catEdu = new Categoria(1, "Educación", "Contenido educativo");
        Categoria catTec = new Categoria(2, "Tecnología", "Contenido tecnológico");

        // Crear contenidos de prueba
        HashMap<String, Object> datos1 = new HashMap<>();
        datos1.put("id", 1);
        datos1.put("titulo", "Introducción a POO");
        datos1.put("texto", "Este es un artículo sobre POO.");
        datos1.put("categoria", catEdu);
        datos1.put("fechaPublicacion", "09/11/2025");
        // Llamada correcta: crear contenido directamente
        Contenido art1 = admin.crearContenido("Articulo", datos1);
        controlador.agregarContenido(art1);

        HashMap<String, Object> datos2 = new HashMap<>();
        datos2.put("id", 2);
        datos2.put("titulo", "Video: Introducción a Java");
        datos2.put("urlVideo", "http://ejemplo.com/video1");
        datos2.put("duracion", 180);
        datos2.put("categoria", catTec);
        datos2.put("fechaPublicacion", "09/11/2025");
        Contenido vid1 = admin.crearContenido("Video", datos2);
        controlador.agregarContenido(vid1);

        HashMap<String, Object> datos3 = new HashMap<>();
        datos3.put("id", 3);
        datos3.put("titulo", "Imagen del LOGO");
        datos3.put("rutaImagen", "/images/logo.png");
        datos3.put("resolucion", "800x600");
        datos3.put("categoria", catTec);
        datos3.put("fechaPublicacion", "09/11/2025");
        Contenido img1 = editor.crearContenido("Imagen", datos3);
        controlador.agregarContenido(img1);

        // Publicar uno para mostrar estado
        admin.publicarContenido(art1);

        // Mostrar todo por consola
        controlador.mostrarTodo();

        // Abrir menú simple de la vista (una interacción rápida)
        vista.mostrarMenu();

        System.out.println("Fin del demo.");
    }
}
