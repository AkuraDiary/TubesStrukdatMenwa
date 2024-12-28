package views.operator;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import java.time.LocalDateTime;

import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

import data.schemas.adt.DllProduk;
import presenter.CustomerPresenter;
import presenter.ProdukPresenter;
import presenter.TransaksiPresenter;
import util.AppEnums;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.AppRouter.AppRoute;

public class CreateTransaksi {
    private final TransaksiPresenter transaksiPresenter;

    public CreateTransaksi(ProdukPresenter produkPresenter, CustomerPresenter custPresenter, TransaksiPresenter transaksiPresenter) {
        this.produkPresenter = produkPresenter;
        this.custPresenter = custPresenter;
        this.transaksiPresenter = transaksiPresenter;
    }

    // untuk membuat ttransaksi setidaknya membtuhkan idProduk dan idCust
    public void showCreateTransaksiMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == AppRouter.AppRoute.TRANSAKSI) {
            try {
                System.out.println();
                System.out.println("Create Transaksi Menu");
                if(idUser == -1){
                    System.out.println("Anda belum memilih customer");
                }else{
                    System.out.println("Customer : " + custPresenter.selectedCust.getName());
                }
                System.out.println();
                System.out.println("1. Pilih Customer");
                System.out.println("2. List Customer");
                System.out.println("3. List Product");
                System.out.println("4. Keranjang Transaksi");
                System.out.println("5. Lihat Keranjang");
                System.out.println("6. Checkout Transaksi");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");

                String inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        selectCustomer();
                        break;
                    case "2":
                        listCustomer();
                        break;

                    case "3":
                        listProduk();
                        break;

                    case "4":
                        keranjang();
                        break;

                    case "5":
                        System.out.println();
                        System.out.println("Keranjang Produk");
                        keranjangProduk.display();
                        System.out.println();
                        break;
                    case "6":
                        if(idUser == -1){
                            System.out.println("Anda belum memilih customer");
                            break;
                        }
                        if(keranjangProduk.getHead() == null){
                            System.out.println("Keranjang masih kosong");
                            break;
                        }
                        doCreateTransaksi();
                        break;

                    case "0":
                        // konfirmasi keluar
                        System.out.println("Apakah anda yakin ingin keluar dari transaksi? (semua item di keranjang akan hilang)");
                        System.out.println("1. Ya");
                        System.out.println("0. Tidak");
                        System.out.print("Pilihan : ");
                        inputUser = InputUtilities.readLine();
                        assert inputUser != null;
                        if (inputUser.equals("1")) {
                            keranjangProduk.clear();
                            AppRouter.navigateTo(OPERATOR_MENU);
                        }
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                Formatter.formatMessageOutput(e.getMessage());
                invalidChoice();
            }
        }
    }

    DllProduk keranjangProduk = new DllProduk();

    private void keranjang() {
        int cek = 1;
        while (cek != 0) {
            System.out.println();
            System.out.println("Menu Keranjang");

            System.out.println("1. Tambah Produk");
            System.out.println("2. Hapus Produk");
            System.out.println("0. Kembali");

            System.out.print("Pilihan : ");
            String inputUser = InputUtilities.readLine();
            assert inputUser != null;
            if (inputUser.equals("0")) {
                cek = 0;
            } else {
                int idProduk = -1;
                switch (inputUser) {
                    case "1":
                        System.out.print("Masukkan Id product : ");
                         idProduk = InputUtilities.readInt();
                        produkPresenter.selectProduk(idProduk);
                        if (produkPresenter.selectedProduk == null) {
                            System.out.println("Produk tidak ditemukan");
                            continue;
                        }
                        keranjangProduk.insertSorted(produkPresenter.selectedProduk);
                        break;
                    case "2":
                        if (keranjangProduk.getHead() == null) {
                            System.out.println("Keranjang masih kosong");
                            break;
                        }
                        System.out.print("Masukkan Id product : ");
                        idProduk = InputUtilities.readInt();
                        produkPresenter.selectProduk(idProduk);
                        if (produkPresenter.selectedProduk == null) {
                            System.out.println("Produk tidak ditemukan");
                            continue;
                        }
                        keranjangProduk.deleteById(idProduk);
                        break;
                    default:
                        break;
                }

            }


        }
    }

    int idUser = -1;

    private void selectCustomer() {
        System.out.print("Masukkan Id Customer : ");
        idUser = InputUtilities.readInt();
        custPresenter.selectCustomer(idUser);
        if (custPresenter.selectedCust == null) {
            System.out.println("Customer tidak ditemukan");
            idUser = -1;
        }
    }

    private void doCreateTransaksi() {
        // habis jumatan
        System.out.print("Mulai Rental kapan (dd-MM-yyyy HH:mm:ss) : ");
        LocalDateTime startRent = InputUtilities.getDateTimeFromInput();
        // validate startRent is not before now
        assert startRent != null;
        if (startRent.isBefore(LocalDateTime.now())) {
            System.out.println("Waktu rental tidak boleh sebelum waktu sekarang");
            return;
        }

        System.out.print("Berapa lama anda merental? [ " + keranjangProduk.getHead().getData().getProdukRentalInterval() +" ] : ");
        int lamaRental = InputUtilities.readInt();
        if (lamaRental <= 0) {
            System.out.println("Lama rental tidak boleh kurang dari 1");
            return;
        }

        transaksiPresenter.cookTransaksi(
                lamaRental,
                startRent,
                idUser,
                keranjangProduk
        );



//        System.out.println();
        transaksiPresenter.getListTransaksiFiltered(null, AppEnums.StatusTransaksi.Pending, -1, -1, null);
//        transaksiPresenter.listSelectedTransaksi.display();
//        System.out.println();

        if (transaksiPresenter.listSelectedTransaksi.getHead() == null) {
            System.out.println("Transaksi Gagal Dibuat");
            return;
        }

        keranjangProduk.clear();
        idUser = -1;
        System.out.println("Transaksi Berhasil Dibuat Silahkan Tunggu Konfirmasi Dari Admin");
        InputUtilities.pressEnter();
        AppRouter.navigateTo(AppRoute.OPERATOR_MENU);

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
