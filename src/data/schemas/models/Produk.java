// redirect to '../.adt/DllProduk.java'
// redirect to '../.nodes/NodeProduk.java'
// redirect to '../.sources/ProductDataSources.java'


package data.schemas.models;

import util.AppEnums;

public class Produk {
    int produkId;
    int produkRentalPrice=0;
    AppEnums.ProdukStatus produkStatus;
    String produkName, produkBrand, produkNumber;
    AppEnums.RentalInterval produkRentalInterval;

    public Produk(int produkId, String produkName, String produkBrand, String produkNumber, int produkRentalPrice, AppEnums.RentalInterval produkRentalInterval) {
        this.produkId = produkId;
        this.produkName = produkName;
        this.produkNumber = produkNumber;
        this.produkBrand = produkBrand;
//        System.out.println(produkRentalPrice);
        this.produkRentalPrice = produkRentalPrice;
        this.produkRentalInterval = produkRentalInterval;
        this.produkStatus = AppEnums.ProdukStatus.Available;
    }

    public AppEnums.ProdukStatus getProdukStatus() {
        return produkStatus;
    }

    public void setProdukStatus(AppEnums.ProdukStatus produkStatus) {
        this.produkStatus = produkStatus;
    }

    public String getProdukNumber() {
        return produkNumber;
    }

    public void setProdukNumber(String produkNumber) {
        this.produkNumber = produkNumber;
    }

    public int getProdukId() {
        return produkId;
    }

    public void setProdukId(int produkId) {
        this.produkId = produkId;
    }

    public int getProdukRentalPrice() {
//        System.out.println("Get Product Price : " + produkRentalPrice);
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

    @Override
    public String toString() {
        return "Produk:\n" +
                "produkId: " + produkId +
                "\nprodukRentalPrice: " + produkRentalPrice +
                "\nprodukName: " + produkName +
                "\nprodukBrand: " + produkBrand +
                "\nprodukNumber: " + produkNumber +
                "\nprodukRentalInterval: " + produkRentalInterval;
    }
}
