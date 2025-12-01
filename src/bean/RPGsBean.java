package bean;

/**
 * Bean da entidade RPGs (herda de Jogos)
 */
public class RPGsBean {
    private int id;
    private int qtdFichasPers;
    private int qtdPecas;
    private Integer idSala;
    private Integer qtdHoras;
    private JogosBean jogo;

    public RPGsBean(int id, int qtdFichasPers, int qtdPecas, Integer idSala, Integer qtdHoras) {
        this.id = id;
        this.qtdFichasPers = qtdFichasPers;
        this.qtdPecas = qtdPecas;
        this.idSala = idSala;
        this.qtdHoras = qtdHoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdFichasPers() {
        return qtdFichasPers;
    }

    public void setQtdFichasPers(int qtdFichasPers) {
        this.qtdFichasPers = qtdFichasPers;
    }

    public int getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(int qtdPecas) {
        this.qtdPecas = qtdPecas;
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
        String result = "RPG{id=" + id + ", qtdFichasPers=" + qtdFichasPers + ", qtdPecas=" + qtdPecas;
        if (idSala != null) result += ", idSala=" + idSala;
        if (qtdHoras != null) result += ", qtdHoras=" + qtdHoras;
        if (jogo != null) result += ", jogo=" + jogo.toString();
        result += "}";
        return result;
    }
}
