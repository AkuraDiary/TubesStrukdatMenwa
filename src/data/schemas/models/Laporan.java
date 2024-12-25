package data.schemas.models;

import data.schemas.adt.DllTransaksi;
import data.schemas.nodes.NodeTransaksi;
import util.AppEnums;

import java.util.Date;
import java.util.List;

public class Laporan {
    int idLaporan, countTransactionSucceeded, countTransactionFailed, countTransaksiPending;
    Date tanggalLaporan;
    Long totalRevenue;
    Date dateRangeStart;
    Date dateRangeEnd;
    DllTransaksi listTransaksi;
    DllTransaksi listTransaksiSucceeded;

    public Laporan(
            int idLaporan,
            Date tanggalLaporan,
            Date dateRangeStart,
            Date dateRangeEnd,
            DllTransaksi listTransaksi
    ) {
        this.idLaporan = idLaporan;
        this.tanggalLaporan = tanggalLaporan;
        this.dateRangeStart = dateRangeStart;
        this.dateRangeEnd = dateRangeEnd;
        this.listTransaksi = listTransaksi;

        cookTransaksiSucceeded();
        cookTransaksiFailed();
        cookTransaksiPending();
        cookTotalRevenue();
    }

    private void cookTransaksiPending() {
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() != AppEnums.StatusTransaksi.Accepted && current.getData().getRental_status() != AppEnums.StatusTransaksi.Rejected) {
                countTransaksiPending++;
            }
            current = current.getNext();
        }
    }

    private void cookTotalRevenue() {
    }

    private void cookTransaksiFailed() {
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() == AppEnums.StatusTransaksi.Rejected) {
                countTransactionFailed++;
            }
            current = current.getNext();
        }
    }

    private void cookTransaksiSucceeded() {
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() == AppEnums.StatusTransaksi.Done) {
                countTransactionSucceeded++;
                listTransaksiSucceeded.insertSortedByStartDate(current.getData());
            }
            current = current.getNext();
        }
    }

    public int getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(int idLaporan) {
        this.idLaporan = idLaporan;
    }

    public int getCountTransactionSucceeded() {
        return countTransactionSucceeded;
    }

    public int getCountTransactionFailed() {
        return countTransactionFailed;
    }

    public int getCountTransaksiPending() {
        return countTransaksiPending;
    }

    public DllTransaksi getListTransaksi() {
        return listTransaksi;
    }

    public DllTransaksi getListTransaksiSucceeded() {
        return listTransaksiSucceeded;
    }

    public Date getTanggalLaporan() {
        return tanggalLaporan;
    }


    public Long getTotalRevenue() {
        return totalRevenue;
    }


    public Date getDateRangeStart() {
        return dateRangeStart;
    }


    public Date getDateRangeEnd() {
        return dateRangeEnd;
    }


    @Override
    public String toString() {
        return "Laporan:\n" +
                "idLaporan: " + idLaporan +
                "\ncountTransactionSucceeded: " + countTransactionSucceeded +
                "\ncountTransactionFailed: " + countTransactionFailed +
                "\ntanggalLaporan: " + tanggalLaporan +
                "\ntotalRevenue: " + totalRevenue +
                "\ndateRangeStart: " + dateRangeStart +
                "\ndateRangeEnd: " + dateRangeEnd ;
    }

    public String overview() {
        return "Laporan Overview\n" +
                "idLaporan: " + idLaporan +
                "\ncountTransactionSucceeded: " + countTransactionSucceeded +
                "\ncountTransactionFailed: " + countTransactionFailed +
                "\ntanggalLaporan: " + tanggalLaporan;
    }
}
