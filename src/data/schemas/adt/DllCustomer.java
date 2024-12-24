package data.schemas.adt;

import data.schemas.models.Customer;
import data.schemas.nodes.NodeCustomer;
import data.schemas.nodes.NodeUser;

public class DllCustomer {
    NodeCustomer head;
    NodeCustomer tail;
    int size;

    public DllCustomer() {
        this.head = null;
        this.tail = null;
    }

    public NodeCustomer getHead() {
        return head;
    }

    public void setHead(NodeCustomer head) {
        this.head = head;
    }

    public NodeCustomer getTail() {
        return tail;
    }

    public void setTail(NodeCustomer tail) {
        this.tail = tail;
    }

    /* Insert Last */
    public void insertEnd(Customer data) {
        NodeCustomer nn = new NodeCustomer(data);
        if (tail == null) {
            head = tail = nn;
        } else {
            tail.setNext(nn);
            nn.setPrev(tail);
            tail = nn;
        }
    }

    /* Insert First */

    public void insertFirst(Customer data) {
        NodeCustomer nn = new NodeCustomer(data);
        if (head == null) {
            head = tail = nn;
        } else {
            head.setPrev(nn);
            nn.setNext(head);
            head = nn;
        }
    }

    /* Insert sorted By ID*/
    public void insertSorted(Customer data) {
        NodeCustomer newNode = new NodeCustomer(data);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Kasus 2. Jika new node adalah angka paling kecil
        if (data.getId() <= head.getData().getId()) {
            insertFirst(data);
            return;
        }

        // Kasus 3. jika new node angka paling besar
        if (data.getId() >= tail.getData().getId()) {
            insertEnd(data);
            return;
        }

        // Kasus 4. Jika new node di tengah
        NodeCustomer current = head;
        while (current != null) {
            if (data.getId() <= current.getData().getId()) {
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
                break;
            }
            current = current.getNext();
        }
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
        if (head.getData().getId() == id) {
            deleteFirst();
            return;
        }
        if (tail.getData().getId() == id) {
            deleteLast();
            return;
        }
        NodeCustomer current = head;
        while (current != null) {
            if (current.getData().getId() == id) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                break;
            }
            current = current.getNext();
        }
    }
    public int getSize() {
        NodeCustomer current = head;
        size = 0;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
    public void display() {
        NodeCustomer current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public Customer searchById(int id) {
        NodeCustomer current = head;
        while (current != null) {
            if (current.getData().getId() == id) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public Customer searchByName(String name) {
        NodeCustomer current = head;
        while (current != null) {
            if (current.getData().getName().equals(name)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public void clear(){
        head = tail = null;
    }

}
