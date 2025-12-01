import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import controller.*;

/**
 * Classe Principal - Sistema de Gerenciamento de Loja de Jogos
 */
public class Principal {
    
    private static Scanner input = new Scanner(System.in);
    private static Connection con;

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        con = c.getConnection();
        
        System.out.println("====================================");
        System.out.println("  SISTEMA DE LOJA DE JOGOS");
        System.out.println("====================================");
        
        int op = 0;
        do {
            op = menuPrincipal();
            try {
                switch (op) {
                    case 1: menuJogos(); break;
                    case 2: menuArcades(); break;
                    case 3: menuTabuleiros(); break;
                    case 4: menuCarteados(); break;
                    case 5: menuRPGs(); break;
                    case 6: menuSalas(); break;
                    case 7: menuClientes(); break;
                    case 8: menuUsos(); break;
                    case 9: menuConsultasJoin(); break;
                    case 10: menuConsultasAgregacao(); break;
                    case 11: listarTodasTabelas(); break;
                    case 0: 
                        System.out.println("\nEncerrando o sistema...");
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                        aguardarTecla();
                }
            } catch (SQLException ex) {
                System.out.println("\nErro SQL: " + ex.getMessage());
                aguardarTecla();
            } catch (Exception ex) {
                System.out.println("\nErro: " + ex.getMessage());
                aguardarTecla();
            }
        } while (op != 0);
        
        input.close();
        con.close();
        System.out.println("Sistema encerrado com sucesso!");
    }
    
    private static void aguardarTecla() {
        System.out.print("\nPressione ENTER para continuar...");
        input.nextLine();
        try { input.nextLine(); } catch (Exception e) {}
    }
    
    private static int menuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("           MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1  - Gerenciar Jogos");
        System.out.println("2  - Gerenciar Arcades");
        System.out.println("3  - Gerenciar Tabuleiros");
        System.out.println("4  - Gerenciar Carteados");
        System.out.println("5  - Gerenciar RPGs");
        System.out.println("6  - Gerenciar Salas");
        System.out.println("7  - Gerenciar Clientes");
        System.out.println("8  - Gerenciar Usos");
        System.out.println("9  - Consultas com JOIN");
        System.out.println("10 - Consultas com Agregação");
        System.out.println("11 - Listar TODAS as Tabelas");
        System.out.println("0  - Sair");
        System.out.println("========================================");
        System.out.print("Sua opção: ");
        return input.nextInt();
    }
    
    private static void menuJogos() throws SQLException {
        System.out.println("\n=== MENU JOGOS ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new JogosController().createJogo(con); aguardarTecla(); break;
            case 2: new JogosController().listarJogos(con); aguardarTecla(); break;
            case 3: new JogosController().atualizarJogo(con); aguardarTecla(); break;
            case 4: new JogosController().deletarJogo(con); aguardarTecla(); break;
        }
    }
    
    private static void menuArcades() throws SQLException {
        System.out.println("\n=== MENU ARCADES ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new ArcadesController().createArcade(con); aguardarTecla(); break;
            case 2: new ArcadesController().listarArcades(con); aguardarTecla(); break;
            case 3: new ArcadesController().atualizarArcade(con); aguardarTecla(); break;
            case 4: new ArcadesController().deletarArcade(con); aguardarTecla(); break;
        }
    }
    
    private static void menuTabuleiros() throws SQLException {
        System.out.println("\n=== MENU TABULEIROS ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new TabuleirosController().createTabuleiro(con); aguardarTecla(); break;
            case 2: new TabuleirosController().listarTabuleiros(con); aguardarTecla(); break;
            case 3: new TabuleirosController().atualizarTabuleiro(con); aguardarTecla(); break;
            case 4: new TabuleirosController().deletarTabuleiro(con); aguardarTecla(); break;
        }
    }
    
    private static void menuCarteados() throws SQLException {
        System.out.println("\n=== MENU CARTEADOS ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new CarteadosController().createCarteado(con); aguardarTecla(); break;
            case 2: new CarteadosController().listarCarteados(con); aguardarTecla(); break;
            case 3: new CarteadosController().atualizarCarteado(con); aguardarTecla(); break;
            case 4: new CarteadosController().deletarCarteado(con); aguardarTecla(); break;
        }
    }
    
    private static void menuRPGs() throws SQLException {
        System.out.println("\n=== MENU RPGs ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new RPGsController().createRPG(con); aguardarTecla(); break;
            case 2: new RPGsController().listarRPGs(con); aguardarTecla(); break;
            case 3: new RPGsController().atualizarRPG(con); aguardarTecla(); break;
            case 4: new RPGsController().deletarRPG(con); aguardarTecla(); break;
        }
    }
    
    private static void menuSalas() throws SQLException {
        System.out.println("\n=== MENU SALAS ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new SalasController().createSala(con); aguardarTecla(); break;
            case 2: new SalasController().listarSalas(con); aguardarTecla(); break;
            case 3: new SalasController().atualizarSala(con); aguardarTecla(); break;
            case 4: new SalasController().deletarSala(con); aguardarTecla(); break;
        }
    }
    
    private static void menuClientes() throws SQLException {
        System.out.println("\n=== MENU CLIENTES ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new ClientesController().createCliente(con); aguardarTecla(); break;
            case 2: new ClientesController().listarClientes(con); aguardarTecla(); break;
            case 3: new ClientesController().atualizarCliente(con); aguardarTecla(); break;
            case 4: new ClientesController().deletarCliente(con); aguardarTecla(); break;
        }
    }
    
    private static void menuUsos() throws SQLException {
        System.out.println("\n=== MENU USOS (Empréstimos) ===");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new UsosController().createUso(con); aguardarTecla(); break;
            case 2: new UsosController().listarUsos(con); aguardarTecla(); break;
            case 3: new UsosController().atualizarUso(con); aguardarTecla(); break;
            case 4: new UsosController().deletarUso(con); aguardarTecla(); break;
        }
    }
    
    private static void menuConsultasJoin() throws SQLException {
        System.out.println("\n=== CONSULTAS COM JOIN ===");
        System.out.println("1 - Jogos Mais Alugados (com total de usos)");
        System.out.println("2 - Histórico Completo de Clientes");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new UsosController().listarJogosMaisUsados(con); aguardarTecla(); break;
            case 2: new UsosController().listarHistoricoClientes(con); aguardarTecla(); break;
        }
    }
    
    private static void menuConsultasAgregacao() throws SQLException {
        System.out.println("\n=== CONSULTAS COM AGREGAÇÃO ===");
        System.out.println("1 - Receita Total por Jogo");
        System.out.println("2 - Clientes que Mais Gastaram");
        System.out.println("3 - Clientes Frequentes");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");
        int op = input.nextInt();
        
        switch(op) {
            case 1: new UsosController().listarReceitaPorJogo(con); aguardarTecla(); break;
            case 2: new UsosController().listarClientesMaisGastaram(con); aguardarTecla(); break;
            case 3: new ClientesController().listarClientesAcimaDaMedia(con); aguardarTecla(); break;
        }
    }
    
    private static void listarTodasTabelas() throws SQLException {
        System.out.println("\n========================================");
        System.out.println("      LISTAGEM DE TODAS AS TABELAS");
        System.out.println("========================================");
        
        System.out.println("\n--- JOGOS ---");
        new JogosController().listarJogos(con);
        
        System.out.println("\n--- ARCADES ---");
        new ArcadesController().listarArcades(con);
        
        System.out.println("\n--- TABULEIROS ---");
        new TabuleirosController().listarTabuleiros(con);
        
        System.out.println("\n--- CARTEADOS ---");
        new CarteadosController().listarCarteados(con);
        
        System.out.println("\n--- RPGs ---");
        new RPGsController().listarRPGs(con);
        
        System.out.println("\n--- SALAS ---");
        new SalasController().listarSalas(con);
        
        System.out.println("\n--- CLIENTES ---");
        new ClientesController().listarClientes(con);
        
        System.out.println("\n--- USOS ---");
        new UsosController().listarUsos(con);
        
        aguardarTecla();
    }
}
