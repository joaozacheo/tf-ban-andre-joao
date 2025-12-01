package controller;

import bean.ArcadesBean;
import model.ArcadesModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Arcades
 */
public class ArcadesController {
    
    public void createArcade(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Arcade ===");
        System.out.print("ID (deve existir em Jogos): ");
        int id = input.nextInt();
        System.out.print("Quantidade de Tickets: ");
        int qtdTickets = input.nextInt();
        
        ArcadesBean arcade = new ArcadesBean(id, qtdTickets);
        ArcadesModel.create(arcade, con);
        System.out.println("Arcade criado com sucesso!");

        input.close();
    }
    
    public void listarArcades(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Arcades ===");
        HashSet<ArcadesBean> all = ArcadesModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum arcade cadastrado.");
        } else {
            Iterator<ArcadesBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarArcade(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Arcade ===");
        System.out.print("ID do Arcade: ");
        int id = input.nextInt();
        ArcadesModel.delete(id, con);
        System.out.println("Arcade removido com sucesso!");
        input.close();
    }
    
    public void atualizarArcade(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Arcade ===");
        System.out.print("ID do Arcade: ");
        int id = input.nextInt();
        System.out.print("Nova Quantidade de Tickets: ");
        int qtdTickets = input.nextInt();
        
        ArcadesBean arcade = new ArcadesBean(id, qtdTickets);
        ArcadesModel.update(arcade, con);
        System.out.println("Arcade atualizado com sucesso!");
        input.close();
    }
}
