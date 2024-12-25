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
            System.out.println(current.getData().overview());
            current = current.getNext();
            System.out.println();
        }
    }

    public void insertSortedByTanggal(Laporan laporan) {
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

    public void deleteById(int idLaporan) {
        NodeLaporan current = head;
        while (current != null && current.getData().getIdLaporan() != idLaporan) {
            current = current.getNext();
        }
        if (current != null) {
            if (current == head) {
                deleteFirst();
            } else if (current == tail) {
                deleteLast();
            } else {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
            }
        }
    }

    public Laporan searchById(int id) {
        NodeLaporan current = head;
        while (current != null && current.getData().getIdLaporan() != id) {
            current = current.getNext();
        }
        if (current != null) {
            return current.getData();
        } else {
            return null;
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public NodeLaporan getHead() {
        return head;
    }

    public int getSize() {
        NodeLaporan current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
