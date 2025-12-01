package controller;

import bean.JogosBean;
import model.JogosModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Jogos
 */
public class JogosController {
    
    public void createJogo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Jogo ===");
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Gênero: ");
        String genero = input.nextLine();
        System.out.print("Idade Mínima: ");
        int idadeMin = input.nextInt();
        System.out.print("Número de Jogadores: ");
        int nJogadores = input.nextInt();
        
        JogosBean jogo = new JogosBean(id, nome, genero, idadeMin, nJogadores);
        JogosModel.create(jogo, con);
        System.out.println("Jogo criado com sucesso!");

        input.close();
    }
    
    public void listarJogos(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Jogos ===");
        HashSet<JogosBean> all = JogosModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
        } else {
            Iterator<JogosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarJogo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Jogo ===");
        System.out.print("ID do Jogo: ");
        int id = input.nextInt();
        JogosModel.delete(id, con);
        System.out.println("Jogo removido com sucesso!");

        input.close();
    }
    
    public void atualizarJogo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Jogo ===");
        System.out.print("ID do Jogo: ");
        int id = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Novo Gênero: ");
        String genero = input.nextLine();
        System.out.print("Nova Idade Mínima: ");
        int idadeMin = input.nextInt();
        System.out.print("Novo Número de Jogadores: ");
        int nJogadores = input.nextInt();
        
        JogosBean jogo = new JogosBean(id, nome, genero, idadeMin, nJogadores);
        JogosModel.update(jogo, con);
        System.out.println("Jogo atualizado com sucesso!");

        input.close();
    }
}
