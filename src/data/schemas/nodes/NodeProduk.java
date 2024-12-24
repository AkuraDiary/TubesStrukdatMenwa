package data.schemas.nodes;

import data.schemas.models.Produk;

public class NodeProduk {
    // Node for DLL Produk
    Produk data;
    NodeProduk next, prev;

    public NodeProduk(Produk data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public data.schemas.models.Produk getData() {
        return data;
    }

    public NodeProduk getNext() {
        return next;
    }

    public void setData(Produk data) {
        this.data = data;
    }

    public void setNext(NodeProduk next) {
        this.next = next;
    }

    public NodeProduk getPrev() {
        return prev;
    }

    public void setPrev(NodeProduk prev) {
        this.prev = prev;
    }
}
