package org.example.uas_pbo_fr;

import javafx.beans.property.SimpleStringProperty;

class Produk {
    private final SimpleStringProperty idProduk;
    private final SimpleStringProperty namaProduk;
    private final SimpleStringProperty jenisProduk;
    private final SimpleStringProperty mutuProduk;
    private final SimpleStringProperty tanggalProduksi;
    private final SimpleStringProperty tanggalExpired;

    public Produk(String id, String namaProduk, String jenisProduk, String mutuProduk, String tanggalProduksi, String tanggalExpired) {
        this.idProduk = new SimpleStringProperty(id);
        this.namaProduk = new SimpleStringProperty(namaProduk);
        this.jenisProduk = new SimpleStringProperty(jenisProduk);
        this.mutuProduk = new SimpleStringProperty(mutuProduk);
        this.tanggalProduksi = new SimpleStringProperty(tanggalProduksi);
        this.tanggalExpired = new SimpleStringProperty(tanggalExpired);
    }

    public String getIdProduk() { return idProduk.get(); }
    public String getNamaProduk() { return namaProduk.get(); }
    public String getJenisProduk() { return jenisProduk.get(); }
    public String getMutuProduk() { return mutuProduk.get(); }
    public String getTanggalProduksi() { return tanggalProduksi.get(); }
    public String getTanggalExpired() { return tanggalExpired.get(); }

    public SimpleStringProperty idProdukProperty() { return idProduk; }
    public SimpleStringProperty namaProdukProperty() { return namaProduk; }
    public SimpleStringProperty jenisProdukProperty() { return jenisProduk; }
    public SimpleStringProperty mutuProdukProperty() { return mutuProduk; }
    public SimpleStringProperty tanggalProduksiProperty() { return tanggalProduksi; }
    public SimpleStringProperty tanggalExpiredProperty() { return tanggalExpired; }
}
