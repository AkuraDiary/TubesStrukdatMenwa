package repositories;

import data.schemas.adt.DllProduk;
import data.schemas.models.Produk;
import data.schemas.nodes.NodeProduk;
import data.sources.ProductDataSource;
import util.AppEnums;

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


    public void selectProduksByStatus(AppEnums.ProdukStatus status) {
        DllProduk temp = new DllProduk();
        NodeProduk current = productDataSource.productList.getHead();
        while (current != null) {
            if (current.getData().getProdukStatus() == status) {
                temp.insertSorted(current.getData());
            }
            current = current.getNext();
        }
        selectedProdukList = temp;
    }
}
