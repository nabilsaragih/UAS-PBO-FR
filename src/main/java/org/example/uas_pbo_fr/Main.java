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
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.StringTemplate.STR;

public class Main implements Initializable {
    @FXML
    private Pane panel1;
    @FXML
    private Pane panel2;
    @FXML
    private Pane panel3;
    @FXML
    private Pane panel4;
    @FXML
    private Pane panel5;
    @FXML
    private Pane paneTambah;
    @FXML
    private Pane paneLihat;
    @FXML
    private Pane paneUbah;
    @FXML
    private Pane paneHapus;
    @FXML
    private Pane paneAbout;

    @FXML
    private TextField tambahTxtNamaProduk;
    @FXML
    private ChoiceBox<String> tambahCbJenisProduk;
    @FXML
    private RadioButton tambahRbIndustrial;
    @FXML
    private RadioButton tambahRbFood;
    @FXML
    private RadioButton tambahRbPharma;
    @FXML
    private RadioButton tambahRbFeed;
    @FXML
    private DatePicker tambahDtProduksi;
    @FXML
    private DatePicker tambahDtExpired;

    @FXML
    private ImageView tambahShowImageView;
    @FXML
    private ImageView lihatShowImageView;

    @FXML
    private ToggleGroup tambahToggleGroup;
    @FXML
    private ToggleGroup ubahToggleGroup;
    @FXML
    private ToggleGroup hapusToggleGroup;

    @FXML
    private TableView<Produk> tambahTblViewProduk;
    @FXML
    private TableColumn<Produk, String> tambahColIDProduk;
    @FXML
    private TableColumn<Produk, String> tambahColNamaProduk;
    @FXML
    private TableColumn<Produk, String> tambahColJenisProduk;
    @FXML
    private TableColumn<Produk, String> tambahColMutuProduk;
    @FXML
    private TableColumn<Produk, String> tambahColTanggalProduksi;
    @FXML
    private TableColumn<Produk, String> tambahColTanggalExpired;

    @FXML
    private TableView<Produk> lihatTblViewProduk;
    @FXML
    private TableColumn<Produk, String> lihatColIDProduk;
    @FXML
    private TableColumn<Produk, String> lihatColNamaProduk;
    @FXML
    private TableColumn<Produk, String> lihatColJenisProduk;
    @FXML
    private TableColumn<Produk, String> lihatColMutuProduk;
    @FXML
    private TableColumn<Produk, String> lihatColTanggalProduksi;
    @FXML
    private TableColumn<Produk, String> lihatColTanggalExpired;

    @FXML
    private TableView<Produk> ubahTblViewProduk;
    @FXML
    private TableColumn<Produk, String> ubahColIDProduk;
    @FXML
    private TableColumn<Produk, String> ubahColNamaProduk;
    @FXML
    private TableColumn<Produk, String> ubahColJenisProduk;
    @FXML
    private TableColumn<Produk, String> ubahColMutuProduk;
    @FXML
    private TableColumn<Produk, String> ubahColTanggalProduksi;
    @FXML
    private TableColumn<Produk, String> ubahColTanggalExpired;

    @FXML
    private TextField ubahTxtNamaProduk;
    @FXML
    private ChoiceBox<String> ubahCbJenisProduk;
    @FXML
    private RadioButton ubahRbIndustrial;
    @FXML
    private RadioButton ubahRbFood;
    @FXML
    private RadioButton ubahRbPharma;
    @FXML
    private RadioButton ubahRbFeed;
    @FXML
    private DatePicker ubahDtProduksi;
    @FXML
    private DatePicker ubahDtExpired;

    @FXML
    private TextField hapusTxtNamaProduk;
    @FXML
    private ChoiceBox<String> hapusCbJenisProduk;
    @FXML
    private RadioButton hapusRbIndustrial;
    @FXML
    private RadioButton hapusRbFood;
    @FXML
    private RadioButton hapusRbPharma;
    @FXML
    private RadioButton hapusRbFeed;
    @FXML
    private DatePicker hapusDtProduksi;
    @FXML
    private DatePicker hapusDtExpired;

    @FXML
    private TableView<Produk> hapusTblViewProduk;
    @FXML
    private TableColumn<Produk, String> hapusColIDProduk;
    @FXML
    private TableColumn<Produk, String> hapusColNamaProduk;
    @FXML
    private TableColumn<Produk, String> hapusColJenisProduk;
    @FXML
    private TableColumn<Produk, String> hapusColMutuProduk;
    @FXML
    private TableColumn<Produk, String> hapusColTanggalProduksi;
    @FXML
    private TableColumn<Produk, String> hapusColTanggalExpired;

    @FXML
    private Button btnTambah;
    @FXML
    private Button btnUbah;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnTambahGambar;

    @FXML
    private Button tambahBtnCancel;
    @FXML
    private Button tambahBtnLogOut;
    @FXML
    private Button ubahBtnCancel;
    @FXML
    private Button ubahBtnLogOut;
    @FXML
    private Button hapusBtnCancel;
    @FXML
    private Button hapusBtnLogOut;

    private static String selectedItemID;
    private String imagePath;

    private void selectPane(Pane paneClicked, Pane pane2, Pane pane3, Pane pane4, Pane pane5) {
        paneClicked.setBackground(Background.fill(Color.color(0.003, 0.239, 0.870)));
        pane2.setBackground(Background.fill(Color.color(0, 0, 0)));
        pane3.setBackground(Background.fill(Color.color(0, 0, 0)));
        pane4.setBackground(Background.fill(Color.color(0, 0, 0)));
        pane5.setBackground(Background.fill(Color.color(0, 0, 0)));
    }

    private void showPane(Pane paneShow, Pane pane2, Pane pane3, Pane pane4, Pane pane5) {
        paneShow.setVisible(true);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clear(String menu) {
        switch (menu) {
            case "tambah":
                clearFields(tambahTxtNamaProduk, tambahCbJenisProduk, tambahRbIndustrial, tambahRbFood, tambahRbPharma, tambahRbFeed, tambahDtProduksi, tambahDtExpired, tambahShowImageView);
                break;
            case "ubah":
                clearFields(ubahTxtNamaProduk, ubahCbJenisProduk, ubahRbIndustrial, ubahRbFood, ubahRbPharma, ubahRbFeed, ubahDtProduksi, ubahDtExpired, null);
                break;
            case "hapus":
                clearFields(hapusTxtNamaProduk, hapusCbJenisProduk, hapusRbIndustrial, hapusRbFood, hapusRbPharma, hapusRbFeed, hapusDtProduksi, hapusDtExpired, null);
                break;
        }
    }
    private void clearFields(TextField txtNamaProduk, ChoiceBox<String> cbJenisProduk, RadioButton rbIndustrial, RadioButton rbFood, RadioButton rbPharma, RadioButton rbFeed, DatePicker dtProduksi, DatePicker dtExpired, ImageView imageView) {
        txtNamaProduk.clear();
        cbJenisProduk.getSelectionModel().clearSelection();
        rbIndustrial.setSelected(false);
        rbFood.setSelected(false);
        rbPharma.setSelected(false);
        rbFeed.setSelected(false);
        dtProduksi.setValue(null);
        dtExpired.setValue(null);
        if (imageView != null) {
            imageView.setImage(null);
        }
    }

    private void logOut(Button button) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(StartApp.class.getResource("/stylesheet/login.css").toExternalForm());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Donghua Jinlong Chemical Co., LTD.");
        stage.setScene(scene);
        stage.show();
    }

    private void setup() {
        selectPane(panel1, panel2, panel3, panel4, panel5);
        showPane(paneTambah, paneLihat, paneUbah, paneHapus, paneAbout);

        tambahToggleGroup = new ToggleGroup();
        tambahRbIndustrial.setToggleGroup(tambahToggleGroup);
        tambahRbFood.setToggleGroup(tambahToggleGroup);
        tambahRbPharma.setToggleGroup(tambahToggleGroup);
        tambahRbFeed.setToggleGroup(tambahToggleGroup);

        ubahToggleGroup = new ToggleGroup();
        ubahRbIndustrial.setToggleGroup(ubahToggleGroup);
        ubahRbFood.setToggleGroup(ubahToggleGroup);
        ubahRbPharma.setToggleGroup(ubahToggleGroup);
        ubahRbFeed.setToggleGroup(ubahToggleGroup);

        hapusToggleGroup = new ToggleGroup();
        hapusRbIndustrial.setToggleGroup(hapusToggleGroup);
        hapusRbFood.setToggleGroup(hapusToggleGroup);
        hapusRbPharma.setToggleGroup(hapusToggleGroup);
        hapusRbFeed.setToggleGroup(hapusToggleGroup);

        ObservableList<String> jenisList = FXCollections.observableArrayList(
                "Glycine", "Hydantoine", "Mg Glycine", "Zn Glycine", "F Glycine", "Ca Glycine", "Na Glycine", "Glycine Carbonate"
        );
        tambahCbJenisProduk.setItems(jenisList);
        ubahCbJenisProduk.setItems(jenisList);
        hapusCbJenisProduk.setItems(jenisList);

        tambahDtProduksi.getEditor().setDisable(true);
        tambahDtProduksi.getEditor().setOpacity(1);
        tambahDtExpired.getEditor().setDisable(true);
        tambahDtExpired.getEditor().setOpacity(1);
    }

    private void showItem(TableColumn<Produk, String> a, TableColumn<Produk, String> b, TableColumn<Produk, String> c, TableColumn<Produk, String> d, TableColumn<Produk, String> e, TableColumn<Produk, String> f, TableView<Produk> g) {
        a.setCellValueFactory(cellData -> cellData.getValue().idProdukProperty());
        b.setCellValueFactory(cellData -> cellData.getValue().namaProdukProperty());
        c.setCellValueFactory(cellData -> cellData.getValue().jenisProdukProperty());
        d.setCellValueFactory(cellData -> cellData.getValue().mutuProdukProperty());
        e.setCellValueFactory(cellData -> cellData.getValue().tanggalProduksiProperty());
        f.setCellValueFactory(cellData -> cellData.getValue().tanggalExpiredProperty());

        ObservableList<Produk> items = FXCollections.observableArrayList();

        try (Connection conn = Koneksi.DBConnect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_produk, nama_produk, jenis_produk, mutu_produk, tanggal_produksi, tanggal_expired FROM data_produk");

            while (resultSet.next()) {
                String ID = resultSet.getString(1);
                String name = resultSet.getString(2);
                String jenis = resultSet.getString(3);
                String golongan = resultSet.getString(4);
                String produksi = resultSet.getString(5);
                String expired = resultSet.getString(6);
                items.add(new Produk(ID, name, jenis, golongan, produksi, expired));
            }

            g.setItems(items);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    private void extractDataFromTable(Produk newValue, TextField txtNamaProduk, ChoiceBox<String> cbJenisProduk, RadioButton rbIndustrial, RadioButton rbFood, RadioButton rbPharma, RadioButton rbFeed, DatePicker dtProduksi, DatePicker dtExpired) {
        if (newValue != null) {
            selectedItemID = newValue.getIdProduk();
            txtNamaProduk.setText(newValue.getNamaProduk());
            cbJenisProduk.setValue(newValue.getJenisProduk());

            String mutu = newValue.getMutuProduk();
            switch (mutu) {
                case "Industrial Grade" -> rbIndustrial.setSelected(true);
                case "Food Additive" -> rbFood.setSelected(true);
                case "Pharmaceutical Standard" -> rbPharma.setSelected(true);
                case "Feed Additive" -> rbFeed.setSelected(true);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateProduksi = LocalDate.parse(newValue.getTanggalProduksi(), formatter);
            LocalDate localDateExpired = LocalDate.parse(newValue.getTanggalExpired(), formatter);
            dtProduksi.setValue(localDateProduksi);
            dtExpired.setValue(localDateExpired);
        }
    }

    private void extractDataForUbah(Produk newValue) {
        extractDataFromTable(newValue, ubahTxtNamaProduk, ubahCbJenisProduk, ubahRbIndustrial, ubahRbFood, ubahRbPharma, ubahRbFeed, ubahDtProduksi, ubahDtExpired);
    }

    private void extractDataForHapus(Produk newValue) {
        extractDataFromTable(newValue, hapusTxtNamaProduk, hapusCbJenisProduk, hapusRbIndustrial, hapusRbFood, hapusRbPharma, hapusRbFeed, hapusDtProduksi, hapusDtExpired);
    }


    private void extractImageFromTable(Produk newValue) throws SQLException {
        if (newValue != null) {
            selectedItemID = newValue.getIdProduk();

            try (Connection conn = Koneksi.DBConnect()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(STR."SELECT gambar_produk FROM data_produk WHERE id_produk = \{selectedItemID}");

                while (resultSet.next()) {
                    imagePath = resultSet.getString(1);
                }

                lihatShowImageView.setImage(new Image(imagePath));
            }
        }
    }

    private String getImagePath() {
        return imagePath;
    }

    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg", "*.JPG", "*.PNG", "*.JPEG"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            tambahShowImageView.setImage(new Image(imagePath));
        }
    }

    private void addItem() {
        boolean nullCheck = !(tambahRbIndustrial.isSelected() || tambahRbFood.isSelected() || tambahRbPharma.isSelected() || tambahRbFeed.isSelected());

        if (tambahTxtNamaProduk.getText().isBlank() || tambahCbJenisProduk.getValue() == null || (tambahDtProduksi.getValue() == null) || (tambahDtProduksi.getValue() == null)) {
            nullCheck = true;
        }

        if (nullCheck) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter blank or empty text field");
        } else {
            String mutuProduk = "";
            if (tambahRbIndustrial.isSelected()) {
                mutuProduk = tambahRbIndustrial.getText();
            } else if (tambahRbFood.isSelected()) {
                mutuProduk= tambahRbFood.getText();
            } else if (tambahRbPharma.isSelected()) {
                mutuProduk = tambahRbPharma.getText();
            } else if (tambahRbFeed.isSelected()) {
                mutuProduk = tambahRbFeed.getText();
            }

            String pathGambar = getImagePath();

            try (Connection conn = Koneksi.DBConnect()) {
                String query = "INSERT INTO data_produk (nama_produk, jenis_produk, mutu_produk, tanggal_produksi, tanggal_expired, gambar_produk) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, tambahTxtNamaProduk.getText());
                preparedStatement.setString(2, tambahCbJenisProduk.getValue());
                preparedStatement.setString(3, mutuProduk);
                preparedStatement.setString(4, tambahDtProduksi.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(5, tambahDtExpired.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(6, pathGambar);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Data Inserted Successfully");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to Insert Data");
                }

                tambahTblViewProduk.getItems().clear();
                showItem(tambahColIDProduk, tambahColNamaProduk, tambahColJenisProduk, tambahColMutuProduk, tambahColTanggalProduksi, tambahColTanggalExpired, tambahTblViewProduk);

                clear("tambah");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while inserting data");
            }
        }
    }

    private void updateItem() {
        boolean nullCheck = !(ubahRbIndustrial.isSelected() || ubahRbFood.isSelected() || ubahRbPharma.isSelected() || ubahRbFeed.isSelected());

        if (ubahTxtNamaProduk.getText().isBlank() || ubahCbJenisProduk.getValue().isBlank() || (ubahDtProduksi.getValue() == null) || (ubahDtProduksi.getValue() == null)) {
            nullCheck = true;
        }

        if (nullCheck) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter blank or empty text field");
        } else {
            String mutuProduk = "";
            if (ubahRbIndustrial.isSelected()) {
                mutuProduk = ubahRbIndustrial.getText();
            } else if (ubahRbFood.isSelected()) {
                mutuProduk= ubahRbFood.getText();
            } else if (ubahRbPharma.isSelected()) {
                mutuProduk = ubahRbPharma.getText();
            } else if (ubahRbFeed.isSelected()) {
                mutuProduk = ubahRbFeed.getText();
            }

            try (Connection conn = Koneksi.DBConnect()) {
                String query = "UPDATE data_produk SET nama_produk = ?, jenis_produk = ?, mutu_produk = ?, tanggal_produksi = ?, tanggal_expired = ? WHERE id_produk = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, ubahTxtNamaProduk.getText());
                preparedStatement.setString(2, ubahCbJenisProduk.getValue());
                preparedStatement.setString(3, mutuProduk);
                preparedStatement.setString(4, ubahDtProduksi.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(5, ubahDtExpired.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(6, selectedItemID);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Data Updated Successfully");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to Update Data");
                }

                ubahTblViewProduk.getItems().clear();
                showItem(ubahColIDProduk, ubahColNamaProduk, ubahColJenisProduk, ubahColMutuProduk, ubahColTanggalProduksi, ubahColTanggalExpired, ubahTblViewProduk);

                clear("ubah");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating data");
            }
        }
    }

    private void deleteItem() {
        try (Connection conn = Koneksi.DBConnect()) {
            String query = "DELETE FROM data_produk WHERE id_produk = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, selectedItemID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Data Deleted Successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to Delete Data");
            }

            hapusTblViewProduk.getItems().clear();
            showItem(hapusColIDProduk, hapusColNamaProduk, hapusColJenisProduk, hapusColMutuProduk, hapusColTanggalProduksi, hapusColTanggalExpired, hapusTblViewProduk);

            clear("hapus");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting data");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        showItem(tambahColIDProduk, tambahColNamaProduk, tambahColJenisProduk, tambahColMutuProduk, tambahColTanggalProduksi, tambahColTanggalExpired, tambahTblViewProduk);

        panel1.setOnMouseClicked(actionEvent -> {
            tambahTblViewProduk.getItems().clear();
            showItem(tambahColIDProduk, tambahColNamaProduk, tambahColJenisProduk, tambahColMutuProduk, tambahColTanggalProduksi, tambahColTanggalExpired, tambahTblViewProduk);

            selectPane(panel1, panel2, panel3, panel4, panel5);
            showPane(paneTambah, paneLihat, paneUbah, paneHapus, paneAbout);
        });

        panel2.setOnMouseClicked(actionEvent -> {
            lihatTblViewProduk.getItems().clear();
            showItem(lihatColIDProduk, lihatColNamaProduk, lihatColJenisProduk, lihatColMutuProduk, lihatColTanggalProduksi, lihatColTanggalExpired, lihatTblViewProduk);

            selectPane(panel2, panel1, panel3, panel4, panel5);
            showPane(paneLihat, paneTambah, paneUbah, paneHapus, paneAbout);
        });

        panel3.setOnMouseClicked(actionEvent -> {
            ubahTblViewProduk.getItems().clear();
            showItem(ubahColIDProduk, ubahColNamaProduk, ubahColJenisProduk, ubahColMutuProduk, ubahColTanggalProduksi, ubahColTanggalExpired, ubahTblViewProduk);

            selectPane(panel3, panel2, panel1, panel4, panel5);
            showPane(paneUbah, paneTambah, paneLihat, paneHapus, paneAbout);
        });

        panel4.setOnMouseClicked(actionEvent -> {
            hapusTblViewProduk.getItems().clear();
            showItem(hapusColIDProduk, hapusColNamaProduk, hapusColJenisProduk, hapusColMutuProduk, hapusColTanggalProduksi, hapusColTanggalExpired, hapusTblViewProduk);

            selectPane(panel4, panel2, panel3, panel1, panel5);
            showPane(paneHapus, paneTambah, paneUbah, paneLihat, paneAbout);
        });

        panel5.setOnMouseClicked(actionEvent -> {
            selectPane(panel5, panel2, panel3, panel4, panel1);
            showPane(paneAbout, paneTambah, paneUbah, paneLihat, paneHapus);
        });

        lihatTblViewProduk.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                extractImageFromTable(newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        ubahTblViewProduk.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clear("ubah");

            try {
                extractDataForUbah(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        hapusTblViewProduk.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clear("hapus");

            try {
                extractDataForHapus(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        btnTambahGambar.setOnAction(actionEvent -> {
            handleUploadImage();
        });

        tambahBtnCancel.setOnAction(actionEvent -> {
            clear("tambah");
        });

        btnTambah.setOnAction(actionEvent -> {
            addItem();
        });

        btnUbah.setOnAction(actionEvent -> {
            updateItem();
        });

        btnHapus.setOnAction(actionEvent -> {
            deleteItem();
        });

        tambahBtnLogOut.setOnAction(actionEvent -> {
            try {
                logOut(tambahBtnLogOut);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ubahBtnCancel.setOnAction(actionEvent -> {
            ubahTblViewProduk.getSelectionModel().clearSelection();

            clear("ubah");
        });

        ubahBtnLogOut.setOnAction(actionEvent -> {
            try {
                logOut(ubahBtnLogOut);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        hapusBtnCancel.setOnAction(actionEvent -> {
            hapusTblViewProduk.getSelectionModel().clearSelection();

            clear("hapus");
        });

        hapusBtnLogOut.setOnAction(actionEvent -> {
            try {
                logOut(hapusBtnLogOut);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
