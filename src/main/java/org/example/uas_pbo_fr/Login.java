package org.example.uas_pbo_fr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private ImageView imgViewLogo;
    @FXML
    private Button btnSignIn;
    @FXML
    private Label lblLupa;
    @FXML
    private Label lblRegister;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private void initImage() {
        imgViewLogo.setImage(new Image(StartApp.class.getResourceAsStream("/assets/logo.png")));
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clear(boolean withUsername) {
        if (withUsername) {
            txtUsername.clear();
            txtPassword.clear();
        } else {
            txtPassword.clear();
        }
    }

    private void register() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/register.css").toExternalForm());
        Stage stage = (Stage) lblRegister.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
        stage.setScene(scene);
        stage.show();
    }

    private void forgotPassword() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("forgot-password.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/forgot-password.css").toExternalForm());
        Stage stage = (Stage) lblLupa.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
        stage.setScene(scene);
        stage.show();
    }

    private void login() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try (Connection conn = Koneksi.DBConnect()) {
            String query = "SELECT * FROM user_creds WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String credsRole = resultSet.getString(4);
                showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, " + username + "!");
                if (credsRole.equals("admin")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/main.css").toExternalForm());
                    Stage stage = (Stage) btnSignIn.getScene().getWindow();
                    stage.setResizable(false);
                    stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("user-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/user-view.css").toExternalForm());
                    Stage stage = (Stage) btnSignIn.getScene().getWindow();
                    stage.setResizable(false);
                    stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Login Error", "Please enter valid username and password.");
                clear(true);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while connecting to the database.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initImage();

        btnSignIn.setOnAction(actionEvent -> {
            login();
        });

        lblRegister.setOnMouseClicked(actionEvent -> {
            try {
                register();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        lblLupa.setOnMouseClicked(actionEvent -> {
            try {
                forgotPassword();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
