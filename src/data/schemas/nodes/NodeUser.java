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

    public User getData() {
        return data;
    }

}
