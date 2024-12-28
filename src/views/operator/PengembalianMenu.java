package views.operator;

import static util.AppEnums.StatusTransaksi.*;
import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;
import static views.AppRouter.AppRoute.PEMBAYARAN;
import static views.AppRouter.AppRoute.PENGEMBALIAN;

import presenter.TransaksiPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

;


public class PengembalianMenu {

    private final TransaksiPresenter transaksiPresenter;

    public PengembalianMenu(TransaksiPresenter transaksiPresenter) {
        this.transaksiPresenter = transaksiPresenter;
    }

    public void showPengembalianMenu() {
        try {
            while (AppRouter.activeRoute == PENGEMBALIAN) {
                System.out.println("Menu Pembayaran");
                System.out.println("1. Tampilkan Transaksi");
                System.out.println("2. Pengembalian Barang");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan pilihan : ");
                String inputUser = InputUtilities.readLine();
                assert inputUser != null;
                switch (inputUser) {
                    case "1":
                        showAllTransaksi();
                        break;

                    case "2":
                        pengembalian();
                        break;

                    case "0":
                        AppRouter.navigateTo(OPERATOR_MENU);
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
            // TODO: handle exception
        }
    }

    private void showAllTransaksi() {
        System.out.println();
        System.out.println("List Transaksi Yang Berjalan");
        transaksiPresenter.getListTransaksiFiltered(null, Running, -1, -1, null);
        transaksiPresenter.listSelectedTransaksi.display();
    }

    private void pengembalian() {
        System.out.println();
        System.out.print("Masukkin Id Transaksi : ");
        int idTransaksi = InputUtilities.readInt();
        transaksiPresenter.selectTransaksi(idTransaksi);
        if (transaksiPresenter.selectedTransaksi != null) {
            System.out.println("Detail Transaksi yang dipilih : ");
            System.out.println(transaksiPresenter.selectedTransaksi);
            if (transaksiPresenter.selectedTransaksi.getRental_status() == Pending) {
                System.out.println("Transaksi Belum Dibayar");
                return;
            }
            System.out.println("Konfirmasi Pengembalian");
            System.out.println("Barang Sudah Diterima? (Y/n) : ");
            String inputUser = InputUtilities.readLine();
            assert inputUser != null;
            if (inputUser.equalsIgnoreCase("Y")) {
                transaksiPresenter.selectedTransaksi.setRental_status(Done);
                transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
                System.out.println();
                return;
            }
            System.out.println("Pengembalian Dibatalkan");

        } else {
            System.out.println("Id Transaksi Tidak ditemukan");
        }
        System.out.println();
    }

}
