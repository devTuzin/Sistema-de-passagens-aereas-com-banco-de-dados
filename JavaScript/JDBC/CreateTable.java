package Sistema_de_passagensAereas.JDBC;

import JDBC_ClassConnection.ClassConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws SQLException {
        Connection conexao = ClassConnection.getConnection();
        String sql ="CREATE TABLE IF NOT EXISTS Passageiro("
                +"codigo int AUTO_INCREMENT PRIMARY KEY,"
                +"nome VARCHAR(80)NOT NULL"
                +")";

        Statement stmt = conexao.createStatement();
        stmt.execute(sql);

        System.out.println("Tabela criada com sucesso!");
        conexao.close();
    }
}
