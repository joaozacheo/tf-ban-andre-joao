package model;

import bean.ArcadesBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Arcades
 */
public class ArcadesModel {

    public static void create(ArcadesBean arcade, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Arcades (id, qtdTickets) VALUES (?,?)");
        st.setInt(1, arcade.getId());
        st.setInt(2, arcade.getQtdTickets());
        st.execute();
        st.close();
    }
    
    public static HashSet<ArcadesBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<ArcadesBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, qtdTickets FROM Arcades ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new ArcadesBean(result.getInt(1), result.getInt(2)));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Arcades WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(ArcadesBean arcade, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Arcades SET qtdTickets=? WHERE id=?");
        st.setInt(1, arcade.getQtdTickets());
        st.setInt(2, arcade.getId());
        st.execute();
        st.close();
    }
}
