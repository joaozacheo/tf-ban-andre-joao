package bean;

/**
 * Bean da entidade Salas
 */
public class SalasBean {
    private int id;
    private int numero;
    private int capacidade;
    private String tipo;

    public SalasBean(int id, int numero, int capacidade, String tipo) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
