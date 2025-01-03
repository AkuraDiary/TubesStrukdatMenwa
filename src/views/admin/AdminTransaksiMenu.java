package views.admin;

import presenter.TransaksiPresenter;
import util.AppEnums;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

import static util.Formatter.invalidChoice;

public class AdminTransaksiMenu {
    private final TransaksiPresenter transaksiPresenter;

    public AdminTransaksiMenu(TransaksiPresenter transaksiPresenter) {
        this.transaksiPresenter = transaksiPresenter;
    }

    String inputUser;

    public void showAdminTransaksiMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == AppRouter.AppRoute.MASTER_APPROVE_TRANSAKSI) {
            try {
                System.out.println("Master Transaksi Menu");
                System.out.println("1. List Transaksi");
                System.out.println("2. Approve / Reject Transaksi");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");

                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        listTransaksi();
                        break;
                    case "2":
                        approveRejectTransaksi();
                        break;
                    case "0":
                        AppRouter.navigateTo(AppRouter.AppRoute.ADMIN_MENU);
                        break;
                    default:
                        invalidChoice();
                }

            } catch (Exception e) {
                Formatter.formatMessageOutput(e.getMessage());
                invalidChoice();
            }
        }
    }

    private void approveRejectTransaksi() {
        try {
            System.out.print("Masukkan ID Transaksi : ");
            int idTransaksi = InputUtilities.readInt();
            transaksiPresenter.selectTransaksi(idTransaksi);
            if(transaksiPresenter.selectedTransaksi == null){
                Formatter.formatMessageOutput("Data Transaksi Tidak Ditemukan");
                return;
            }
            System.out.println("1. Approve");
            System.out.println("2. Reject");
            System.out.println("0. Kembali");
            System.out.print("Masukkan Pilihan : ");
            String choice = InputUtilities.inputReader.readLine();
            switch (choice){
                case "1":
                    transaksiPresenter.selectedTransaksi.setRental_status(AppEnums.StatusTransaksi.Accepted);
                    transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
                    Formatter.formatMessageOutput("Transaksi Berhasil Di Approve");
                    break;
                case "2":
                    transaksiPresenter.selectedTransaksi.setRental_status(AppEnums.StatusTransaksi.Rejected);
                    transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
                    Formatter.formatMessageOutput("Transaksi Berhasil Di Reject");
                    break;
                case "0":
                    break;
                default:
                    invalidChoice();
            }
        }catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void listTransaksi() {

//        transaksiPresenter.getListTransaksiFiltered(
//                null,
//                AppEnums.StatusTransaksi.Pending,
//                -1,
//                -1,
//                null
//        );
        transaksiPresenter.getListTransaksiFiltered(
                null,
AppEnums.StatusTransaksi.Pending,
                -1,
                -1,
                null
        );

        System.out.println("List Of Pending Transaksi");
        transaksiPresenter.listSelectedTransaksi.display();
    }
}
