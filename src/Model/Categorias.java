package Model;

public class Categorias {
    
    private int id;
    private String descripcion;

    public Categorias(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion; 
    }
    
}
