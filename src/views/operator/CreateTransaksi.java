package views.operator;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import presenter.CustomerPresenter;
import presenter.ProdukPresenter;
import presenter.TransaksiPresenter;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.AppRouter.AppRoute;

public class CreateTransaksi {
    private final TransaksiPresenter transaksiPresenter;

    public CreateTransaksi(ProdukPresenter produkPresenter,CustomerPresenter custPresenter,TransaksiPresenter transaksiPresenter) {
        this.produkPresenter= produkPresenter;
        this.custPresenter=custPresenter;
        this.transaksiPresenter = transaksiPresenter;
    }

    // untuk membuat ttransaksi setidaknya membtuhkan idProduk dan idCust
    public void showCreateTransaksiMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == AppRouter.AppRoute.TRANSAKSI) {
            try {
                System.out.println("Create Transaksi Menu");
                System.out.println("1. List Id Customer");
                System.out.println("2. List Id Product");
                System.out.println("3. Create Transaksi");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");

                String inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        listCustomer();
                        break;

                    case "2":
                        listProduk();
                        break;

                    case "3":
                        doCreateTransaksi();
                        break;

                    case "0":
                        AppRouter.navigateTo(OPERATOR_MENU);
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                Formatter.formatMessageOutput(e.getMessage());
                invalidChoice();
                // TODO: handle exception
            }
        }
    }

    private void doCreateTransaksi() {
        System.out.println("Tambah Transaksi");
        System.out.println();
    }

    private final ProdukPresenter produkPresenter;

    private void listProduk() {
        System.out.println();
        System.out.println("List Produk");
        produkPresenter.getAllProduk(null).display();
        System.out.println();
    }

    private final CustomerPresenter custPresenter;

    private void listCustomer() {
        System.out.println();
        System.out.println("List Customer");
        custPresenter.getAllCustomers().display();
        System.out.println();
    }
}
