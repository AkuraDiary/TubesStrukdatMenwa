package views.operator;

import static util.AppEnums.StatusTransaksi.*;
import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import data.schemas.adt.DllTransaksi;
import presenter.TransaksiPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.AppRouter.AppRoute;

public class PembayaranMenu {

    private final TransaksiPresenter transaksiPresenter;

    public PembayaranMenu(TransaksiPresenter transaksiPresenter) {
        this.transaksiPresenter = transaksiPresenter;
    }

    public void showPembayaranMenu() {
        try {
            while (AppRouter.activeRoute == AppRouter.AppRoute.PEMBAYARAN) {
                System.out.println("Menu Pembayaran");
                System.out.println("1. Tampilkan Transaksi Yang Perlu Dibayar");
                System.out.println("2. Tampilkan Transaksi Yang Terkena Denda");
                System.out.println("3. Pembayaran");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan pilihan : ");
                String inputUser = InputUtilities.readLine();
                switch (inputUser) {
                    case "1":
                        showAllTransaksi();
                        break;
                    case "2":
                        showAllDueTransaksi();
                        break;
                    case "3":
                        pembayaran();
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
        }
    }



    private void pembayaran() {
        System.out.println();
        System.out.print("Masukkan Id Transaksi : ");
        int idTransaksi = InputUtilities.readInt();
        transaksiPresenter.selectTransaksi(idTransaksi);

        if (transaksiPresenter.selectedTransaksi != null) {

            System.out.println("Detail Transaksi yang dipilih : ");
            System.out.println(transaksiPresenter.selectedTransaksi);;
            System.out.println("Detail Pembayaran : ");

            if(transaksiPresenter.selectedTransaksi.getRental_status() == Due){
                System.out.println("Denda : " + transaksiPresenter.selectedTransaksi.getRental_fine());
                System.out.println("Total Pembayaran : " + Formatter.formatRupiah(transaksiPresenter.selectedTransaksi.getRental_due()));

                System.out.println("Konfirmasi Pembayaran Denda Transaksi");
                System.out.print("Konfirmasi Pembayaran sudah diterima? (Y/N) : ");
                String input = InputUtilities.readLine();
                assert input != null;
                if(input.equalsIgnoreCase("Y")){
                    transaksiPresenter.selectedTransaksi.setRental_status(Done);
                }else{
                    System.out.println("Pembayaran Dibatalkan");
                    return;
                }

            }else{
                System.out.println("Total Pembayaran : " + Formatter.formatRupiah(transaksiPresenter.selectedTransaksi.getRental_due()));
                System.out.println("Konfirmasi Pembayaran Transaksi");
                System.out.print("Konfirmasi Pembayaran sudah diterima? (Y/N) : ");
                String input = InputUtilities.readLine();
                assert input != null;
                if(input.equalsIgnoreCase("Y")){
                    transaksiPresenter.selectedTransaksi.setRental_status(Running);
                }else {
                    System.out.println("Pembayaran Dibatalkan");
                    return;
                }
            }
            transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
        }else{
            System.out.println("id tidak ditemukan");
        }
    }

    private void showAllTransaksi() {
        System.out.println();
        System.out.println("List Transaksi");
        transaksiPresenter.getListTransaksiFiltered(null, Accepted, -1, -1, null);
        transaksiPresenter.listSelectedTransaksi.display();
        System.out.println();
    }

    private void showAllDueTransaksi() {
        System.out.println("List Transaksi Terkena Denda");
        transaksiPresenter.getListTransaksiFiltered(null, Due, -1, -1, null);
        transaksiPresenter.listSelectedTransaksi.display();
        System.out.println();
    }
}
