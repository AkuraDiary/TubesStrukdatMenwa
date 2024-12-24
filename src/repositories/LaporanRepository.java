package repositories;

import data.schemas.adt.DllLaporan;
import data.schemas.models.Laporan;
import data.schemas.nodes.NodeLaporan;
import data.sources.LaporanDataSource;

import java.util.Date;

public class LaporanRepository {
    LaporanDataSource laporanDataSource;
    public Laporan selectedLaporan = null;
    public  DllLaporan selectedLaporanList = new DllLaporan();

    public LaporanRepository(LaporanDataSource laporanDataSource) {
        this.laporanDataSource = laporanDataSource;
    }

    public void saveLaporan(
            Laporan laporan
    ) {
        laporanDataSource.laporanList.insertSortedByTanggal(laporan);
    }

    public void updateLaporan(
            Laporan newLaporanData
    ) {
        laporanDataSource.laporanList.deleteById(newLaporanData.getIdLaporan());
        laporanDataSource.laporanList.insertSortedByTanggal(newLaporanData);
    }

    public void deleteLaporan(
            int id
    ) {
        laporanDataSource.laporanList.deleteById(id);
    }

    public void selectLaporanById(int id) {
        selectedLaporan = laporanDataSource.laporanList.searchById(id);
    }

    public DllLaporan getAllLaporan() {
        return laporanDataSource.laporanList;
    }

    public void getAllLaporanBasedOnTanggal(
            Date tanggal
    ) {
        selectedLaporanList.clear();
        NodeLaporan current = laporanDataSource.laporanList.getHead();
        while (current != null) {
            if (current.getData().getTanggalLaporan().equals(tanggal)) {
                selectedLaporanList.insertSortedByTanggal(current.getData());
            }
            current = current.getNext();
        }
    }


}

