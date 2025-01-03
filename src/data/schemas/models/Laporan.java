// redirect to '../.adt/DllLaporan.java'
// redirect to '../.nodes/NodeLaporan.java'

package data.schemas.models;

import data.schemas.adt.DllTransaksi;
import data.schemas.nodes.NodeTransaksi;
import util.AppEnums;
import util.Formatter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Laporan {
    int idLaporan, countTransactionSucceeded, countTransactionFailed, countTransaksiPending;
    Date tanggalLaporan;
    long totalRevenue=0;
    long totalDueFine=0;
    LocalDateTime dateRangeStart;
    LocalDateTime dateRangeEnd;
    DllTransaksi listTransaksi;
    DllTransaksi listTransaksiSucceeded;

    public int getCountTransaksiDue() {
        return countTransaksiDue;
    }

    public void setCountTransaksiDue(int countTransaksiDue) {
        this.countTransaksiDue = countTransaksiDue;
    }

    int countTransaksiDue = 0;

    public Laporan(
            int idLaporan,
            Date tanggalLaporan,
            LocalDateTime dateRangeStart,
            LocalDateTime dateRangeEnd,
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
        cookTransaksiDue();
    }

    private void cookTransaksiDue() {
        countTransaksiDue = 0;
        totalDueFine = 0;
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() == AppEnums.StatusTransaksi.Due) {

                totalDueFine += current.getData().getRental_fine();
                countTransaksiDue++;
            }
            current = current.getNext();
        }
    }

    private void cookTransaksiPending() {
        countTransaksiPending = 0;
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() != AppEnums.StatusTransaksi.Accepted && current.getData().getRental_status() != AppEnums.StatusTransaksi.Rejected) {
                countTransaksiPending++;
            }
            current = current.getNext();
        }
    }



    private void cookTransaksiFailed() {
        countTransactionFailed = 0;
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() == AppEnums.StatusTransaksi.Rejected) {
                countTransactionFailed++;
            }
            current = current.getNext();
        }
    }

    private void cookTransaksiSucceeded() {
        countTransactionSucceeded = 0;
        totalRevenue = 0;
        NodeTransaksi current = listTransaksi.getHead();
        while (current != null) {
            if (current.getData().getRental_status() == AppEnums.StatusTransaksi.Done) {
                current.getData().calculateTotalPrice();
                totalRevenue += current.getData().getTotal_price();
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


    public long getTotalRevenue() {
        return totalRevenue;
    }


    public LocalDateTime getDateRangeStart() {
        return dateRangeStart;
    }


    public LocalDateTime getDateRangeEnd() {
        return dateRangeEnd;
    }


    @Override
    public String toString() {

        return "Laporan:\n" +
                "idLaporan: " + idLaporan +
                "\nCount Transaction Succeeded: " + countTransactionSucceeded +
                "\nCount TransactionFailed: " + countTransactionFailed +
                "\nCount Transaction Due: " + countTransaksiDue +
                "\nTanggal Laporan: " + tanggalLaporan +
                "\nTotal Revenue: " + Formatter.formatRupiah((double) this.totalRevenue) +
                "\nTotal Due To be paid: " + Formatter.formatRupiah((double) this.totalDueFine) +
                "\nDate Range Start: " + dateRangeStart +
                "\nDate Range End: " + dateRangeEnd ;
    }

    public String overview() {
        return "Laporan Overview\n" +
                "idLaporan: " + idLaporan +
                "\nCount Transaction Succeeded: " + countTransactionSucceeded +
                "\nCount Transaction Failed: " + countTransactionFailed +
                "\nTanggal Laporan: " + tanggalLaporan;
    }
}
