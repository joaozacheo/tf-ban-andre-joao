package model;

import bean.JogosBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Model da entidade Jogos
 */
public class JogosModel {

    public static void create(JogosBean jogo, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Jogos (id, nome, genero, idadeMin, nJogadores) VALUES (?,?,?,?,?)");
        st.setInt(1, jogo.getId());
        st.setString(2, jogo.getNome());
        st.setString(3, jogo.getGenero());
        st.setInt(4, jogo.getIdadeMin());
        st.setInt(5, jogo.getnJogadores());
        st.execute();
        st.close();
    }
    
    public static HashSet<JogosBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<JogosBean> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, nome, genero, idadeMin, nJogadores FROM Jogos ORDER BY id";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new JogosBean(result.getInt(1), result.getString(2), result.getString(3), 
                    result.getInt(4), result.getInt(5)));
        }
        st.close();
        return list;
    }
    
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Jogos WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
    
    public static void update(JogosBean jogo, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE Jogos SET nome=?, genero=?, idadeMin=?, nJogadores=? WHERE id=?");
        st.setString(1, jogo.getNome());
        st.setString(2, jogo.getGenero());
        st.setInt(3, jogo.getIdadeMin());
        st.setInt(4, jogo.getnJogadores());
        st.setInt(5, jogo.getId());
        st.execute();
        st.close();
    }
}
