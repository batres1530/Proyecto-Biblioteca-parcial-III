package Controller;

import Model.EmpleadoComboItem;
import Model.LibroComboItem;
import Model.PrestamoComboItem;
import bd.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Prestamo;
import Model.UsuarioComboItem;

public class PrestamosDAO {
    
    
    public List<PrestamoComboItem> getAllPrestamosConLibro() {
    List<PrestamoComboItem> lista = new ArrayList<>();
    String sql = "SELECT p.Prestamo_ID, l.Titulo_Libro " +
                 "FROM Prestamos p " +
                 "JOIN Libros l ON p.Libro_ID = l.Libro_ID";

    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("Prestamo_ID");
            String tituloLibro = rs.getString("Titulo_Libro");
            PrestamoComboItem item = new PrestamoComboItem(id, "ID: " + id + " - " + tituloLibro);
            lista.add(item);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return lista;
}
    public List<Object> getAll() {
        List<Object> listado = new ArrayList<>();
        String query = "select * from prestamos;";
        try (Connection con = ConnectionBD.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            
          while (resultado.next()) {
          listado.add(new Prestamo(
          resultado.getInt("Prestamo_ID"),
          resultado.getInt("Usuario_ID"),
          resultado.getInt("Libro_ID"),
          resultado.getInt("Empleado_ID"),
          resultado.getDate("Fecha_Prestamo"),
          resultado.getDate("Fecha_Limite")
            ));
                
 
}
        }catch (SQLException ex) {

            System.err.println("error al listar usuarios: " + ex.getMessage());
        }

        return listado;     
   }
    
       public boolean insertar(Prestamo prestamo) {
    String sql = "INSERT INTO prestamos (Usuario_ID, Libro_ID, Empleado_ID, Fecha_Prestamo, Fecha_Limite) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, prestamo.getUsuarioId());
        ps.setInt(2, prestamo.getLibroId());
        ps.setInt(3, prestamo.getEmpleadoId());
        ps.setDate(4, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
        ps.setDate(5, java.sql.Date.valueOf(prestamo.getFechaLimite()));

        ps.executeUpdate();
        return true;

    } catch (SQLException ex) {
        System.err.println("error al insertar préstamo: " + ex.getMessage());
        return false;
    }
}
       public boolean actualizar(Prestamo p) {
    String sql = "UPDATE prestamos SET Usuario_ID = ?, Libro_ID = ?, Empleado_ID = ?, Fecha_Prestamo = ?, Fecha_Limite = ? WHERE Prestamo_ID = ?";

    try (Connection con = bd.ConnectionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, p.getUsuarioId());
        ps.setInt(2, p.getLibroId());
        ps.setInt(3, p.getEmpleadoId());
        ps.setDate(4, java.sql.Date.valueOf(p.getFechaPrestamo()));
        ps.setDate(5, java.sql.Date.valueOf(p.getFechaLimite()));
        ps.setInt(6, p.getId());

        return ps.executeUpdate() > 0;

    } catch (SQLException ex) {
        System.err.println("error al actualizar prestamo: " + ex.getMessage());
        return false;
    }
}
    public boolean eliminar(int id) {
    String query = "DELETE FROM Prestamos WHERE Prestamo_ID = ?";

    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {

        stmt.setInt(1, id);
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException ex) {
        System.err.println("error al eliminar prestamo: " + ex.getMessage());
        return false;
    }
}
    
    public List<Prestamo> getAllConNombres() {
    List<Prestamo> lista = new ArrayList<>();
    String sql = "SELECT p.Prestamo_ID, p.Fecha_Prestamo, p.Fecha_Limite, " +
                 "u.Nombre AS NombreUsuario, " +
                 "l.Titulo_Libro AS TituloLibro, " +
                 "e.Nombre AS NombreEmpleado " +
                 "FROM Prestamos p " +
                 "LEFT JOIN Usuarios u ON p.Usuario_ID = u.Usuario_ID " +
                 "LEFT JOIN Libros l ON p.Libro_ID = l.Libro_ID " +
                 "LEFT JOIN Empleados e ON p.Empleado_ID = e.Empleado_ID";

    try (Connection con = ConnectionBD.getConnection();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Prestamo p = new Prestamo(
                rs.getInt("Prestamo_ID"),
                0, 0, 0,
                rs.getDate("Fecha_Prestamo"),
                rs.getDate("Fecha_Limite")
            );
            p.setNombreUsuario(rs.getString("NombreUsuario"));
            p.setTituloLibro(rs.getString("TituloLibro"));
            p.setNombreEmpleado(rs.getString("NombreEmpleado"));
            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

    
     public List<UsuarioComboItem> obtenerUsuariosParaCombo() {
        List<UsuarioComboItem> lista = new ArrayList<>();
        String sql = "SELECT Usuario_ID, Nombre FROM Usuarios";

        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new UsuarioComboItem(rs.getInt("Usuario_ID"), rs.getString("Nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<EmpleadoComboItem> obtenerEmpleadosParaCombo() {
        List<EmpleadoComboItem> lista = new ArrayList<>();
        String sql = "SELECT Empleado_ID, Nombre FROM Empleados";

        try (Connection con = ConnectionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new EmpleadoComboItem(rs.getInt("Empleado_ID"), rs.getString("Nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<LibroComboItem> obtenerLibrosParaCombo() {
        List<LibroComboItem> lista = new ArrayList<>();
        String sql = "SELECT Libro_ID, Titulo_Libro FROM Libros";

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

    public Prestamo buscarPorId(int id) {
        String sql = "SELECT * FROM Prestamos WHERE Prestamo_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Prestamo(
                        rs.getInt("Prestamo_ID"),
                        rs.getInt("Usuario_ID"),
                        rs.getInt("Libro_ID"),
                        rs.getInt("Empleado_ID"),
                        rs.getDate("Fecha_Prestamo"),
                        rs.getDate("Fecha_Limite")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
}
