package views.operator;

import static util.Formatter.invalidChoice;

import presenter.TransaksiPresenter;
import util.Formatter;
import views.AppRouter;
import views.AppRouter.AppRoute;

public class PembayaranMenu {

    private final TransaksiPresenter transaksiPresenter;

    public PembayaranMenu(TransaksiPresenter transaksiPresenter){
        this.transaksiPresenter= transaksiPresenter;
    }

    public void showPembayaranMenu(){
        try {
            while (AppRouter.activeRoute==AppRouter.AppRoute.PEMBAYARAN) {
                System.out.println("Menu Pembayaran");
                System.out.println("1.");
            }
        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
            // TODO: handle exception
        }
    }
}
