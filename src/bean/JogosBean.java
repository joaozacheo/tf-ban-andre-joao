package bean;

/**
 * Bean da entidade Jogos
 */
public class JogosBean {
    private int id;
    private String nome;
    private String genero;
    private int idadeMin;
    private int nJogadores;

    public JogosBean(int id, String nome, String genero, int idadeMin, int nJogadores) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idadeMin = idadeMin;
        this.nJogadores = nJogadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdadeMin() {
        return idadeMin;
    }

    public void setIdadeMin(int idadeMin) {
        this.idadeMin = idadeMin;
    }

    public int getnJogadores() {
        return nJogadores;
    }

    public void setnJogadores(int nJogadores) {
        this.nJogadores = nJogadores;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", idadeMin=" + idadeMin +
                ", nJogadores=" + nJogadores +
                '}';
    }
}
