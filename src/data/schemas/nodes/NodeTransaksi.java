package data.schemas.nodes;

import data.schemas.models.Customer;
import data.schemas.models.Transaksi;
import data.schemas.models.User;

public class NodeTransaksi {


    Transaksi data;

    NodeTransaksi next, prev;

    public NodeTransaksi(Transaksi data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Transaksi getData() {
        return data;
    }

    public void setData(Transaksi data) {
        this.data = data;
    }



    public NodeTransaksi getNext() {
        return next;
    }

    public void setNext(NodeTransaksi next) {
        this.next = next;
    }

    public NodeTransaksi getPrev() {
        return prev;
    }

    public void setPrev(NodeTransaksi prev) {
        this.prev = prev;
    }
}
