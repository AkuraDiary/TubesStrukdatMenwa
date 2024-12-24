package data.schemas.nodes;

public class NodeLaporan {
    String data;
    NodeLaporan next, prev;

    public NodeLaporan(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public String getData() {
        return data;
    }

    public NodeLaporan getNext() {
        return next;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNext(NodeLaporan next) {
        this.next = next;
    }

    public NodeLaporan getPrev() {
        return prev;
    }

    public void setPrev(NodeLaporan prev) {
        this.prev = prev;
    }
}
