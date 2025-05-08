// LoginController.java
package controller;

import dao.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private Label lblMensagem;

    @FXML
    public void loginAction() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            lblMensagem.setText("Informe o email e a senha.");
            return;
        }

        try (Connection conn = ConnectionFactory.conectar()) {
            String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("id");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bilheteria.fxml"));
                Parent root = loader.load();

                BilheteriaController controller = loader.getController();
                controller.setIdClienteLogado(idCliente);

                Stage stage = (Stage) txtEmail.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Bilheteria - Parque 🎟️");
                stage.show();
            } else {
                lblMensagem.setText("Email ou senha inválidos.");
            }
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    public void abrirCadastro() {
        try {
            Parent cadastro = FXMLLoader.load(getClass().getResource("/view/cadastro.fxml"));
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(cadastro));
            stage.setTitle("Cadastro de Cliente");
        } catch (Exception e) {
            lblMensagem.setText("Erro ao abrir cadastro: " + e.getMessage());
        }
    }
}




