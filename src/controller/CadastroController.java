package controller;

import dao.ClienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cliente;

public class CadastroController {

    @FXML private TextField txtNome, txtCpf, txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private Label lblMensagem;

    @FXML
    public void cadastrar() {
        try {
            // Corrigido: ordem correta dos parâmetros
            Cliente cliente = new Cliente(
                    txtNome.getText().trim(),
                    txtEmail.getText().trim(),
                    txtCpf.getText().trim(),
                    txtSenha.getText().trim()
            );

            new ClienteDAO().inserir(cliente);

            // Alerta de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro Realizado");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro realizado com sucesso!");
            alert.showAndWait();

            // Voltar para tela de login
            Parent login = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = (Stage) txtNome.getScene().getWindow();
            stage.setScene(new Scene(login));
            stage.setTitle("Login - Parque 🎢");

        } catch (Exception e) {
            String erro = e.getMessage();

            if (erro.contains("cliente_cpf_key")) {
                lblMensagem.setText("CPF já cadastrado.");
            } else if (erro.contains("cliente_email_key")) {
                lblMensagem.setText("Email já cadastrado.");
            } else {
                lblMensagem.setText("Erro ao cadastrar: " + erro);
            }
        }
    }
}








