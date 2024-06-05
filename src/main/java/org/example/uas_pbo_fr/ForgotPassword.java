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

public class ForgotPassword implements Initializable {
    @FXML
    private ImageView imgViewLogo;
    @FXML
    private Label lblSignIn;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtUlangPassword;
    @FXML
    private Button btnReset;

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
            txtUlangPassword.clear();
        } else {
            txtPassword.clear();
            txtUlangPassword.clear();
        }
    }

    private void signIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/login.css").toExternalForm());
        Stage stage = (Stage) lblSignIn.getScene().getWindow();
        stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
        stage.getIcons().add(new Image(StartApp.class.getResourceAsStream("/assets/icon.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void resetPassword() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String ulangPassword = txtUlangPassword.getText();

        if (username.isBlank() || password.isBlank() || ulangPassword.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter a valid username or password");
            clear(true);
            return;
        }

        if (!password.equals(ulangPassword)) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "The passwords you entered do not match");
            clear(false);
            return;
        }

        if (username.equals("admin")) {
            showAlert(Alert.AlertType.WARNING, "Credential Error", "You are not allowed to do this action");
            clear(true);
            return;
        }

        try (Connection conn = Koneksi.DBConnect()) {
            String checkQuery = "SELECT username FROM user_creds WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, username);
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (!resultSet.next()) {
                        showAlert(Alert.AlertType.WARNING, "Credentials Error", "Username not found");
                        clear(true);
                        return;
                    }
                }
            }


            String insertQuery = "UPDATE user_creds SET password = ? WHERE username = ?";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, password);
                insertStmt.setString(2, username);

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Password was updated successfully");
                    signIn();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while connecting to the database.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initImage();

        btnReset.setOnAction(actionEvent -> {
            resetPassword();
        });

        lblSignIn.setOnMouseClicked(actionEvent -> {
            try {
                signIn();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
