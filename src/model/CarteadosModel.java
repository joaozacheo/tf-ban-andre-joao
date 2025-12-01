package model;

import bean.CarteadosBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Carteados
 */
public class CarteadosModel {

    public static void create(CarteadosBean carteado, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Carteados (id, qtdCartas, idSala, qtdHoras) VALUES (?,?,?,?)");
        st.setInt(1, carteado.getId());
        st.setInt(2, carteado.getQtdCartas());
        if (carteado.getIdSala() != null) {
            st.setInt(3, carteado.getIdSala());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        if (carteado.getQtdHoras() != null) {
            st.setInt(4, carteado.getQtdHoras());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        st.execute();
        st.close();
    }
    
    public static HashSet<CarteadosBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<CarteadosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, qtdCartas, idSala, qtdHoras FROM Carteados ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Integer idSala = result.getObject(3) != null ? result.getInt(3) : null;
            Integer qtdHoras = result.getObject(4) != null ? result.getInt(4) : null;
            list.add(new CarteadosBean(result.getInt(1), result.getInt(2), idSala, qtdHoras));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Carteados WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(CarteadosBean carteado, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Carteados SET qtdCartas=?, idSala=?, qtdHoras=? WHERE id=?");
        st.setInt(1, carteado.getQtdCartas());
        if (carteado.getIdSala() != null) {
            st.setInt(2, carteado.getIdSala());
        } else {
            st.setNull(2, java.sql.Types.INTEGER);
        }
        if (carteado.getQtdHoras() != null) {
            st.setInt(3, carteado.getQtdHoras());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        st.setInt(4, carteado.getId());
        st.execute();
        st.close();
    }
}
