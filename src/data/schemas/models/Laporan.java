package data.schemas.models;

import java.util.Date;
import java.util.List;

public class Laporan {
    int idLaporan, countTransactionSucceeded, countTransactionFailed;
    Date tanggalLaporan;
    Long totalRevenue;
    Date dateRangeStart;
    Date dateRangeEnd;

    public Laporan(int idLaporan, int countTransactionSucceeded, int countTransactionFailed, Date tanggalLaporan, Long totalRevenue, Date dateRangeStart, Date dateRangeEnd) {
        this.idLaporan = idLaporan;
        this.countTransactionSucceeded = countTransactionSucceeded;
        this.countTransactionFailed = countTransactionFailed;
        this.tanggalLaporan = tanggalLaporan;
        this.totalRevenue = totalRevenue;
        this.dateRangeStart = dateRangeStart;
        this.dateRangeEnd = dateRangeEnd;
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

    public void setCountTransactionSucceeded(int countTransactionSucceeded) {
        this.countTransactionSucceeded = countTransactionSucceeded;
    }

    public int getCountTransactionFailed() {
        return countTransactionFailed;
    }

    public void setCountTransactionFailed(int countTransactionFailed) {
        this.countTransactionFailed = countTransactionFailed;
    }

    public Date getTanggalLaporan() {
        return tanggalLaporan;
    }

    public void setTanggalLaporan(Date tanggalLaporan) {
        this.tanggalLaporan = tanggalLaporan;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Date getDateRangeStart() {
        return dateRangeStart;
    }

    public void setDateRangeStart(Date dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    public Date getDateRangeEnd() {
        return dateRangeEnd;
    }

    public void setDateRangeEnd(Date dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
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
}
