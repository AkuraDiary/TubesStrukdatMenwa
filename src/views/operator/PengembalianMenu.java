package views.operator;

import static util.Formatter.invalidChoice;

import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

public class PengembalianMenu {

    public void showPengembalianMenu() {
        try {
            while (AppRouter.activeRoute == AppRouter.AppRoute.PEMBAYARAN) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showAllTransaksi'");
    }

    private void pengembalian() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pengembalian'");
    }

}
