package Controller;

import Model.Autores;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AutoresDAO {
    
    public ArrayList<Autores> getAllAutor() {
        ArrayList<Autores> lista = new ArrayList<>();
        String sql = "SELECT Autor_ID, Nombre, Apellido FROM Autores";
        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Autores autor = new Autores(
                    rs.getInt("Autor_ID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
                lista.add(autor);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar autores: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean insert(Autores autor) {
        String sql = "INSERT INTO Autores (Nombre, Apellido) VALUES (?, ?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, autor.getNombre());
            pst.setString(2, autor.getApellido());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Autores autor) {
        String sql = "UPDATE Autores SET Nombre = ?, Apellido = ? WHERE Autor_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, autor.getNombre());
            pst.setString(2, autor.getApellido());
            pst.setInt(3, autor.getId());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Autores WHERE Autor_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
            return false;
        }
    }

    public Autores buscarPorId(int id) {
        String sql = "SELECT Autor_ID, Nombre, Apellido FROM Autores WHERE Autor_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Autores(
                    rs.getInt("Autor_ID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener autor: " + e.getMessage());
        }
        return null;
    }

    
}
