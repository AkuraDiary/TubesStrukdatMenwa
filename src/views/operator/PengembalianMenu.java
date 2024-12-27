package views.operator;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;
import static views.AppRouter.AppRoute.PEMBAYARAN;
import static views.AppRouter.AppRoute.PENGEMBALIAN;

import presenter.TransaksiPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import static util.AppEnums.StatusTransaksi.Pending;;


public class PengembalianMenu {

    private final TransaksiPresenter transaksiPresenter;

    public PengembalianMenu(TransaksiPresenter transaksiPresenter){
        this.transaksiPresenter = transaksiPresenter;
    }
    public void showPengembalianMenu() {
        try {
            while (AppRouter.activeRoute == PENGEMBALIAN) {
                System.out.println("Menu Pembayaran");
                System.out.println("1.Tampilkan Transaksi");
                System.out.println("2. Pengembalian Barang");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan pilihan : ");
                String inputUser = InputUtilities.readLine();
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
        System.out.println("List Transaksi");
        transaksiPresenter.getListTransaksiFiltered(null, Pending, -1, -1, null);
       
        // transaksiPresenter.getListTransaksiRunning().display();
        System.out.println();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'showAllTransaksi'");
    }

    private void pengembalian() {
       System.out.println();
       System.out.print("Masukkin Id Transaksi : ");
       int idTransaksi = InputUtilities.readInt();
       transaksiPresenter.selectTransaksi(idTransaksi);

       if (transaksiPresenter.selectedTransaksi!=null) {
          transaksiPresenter.updateTransaksi(idTransaksi, transaksiPresenter.selectedTransaksi);
       }else{
        System.out.println("Id Transaksi Tidak ditemukan");
       }
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'pengembalian'");
    }

}
