package model;

public class AtracaoCliente {
    private int id;
    private int idAtracao;
    private int idIngresso;

    public AtracaoCliente(int id, int idAtracao, int idIngresso) {
        this.id = id;
        this.idAtracao = idAtracao;
        this.idIngresso = idIngresso;
    }

    public AtracaoCliente(int idAtracao, int idIngresso) {
        this.idAtracao = idAtracao;
        this.idIngresso = idIngresso;
    }

    public int getId() {
        return id;
    }

    public int getIdAtracao() {
        return idAtracao;
    }

    public int getIdIngresso() {
        return idIngresso;
    }
}


