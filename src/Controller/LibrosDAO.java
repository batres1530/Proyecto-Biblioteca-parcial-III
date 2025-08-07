package Controller;

import Model.Libros;
import java.util.ArrayList;
import java.util.List;
import bd.ConnectionBD;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LibrosDAO {
    
    
public List<Object> getAll() {
    List<Object> listado = new ArrayList<>();
    String query = "SELECT l.Libro_ID, l.Titulo_Libro, l.Fecha_Publicación, "
                 + "l.Editorial_ID, e.Nombre AS Nombre_Editorial, "
                 + "l.Categoria_ID, c.Descripcion AS Nombre_Categoria "
                 + "FROM Libros l "
                 + "LEFT JOIN Editorial e ON l.Editorial_ID = e.Editorial_ID "
                 + "LEFT JOIN Categorias c ON l.Categoria_ID = c.Categoria_ID;";



    try (Connection con = ConnectionBD.getConnection()) {
        Statement stmt = con.createStatement();
        ResultSet resultado = stmt.executeQuery(query);

        while (resultado.next()) {
            listado.add(new Libros(
                resultado.getInt("Libro_ID"),
                resultado.getString("Titulo_Libro"),
                resultado.getDate("Fecha_Publicación"),
                resultado.getInt("Editorial_ID"),
                resultado.getString("Nombre_Editorial"),
                resultado.getInt("Categoria_ID"),
                resultado.getString("Nombre_Categoria")
            ));
        }

    } catch (SQLException ex) {
        System.err.println("Error al listar libros con nombres: " + ex.getMessage());
    }

    return listado;
}

    public boolean insert(Object object){
    Libros libro = (Libros) object;
    String sql = "INSERT INTO Libros (Titulo_Libro, Fecha_Publicación, Editorial_ID, Categoria_ID) VALUES (?, ?, ?, ?);";
    
    try (Connection con = ConnectionBD.getConnection()){
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, libro.getTituloLibro());
        pst.setDate(2, libro.getFechaPublicacion());
        pst.setInt(3, libro.getEditorialId());
        pst.setInt(4, libro.getCategoriaId());
        return pst.executeUpdate() > 0;
        
    } catch (SQLException ex) {
        System.err.println("Error al insertar libro: " + ex.getMessage());
        return false;
    }
    
 }
    public boolean delete(int libroId) {
    String sql = "DELETE FROM Libros WHERE Libro_ID = ?;";
    
    try (Connection con = ConnectionBD.getConnection()) {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, libroId);
        return pst.executeUpdate() > 0;
        
    } catch (SQLException ex) {
        System.err.println("Error al eliminar libro: " + ex.getMessage());
        return false;
    }
}
    public boolean update(Object object) {
     Libros libro = (Libros) object;
     String sql = "UPDATE Libros SET Titulo_Libro = ?, Fecha_Publicación = ?, Editorial_ID = ?, Categoria_ID = ? WHERE Libro_ID = ?";

        try (Connection con = ConnectionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, libro.getTituloLibro());
            pst.setDate(2, libro.getFechaPublicacion());
            pst.setInt(3, libro.getEditorialId());
            pst.setInt(4, libro.getCategoriaId());
            pst.setInt(5, libro.getLibroId());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar libro: " + ex.getMessage());
            return false;
        }
    }
    
    public Libros getById(int id) {
    String query = "SELECT * FROM Libros WHERE Libro_ID = ?";
    Libros libro = null;

    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            libro = new Libros(
                rs.getInt("Libro_ID"),
                rs.getString("Titulo_Libro"),
                rs.getDate("Fecha_Publicación"),
                rs.getInt("Editorial_ID"),
                rs.getInt("Categoria_ID")
            );
        }
    } catch (SQLException ex) {
        System.err.println("Error al obtener libro por ID: " + ex.getMessage());
    }

    return libro;
}

}
