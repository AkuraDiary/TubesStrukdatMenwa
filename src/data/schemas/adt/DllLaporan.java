package data.schemas.adt;

import data.schemas.models.Laporan;
import data.schemas.nodes.NodeLaporan;

public class DllLaporan {
    NodeLaporan head;
    NodeLaporan tail;

    public DllLaporan() {
        head = null;
        tail = null;
    }

    public void insertFirst(Laporan data) {
        NodeLaporan newNode = new NodeLaporan(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void insertLast(Laporan data) {
        NodeLaporan newNode = new NodeLaporan(data);
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

    public void display() {
        NodeLaporan current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public void insertSortedByTanggal(Laporan laporan){
        NodeLaporan newNode = new NodeLaporan(laporan);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            NodeLaporan current = head;
            while (current != null && current.getData().getTanggalLaporan().compareTo(laporan.getTanggalLaporan()) < 0) {
                current = current.getNext();
            }
            if (current == head) {
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            } else if (current == null) {
                tail.setNext(newNode);
                newNode.setPrev(tail);
                tail = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }

    }

}
