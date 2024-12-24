package data.schemas.adt;

import data.schemas.models.User;
import data.schemas.nodes.NodeUser;

public class DllUser {
    NodeUser head;
    NodeUser tail;
    int size;

    /* Insert Last */
    public void insertEnd(User data) {
        NodeUser nn = new NodeUser(data);
        if (tail == null) {
            head = tail = nn;
        } else {
            tail.setNext(nn);
            nn.setPrev(tail);
            tail = nn;
        }
    }

    /* Insert First */

    public void insertFirst(User data) {
        NodeUser nn = new NodeUser(data);
        if (head == null) {
            head = tail = nn;
        } else {
            head.setPrev(nn);
            nn.setNext(head);
            head = nn;
        }
//        System.out.println("User Inserted At The Start ");
    }

    /* Insert sorted By ID*/
    public void insertSorted(User data) {
        NodeUser newNode = new NodeUser(data);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Kasus 2. Jika new node adalah angka paling kecil
        if (data.getId() <= head.getData().getId()) {
            insertFirst(data);
//            newNode.setPrev(head);
//            head.setPrev(newNode);
//            head = newNode;
            return;
        }

        // Kasus 3. jika new node angka paling besar
        if (data.getId() >= tail.getData().getId()) {
            insertEnd(data);
//            newNode.setPrev(tail);
//            tail.setNext(newNode);
//            tail = newNode;
            return;
        }

        // Kasus 4. diantara head dan tail
        NodeUser current = head;
        while (current != null && current.getData().getId() < data.getId()) {
            current = current.getNext();
        }
        // Insert the node before the current node
        newNode.setNext(current);
        assert current != null;
        newNode.setPrev(current.getPrev());
        if (current.getPrev() != null) {
            current.getPrev().setNext(newNode);
        }
        current.setPrev(newNode);
    }

    public User searchById(int id) {
        NodeUser current = head;
        while (current != null) {
            if (current.getData().getId() == id) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public void deleteById(int id) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        NodeUser current = head;

        // Case 1 : Delete the head node
        if (head.getData().getId() == id) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null; // if the list becomes empty
            }
            System.out.println("User dengan id " + id + " Berhasil Dihapus");
            System.out.println();
            return;
        }

        // traverse to find the node to delete
        while (current != null && current.getData().getId() != id) {
            current = current.getNext();

        }

        // Case 2: Node not found
        if (current == null) {
            System.out.println("User dengan id " + id + " tidak ditemukan");
            System.out.println();
            return;
        }

        // Case 3 : Delete a middle node
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            tail = current.getPrev();// Jika node yang dihapus adalah tail

        }

        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        }
        System.out.println("User dengan id " + id + " Berhasil Dihapus");
        System.out.println();
    }

    public int getSize() {
        NodeUser current = head;
        size = 0;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public void clear(){
        head = tail = null;
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
    public void displau() {
        NodeUser current = head;
        while (current != null) {
            System.out.println(current.getData().toString() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public DllUser() {
        head = tail = null;
    }

    public NodeUser getHead() {
        return head;
    }

    public void setHead(NodeUser head) {
        this.head = head;
    }

    public NodeUser getTail() {
        return tail;
    }

    public void setTail(NodeUser tail) {
        this.tail = tail;
    }
}
