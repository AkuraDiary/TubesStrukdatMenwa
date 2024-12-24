package data.schemas.nodes;

import data.schemas.models.Customer;

public class NodeCustomer {
    Customer data;
    NodeCustomer next, prev;
    public NodeCustomer(Customer data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Customer getData() {
        return data;
    }

    public NodeCustomer getNext() {
        return next;
    }

    public void setData(Customer data) {
        this.data = data;
    }

    public void setNext(NodeCustomer next) {
        this.next = next;
    }

    public NodeCustomer getPrev() {
        return prev;
    }

    public void setPrev(NodeCustomer prev) {
        this.prev = prev;
    }
}
