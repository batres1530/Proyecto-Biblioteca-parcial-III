package Model;

import java.time.LocalDate;
import java.sql.Date;

public class Prestamo {
    private int id;
    private int usuarioId;
    private int libroId;
    private int empleadoId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;

    // Campos para mostrar nombres
    private String nombreUsuario;
    private String tituloLibro;
    private String nombreEmpleado;

    // Constructor usando java.sql.Date
    public Prestamo(int prestamoId, int usuarioId, int libroId, int empleadoId, Date fechaPrestamo, Date fechaLimite) {
        this.id = prestamoId;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.empleadoId = empleadoId;
        this.fechaPrestamo = fechaPrestamo.toLocalDate();
        this.fechaLimite = fechaLimite.toLocalDate();
    }

    // Constructor usando LocalDate
    public Prestamo(int prestamoId, int usuarioId, int libroId, int empleadoId, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.id = prestamoId;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.empleadoId = empleadoId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
    }

    // Getters y Setters principales
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getLibroId() {
        return libroId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}
