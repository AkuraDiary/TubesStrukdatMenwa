package views.operator;

import static util.Formatter.invalidChoice;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pembayaran'");
    }

    private void showAllTransaksi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showAllTransaksi'");
    }
}
