package model;

public class Ingresso {
    private int id;
    private String nomeCliente;
    private String descricaoBilheteria;
    private String pagamento;

    public Ingresso(int id, String nomeCliente, String descricaoBilheteria, String pagamento) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.descricaoBilheteria = descricaoBilheteria;
        this.pagamento = pagamento;
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getDescricaoBilheteria() {
        return descricaoBilheteria;
    }

    public String getPagamento() {
        return pagamento;
    }
}




