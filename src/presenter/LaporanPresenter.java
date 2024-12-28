package presenter;

import data.schemas.adt.DllLaporan;
import data.schemas.adt.DllTransaksi;
import data.schemas.models.Laporan;
import repositories.LaporanRepository;
import repositories.TransaksiRepository;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;

public class LaporanPresenter {
    UserRepository userRepository;
    TransaksiRepository transaksiRepository;
    LaporanRepository laporanRepository;

    public Laporan selectedLaporan = null;
    public DllLaporan selectedLaporanList = new DllLaporan();

    public LaporanPresenter(UserRepository userRepository, TransaksiRepository transaksiRepository, LaporanRepository laporanRepository) {
        this.userRepository = userRepository;
        this.transaksiRepository = transaksiRepository;
        this.laporanRepository = laporanRepository;
    }

    public void cookLaporan(
            LocalDateTime tanggalAwal,
            LocalDateTime tanggalAkhir
    ) {
        transaksiRepository.selectTransaksisByDate(tanggalAwal, tanggalAkhir);
        DllTransaksi listTransaksi = transaksiRepository.getSelectedListTransaksi();
        Laporan laporan = new Laporan(
                -1,
                new Date(),
                tanggalAwal,
                tanggalAkhir,
                listTransaksi
        );

        laporanRepository.saveLaporan(laporan);
    }

    public void updateLaporan(
            Laporan newLaporanData
    ) {
        laporanRepository.updateLaporan(newLaporanData);
    }

    public void deleteLaporan(
            int id
    ) {
        laporanRepository.deleteLaporan(id);
    }

    public void selectLaporanById(int id) {
        laporanRepository.selectLaporanById(id);
        selectedLaporan = laporanRepository.selectedLaporan;
    }

    public void getAllLaporanBasedOnTanggal(
            Date tanggal
    ) {
        laporanRepository.getAllLaporanBasedOnTanggal(tanggal);
        selectedLaporanList = laporanRepository.selectedLaporanList;
    }

    public void getAllLaporan() {
        selectedLaporanList = laporanRepository.getAllLaporan();
    }

}
