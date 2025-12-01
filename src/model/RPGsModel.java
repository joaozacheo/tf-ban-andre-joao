package model;

import bean.RPGsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade RPGs
 */
public class RPGsModel {

    public static void create(RPGsBean rpg, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO RPGs (id, qtdFichasPers, qtdPecas, idSala, qtdHoras) VALUES (?,?,?,?,?)");
        st.setInt(1, rpg.getId());
        st.setInt(2, rpg.getQtdFichasPers());
        st.setInt(3, rpg.getQtdPecas());
        if (rpg.getIdSala() != null) {
            st.setInt(4, rpg.getIdSala());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        if (rpg.getQtdHoras() != null) {
            st.setInt(5, rpg.getQtdHoras());
        } else {
            st.setNull(5, java.sql.Types.INTEGER);
        }
        st.execute();
        st.close();
    }
    
    public static HashSet<RPGsBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<RPGsBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, qtdFichasPers, qtdPecas, idSala, qtdHoras FROM RPGs ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Integer idSala = result.getObject(4) != null ? result.getInt(4) : null;
            Integer qtdHoras = result.getObject(5) != null ? result.getInt(5) : null;
            list.add(new RPGsBean(result.getInt(1), result.getInt(2), result.getInt(3), idSala, qtdHoras));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM RPGs WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(RPGsBean rpg, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE RPGs SET qtdFichasPers=?, qtdPecas=?, idSala=?, qtdHoras=? WHERE id=?");
        st.setInt(1, rpg.getQtdFichasPers());
        st.setInt(2, rpg.getQtdPecas());
        if (rpg.getIdSala() != null) {
            st.setInt(3, rpg.getIdSala());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        if (rpg.getQtdHoras() != null) {
            st.setInt(4, rpg.getQtdHoras());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        st.setInt(5, rpg.getId());
        st.execute();
        st.close();
    }
}
