package Model;


public class Usuarios {
    
    private int id;
    private String nombre;
    private String correo;
    private int tipoUsuarioId;

    public Usuarios() {}

    public Usuarios(int id, String nombre, String correo, int tipoUsuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public Usuarios(String nombre, String correo, int tipoUsuarioId) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }
    
}
