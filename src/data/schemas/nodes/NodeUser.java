package data.schemas.nodes;

import data.schemas.models.User;

public class NodeUser {
    // Node for DLL User
    User data;
    NodeUser next, prev;


    public NodeUser(User data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public void setData(User data) {
        this.data = data;
    }

    public NodeUser getNext() {
        return next;
    }

    public void setNext(NodeUser next) {
        this.next = next;
    }

    public NodeUser getPrev() {
        return prev;
    }

    public void setPrev(NodeUser prev) {
        this.prev = prev;
    }

    public User getData() {
        return data;
    }

}
