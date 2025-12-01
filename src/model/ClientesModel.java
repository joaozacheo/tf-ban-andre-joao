package model;

import bean.ClientesBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Clientes
 */
public class ClientesModel {

    public static void create(ClientesBean cliente, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Clientes (cpf, nome, dtNasc, frequencia, idCartao, valorCartao) VALUES (?,?,?,?,?,?)");
        st.setString(1, cliente.getCpf());
        st.setString(2, cliente.getNome());
        st.setDate(3, cliente.getDtNasc());
        st.setInt(4, cliente.getFrequencia());
        if (cliente.getIdCartao() != null) {
            st.setInt(5, cliente.getIdCartao());
        } else {
            st.setNull(5, java.sql.Types.INTEGER);
        }
        if (cliente.getValorCartao() != null) {
            st.setInt(6, cliente.getValorCartao());
        } else {
            st.setNull(6, java.sql.Types.INTEGER);
        }
        st.execute();
        st.close();
    }
    
    public static HashSet<ClientesBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<ClientesBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT cpf, nome, dtNasc, frequencia, idCartao, valorCartao FROM Clientes ORDER BY cpf";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Integer idCartao = result.getObject(5) != null ? result.getInt(5) : null;
            Integer valorCartao = result.getObject(6) != null ? result.getInt(6) : null;
            list.add(new ClientesBean(result.getString(1), result.getString(2), result.getDate(3),
                    result.getInt(4), idCartao, valorCartao));
        }
        st.close();
        return list;
    }
    
    public static void delete(String cpf, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Clientes WHERE cpf = ?");
        st.setString(1, cpf);
        st.execute();
        st.close();
    }
    
    public static void update(ClientesBean cliente, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Clientes SET nome=?, dtNasc=?, frequencia=?, idCartao=?, valorCartao=? WHERE cpf=?");
        st.setString(1, cliente.getNome());
        st.setDate(2, cliente.getDtNasc());
        st.setInt(3, cliente.getFrequencia());
        if (cliente.getIdCartao() != null) {
            st.setInt(4, cliente.getIdCartao());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        if (cliente.getValorCartao() != null) {
            st.setInt(5, cliente.getValorCartao());
        } else {
            st.setNull(5, java.sql.Types.INTEGER);
        }
        st.setString(6, cliente.getCpf());
        st.execute();
        st.close();
    }

    public static void listarClientesAcimaDaMedia(Connection con) throws SQLException {
        Statement st;
        
        st = con.createStatement();
        String sql =
            "SELECT c.nome, c.cpf, c.frequencia " +
            "FROM Clientes c " +
            "WHERE c.frequencia > (SELECT AVG(frequencia) FROM Clientes)";

        ResultSet result = st.executeQuery(sql);

        System.out.println("\n=== Clientes com média de frequência acima da média geral ===");
        while (result.next()) {
            System.out.println("Cliente: " + result.getString(1) +
                            " (CPF: " + result.getString(2) + ")" +
                            " | Frequência: " + result.getDouble(3));
        }

        st.close();
    }

}
