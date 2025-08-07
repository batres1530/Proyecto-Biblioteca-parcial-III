package Controller;

import Model.Cargos;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CargosDAO {
    
        public ArrayList<Cargos> getAllCargos() {
        ArrayList<Cargos> lista = new ArrayList<>();
        String sql = "SELECT Cargos_ID, Descripcion FROM Cargos";
        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cargos c = new Cargos(
                    rs.getInt("Cargos_ID"),
                    rs.getString("Descripcion")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar cargos: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean insert(Cargos cargo) {
        String sql = "INSERT INTO Cargos (Descripcion) VALUES (?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cargo.getDescripcion());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar cargo: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Cargos cargo) {
        String sql = "UPDATE Cargos SET Descripcion = ? WHERE Cargos_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cargo.getDescripcion());
            pst.setInt(2, cargo.getId());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cargo: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Cargos WHERE Cargos_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cargo: " + e.getMessage());
            return false;
        }
    }

    public Cargos buscarPorId(int id) {
        String sql = "SELECT Cargos_ID, Descripcion FROM Cargos WHERE Cargos_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Cargos(
                    rs.getInt("Cargos_ID"),
                    rs.getString("Descripcion")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cargo: " + e.getMessage());
        }
        return null;
    }

    
}
