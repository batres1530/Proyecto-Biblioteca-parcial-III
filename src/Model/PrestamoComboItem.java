package Model;

public class PrestamoComboItem {
    private int id;
    private String descripcion;

    public PrestamoComboItem(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

