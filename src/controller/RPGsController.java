package controller;

import bean.RPGsBean;
import model.RPGsModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade RPGs
 */
public class RPGsController {
    
    public void createRPG(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo RPG ===");
        System.out.print("ID (deve existir em Jogos): ");
        int id = input.nextInt();
        System.out.print("Quantidade de Fichas de Personagem: ");
        int qtdFichasPers = input.nextInt();
        System.out.print("Quantidade de Peças: ");
        int qtdPecas = input.nextInt();
        System.out.print("ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        RPGsBean rpg = new RPGsBean(id, qtdFichasPers, qtdPecas, idSala, qtdHoras);
        RPGsModel.create(rpg, con);
        System.out.println("RPG criado com sucesso!");

        input.close();
    }
    
    public void listarRPGs(Connection con) throws SQLException {
        System.out.println("\n=== Lista de RPGs ===");
        HashSet<RPGsBean> all = RPGsModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum RPG cadastrado.");
        } else {
            Iterator<RPGsBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarRPG(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover RPG ===");
        System.out.print("ID do RPG: ");
        int id = input.nextInt();
        RPGsModel.delete(id, con);
        System.out.println("RPG removido com sucesso!");

        input.close();
    }
    
    public void atualizarRPG(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar RPG ===");
        System.out.print("ID do RPG: ");
        int id = input.nextInt();
        System.out.print("Nova Quantidade de Fichas de Personagem: ");
        int qtdFichasPers = input.nextInt();
        System.out.print("Nova Quantidade de Peças: ");
        int qtdPecas = input.nextInt();
        System.out.print("Novo ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Nova Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        RPGsBean rpg = new RPGsBean(id, qtdFichasPers, qtdPecas, idSala, qtdHoras);
        RPGsModel.update(rpg, con);
        System.out.println("RPG atualizado com sucesso!");

        input.close();
    }
}
