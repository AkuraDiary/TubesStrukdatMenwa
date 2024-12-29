package views.admin;

import presenter.LaporanPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

import java.time.LocalDateTime;
import java.util.Date;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.*;

public class AdminLaporanMenu {

    private final LaporanPresenter laporanPresenter;

    public AdminLaporanMenu(LaporanPresenter laporanPresenter) {
        this.laporanPresenter = laporanPresenter;
    }

    String inputUser;

    public void showLaporanMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == MASTER_LAPORAN) {
            try {
                System.out.println("Master Laporan Menu");
                System.out.println("1. List Laporan");
                System.out.println("2. Lihat Detail Laporan");
                System.out.println("3. Buat Laporan");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        listLaporan();
                        break;
                    case "2":
                        detailLaporan();
                        break;
                    case "3":
                        buatLaporanBaru();
                        break;
                    case "0":
                        AppRouter.navigateTo(ADMIN_MENU);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (Exception e) {
                invalidChoice();
            }

        }

    }

    private void buatLaporanBaru() {
        try {
            System.out.println();
            System.out.println("Buat Laporan Baru");
            System.out.print("Masukkan Tanggal Awal Rentang [dd-MM-yyyy HH:mm:ss] : ");
            LocalDateTime dateRangeStart = InputUtilities.getDateTimeFromInput();
            System.out.print("Masukkan Tanggal Akhir Rentang [dd-MM-yyyy HH:mm:ss] : ");
            LocalDateTime dateRangeEnd = InputUtilities.getDateTimeFromInput();
            laporanPresenter.cookLaporan(dateRangeStart, dateRangeEnd);
            System.out.println("Laporan Berhasil Dibuat");
            System.out.println();
        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void detailLaporan() {
        try {
            System.out.println();
            System.out.println("Tampil Detail Laporan");
            System.out.print("Masukkan idLaporan : ");
            int idLaporan = InputUtilities.readInt();

            laporanPresenter.selectLaporanById(idLaporan);
            if (laporanPresenter.selectedLaporan == null) {
                throw new Exception("Laporan Tidak Ditemukan");
            }
            System.out.println("Detail Laporan");
            System.out.println(laporanPresenter.selectedLaporan.toString());
            System.out.println("Daftar Transaksi");
            laporanPresenter.selectedLaporan.getListTransaksi().display();

            System.out.println();

        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void listLaporan() {
        try {
            System.out.println();
            System.out.println("Tampil Laporan");
            System.out.print("Filter Tanggal? [y/n] : ");
            String jawab = InputUtilities.readLine();
            if (jawab != null && jawab.equalsIgnoreCase("y")) {
                System.out.print("Masukkan Tanggal : ");
                Date tanggal = InputUtilities.getDateFromInput();
                laporanPresenter.getAllLaporanBasedOnTanggal(tanggal);

            }else{
                laporanPresenter.getAllLaporan();
            }

            laporanPresenter.selectedLaporanList.display();

            System.out.println();

        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }
}
