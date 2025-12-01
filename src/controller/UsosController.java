package controller;

import bean.UsosBean;
import model.UsosModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Usos
 */
public class UsosController {
    
    public void createUso(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Uso ===");
        System.out.print("ID: ");
        int id = input.nextInt();
        System.out.print("ID do Jogo: ");
        int idJogo = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("CPF do Cliente: ");
        String cpfCliente = input.nextLine();
        System.out.print("Data/Hora Empréstimo (AAAA-MM-DD HH:MM:SS): ");
        String dtHoraEmpStr = input.nextLine();
        Timestamp dtHoraEmprestimo = Timestamp.valueOf(dtHoraEmpStr);
        System.out.print("Data/Hora Devolução (AAAA-MM-DD HH:MM:SS): ");
        String dtHoraDevStr = input.nextLine();
        Timestamp dtHoraDevolucao = Timestamp.valueOf(dtHoraDevStr);
        System.out.print("Preço: ");
        double preco = input.nextDouble();
        
        UsosBean uso = new UsosBean(id, idJogo, cpfCliente, dtHoraEmprestimo, dtHoraDevolucao, preco);
        UsosModel.create(uso, con);
        System.out.println("Uso criado com sucesso!");

        input.close();
    }
    
    public void listarUsos(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Usos ===");
        HashSet<UsosBean> all = UsosModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum uso cadastrado.");
        } else {
            Iterator<UsosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarUso(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Uso ===");
        System.out.print("ID do Uso: ");
        int id = input.nextInt();
        UsosModel.delete(id, con);
        System.out.println("Uso removido com sucesso!");

        input.close();
    }
    
    public void atualizarUso(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Uso ===");
        System.out.print("ID do Uso: ");
        int id = input.nextInt();
        System.out.print("Novo ID do Jogo: ");
        int idJogo = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Novo CPF do Cliente: ");
        String cpfCliente = input.nextLine();
        System.out.print("Nova Data/Hora Empréstimo (AAAA-MM-DD HH:MM:SS): ");
        String dtHoraEmpStr = input.nextLine();
        Timestamp dtHoraEmprestimo = Timestamp.valueOf(dtHoraEmpStr);
        System.out.print("Nova Data/Hora Devolução (AAAA-MM-DD HH:MM:SS): ");
        String dtHoraDevStr = input.nextLine();
        Timestamp dtHoraDevolucao = Timestamp.valueOf(dtHoraDevStr);
        System.out.print("Novo Preço: ");
        double preco = input.nextDouble();
        
        UsosBean uso = new UsosBean(id, idJogo, cpfCliente, dtHoraEmprestimo, dtHoraDevolucao, preco);
        UsosModel.update(uso, con);
        System.out.println("Uso atualizado com sucesso!");

        input.close();
    }
    
    public void listarUsosComJogos(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Usos com Informações dos Jogos ===");
        HashSet<UsosBean> all = UsosModel.listUsosComJogos(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum uso cadastrado.");
        } else {
            Iterator<UsosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void listarUsosComClientes(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Usos com Informações dos Clientes ===");
        HashSet<UsosBean> all = UsosModel.listUsosComClientes(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum uso cadastrado.");
        } else {
            Iterator<UsosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void listarReceitaPorJogo(Connection con) throws SQLException {
        UsosModel.listarReceitaPorJogo(con);
    }
    
    public void listarClientesMaisGastaram(Connection con) throws SQLException {
        UsosModel.listarClientesMaisGastaram(con);
    }
    
    public void listarJogosMaisUsados(Connection con) throws SQLException {
        UsosModel.listarJogosMaisUsados(con);
    }
    
    public void listarHistoricoClientes(Connection con) throws SQLException {
        UsosModel.listarHistoricoClientes(con);
    }
}
