package model;

import javafx.beans.property.*;

public class Atracao {
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty descricao;
    private StringProperty horario;
    private IntegerProperty capacidade;

    public Atracao(int id, String nome, String descricao, String horario, int capacidade) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.horario = new SimpleStringProperty(horario);
        this.capacidade = new SimpleIntegerProperty(capacidade);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public String getHorario() {
        return horario.get();
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public int getCapacidade() {
        return capacidade.get();
    }

    public IntegerProperty capacidadeProperty() {
        return capacidade;
    }
}









