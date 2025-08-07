package Controller;

import Model.TipoUsuario;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoUsuarioDAO {
    
    public ArrayList<TipoUsuario> getAllTipoUsuario() {
        ArrayList<TipoUsuario> lista = new ArrayList<>();
        String sql = "SELECT TipoUsuario_ID, Descripcion FROM TipoUsuario";
        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                TipoUsuario tipo = new TipoUsuario(
                    rs.getInt("TipoUsuario_ID"),
                    rs.getString("Descripcion")
                );
                lista.add(tipo);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tipos de usuario: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean insert(TipoUsuario tipo) {
        String sql = "INSERT INTO TipoUsuario (Descripcion) VALUES (?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, tipo.getDescripcion());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar tipo de usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean update(TipoUsuario tipo) {
        String sql = "UPDATE TipoUsuario SET Descripcion = ? WHERE TipoUsuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, tipo.getDescripcion());
            pst.setInt(2, tipo.getId());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo de usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM TipoUsuario WHERE TipoUsuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo de usuario: " + e.getMessage());
            return false;
        }
    }

    public TipoUsuario buscarPorId(int id) {
        String sql = "SELECT TipoUsuario_ID, Descripcion FROM TipoUsuario WHERE TipoUsuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new TipoUsuario(
                    rs.getInt("TipoUsuario_ID"),
                    rs.getString("Descripcion")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de usuario: " + e.getMessage());
        }
        return null;
    }

    
}
