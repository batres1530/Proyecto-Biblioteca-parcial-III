package Controller;

import Model.AutorComboItem;
import Model.LibroComboItem;
import Model.LibrosAutores;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LibrosAutoresDAO {
    
    public List<LibrosAutores> getAllConNombres() {
    List<LibrosAutores> lista = new ArrayList<>();
    String sql = "SELECT la.LibroAutor_ID, la.Libro_ID, la.Autor_ID, " +
             "l.Titulo_Libro AS TituloLibro, " +
             "CONCAT(a.Nombre, ' ', a.Apellido) AS NombreAutor " +
             "FROM Libros_Autores la " +
             "LEFT JOIN Libros l ON la.Libro_ID = l.Libro_ID " +
             "LEFT JOIN Autores a ON la.Autor_ID = a.Autor_ID";



        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                LibrosAutores la = new LibrosAutores();
                la.setLibroAutorID(rs.getInt("LibroAutor_ID"));
                la.setLibroID(rs.getInt("Libro_ID"));
                la.setAutorID(rs.getInt("Autor_ID"));
                la.setTituloLibro(rs.getString("TituloLibro"));
                la.setNombreAutor(rs.getString("NombreAutor"));
                lista.add(la);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    return lista;
}
    
    public boolean insert(LibrosAutores la) {
        String sql = "INSERT INTO Libros_Autores (Libro_ID, Autor_ID) VALUES (?, ?)";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, la.getLibroID());
            ps.setInt(2, la.getAutorID());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LibrosAutores la) {
        String sql = "UPDATE Libros_Autores SET Libro_ID = ?, Autor_ID = ? WHERE LibroAutor_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, la.getLibroID());
            ps.setInt(2, la.getAutorID());
            ps.setInt(3, la.getLibroAutorID());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Libros_Autores WHERE LibroAutor_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public LibrosAutores buscarPorId(int id) {
        String sql = "SELECT * FROM Libros_Autores WHERE LibroAutor_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new LibrosAutores(
                    rs.getInt("LibroAutor_ID"),
                    rs.getInt("Libro_ID"),
                    rs.getInt("Autor_ID")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<LibroComboItem> obtenerLibrosParaCombo() {
        List<LibroComboItem> lista = new ArrayList<>();
        String sql = "SELECT Libro_ID, Titulo_Libro FROM Libros";  // <-- CORREGIDO

            try (Connection con = ConnectionBD.getConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    lista.add(new LibroComboItem(rs.getInt("Libro_ID"), rs.getString("Titulo_Libro")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return lista;
    }


    public List<AutorComboItem> obtenerAutoresParaCombo() {
        List<AutorComboItem> lista = new ArrayList<>();
        String sql = "SELECT Autor_ID, Nombre, Apellido FROM Autores";

        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Autor_ID");
                String nombreCompleto = rs.getString("Nombre") + " " + rs.getString("Apellido");
                lista.add(new AutorComboItem(id, nombreCompleto));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    
}
