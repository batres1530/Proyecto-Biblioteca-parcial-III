package Controller;

import Model.Usuarios;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuariosDAO {
    
    public ArrayList<Usuarios> getAllUsuarios() {
        ArrayList<Usuarios> lista = new ArrayList<>();
        String sql = "SELECT Usuario_ID, Nombre, Correo, TipoUsuario_ID FROM Usuarios";
        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuarios usuario = new Usuarios(
                    rs.getInt("Usuario_ID"),
                    rs.getString("Nombre"),
                    rs.getString("Correo"),
                    rs.getInt("TipoUsuario_ID")
                );
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean insert(Usuarios usuario) {
        String sql = "INSERT INTO Usuarios (Nombre, Correo, TipoUsuario_ID) VALUES (?, ?, ?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setInt(3, usuario.getTipoUsuarioId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Usuarios usuario) {
        String sql = "UPDATE Usuarios SET Nombre = ?, Correo = ?, TipoUsuario_ID = ? WHERE Usuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setInt(3, usuario.getTipoUsuarioId());
            pst.setInt(4, usuario.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Usuarios WHERE Usuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuarios buscarPorId(int id) {
        String sql = "SELECT Usuario_ID, Nombre, Correo, TipoUsuario_ID FROM Usuarios WHERE Usuario_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Usuarios(
                    rs.getInt("Usuario_ID"),
                    rs.getString("Nombre"),
                    rs.getString("Correo"),
                    rs.getInt("TipoUsuario_ID")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }
        return null;
    }
    
}
