package data.schemas.adt;

import data.schemas.models.Produk;
import data.schemas.nodes.NodeCustomer;
import data.schemas.nodes.NodeProduk;

public class DllProduk {
    NodeProduk head;
    NodeProduk tail;
    int size;

    public DllProduk() {
        this.head = null;
        this.tail = null;
    }

    public NodeProduk getHead() {
        return head;
    }

    public void setHead(NodeProduk head) {
        this.head = head;
    }

    public NodeProduk getTail() {
        return tail;
    }

    public void setTail(NodeProduk tail) {
        this.tail = tail;
    }

    /* Insert Last */
    public void insertEnd(Produk data) {
        NodeProduk nn = new NodeProduk(data);
        if (tail == null) {
            head = tail = nn;
        } else {
            tail.setNext(nn);
            nn.setPrev(tail);
            tail = nn;
        }
    }

    /* Insert First */
    public void insertFirst(Produk data) {
        NodeProduk nn = new NodeProduk(data);
        if (head == null) {
            head = tail = nn;
        } else {
            head.setPrev(nn);
            nn.setNext(head);
            head = nn;
        }
    }

    /* Insert sorted By ID*/
    public void insertSorted(Produk data) {
        NodeProduk newNode = new NodeProduk(data);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Kasus 2. Jika new node adalah angka paling kecil
        if (data.getProdukId() <= head.getData().getProdukId()) {
            insertFirst(data);
            return;
        }

        // Kasus 3. jika new node angka paling besar
        if (data.getProdukId() >= tail.getData().getProdukId()) {
            insertEnd(data);
            return;
        }

        // Kasus 4. Jika new node di tengah
        NodeProduk current = head;
        while (current != null) {
            if (data.getProdukId() < current.getData().getProdukId()) {
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
                return;
            }
            current = current.getNext();
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void deleteFirst() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
    }

    public void deleteLast() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
    }

    public void deleteById(int id) {
        if (head == null) {
            return;
        }
        if (head.getData().getProdukId() == id) {
            deleteFirst();
            return;
        }
        if (tail.getData().getProdukId() == id) {
            deleteLast();
            return;
        }
        NodeProduk current = head;
        while (current != null) {
            if (current.getData().getProdukId() == id) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                break;
            }
            current = current.getNext();
        }
    }

    public int getSize() {
        NodeProduk current = head;
        size = 0;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public void display() {
        NodeProduk current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public Produk searchById(int id) {
        NodeProduk current = head;
        while (current != null) {
            if (current.getData().getProdukId() == id) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}
