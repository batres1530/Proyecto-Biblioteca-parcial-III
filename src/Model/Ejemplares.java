package Model;

public class Ejemplares {
    private int ejemplarID;
    private int librosID;
    private String estado;
    private String tituloLibro; 

    public Ejemplares() {
    }

    public Ejemplares(int ejemplarID, int librosID, String estado) {
        this.ejemplarID = ejemplarID;
        this.librosID = librosID;
        this.estado = estado;
    }

    public Ejemplares(int ejemplarID, String estado, String tituloLibro) {
        this.ejemplarID = ejemplarID;
        this.estado = estado;
        this.tituloLibro = tituloLibro;
    }

    public int getEjemplarID() {
        return ejemplarID;
    }

    public void setEjemplarID(int ejemplarID) {
        this.ejemplarID = ejemplarID;
    }

    public int getLibrosID() {
        return librosID;
    }

    public void setLibrosID(int librosID) {
        this.librosID = librosID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }
}
