// redirect to '../.adt/DllLaporan.java'


package data.schemas.nodes;

import data.schemas.models.Laporan;

public class NodeLaporan {
    Laporan data;
    NodeLaporan next, prev;

    public NodeLaporan(Laporan data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Laporan getData() {
        return data;
    }

    public void setData(Laporan data) {
        this.data = data;
    }

    public NodeLaporan getNext() {
        return next;
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
