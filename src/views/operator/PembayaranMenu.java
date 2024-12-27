package views.operator;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import presenter.TransaksiPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.AppRouter.AppRoute;
import static util.AppEnums.StatusTransaksi.Running;

public class PembayaranMenu {

    private final TransaksiPresenter transaksiPresenter;

    public PembayaranMenu(TransaksiPresenter transaksiPresenter) {
        this.transaksiPresenter = transaksiPresenter;
    }

    public void showPembayaranMenu() {
        try {
            while (AppRouter.activeRoute == AppRouter.AppRoute.PEMBAYARAN) {
                System.out.println("Menu Pembayaran");
                System.out.println("1.Tampilkan Transaksi");
                System.out.println("2. Pembayaran");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan pilihan : ");
                String inputUser = InputUtilities.readLine();
                switch (inputUser) {
                    case "1":
                        showAllTransaksi();
                        break;

                    case "2":
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
            // TODO: handle exception
        }
    }

    private void pembayaran() {
        System.out.println();
        System.out.print("Masukkan Id Transaksi : ");
        int idTransaksi = InputUtilities.readInt();
        transaksiPresenter.selectTransaksi(idTransaksi);

        if (transaksiPresenter.selectedTransaksi != null) {
            transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
        }else{
            System.out.println("id tidak ditemukan");
        }
    }

    private void showAllTransaksi() {
        System.out.println();
        System.out.println("List Transaksi");
        // transaksiPresenter.getListTransaksiRunning().display();
        transaksiPresenter.getListTransaksiFiltered(null, Running, -1, -1, null);
        System.out.println();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'showAllTransaksi'");
    }
}
