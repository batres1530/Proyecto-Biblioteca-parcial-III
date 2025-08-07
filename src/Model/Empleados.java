package Model;

public class Empleados {
    private int empleadoID;
    private String nombre;
    private String apellido;
    private int cargosID;
    private String telefono;
    private String cargoNombre; 

    public Empleados() {
    }

    public Empleados(int empleadoID, String nombre, String apellido, int cargosID, String telefono) {
        this.empleadoID = empleadoID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargosID = cargosID;
        this.telefono = telefono;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCargosID() {
        return cargosID;
    }

    public void setCargosID(int cargosID) {
        this.cargosID = cargosID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargoNombre() {
        return cargoNombre;
    }

    public void setCargoNombre(String cargoNombre) {
        this.cargoNombre = cargoNombre;
    }
}
