package org.example.uas_pbo_fr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.util.ResourceBundle;

public class UserView implements Initializable {
    @FXML
    private Label lblNamaProduk;
    @FXML
    private Label lblJenisProduk;
    @FXML
    private Label lblMutuProduk;
    @FXML
    private Label lblTanggalProduksi;
    @FXML
    private Label lblTanggalExpired;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnCari;
    @FXML
    private Button userBtnCancel;
    @FXML
    private Button userBtnLogOut;

    @FXML
    private TableView<Produk> userTblViewProduk;
    @FXML
    private TableColumn<Produk, String> userColNamaProduk;
    @FXML
    private TableColumn<Produk, String> userColJenisProduk;
    @FXML
    private TableColumn<Produk, String> userColMutuProduk;
    @FXML
    private TableColumn<Produk, String> userColTanggalProduksi;
    @FXML
    private TableColumn<Produk, String> userColTanggalExpired;

    @FXML
    private ImageView userImageView;

    String selectedItemID;
    String search;

    private void initUserForm() {
        txtSearch.setText("");
        lblNamaProduk.setText("");
        lblJenisProduk.setText("");
        lblMutuProduk.setText("");
        lblTanggalProduksi.setText("");
        lblTanggalExpired.setText("");
        userImageView.setImage(null);
    }

    private void showItemOnTable() {
        userColNamaProduk.setCellValueFactory(cellData -> cellData.getValue().namaProdukProperty());
        userColJenisProduk.setCellValueFactory(cellData -> cellData.getValue().jenisProdukProperty());
        userColMutuProduk.setCellValueFactory(cellData -> cellData.getValue().mutuProdukProperty());
        userColTanggalProduksi.setCellValueFactory(cellData -> cellData.getValue().tanggalProduksiProperty());
        userColTanggalExpired.setCellValueFactory(cellData -> cellData.getValue().tanggalExpiredProperty());

        ObservableList<Produk> items = FXCollections.observableArrayList();

        try (Connection conn = Koneksi.DBConnect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_produk, nama_produk, jenis_produk, mutu_produk, tanggal_produksi, tanggal_expired FROM data_produk");

            while (resultSet.next()) {
                String ID = resultSet.getString(1);
                String name = resultSet.getString(2);
                String jenis = resultSet.getString(3);
                String mutu = resultSet.getString(4);
                String produksi = resultSet.getString(5);
                String expired = resultSet.getString(6);
                items.add(new Produk(ID, name, jenis, mutu, produksi, expired));
            }

            userTblViewProduk.setItems(items);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    private void extractDataFromTable(Produk newValue, Label namaProduk, Label jenisProduk, Label mutuProduk, Label tanggalProduksi, Label tanggalExpired, ImageView imageView) {
        if (newValue != null) {
            selectedItemID = newValue.getIdProduk();

            namaProduk.setText(newValue.getNamaProduk());
            jenisProduk.setText(newValue.getJenisProduk());
            mutuProduk.setText(newValue.getMutuProduk());
            tanggalProduksi.setText(newValue.getTanggalProduksi());
            tanggalExpired.setText(newValue.getTanggalExpired());

            try (Connection conn = Koneksi.DBConnect()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(STR."SELECT gambar_produk FROM data_produk WHERE id_produk = \{selectedItemID}");

                while (resultSet.next()) {
                    imageView.setImage(new Image(resultSet.getString(1)));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void searchItem() throws SQLException {
        userColNamaProduk.setCellValueFactory(cellData -> cellData.getValue().namaProdukProperty());
        userColJenisProduk.setCellValueFactory(cellData -> cellData.getValue().jenisProdukProperty());
        userColMutuProduk.setCellValueFactory(cellData -> cellData.getValue().mutuProdukProperty());
        userColTanggalProduksi.setCellValueFactory(cellData -> cellData.getValue().tanggalProduksiProperty());
        userColTanggalExpired.setCellValueFactory(cellData -> cellData.getValue().tanggalExpiredProperty());

        ObservableList<Produk> items = FXCollections.observableArrayList();

        search = txtSearch.getText();

        try (Connection conn = Koneksi.DBConnect()) {
            String query = "SELECT id_produk, nama_produk, jenis_produk, mutu_produk, tanggal_produksi, tanggal_expired FROM data_produk WHERE nama_produk = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, search);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String ID = resultSet.getString(1);
                String name = resultSet.getString(2);
                String jenis = resultSet.getString(3);
                String mutu = resultSet.getString(4);
                String produksi = resultSet.getString(5);
                String expired = resultSet.getString(6);
                items.add(new Produk(ID, name, jenis, mutu, produksi, expired));
            }

            userTblViewProduk.setItems(items);
        }
    }

    private void cancelSelection() {
        userTblViewProduk.getSelectionModel().clearSelection();
        initUserForm();
        showItemOnTable();
    }

    private void signOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/login.css").toExternalForm());
        Stage stage = (Stage) userBtnLogOut.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initUserForm();
        showItemOnTable();

        userTblViewProduk.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            extractDataFromTable(newValue, lblNamaProduk, lblJenisProduk, lblMutuProduk, lblTanggalProduksi, lblTanggalExpired, userImageView);
        });

        userBtnCancel.setOnAction(actionEvent -> {
            cancelSelection();
        });

        userBtnLogOut.setOnAction(actionEvent -> {
            try {
                signOut();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnCari.setOnAction(actionEvent -> {
            try {
                searchItem();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
