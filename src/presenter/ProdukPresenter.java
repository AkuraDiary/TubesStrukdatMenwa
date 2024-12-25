package presenter;

import data.schemas.adt.DllProduk;
import data.schemas.models.Produk;
import repositories.ProdukRepository;
import util.AppEnums;

public class ProdukPresenter {
    ProdukRepository produkRepository;

    public ProdukPresenter(ProdukRepository produkPresenter) {
        this.produkRepository = produkPresenter;
    }

    public Produk selectedProduk = null;
    public DllProduk selectedProdukList = null;

    public void addProduk(
            int produkId, String produkName, String produkBrand, String produkNumber, int produkRentalPrice, AppEnums.RentalInterval produkRentalInterval
    ) {
        Produk newProduct = new Produk(produkId, produkName, produkBrand, produkNumber, produkRentalPrice, produkRentalInterval);
        produkRepository.saveProduk(newProduct);
    }

    public void deleteProduk(int id) {
        produkRepository.deleteProduk(id);
    }

    public void selectProduk(int id) {
        produkRepository.selectProdukById(id);
        selectedProduk = produkRepository.selectedProduk;
    }

    public void selectProduksByStatus(AppEnums.ProdukStatus status) {
        produkRepository.selectProduksByStatus(status);
        selectedProdukList = produkRepository.selectedProdukList;
    }

    public void updateProduk(
            int produkId, String produkName, String produkBrand, String produkNumber, int produkRentalPrice, AppEnums.RentalInterval produkRentalInterval
    ) {

        Produk newProduct = new Produk(
                produkId,
                produkName == null ?  selectedProduk.getProdukName() : produkName,
                produkBrand == null ? selectedProduk.getProdukBrand() : produkBrand,
                produkNumber == null ? selectedProduk.getProdukNumber() : produkNumber,
                produkRentalPrice == -1 ? selectedProduk.getProdukRentalPrice() : produkRentalPrice,
                produkRentalInterval == null ? selectedProduk.getProdukRentalInterval() : produkRentalInterval
        );
        produkRepository.updateProduk(newProduct);
    }

    public void updateProdukStatus(int id, AppEnums.ProdukStatus status) {
        produkRepository.selectProdukById(id);
        Produk produk = produkRepository.selectedProduk;
        produk.setProdukStatus(status);
        produkRepository.updateProduk(produk);
    }

    public DllProduk getAllProduk(
            AppEnums.ProdukStatus status
    ) {
        if(status != null){
            produkRepository.selectProduksByStatus(status);
            return selectedProdukList;
        }
        return produkRepository.getAllProduk();
    }

}
