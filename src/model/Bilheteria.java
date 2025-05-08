package model;

public class Bilheteria {
    private int id;
    private double preco;
    private int quantidadeDisponivel;
    private String horarioFechamento;

    public Bilheteria(int id, double preco, int quantidadeDisponivel, String horarioFechamento) {
        this.id = id;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.horarioFechamento = horarioFechamento;
    }

    public int getId() { return id; }
    public double getPreco() { return preco; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public String getHorarioFechamento() { return horarioFechamento; }
}

