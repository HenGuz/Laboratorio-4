import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reporte {
    private int idReporte;
    private String fechaGeneracion;
    private String tipoReporte; // "porTipo" o "porCategoria"
    private ArrayList<Contenido> resultado;

    // almacenamos reportes generados
    private static ArrayList<Reporte> reportesGuardados = new ArrayList<>();
    private static int contadorId = 1;

    public Reporte() {
        this.idReporte = contadorId++;
        this.fechaGeneracion = LocalDateTime.now().toString();
        this.resultado = new ArrayList<>();
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String tipo)
     * @return  Reporte: nuevo reporte con contenidos filtrados por tipo
     */
    public Reporte generarPorTipo(String tipo) {
        Reporte r = new Reporte();
        r.tipoReporte = "porTipo:" + tipo;
        // si hay contenidos en algún lugar, en la práctica el controlador pasaría la lista
        // aquí solo se deja vacío; controlador debe llenar resultado luego de crear
        return r;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String categoria)
     * @return  Reporte: nuevo reporte con contenidos filtrados por categoria
     */
    public Reporte generarPorCategoria(String categoria) {
        Reporte r = new Reporte();
        r.tipoReporte = "porCategoria:" + categoria;
        return r;
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo
     * @return  void: guarda este reporte en el historial (reportesGuardados)
     */
    public void guardar() {
        reportesGuardados.add(this);
    }

    /**
     * funcion del metodo
     * atributos o datos que usan solo su tipo (String ruta)
     * @return  void: exporta a CSV (simulación)
     */
    public void exportarCSV(String ruta) {
        // En versión simple: imprimimos por consola la estructura del CSV
        System.out.println("Exportando reporte id=" + idReporte + " a " + ruta);
        for (Contenido c : resultado) {
            System.out.println(c.getId() + "," + c.getTitulo() + "," + (c.getCategoria() != null ? c.getCategoria().getNombre() : ""));
        }
    }

    public static List<Reporte> obtenerReportesGuardados() {
        return reportesGuardados;
    }

    public static Reporte obtenerReportePorId(int id) {
        for (Reporte r : reportesGuardados) {
            if (r.idReporte == id) return r;
        }
        return null;
    }

    // getters y setters
    public int getIdReporte() { return idReporte; }
    public String getFechaGeneracion() { return fechaGeneracion; }
    public String getTipoReporte() { return tipoReporte; }
    public ArrayList<Contenido> getResultado() { return resultado; }
    public void setResultado(ArrayList<Contenido> resultado) { this.resultado = resultado; }
}
