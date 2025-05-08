package controller;

import dao.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Ingresso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngressoController {

    @FXML private TableView<Ingresso> tabelaIngressos;
    @FXML private TableColumn<Ingresso, Integer> colId;
    @FXML private TableColumn<Ingresso, String> colCliente;
    @FXML private TableColumn<Ingresso, String> colBilheteria;
    @FXML private TableColumn<Ingresso, String> colPagamento;

    private int idCliente;

    public void setIdCliente(int id) {
        this.idCliente = id;
        carregarIngressos();
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colBilheteria.setCellValueFactory(new PropertyValueFactory<>("descricaoBilheteria"));
        colPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    }

    private void carregarIngressos() {
        List<Ingresso> lista = new ArrayList<>();
        String sql = """
            SELECT i.id, c.nome AS cliente, 
                   CONCAT('Bilheteria ', b.id, ' - R$ ', b.preco) AS bilheteria,
                   i.pagamento
            FROM ingresso i
            JOIN cliente c ON i.id_cliente = c.id
            JOIN bilheteria b ON i.id_bilheteria = b.id
            WHERE c.id = ?
        """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ingresso ing = new Ingresso(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("bilheteria"),
                        rs.getString("pagamento")
                );
                lista.add(ing);
            }

            tabelaIngressos.setItems(FXCollections.observableArrayList(lista));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void fecharJanela() {
        tabelaIngressos.getScene().getWindow().hide();
    }
}











