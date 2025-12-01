package bean;

import java.sql.Timestamp;

/**
 * Bean da entidade Uso (relação entre Jogos e Clientes)
 */
public class UsosBean {
    private int id;
    private int idJogo;
    private String cpfCliente;
    private Timestamp dtHoraEmprestimo;
    private Timestamp dtHoraDevolucao;
    private double preco;
    private JogosBean jogo;
    private ClientesBean cliente;

    public UsosBean(int id, int idJogo, String cpfCliente, Timestamp dtHoraEmprestimo, 
                    Timestamp dtHoraDevolucao, double preco) {
        this.id = id;
        this.idJogo = idJogo;
        this.cpfCliente = cpfCliente;
        this.dtHoraEmprestimo = dtHoraEmprestimo;
        this.dtHoraDevolucao = dtHoraDevolucao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Timestamp getDtHoraEmprestimo() {
        return dtHoraEmprestimo;
    }

    public void setDtHoraEmprestimo(Timestamp dtHoraEmprestimo) {
        this.dtHoraEmprestimo = dtHoraEmprestimo;
    }

    public Timestamp getDtHoraDevolucao() {
        return dtHoraDevolucao;
    }

    public void setDtHoraDevolucao(Timestamp dtHoraDevolucao) {
        this.dtHoraDevolucao = dtHoraDevolucao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public JogosBean getJogo() {
        return jogo;
    }

    public void setJogo(JogosBean jogo) {
        this.jogo = jogo;
    }

    public ClientesBean getCliente() {
        return cliente;
    }

    public void setCliente(ClientesBean cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String result = "Uso{" +
                "id=" + id +
                ", idJogo=" + idJogo +
                ", cpfCliente='" + cpfCliente + '\'' +
                ", dtHoraEmprestimo=" + dtHoraEmprestimo +
                ", dtHoraDevolucao=" + dtHoraDevolucao +
                ", preco=" + preco;
        if (jogo != null) result += ", jogo=" + jogo.toString();
        if (cliente != null) result += ", cliente=" + cliente.toString();
        result += '}';
        return result;
    }
}
