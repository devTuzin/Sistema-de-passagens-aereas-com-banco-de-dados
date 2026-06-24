package Sistema_de_passagensAereas.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBD_2 {
    static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306"; // URL de conexão com o MySQL
        String user = "root"; // Nome do usuário
        String password = null; // Senha do banco de dados

        try (Connection conexao = DriverManager.getConnection(url, user, null)) {
            System.out.println("Conexão realizada com sucesso!");


            Statement stmt = conexao.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS passagensAereas");

            /**APOS CRIAR/MOSTRAR A CLASS DE CONEXÃO TESTAR A CONEXAO ABAIXO**/
            //Connection conexao = ClassConnection.getConnection();

            System.out.println("Banco Criado com sucesso!");

            conexao.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}