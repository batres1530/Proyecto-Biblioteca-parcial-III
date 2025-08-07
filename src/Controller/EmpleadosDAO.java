package Controller;

import Model.CargoComboItem;
import Model.Empleados;
import bd.ConnectionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {

public List<Empleados> getAllEmpleados() {
    List<Empleados> lista = new ArrayList<>();
    String sql = "SELECT e.Empleado_ID, e.Nombre, e.Apellido, e.Cargos_ID, e.Telefono, c.Descripcion AS CargoNombre " +
             "FROM Empleados e " +
             "LEFT JOIN Cargos c ON e.Cargos_ID = c.Cargos_ID";

    try (Connection con = ConnectionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Empleados emp = new Empleados();
            emp.setEmpleadoID(rs.getInt("Empleado_ID"));
            emp.setNombre(rs.getString("Nombre"));
            emp.setApellido(rs.getString("Apellido"));
            emp.setCargosID(rs.getInt("Cargos_ID"));
            emp.setTelefono(rs.getString("Telefono"));
            emp.setCargoNombre(rs.getString("CargoNombre")); 

            lista.add(emp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

    public boolean insert(Empleados emp) {
        String sql = "INSERT INTO Empleados (Nombre, Apellido, Cargos_ID, Telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setInt(3, emp.getCargosID());
            ps.setString(4, emp.getTelefono());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Empleados emp) {
        String sql = "UPDATE Empleados SET Nombre = ?, Apellido = ?, Cargos_ID = ?, Telefono = ? WHERE Empleado_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setInt(3, emp.getCargosID());
            ps.setString(4, emp.getTelefono());
            ps.setInt(5, emp.getEmpleadoID());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Empleados WHERE Empleado_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Empleados buscarPorId(int id) {
        String sql = "SELECT * FROM Empleados WHERE Empleado_ID = ?";

        try (Connection con = ConnectionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Empleados(
                    rs.getInt("Empleado_ID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Cargos_ID"),
                    rs.getString("Telefono")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener empleado: " + e.getMessage());
        }

        return null;
    }
    
    public List<CargoComboItem> obtenerCargosParaCombo() {
    List<CargoComboItem> lista = new ArrayList<>();
    String sql = "SELECT Cargos_ID, Descripcion FROM Cargos";

    try (Connection con = ConnectionBD.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("Cargos_ID");
            String descripcion = rs.getString("Descripcion");
            lista.add(new CargoComboItem(id, descripcion));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

}

