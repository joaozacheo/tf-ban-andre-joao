package model;

import bean.UsosBean;
import bean.JogosBean;
import bean.ClientesBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Usos
 */
public class UsosModel {

    public static void create(UsosBean uso, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Uso (id, idJogo, cpfCliente, dtHoraEmprestimo, dtHoraDevolucao, preco) VALUES (?,?,?,?,?,?)");
        st.setInt(1, uso.getId());
        st.setInt(2, uso.getIdJogo());
        st.setString(3, uso.getCpfCliente());
        st.setTimestamp(4, uso.getDtHoraEmprestimo());
        st.setTimestamp(5, uso.getDtHoraDevolucao());
        st.setDouble(6, uso.getPreco());
        st.execute();
        st.close();
    }
    
    public static HashSet<UsosBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<UsosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, idJogo, cpfCliente, dtHoraEmprestimo, dtHoraDevolucao, preco FROM Uso ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new UsosBean(result.getInt(1), result.getInt(2), result.getString(3),
                    result.getTimestamp(4), result.getTimestamp(5), result.getDouble(6)));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Uso WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(UsosBean uso, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Uso SET idJogo=?, cpfCliente=?, dtHoraEmprestimo=?, dtHoraDevolucao=?, preco=? WHERE id=?");
        st.setInt(1, uso.getIdJogo());
        st.setString(2, uso.getCpfCliente());
        st.setTimestamp(3, uso.getDtHoraEmprestimo());
        st.setTimestamp(4, uso.getDtHoraDevolucao());
        st.setDouble(5, uso.getPreco());
        st.setInt(6, uso.getId());
        st.execute();
        st.close();
    }
    
    // Consulta com JOIN: Listar usos com informações de jogos
    public static HashSet<UsosBean> listUsosComJogos(Connection con) throws SQLException {
        Statement st;
        HashSet<UsosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT u.id, u.idJogo, u.cpfCliente, u.dtHoraEmprestimo, u.dtHoraDevolucao, u.preco, " +
                     "j.nome, j.genero, j.idadeMin, j.nJogadores " +
                     "FROM Uso u INNER JOIN Jogos j ON u.idJogo = j.id " +
                     "ORDER BY u.id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            UsosBean uso = new UsosBean(result.getInt(1), result.getInt(2), result.getString(3),
                    result.getTimestamp(4), result.getTimestamp(5), result.getDouble(6));
            JogosBean jogo = new JogosBean(result.getInt(2), result.getString(7), result.getString(8),
                    result.getInt(9), result.getInt(10));
            uso.setJogo(jogo);
            list.add(uso);
        }
        st.close();
        return list;
    }
    
    // Consulta com JOIN: Listar usos com informações de clientes
    public static HashSet<UsosBean> listUsosComClientes(Connection con) throws SQLException {
        Statement st;
        HashSet<UsosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT u.id, u.idJogo, u.cpfCliente, u.dtHoraEmprestimo, u.dtHoraDevolucao, u.preco, " +
                     "c.nome, c.dtNasc, c.frequencia, c.idCartao, c.valorCartao " +
                     "FROM Uso u INNER JOIN Clientes c ON u.cpfCliente = c.cpf " +
                     "ORDER BY u.id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            UsosBean uso = new UsosBean(result.getInt(1), result.getInt(2), result.getString(3),
                    result.getTimestamp(4), result.getTimestamp(5), result.getDouble(6));
            Integer idCartao = result.getObject(10) != null ? result.getInt(10) : null;
            Integer valorCartao = result.getObject(11) != null ? result.getInt(11) : null;
            ClientesBean cliente = new ClientesBean(result.getString(3), result.getString(7), 
                    result.getDate(8), result.getInt(9), idCartao, valorCartao);
            uso.setCliente(cliente);
            list.add(uso);
        }
        st.close();
        return list;
    }
    
    // Consulta com subconsulta e agregação: Total arrecadado por jogo
    public static void listarReceitaPorJogo(Connection con) throws SQLException {
        Statement st;
        st = con.createStatement();
        String sql = "SELECT j.nome, SUM(u.preco) as total_receita, COUNT(u.id) as total_usos " +
                     "FROM (SELECT nome FROM Jogos) as j" +
                     "LEFT JOIN Uso u ON j.id = u.idJogo " +
                     "GROUP BY j.id, j.nome " +
                     "ORDER BY total_receita DESC";
        ResultSet result = st.executeQuery(sql);
        System.out.println("\n=== Receita por Jogo ===");
        while(result.next()) {
            System.out.println("Jogo: " + result.getString(1) + 
                             " | Receita Total: R$ " + result.getDouble(2) + 
                             " | Total de Usos: " + result.getInt(3));
        }
        st.close();
    }
    
    // Consulta com subconsulta e agregação: Clientes que gastaram mais
    public static void listarClientesMaisGastaram(Connection con) throws SQLException {
        Statement st;
        st = con.createStatement();
        String sql = "SELECT c.nome, c.cpf, SUM(u.preco) as total_gasto, COUNT(u.id) as total_usos " +
                     "FROM Clientes c " +
                     "LEFT JOIN Uso u ON c.cpf = u.cpfCliente " +
                     "GROUP BY c.cpf, c.nome " +
                     "HAVING SUM(u.preco) > 0 " +
                     "ORDER BY total_gasto DESC";
        ResultSet result = st.executeQuery(sql);
        System.out.println("\n=== Clientes que Mais Gastaram ===");
        while(result.next()) {
            System.out.println("Cliente: " + result.getString(1) + 
                             " (CPF: " + result.getString(2) + ")" +
                             " | Total Gasto: R$ " + result.getDouble(3) + 
                             " | Total de Usos: " + result.getInt(4));
        }
        st.close();
    }
    
    // Consulta JOIN útil 1: Jogos mais usados/alugados com total de vezes
    public static void listarJogosMaisUsados(Connection con) throws SQLException {
        Statement st;
        st = con.createStatement();
        String sql = "SELECT j.nome, j.genero, COUNT(u.id) as total_usos, SUM(u.preco) as receita_total " +
                     "FROM Jogos j " +
                     "INNER JOIN Uso u ON j.id = u.idJogo " +
                     "GROUP BY j.id, j.nome, j.genero " +
                     "ORDER BY total_usos DESC, receita_total DESC";
        ResultSet result = st.executeQuery(sql);
        System.out.println("\n=== Jogos Mais Alugados (Ranking) ===");
        System.out.println("Ranking | Jogo | Gênero | Vezes Usado | Receita");
        System.out.println("--------|------|--------|-------------|--------");
        int rank = 1;
        while(result.next()) {
            System.out.printf("%d. %-20s | %-15s | %5d vezes | R$ %.2f%n",
                rank++,
                result.getString(1),
                result.getString(2),
                result.getInt(3),
                result.getDouble(4));
        }
        st.close();
    }
    
    // Consulta JOIN útil 2: Histórico completo de cada cliente com detalhes dos jogos
    public static void listarHistoricoClientes(Connection con) throws SQLException {
        Statement st;
        st = con.createStatement();
        String sql = "SELECT c.nome AS cliente, c.cpf, j.nome AS jogo, " +
                     "u.dtHoraEmprestimo, u.dtHoraDevolucao, u.preco, " +
                     "(EXTRACT(EPOCH FROM (u.dtHoraDevolucao - u.dtHoraEmprestimo))/3600) as horas " +
                     "FROM Clientes c " +
                     "INNER JOIN Uso u ON c.cpf = u.cpfCliente " +
                     "INNER JOIN Jogos j ON u.idJogo = j.id " +
                     "ORDER BY c.nome, u.dtHoraEmprestimo DESC";
        ResultSet result = st.executeQuery(sql);
        System.out.println("\n=== Histórico Completo de Clientes ===");
        String clienteAtual = "";
        double totalGasto = 0;
        int totalUsos = 0;
        
        while(result.next()) {
            String cliente = result.getString(1);
            if (!cliente.equals(clienteAtual)) {
                if (!clienteAtual.equals("")) {
                    System.out.println("  └─ Total: " + totalUsos + " usos | R$ " + String.format("%.2f", totalGasto));
                    System.out.println();
                }
                clienteAtual = cliente;
                totalGasto = 0;
                totalUsos = 0;
                System.out.println("Cliente: " + cliente + " (CPF: " + result.getString(2) + ")");
            }
            System.out.printf("  ├─ %-20s | %s a %s (%.1fh) | R$ %.2f%n",
                result.getString(3),
                result.getTimestamp(4),
                result.getTimestamp(5),
                result.getDouble(7),
                result.getDouble(6));
            totalGasto += result.getDouble(6);
            totalUsos++;
        }
        
        if (!clienteAtual.equals("")) {
            System.out.println("  └─ Total: " + totalUsos + " usos | R$ " + String.format("%.2f", totalGasto));
        }
        
        st.close();
    }
}
