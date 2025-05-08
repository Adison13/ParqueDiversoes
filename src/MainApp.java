import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/login.fxml")));
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Login - Parque de Diversões");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
