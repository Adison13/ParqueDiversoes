package controller;

import dao.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bilheteria;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilheteriaController {

    @FXML private TableView<Bilheteria> tabelaBilheteria;
    @FXML private TableColumn<Bilheteria, Integer> colId;
    @FXML private TableColumn<Bilheteria, Double> colPreco;
    @FXML private TableColumn<Bilheteria, Integer> colQuantidade;
    @FXML private TableColumn<Bilheteria, String> colHorario;

    @FXML private ComboBox<String> comboPagamento;
    @FXML private Label lblMensagem;

    private int idClienteLogado;

    // Setter para receber o ID do cliente após login
    public void setIdClienteLogado(int idCliente) {
        this.idClienteLogado = idCliente;
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeDisponivel"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horarioFechamento"));

        comboPagamento.setItems(FXCollections.observableArrayList("pix", "credito", "debito", "dinheiro"));

        carregarBilheterias();
    }

    private void carregarBilheterias() {
        List<Bilheteria> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.conectar()) {
            String sql = "SELECT * FROM bilheteria";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Bilheteria b = new Bilheteria(
                        rs.getInt("id"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade_disponivel"),
                        rs.getString("horario_fechamento")
                );
                lista.add(b);
            }
            tabelaBilheteria.setItems(FXCollections.observableArrayList(lista));
        } catch (SQLException e) {
            lblMensagem.setText("Erro ao carregar bilhetes: " + e.getMessage());
        }
    }

    @FXML
    public void comprarIngresso() {
        Bilheteria selecionada = tabelaBilheteria.getSelectionModel().getSelectedItem();
        String forma = comboPagamento.getValue();

        if (selecionada == null || forma == null) {
            lblMensagem.setText("Selecione a bilheteria e a forma de pagamento.");
            return;
        }

        try (Connection conn = ConnectionFactory.conectar()) {
            String sql = "INSERT INTO ingresso (id_cliente, id_bilheteria, pagamento) VALUES (?, ?, ?::forma_pagamento)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idClienteLogado);
            stmt.setInt(2, selecionada.getId());
            stmt.setString(3, forma);
            stmt.executeUpdate();

            lblMensagem.setStyle("-fx-text-fill: green;");
            lblMensagem.setText("Ingresso comprado com sucesso!");
            carregarBilheterias();
        } catch (SQLException e) {
            lblMensagem.setText("Erro ao comprar: " + e.getMessage());
        }
    }

    @FXML
    public void abrirAtracoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/atracoes.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Atrações");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            lblMensagem.setText("Erro ao abrir atrações: " + e.getMessage());
        }
    }

    @FXML
    public void abrirIngressos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ingressos.fxml"));
            Parent root = loader.load();

            // Passa o idClienteLogado para IngressoController
            controller.IngressoController ingressoCtrl = loader.getController();
            ingressoCtrl.setIdCliente(idClienteLogado);

            Stage stage = new Stage();
            stage.setTitle("Meus Ingressos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            lblMensagem.setText("Erro ao abrir ingressos: " + e.getMessage());
        }
    }
}








