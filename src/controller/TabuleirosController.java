package controller;

import bean.TabuleirosBean;
import model.TabuleirosModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Tabuleiros
 */
public class TabuleirosController {
    
    public void createTabuleiro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Tabuleiro ===");
        System.out.print("ID (deve existir em Jogos): ");
        int id = input.nextInt();
        System.out.print("Quantidade de Peças: ");
        int qtdPecas = input.nextInt();
        System.out.print("ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        TabuleirosBean tabuleiro = new TabuleirosBean(id, qtdPecas, idSala, qtdHoras);
        TabuleirosModel.create(tabuleiro, con);
        System.out.println("Tabuleiro criado com sucesso!");

        input.close();
    }
    
    public void listarTabuleiros(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Tabuleiros ===");
        HashSet<TabuleirosBean> all = TabuleirosModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum tabuleiro cadastrado.");
        } else {
            Iterator<TabuleirosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarTabuleiro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Tabuleiro ===");
        System.out.print("ID do Tabuleiro: ");
        int id = input.nextInt();
        TabuleirosModel.delete(id, con);
        System.out.println("Tabuleiro removido com sucesso!");

        input.close();
    }
    
    public void atualizarTabuleiro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Tabuleiro ===");
        System.out.print("ID do Tabuleiro: ");
        int id = input.nextInt();
        System.out.print("Nova Quantidade de Peças: ");
        int qtdPecas = input.nextInt();
        System.out.print("Novo ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Nova Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        TabuleirosBean tabuleiro = new TabuleirosBean(id, qtdPecas, idSala, qtdHoras);
        TabuleirosModel.update(tabuleiro, con);
        System.out.println("Tabuleiro atualizado com sucesso!");

        input.close();
    }
}
