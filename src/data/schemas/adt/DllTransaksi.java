package data.schemas.adt;

import data.schemas.models.Transaksi;
import data.schemas.nodes.NodeTransaksi;

public class DllTransaksi {

    NodeTransaksi head, tail;

    public DllTransaksi() {
        this.head = null;
        this.tail = null;
    }

    public void insertFirst(Transaksi data) {
        NodeTransaksi newNode = new NodeTransaksi(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }
    public void clear(){
        head = null;
        tail = null;
    }
    public void insertLast(Transaksi data) {
        NodeTransaksi newNode = new NodeTransaksi(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrev(null);
            }
        }
    }

    public void deleteLast() {
        if (head != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrev();
                tail.setNext(null);
            }
        }
    }

    public void deleteById(int id) {
        NodeTransaksi current = head;
        while (current != null) {
            if (current.getData().getId_transaksi() == id) {
                if (current == head) {
                    deleteFirst();
                } else if (current == tail) {
                    deleteLast();
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                break;
            }
            current = current.getNext();
        }
    }

    public void display() {
        NodeTransaksi current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public void insertSortedByStartDate(Transaksi data) {
        NodeTransaksi newNode = new NodeTransaksi(data);
        NodeTransaksi current = head;
        while (current != null) {
            if (current.getData().getRental_start().compareTo(data.getRental_start()) > 0) {
                if (current == head) {
                    insertFirst(data);
                } else {
                    newNode.setNext(current);
                    newNode.setPrev(current.getPrev());
                    current.getPrev().setNext(newNode);
                    current.setPrev(newNode);
                }
                break;
            }
            current = current.getNext();
        }
    }

    public NodeTransaksi getHead() {
        return head;
    }

    public void setHead(NodeTransaksi head) {
        this.head = head;
    }

    public NodeTransaksi getTail() {
        return tail;
    }

    public void setTail(NodeTransaksi tail) {
        this.tail = tail;
    }

    public Transaksi searchById(int id) {
        NodeTransaksi current = head;
        while (current != null) {
            if (current.getData().getId_transaksi() == id) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public int getSize() {
        NodeTransaksi current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
