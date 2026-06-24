package Sistema_de_passagensAereas.application;

import Sistema_de_passagensAereas.Entities.Passagem;
import Sistema_de_passagensAereas.Entities.ClasseEconomica;
import Sistema_de_passagensAereas.Entities.ClasseExecutiva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nome do passageiro: ");
            String nome = sc.nextLine();

            System.out.print("Numero do voo: ");
            String voo = sc.nextLine();

            if (nome.trim().isEmpty() || voo.trim().isEmpty()) {
                System.out.println("\nERRO: Nome do passageiro e número do voo não podem ser vazios.");
                return;
            }

            System.out.print("Preço da passagem: ");
            double preco = sc.nextDouble();

            System.out.println("\n1 - Econômica");
            System.out.println("2 - Executiva");
            System.out.print("Escolha a classe: ");
            int opcao = sc.nextInt();

            Passagem passagem = null;

            String classe = "";
            boolean despacharMala = false;

            if (opcao == 1) {

                System.out.print("Deseja despachar mala? (sim/não): ");
                String resposta = sc.next();

                despacharMala = resposta.equalsIgnoreCase("sim");

                passagem = new ClasseEconomica(
                        nome,
                        voo,
                        preco,
                        despacharMala
                );

                classe = "ECONOMICA";

                System.out.println(passagem);

            } else if (opcao == 2) {

                passagem = new ClasseExecutiva(
                        nome,
                        voo,
                        preco
                );

                classe = "EXECUTIVA";

                System.out.println(passagem);

            } else {

                System.out.println("\nOpção de classe inválida!");
                return;
            }

            // Conexão com o banco
            String url = "jdbc:mysql://localhost:3306/passagensaereas";
            String usuario = "root";
            String senha = null;

            Connection con = DriverManager.getConnection(
                    url,
                    usuario,
                    senha
            );

            // Inserir Passageiro
            String sqlPassageiro =
                    "INSERT INTO Passageiro(nome) VALUES(?)";

            PreparedStatement psPassageiro =
                    con.prepareStatement(
                            sqlPassageiro,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            psPassageiro.setString(1, nome);
            psPassageiro.executeUpdate();

            ResultSet rsPassageiro =
                    psPassageiro.getGeneratedKeys();

            rsPassageiro.next();

            int idPassageiro =
                    rsPassageiro.getInt(1);

            // Inserir Voo
            String sqlVoo =
                    "INSERT INTO Voo(numero_voo) VALUES(?)";

            PreparedStatement psVoo =
                    con.prepareStatement(
                            sqlVoo,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            psVoo.setString(1, voo);
            psVoo.executeUpdate();

            ResultSet rsVoo =
                    psVoo.getGeneratedKeys();

            rsVoo.next();

            int idVoo =
                    rsVoo.getInt(1);

            // Inserir Passagem
            String sqlPassagem =
                    "INSERT INTO Passagem(preco, tipo_classe, despachar_mala, id_passageiro, id_voo) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement psPassagem =
                    con.prepareStatement(sqlPassagem);

            psPassagem.setDouble(1, preco);
            psPassagem.setString(2, classe);
            psPassagem.setBoolean(3, despacharMala);
            psPassagem.setInt(4, idPassageiro);
            psPassagem.setInt(5, idVoo);

            psPassagem.executeUpdate();

            System.out.println("\nDados salvos no banco com sucesso!");

            psPassageiro.close();
            psVoo.close();
            psPassagem.close();
            con.close();

        } catch (InputMismatchException e) {

            System.out.println(
                    "\n[ERRO]: Entrada de dados inválida!"
            );

        } catch (Exception e) {

            System.out.println(
                    "\nErro ao salvar no banco: "
                            + e.getMessage()
            );

            sc.close();
        }
    }
}

//***********************Feito por devTuzin. Link: https://github.com/devTuzin***********************