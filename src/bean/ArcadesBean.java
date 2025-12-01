package bean;

/**
 * Bean da entidade Arcades (herda de Jogos)
 */
public class ArcadesBean {
    private int id;
    private int qtdTickets;
    private JogosBean jogo;

    public ArcadesBean(int id, int qtdTickets) {
        this.id = id;
        this.qtdTickets = qtdTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdTickets() {
        return qtdTickets;
    }

    public void setQtdTickets(int qtdTickets) {
        this.qtdTickets = qtdTickets;
    }

    public JogosBean getJogo() {
        return jogo;
    }

    public void setJogo(JogosBean jogo) {
        this.jogo = jogo;
    }

    @Override
    public String toString() {
        if (jogo != null) {
            return "Arcade{id=" + id + ", qtdTickets=" + qtdTickets + ", jogo=" + jogo.toString() + "}";
        }
        return "Arcade{id=" + id + ", qtdTickets=" + qtdTickets + "}";
    }
}
