package controller;

import bean.ClientesBean;
import model.ClientesModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Clientes
 */
public class ClientesController {
    
    public void createCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Cliente ===");
        System.out.print("CPF (11 dígitos): ");
        String cpf = input.nextLine();
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        String dtNascStr = input.nextLine();
        Date dtNasc = Date.valueOf(dtNascStr);
        System.out.print("Frequência (visitas): ");
        int frequencia = input.nextInt();
        System.out.print("ID do Cartão (ou 0 para NULL): ");
        int idCartaoInput = input.nextInt();
        Integer idCartao = idCartaoInput > 0 ? idCartaoInput : null;
        System.out.print("Valor do Cartão (ou 0 para NULL): ");
        int valorCartaoInput = input.nextInt();
        Integer valorCartao = valorCartaoInput > 0 ? valorCartaoInput : null;
        
        ClientesBean cliente = new ClientesBean(cpf, nome, dtNasc, frequencia, idCartao, valorCartao);
        ClientesModel.create(cliente, con);
        System.out.println("Cliente criado com sucesso!");

        input.close();
    }
    
    public void listarClientes(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Clientes ===");
        HashSet<ClientesBean> all = ClientesModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            Iterator<ClientesBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Cliente ===");
        System.out.print("CPF do Cliente: ");
        String cpf = input.nextLine();
        ClientesModel.delete(cpf, con);
        System.out.println("Cliente removido com sucesso!");

        input.close();
    }
    
    public void atualizarCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Cliente ===");
        System.out.print("CPF do Cliente: ");
        String cpf = input.nextLine();
        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Nova Data de Nascimento (AAAA-MM-DD): ");
        String dtNascStr = input.nextLine();
        Date dtNasc = Date.valueOf(dtNascStr);
        System.out.print("Nova Frequência: ");
        int frequencia = input.nextInt();
        System.out.print("Novo ID do Cartão (ou 0 para NULL): ");
        int idCartaoInput = input.nextInt();
        Integer idCartao = idCartaoInput > 0 ? idCartaoInput : null;
        System.out.print("Novo Valor do Cartão (ou 0 para NULL): ");
        int valorCartaoInput = input.nextInt();
        Integer valorCartao = valorCartaoInput > 0 ? valorCartaoInput : null;
        
        ClientesBean cliente = new ClientesBean(cpf, nome, dtNasc, frequencia, idCartao, valorCartao);
        ClientesModel.update(cliente, con);
        System.out.println("Cliente atualizado com sucesso!");

        input.close();
    }

    public void listarClientesAcimaDaMedia(Connection con) throws SQLException {
        ClientesModel.listarClientesAcimaDaMedia(con);
    }
}
