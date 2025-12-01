package bean;

import java.sql.Date;

/**
 * Bean da entidade Clientes
 */
public class ClientesBean {
    private String cpf;
    private String nome;
    private Date dtNasc;
    private int frequencia;
    private Integer idCartao;
    private Integer valorCartao;

    public ClientesBean(String cpf, String nome, Date dtNasc, int frequencia, Integer idCartao, Integer valorCartao) {
        this.cpf = cpf;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.frequencia = frequencia;
        this.idCartao = idCartao;
        this.valorCartao = valorCartao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public Integer getValorCartao() {
        return valorCartao;
    }

    public void setValorCartao(Integer valorCartao) {
        this.valorCartao = valorCartao;
    }

    @Override
    public String toString() {
        String result = "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", dtNasc=" + dtNasc +
                ", frequencia=" + frequencia;
        if (idCartao != null) result += ", idCartao=" + idCartao;
        if (valorCartao != null) result += ", valorCartao=" + valorCartao;
        result += '}';
        return result;
    }
}
