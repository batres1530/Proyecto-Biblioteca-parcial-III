package Model;


public class LibrosAutores {
    private int libroAutorID;
    private int libroID;
    private int autorID;
    private String tituloLibro;
    private String nombreAutor;

    public LibrosAutores() {}

    public LibrosAutores(int libroAutorID, int libroID, int autorID, String tituloLibro, String nombreAutor) {
        this.libroAutorID = libroAutorID;
        this.libroID = libroID;
        this.autorID = autorID;
        this.tituloLibro = tituloLibro;
        this.nombreAutor = nombreAutor;
    }
    
    public LibrosAutores(int libroAutorID, int libroID, int autorID) {
    this.libroAutorID = libroAutorID;
    this.libroID = libroID;
    this.autorID = autorID;
}

    public int getLibroAutorID() {
        return libroAutorID;
    }

    public void setLibroAutorID(int libroAutorID) {
        this.libroAutorID = libroAutorID;
    }

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
}

