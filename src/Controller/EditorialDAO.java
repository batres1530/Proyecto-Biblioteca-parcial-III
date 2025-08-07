package Controller;

import Model.Editorial;
import bd.ConnectionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    public List<Editorial> getAllEditoriales() {
        List<Editorial> lista = new ArrayList<>();
        String sql = "SELECT Editorial_ID, Nombre FROM Editorial";

        try (Connection con = ConnectionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Editorial_ID");
                String nombre = rs.getString("Nombre");
                lista.add(new Editorial(id, nombre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean insert(Editorial editorial) {
        String sql = "INSERT INTO Editorial (Nombre, Direccion, Telefono) VALUES (?, ?, ?)";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, editorial.getNombre());
            ps.setString(2, editorial.getDireccion());
            ps.setString(3, editorial.getTelefono());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Editorial editorial) {
        String sql = "UPDATE Editorial SET Nombre = ?, Direccion = ?, Telefono = ? WHERE Editorial_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, editorial.getNombre());
            ps.setString(2, editorial.getDireccion());
            ps.setString(3, editorial.getTelefono());
            ps.setInt(4, editorial.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Editorial WHERE Editorial_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public
         Editorial buscarPorId(int id) {
    String sql = "SELECT Editorial_ID, Nombre, Direccion, Telefono FROM Editorial WHERE Editorial_ID = ?";
    
    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return new Editorial(
                rs.getInt("Editorial_ID"),
                rs.getString("Nombre"),
                rs.getString("Direccion"),
                rs.getString("Telefono")
            );
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener editorial: " + e.getMessage());
    }

    return null;
}


    public List<Editorial> getAllEditoriales2() {
        List<Editorial> lista = new ArrayList<>();
        String sql = "SELECT * FROM Editorial";

        try (Connection con = ConnectionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Editorial_ID");
                String nombre = rs.getString("Nombre");
                String direccion = rs.getString("Direccion");
                String telefono = rs.getString("Telefono");
                lista.add(new Editorial(id, nombre, direccion, telefono));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
}
