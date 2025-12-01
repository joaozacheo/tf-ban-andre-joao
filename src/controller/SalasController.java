package controller;

import bean.SalasBean;
import model.SalasModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Salas
 */
public class SalasController {
    
    public void createSala(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir nova Sala ===");
        System.out.print("ID: ");
        int id = input.nextInt();
        System.out.print("Número: ");
        int numero = input.nextInt();
        System.out.print("Capacidade: ");
        int capacidade = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Tipo (RPG, tabuleiro, etc.): ");
        String tipo = input.nextLine();
        
        SalasBean sala = new SalasBean(id, numero, capacidade, tipo);
        SalasModel.create(sala, con);
        System.out.println("Sala criada com sucesso!");

        input.close();
    }
    
    public void listarSalas(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Salas ===");
        HashSet<SalasBean> all = SalasModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
        } else {
            Iterator<SalasBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarSala(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Sala ===");
        System.out.print("ID da Sala: ");
        int id = input.nextInt();
        SalasModel.delete(id, con);
        System.out.println("Sala removida com sucesso!");

        input.close();
    }
    
    public void atualizarSala(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Sala ===");
        System.out.print("ID da Sala: ");
        int id = input.nextInt();
        System.out.print("Novo Número: ");
        int numero = input.nextInt();
        System.out.print("Nova Capacidade: ");
        int capacidade = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Novo Tipo: ");
        String tipo = input.nextLine();
        
        SalasBean sala = new SalasBean(id, numero, capacidade, tipo);
        SalasModel.update(sala, con);
        System.out.println("Sala atualizada com sucesso!");

        input.close();
    }
}
