package vallegrande.edu.pe.tallerpoo1.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vallegrande.edu.pe.tallerpoo1.ConexionDB;

public class VehiculoDAO {

    // 1. REGISTRAR (Create)
    public void registrarVehiculo(Vehiculo v) {
        String sql = "INSERT INTO vehiculo (placa, marca, propietario, problema) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getPropietario());
            ps.setString(4, v.getProblema());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. CONSULTAR (Read)
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setId(rs.getInt("id"));
                v.setPlaca(rs.getString("placa"));
                v.setMarca(rs.getString("marca"));
                v.setPropietario(rs.getString("propietario"));
                v.setProblema(rs.getString("problema"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 3. ACTUALIZAR (Update)
    public void actualizarVehiculo(Vehiculo v) {
        String sql = "UPDATE vehiculo SET placa=?, marca=?, propietario=?, problema=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getPropietario());
            ps.setString(4, v.getProblema());
            ps.setInt(5, v.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. ELIMINAR (Delete)
    public void eliminarVehiculo(int id) {
        String sql = "DELETE FROM vehiculo WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}