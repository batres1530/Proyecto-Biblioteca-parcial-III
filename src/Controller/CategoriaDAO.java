package Controller;

import Model.Categorias;
import bd.ConnectionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    
    public List<Categorias> getAllCategorias() {
        List<Categorias> lista = new ArrayList<>();
        String sql = "SELECT Categoria_ID, Descripcion FROM Categorias";

        try (Connection con = ConnectionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Categoria_ID");
                String desc = rs.getString("Descripcion");
                lista.add(new Categorias(id, desc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
     public boolean insertarCategoria(Categorias cat) {
        String sql = "INSERT INTO Categorias (Descripcion) VALUES (?)";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cat.getDescripcion());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCategoria(Categorias cat) {
        String sql = "UPDATE Categorias SET Descripcion = ? WHERE Categoria_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cat.getDescripcion());
            pst.setInt(2, cat.getId());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM Categorias WHERE Categoria_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }

    public Categorias buscarPorId(int id) {
        String sql = "SELECT Categoria_ID, Descripcion FROM Categorias WHERE Categoria_ID = ?";
        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Categorias(
                    rs.getInt("Categoria_ID"),
                    rs.getString("Descripcion")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener categoría: " + e.getMessage());
        }
        return null;
    }
    
}
