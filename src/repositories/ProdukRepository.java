package repositories;

import data.schemas.adt.DllProduk;
import data.schemas.models.Produk;
import data.sources.ProductDataSource;

public class ProdukRepository {
    ProductDataSource productDataSource;
    public Produk selectedProduk = null;
    public DllProduk selectedProdukList = null;

    public ProdukRepository(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    //    READ ALL
    public DllProduk getAllProduk() {
        return productDataSource.productList;
    }

    // Create
    public void saveProduk(
            Produk produk
    ) {
        productDataSource.productList.insertSorted(produk);
    }

    // Find By Id
    public void selectProdukById(int id) {
        selectedProduk = productDataSource.productList.searchById(id);
    }

    // Update
    public void updateProduk(
            Produk newProdukData
    ) {
        productDataSource.productList.deleteById(newProdukData.getProdukId());
        productDataSource.productList.insertSorted(newProdukData);
    }

    // Delete
    public void deleteProduk(
            int id
    ) {
        productDataSource.productList.deleteById(id);
    }




}
