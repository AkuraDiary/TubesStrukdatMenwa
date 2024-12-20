package data.schemas.models;

import util.AppEnums;

public class Produk {
    int produkId, produkRentalPrice;
    String produkName, produkBrand;
    AppEnums.RentalInterval produkRentalInterval;
    public Produk(int produkId, String produkName, String produkBrand, int produkRentalPrice, AppEnums.RentalInterval produkRentalInterval) {
        this.produkId = produkId;
        this.produkName = produkName;
        this.produkBrand = produkBrand;
        this.produkRentalPrice = produkRentalPrice;
        this.produkRentalInterval = produkRentalInterval;
    }

    public int getProdukId() {
        return produkId;
    }

    public void setProdukId(int produkId) {
        this.produkId = produkId;
    }

    public int getProdukRentalPrice() {
        return produkRentalPrice;
    }

    public void setProdukRentalPrice(int produkRentalPrice) {
        this.produkRentalPrice = produkRentalPrice;
    }

    public String getProdukName() {
        return produkName;
    }

    public void setProdukName(String produkName) {
        this.produkName = produkName;
    }

    public String getProdukBrand() {
        return produkBrand;
    }

    public void setProdukBrand(String produkBrand) {
        this.produkBrand = produkBrand;
    }

    public AppEnums.RentalInterval getProdukRentalInterval() {
        return produkRentalInterval;
    }

    public void setProdukRentalInterval(AppEnums.RentalInterval produkRentalInterval) {
        this.produkRentalInterval = produkRentalInterval;
    }
}
