package model;

import bean.SalasBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Salas
 */
public class SalasModel {

    public static void create(SalasBean sala, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Salas (id, numero, capacidade, tipo) VALUES (?,?,?,?)");
        st.setInt(1, sala.getId());
        st.setInt(2, sala.getNumero());
        st.setInt(3, sala.getCapacidade());
        st.setString(4, sala.getTipo());
        st.execute();
        st.close();
    }
    
    public static HashSet<SalasBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<SalasBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, numero, capacidade, tipo FROM Salas ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new SalasBean(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4)));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Salas WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(SalasBean sala, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Salas SET numero=?, capacidade=?, tipo=? WHERE id=?");
        st.setInt(1, sala.getNumero());
        st.setInt(2, sala.getCapacidade());
        st.setString(3, sala.getTipo());
        st.setInt(4, sala.getId());
        st.execute();
        st.close();
    }
}
