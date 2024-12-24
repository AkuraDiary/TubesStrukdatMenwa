package data.schemas.nodes;

import data.schemas.models.Customer;
import data.schemas.models.Transaksi;
import data.schemas.models.User;

public class NodeTransaksi {

    // TODO MOVE THESE TO NODE
    //    List<Produk> Products;
    //    User pic;
    //    Customer customer;
    Transaksi data;
    User pic;
    Customer customer;
    NodeTransaksi next, prev;

    public NodeTransaksi(Transaksi data, User pic, Customer customer) {
        this.data = data;
        this.pic = pic;
        this.customer = customer;
        this.next = null;
        this.prev = null;
    }

    public Transaksi getData() {
        return data;
    }

    public void setData(Transaksi data) {
        this.data = data;
    }

    public User getPic() {
        return pic;
    }

    public void setPic(User pic) {
        this.pic = pic;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
