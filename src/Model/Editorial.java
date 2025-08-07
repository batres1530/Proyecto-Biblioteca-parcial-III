package Model;

public class Editorial {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;

    // Constructor con ID (para lectura desde la BD)
    public Editorial(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor sin ID (para insertar nuevos registros)
    public Editorial(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor con solo ID y nombre (opcional, por si lo usas en JComboBox)
    public Editorial(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
