package model;

import bean.TabuleirosBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Tabuleiros
 */
public class TabuleirosModel {

    public static void create(TabuleirosBean tabuleiro, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Tabuleiros (id, qtdPecas, idSala, qtdHoras) VALUES (?,?,?,?)");
        st.setInt(1, tabuleiro.getId());
        st.setInt(2, tabuleiro.getQtdPecas());
        if (tabuleiro.getIdSala() != null) {
            st.setInt(3, tabuleiro.getIdSala());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        if (tabuleiro.getQtdHoras() != null) {
            st.setInt(4, tabuleiro.getQtdHoras());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        st.execute();
        st.close();
    }
    
    public static HashSet<TabuleirosBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<TabuleirosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, qtdPecas, idSala, qtdHoras FROM Tabuleiros ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Integer idSala = result.getObject(3) != null ? result.getInt(3) : null;
            Integer qtdHoras = result.getObject(4) != null ? result.getInt(4) : null;
            list.add(new TabuleirosBean(result.getInt(1), result.getInt(2), idSala, qtdHoras));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Tabuleiros WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(TabuleirosBean tabuleiro, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Tabuleiros SET qtdPecas=?, idSala=?, qtdHoras=? WHERE id=?");
        st.setInt(1, tabuleiro.getQtdPecas());
        if (tabuleiro.getIdSala() != null) {
            st.setInt(2, tabuleiro.getIdSala());
        } else {
            st.setNull(2, java.sql.Types.INTEGER);
        }
        if (tabuleiro.getQtdHoras() != null) {
            st.setInt(3, tabuleiro.getQtdHoras());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        st.setInt(4, tabuleiro.getId());
        st.execute();
        st.close();
    }
}
