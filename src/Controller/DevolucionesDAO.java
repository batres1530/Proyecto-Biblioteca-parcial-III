package Controller;

import Model.Devoluciones;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DevolucionesDAO {
    
    
    public List<Devoluciones> getAll() {
        List<Devoluciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM Devoluciones";
        try (Connection con = ConnectionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Devoluciones dev = new Devoluciones(
                    rs.getInt("Devoluciones_ID"),
                    rs.getInt("Prestamo_ID"),
                    rs.getDate("Fecha_devolucion"),
                    rs.getFloat("Multa")
                );
                lista.add(dev);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar devoluciones: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Devoluciones devolucion) {
        String sql = "INSERT INTO Devoluciones (Prestamo_ID, Fecha_devolucion, Multa) VALUES (?, ?, ?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, devolucion.getPrestamoId());
            pst.setDate(2, devolucion.getFechaDevolucion());
            pst.setFloat(3, devolucion.getMulta());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar devolución: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Devoluciones devolucion) {
        String sql = "UPDATE Devoluciones SET Prestamo_ID = ?, Fecha_devolucion = ?, Multa = ? WHERE Devoluciones_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, devolucion.getPrestamoId());
            pst.setDate(2, devolucion.getFechaDevolucion());
            pst.setFloat(3, devolucion.getMulta());
            pst.setInt(4, devolucion.getDevolucionesId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar devolución: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Devoluciones WHERE Devoluciones_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar devolución: " + e.getMessage());
            return false;
        }
    }

    public Devoluciones getById(int id) {
        String sql = "SELECT * FROM Devoluciones WHERE Devoluciones_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Devoluciones(
                    rs.getInt("Devoluciones_ID"),
                    rs.getInt("Prestamo_ID"),
                    rs.getDate("Fecha_devolucion"),
                    rs.getFloat("Multa")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener devolución: " + e.getMessage());
        }
        return null;
    }

}