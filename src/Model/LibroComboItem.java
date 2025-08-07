package Model;

public class LibroComboItem {
    private int id;
    private String titulo;

    public LibroComboItem(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }
}

