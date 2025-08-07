package Model;

import java.sql.Date;

public class Devoluciones {
    private int devolucionesId;
    private int prestamoId;
    private Date fechaDevolucion;
    private float multa;

    public Devoluciones() {}

    public Devoluciones(int devolucionesId, int prestamoId, Date fechaDevolucion, float multa) {
        this.devolucionesId = devolucionesId;
        this.prestamoId = prestamoId;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
    }

    public Devoluciones(int prestamoId, Date fechaDevolucion, float multa) {
        this.prestamoId = prestamoId;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
    }

    public int getDevolucionesId() {
        return devolucionesId;
    }

    public void setDevolucionesId(int devolucionesId) {
        this.devolucionesId = devolucionesId;
    }

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "ID: " + devolucionesId + ", Préstamo: " + prestamoId + ", Fecha: " + fechaDevolucion + ", Multa: L. " + multa;
    }
}
