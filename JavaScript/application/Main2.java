package Sistema_de_passagensAereas.application;

import java.sql.*;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/passagensaereas";
        String usuario = "root";
        String senha = null;

        try {

            Connection con = DriverManager.getConnection(url, usuario, senha);

            System.out.println("\n SISTEMA DE PASSAGENS AÉREAS ");
            System.out.println("1 - Inserir Passagem");
            System.out.println("2 - Consultar Passagens");
            System.out.println("3 - Atualizar Preço");
            System.out.println("4 - Excluir Passagem");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("Nome do passageiro: ");
                    String nome = sc.nextLine();

                    System.out.print("Número do voo: ");
                    String numeroVoo = sc.nextLine();

                    System.out.print("Preço da passagem: ");
                    double preco = sc.nextDouble();

                    System.out.println("\n1 - Econômica");
                    System.out.println("2 - Executiva");
                    System.out.print("Classe: ");

                    int classeOpcao = sc.nextInt();

                    String classe = sc.nextLine();
                    boolean despacharMala = false;


                    if (classeOpcao == 1) {

                        classe = "ECONOMICA";

                        System.out.print("Despachar mala? (Sim/Não): ");
                        String resposta = sc.next();

                        despacharMala = resposta.equalsIgnoreCase("sim");

                    } else {

                        classe = "EXECUTIVA";
                    }

                    // PASSAGEIRO
                    String sqlPassageiro =
                            "INSERT INTO Passageiro(nome) VALUES(?)";

                    PreparedStatement psPassageiro =
                            con.prepareStatement(
                                    sqlPassageiro,
                                    Statement.RETURN_GENERATED_KEYS);

                    psPassageiro.setString(1, nome);
                    psPassageiro.executeUpdate();

                    ResultSet rsPassageiro =
                            psPassageiro.getGeneratedKeys();

                    rsPassageiro.next();
                    int idPassageiro =
                            rsPassageiro.getInt(1);

                    // VOO
                    String sqlVoo =
                            "INSERT INTO Voo(numero_voo) VALUES(?)";

                    PreparedStatement psVoo =
                            con.prepareStatement(
                                    sqlVoo,
                                    Statement.RETURN_GENERATED_KEYS);

                    psVoo.setString(1, numeroVoo);
                    psVoo.executeUpdate();

                    ResultSet rsVoo =
                            psVoo.getGeneratedKeys();

                    rsVoo.next();
                    int idVoo =
                            rsVoo.getInt(1);

                    // PASSAGEM
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

                    System.out.println("\nPassagem cadastrada com sucesso!");

                    break;

                case 2:

                    String consulta =
                            "SELECT " +
                                    "p.id_passageiro, " +
                                    "p.nome, " +
                                    "v.numero_voo, " +
                                    "pa.id_passagem, " +
                                    "pa.preco, " +
                                    "pa.tipo_classe, " +
                                    "pa.despachar_mala " +
                                    "FROM Passagem pa " +
                                    "INNER JOIN Passageiro p ON pa.id_passageiro = p.id_passageiro " +
                                    "INNER JOIN Voo v ON pa.id_voo = v.id_voo";

                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery(consulta);

                    while (rs.next()) {

                        System.out.println("\nID Passagem: "
                                + rs.getInt("id_passagem"));

                        System.out.println("Passageiro: "
                                + rs.getString("nome"));

                        System.out.println("Voo: "
                                + rs.getString("numero_voo"));

                        System.out.println("Preço: R$ "
                                + rs.getDouble("preco"));

                        System.out.println("Classe: "
                                + rs.getString("tipo_classe"));

                        System.out.println("Despachar Mala: "
                                + rs.getBoolean("despachar_mala"));
                    }

                    break;

                case 3:

                    System.out.print("ID da passagem: ");
                    int idAtualizar = sc.nextInt();

                    System.out.print("Novo preço: ");
                    double novoPreco = sc.nextDouble();

                    String update =
                            "UPDATE Passagem SET preco = ? WHERE id_passagem = ?";

                    PreparedStatement psUpdate =
                            con.prepareStatement(update);

                    psUpdate.setDouble(1, novoPreco);
                    psUpdate.setInt(2, idAtualizar);

                    psUpdate.executeUpdate();

                    System.out.println("Preço atualizado!");

                    break;

                case 4:

                    System.out.print("ID da passagem: ");
                    int idExcluir = sc.nextInt();

                    String delete =
                            "DELETE FROM Passagem WHERE id_passagem = ?";

                    PreparedStatement psDelete =
                            con.prepareStatement(delete);

                    psDelete.setInt(1, idExcluir);

                    psDelete.executeUpdate();

                    System.out.println("Passagem excluída!");

                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            con.close();
            sc.close();

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
}

//****************************************************** MADE BY devtuzin ******************************************************
