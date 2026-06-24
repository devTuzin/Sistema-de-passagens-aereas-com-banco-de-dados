package Sistema_de_passagensAereas.JDBC;


import java.sql.*;

public class SelectWhere {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/passagensAereas";
        String usuario = "root";
        String senha = null;

        String sql = "SELECT * FROM pintinhopreto WHERE nome = ? AND codigo = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "jordan"); // Substitui o primeiro '?'
            stmt.setInt(2, 1);             // Substitui o segundo '?'

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome") + ", Código: " + rs.getInt("codigo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
