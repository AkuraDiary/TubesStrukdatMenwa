package repositories;

import data.schemas.adt.DllTransaksi;
import data.schemas.models.Transaksi;
import data.schemas.nodes.NodeTransaksi;
import data.sources.TransaksiDataSource;
import util.AppEnums;

import java.util.Date;

public class TransaksiRepository {

    TransaksiDataSource transaksiDataSource;
    public Transaksi selectedTransaksi=null;
    public DllTransaksi selectedTransaksiList = null;
    public TransaksiRepository(TransaksiDataSource transaksiDataSource) {
        this.transaksiDataSource = transaksiDataSource;
    }

    public void addTransaksi(Transaksi data) {
        if(data.getId_transaksi()==-1){
            data.setId_transaksi(transaksiDataSource.transaksiList.getSize()+1);
        }
        transaksiDataSource.transaksiList.insertSortedByStartDate(data);
    }

    public void deleteTransaksi(int id) {
        transaksiDataSource.transaksiList.deleteById(id);
    }

    public void selectTransaksi(int id) {
        selectedTransaksi = transaksiDataSource.transaksiList.searchById(id);
    }

    public void selectTransaksisByDate(Date date) {
        NodeTransaksi current = transaksiDataSource.transaksiList.getHead();
        DllTransaksi temp = new DllTransaksi();
        while (current != null) {
            if (current.getData().getRental_start().equals(date)) {
                temp.insertLast(current.getData());
            }
            current = current.getNext();
        }
        selectedTransaksiList = temp;
    }

    public void selectTransaksisByCustomer(int id) {
        NodeTransaksi current = transaksiDataSource.transaksiList.getHead();
        DllTransaksi temp = new DllTransaksi();
        while (current != null) {
            if (current.getData().getCustomer().getId() == id) {
                temp.insertLast(current.getData());
            }
            current = current.getNext();
        }
        selectedTransaksiList = temp;
    }


    public void selectTransaksiByStatus(
            AppEnums.StatusTransaksi status
    ){
        NodeTransaksi current = transaksiDataSource.transaksiList.getHead();
        DllTransaksi temp = new DllTransaksi();
        while (current != null) {
            if (current.getData().getRental_status() == status) {
                temp.insertLast(current.getData());
            }
            current = current.getNext();
        }
        selectedTransaksiList = temp;
    }

    public void selectTransaksiByInterval(
            AppEnums.RentalInterval interval
    ){
        NodeTransaksi current = transaksiDataSource.transaksiList.getHead();
        DllTransaksi temp = new DllTransaksi();
        while (current != null) {
            if (current.getData().getRental_interval() == interval) {
                temp.insertLast(current.getData());
            }
            current = current.getNext();
        }
        selectedTransaksiList = temp;
    }

    public void selectTransaksiByUserId(
            int id
    ) {
        NodeTransaksi current = transaksiDataSource.transaksiList.getHead();
        DllTransaksi temp = new DllTransaksi();
        while (current != null) {
            if (current.getData().getPic().getId() == id) {
                temp.insertLast(current.getData());
            }
            current = current.getNext();
        }
        selectedTransaksiList = temp;
    }

}
