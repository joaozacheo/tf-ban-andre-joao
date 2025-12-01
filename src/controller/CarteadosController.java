package controller;

import bean.CarteadosBean;
import model.CarteadosModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller da entidade Carteados
 */
public class CarteadosController {
    
    public void createCarteado(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Inserir novo Carteado ===");
        System.out.print("ID (deve existir em Jogos): ");
        int id = input.nextInt();
        System.out.print("Quantidade de Cartas: ");
        int qtdCartas = input.nextInt();
        System.out.print("ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        CarteadosBean carteado = new CarteadosBean(id, qtdCartas, idSala, qtdHoras);
        CarteadosModel.create(carteado, con);
        System.out.println("Carteado criado com sucesso!");

        input.close();
    }
    
    public void listarCarteados(Connection con) throws SQLException {
        System.out.println("\n=== Lista de Carteados ===");
        HashSet<CarteadosBean> all = CarteadosModel.listAll(con);
        if (all.isEmpty()) {
            System.out.println("Nenhum carteado cadastrado.");
        } else {
            Iterator<CarteadosBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            }
        }
    }
    
    public void deletarCarteado(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Remover Carteado ===");
        System.out.print("ID do Carteado: ");
        int id = input.nextInt();
        CarteadosModel.delete(id, con);
        System.out.println("Carteado removido com sucesso!");

        input.close();
    }
    
    public void atualizarCarteado(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Atualizar Carteado ===");
        System.out.print("ID do Carteado: ");
        int id = input.nextInt();
        System.out.print("Nova Quantidade de Cartas: ");
        int qtdCartas = input.nextInt();
        System.out.print("Novo ID da Sala (ou 0 para NULL): ");
        int idSalaInput = input.nextInt();
        Integer idSala = idSalaInput > 0 ? idSalaInput : null;
        System.out.print("Nova Quantidade de Horas (ou 0 para NULL): ");
        int qtdHorasInput = input.nextInt();
        Integer qtdHoras = qtdHorasInput > 0 ? qtdHorasInput : null;
        
        CarteadosBean carteado = new CarteadosBean(id, qtdCartas, idSala, qtdHoras);
        CarteadosModel.update(carteado, con);
        System.out.println("Carteado atualizado com sucesso!");

        input.close();
    }
}
