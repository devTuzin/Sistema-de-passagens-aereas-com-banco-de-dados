package Sistema_de_passagensAereas.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassConnection {
    public static Connection getConnection() throws SQLException{
        try {
            // Defina as informações de conexão
            String url = "jdbc:mysql://localhost:3306/passagensAereas"; // URL de conexão com o MySQL
            String user = "root"; // Nome do usuário
            String password = null; // Senha do banco de dados

            // Conectar ao banco de dados
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
