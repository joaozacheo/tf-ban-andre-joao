package bean;

/**
 * Bean da entidade Carteados (herda de Jogos)
 */
public class CarteadosBean {
    private int id;
    private int qtdCartas;
    private Integer idSala;
    private Integer qtdHoras;
    private JogosBean jogo;

    public CarteadosBean(int id, int qtdCartas, Integer idSala, Integer qtdHoras) {
        this.id = id;
        this.qtdCartas = qtdCartas;
        this.idSala = idSala;
        this.qtdHoras = qtdHoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdCartas() {
        return qtdCartas;
    }

    public void setQtdCartas(int qtdCartas) {
        this.qtdCartas = qtdCartas;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public Integer getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(Integer qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public JogosBean getJogo() {
        return jogo;
    }

    public void setJogo(JogosBean jogo) {
        this.jogo = jogo;
    }

    @Override
    public String toString() {
        String result = "Carteado{id=" + id + ", qtdCartas=" + qtdCartas;
        if (idSala != null) result += ", idSala=" + idSala;
        if (qtdHoras != null) result += ", qtdHoras=" + qtdHoras;
        if (jogo != null) result += ", jogo=" + jogo.toString();
        result += "}";
        return result;
    }
}
