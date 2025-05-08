package controller;

import dao.AtracaoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Atracao;

import java.util.List;

public class AtracaoController {

    @FXML private TableView<Atracao> tabelaAtracoes;
    @FXML private TableColumn<Atracao, Integer> colId;
    @FXML private TableColumn<Atracao, String> colNome;
    @FXML private TableColumn<Atracao, String> colDescricao;
    @FXML private TableColumn<Atracao, String> colHorario;
    @FXML private TableColumn<Atracao, Integer> colCapacidade;

    private int idClienteLogado = 1; // Substitua pelo ID real do cliente logado

    @FXML
    private void initialize() {
        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());
        colDescricao.setCellValueFactory(cell -> cell.getValue().descricaoProperty());
        colHorario.setCellValueFactory(cell -> cell.getValue().horarioProperty());
        colCapacidade.setCellValueFactory(cell -> cell.getValue().capacidadeProperty().asObject());

        carregarAtracoes();
    }

    private void carregarAtracoes() {
        List<Atracao> lista = new AtracaoDAO().buscarTodas();
        tabelaAtracoes.getItems().setAll(lista);
    }

    @FXML
    private void btnParticiparAction() {
        Atracao atracao = tabelaAtracoes.getSelectionModel().getSelectedItem();

        if (atracao == null) {
            mostrarAlerta("Selecione uma atração!");
            return;
        }

        boolean sucesso = new AtracaoDAO().registrarParticipacao(idClienteLogado, atracao.getId());

        if (sucesso) {
            mostrarInfo("Participação registrada com sucesso!");
        } else {
            mostrarAlerta("Você não possui ingresso válido ou já participou desta atração.");
        }
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void mostrarInfo(String mensagem) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Sucesso");
        info.setHeaderText(null);
        info.setContentText(mensagem);
        info.showAndWait();
    }
}
















