package Controller;

import Model.Ejemplares;
import Model.LibroComboItem;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EjemplaresDAO {

    public List<Ejemplares> getAllEjemplares() {
        List<Ejemplares> lista = new ArrayList<>();
        String sql = "SELECT e.Ejemplar_ID, e.Estado, l.Titulo_Libro " +
                     "FROM Ejemplares e " +
                     "JOIN Libros l ON e.Libros_ID = l.Libro_ID";

        try (Connection con = ConnectionBD.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ejemplares ej = new Ejemplares();
                ej.setEjemplarID(rs.getInt("Ejemplar_ID"));
                ej.setEstado(rs.getString("Estado"));
                ej.setTituloLibro(rs.getString("Titulo_Libro"));
                lista.add(ej);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insert(Ejemplares ejemplar) {
        String sql = "INSERT INTO Ejemplares (Libros_ID, Estado) VALUES (?, ?)";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ejemplar.getLibrosID());
            ps.setString(2, ejemplar.getEstado());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Ejemplares ejemplar) {
        String sql = "UPDATE Ejemplares SET Libros_ID = ?, Estado = ? WHERE Ejemplar_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ejemplar.getLibrosID());
            ps.setString(2, ejemplar.getEstado());
            ps.setInt(3, ejemplar.getEjemplarID());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Ejemplares WHERE Ejemplar_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Ejemplares buscarPorId(int id) {
        String sql = "SELECT Ejemplar_ID, Libros_ID, Estado FROM Ejemplares WHERE Ejemplar_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Ejemplares(
                    rs.getInt("Ejemplar_ID"),
                    rs.getInt("Libros_ID"),
                    rs.getString("Estado")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ejemplar: " + e.getMessage());
        }

        return null;
    }
    
    public List<LibroComboItem> obtenerLibrosParaCombo() {
    List<LibroComboItem> lista = new ArrayList<>();
    String sql = "SELECT Libro_ID, Titulo_Libro FROM Libros";

    try (Connection con = ConnectionBD.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("Libro_ID");
            String titulo = rs.getString("Titulo_Libro");
            lista.add(new LibroComboItem(id, titulo));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

}    