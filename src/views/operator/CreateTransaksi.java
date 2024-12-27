package views.operator;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import java.time.LocalDateTime;

import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

import data.schemas.adt.DllProduk;
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
                System.out.println("1. List Customer");
                System.out.println("2. List Product");
                System.out.println("3. Masukkan Barang Ke Keranjang");
                System.out.println("4. Create Transaksi");
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
                        keranjang();
                        break;

                    case "4":
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

    DllProduk keranjangProduk ;
    private void keranjang(){
        int cek =1;
        while (cek!=0) {
            System.out.println("Menu Keranjang");
            System.out.println("Masukkan Id product : ");
            int idProduk= InputUtilities.readInt();
            produkPresenter.selectProduk(idProduk);

            keranjangProduk.insertSorted(produkPresenter.selectedProduk);

            System.out.println("1. Masukkan Product lagi");
            System.out.println("0. Kembali");
            System.out.print("Pilihan : ");
            String inputUser = InputUtilities.readLine();
            if (inputUser.equals("0")) {
                cek=0;
            }

        }
    }
    private void doCreateTransaksi() {
        // habis jumatan
        System.out.print("Masukkan Id Customer : ");
        int idUser = InputUtilities.readInt();
        System.out.println("Mulai Rental kapan : (dd-MM-yyyy HH:mm:ss)");
        LocalDateTime startRent = InputUtilities.getDateTimeFromInput();
        System.out.println("Berapa lama anda merental?");
        int lamaRental = InputUtilities.readInt();

        transaksiPresenter.cookTransaksi(
            lamaRental,
            startRent,
            idUser,
            keranjangProduk
        );
        

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
