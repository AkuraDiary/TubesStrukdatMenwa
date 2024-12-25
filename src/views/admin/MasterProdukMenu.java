package views.admin;

import data.schemas.models.Produk;
import presenter.ProdukPresenter;
import util.AppEnums;
import util.Encryption;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.*;

public class MasterProdukMenu {
    private final ProdukPresenter produkPresenter;

    public MasterProdukMenu(ProdukPresenter produkPresenter) {
        this.produkPresenter = produkPresenter;
    }

    String inputUser;

    public void showMasterProdukMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == MASTER_PRODUK) {
            try {
                System.out.println("Master Operator Menu");
                System.out.println("1. List Produk");
                System.out.println("2. Tambah Produk");
                System.out.println("3. Edit Produk");
                System.out.println("4. Hapus Produk");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        tampilListProduk();
                        break;
                    case "2":
                        tambahProduk();
                        break;
                    case "3":
                        editProduk();
                        break;
                    case "4":
                        hapusProduk();
                        break;
                    case "0":
                        AppRouter.navigateTo(ADMiN_MENU);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (Exception e) {
                invalidChoice();
            }

        }


    }

    private void hapusProduk() {
        try {
            System.out.println("Pilih Produk untuk dihapus");
            System.out.print("Masukkan Id Produk untuk dipilih : ");
            int idProduk = InputUtilities.readInt();
            produkPresenter.selectProduk(idProduk);

            System.out.print("[Konfirmasi] apakah anda yakin untuk menghapus data ini? [y/n]: ");
            String jawab = InputUtilities.readLine();
            if (jawab != null && jawab.equalsIgnoreCase("y")) {
                produkPresenter.deleteProduk(idProduk);
                System.out.println();
            }
        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }

    }

    private void editProduk() {
        try {
            System.out.println("Pilih Produk untuk diedit");
            System.out.print("Masukkan Id Produk untuk dipilih : ");
            int idProduk = InputUtilities.readInt();

            produkPresenter.selectProduk(idProduk);

            if (produkPresenter.selectedProduk != null) {
                System.out.println("Edit Data Produk (kosongkan jika tidak ingin merubah)");
                System.out.print("Masukkan Nama Produk : ");
                String produkName = InputUtilities.readLine();
                System.out.print("Masukkan Brand Produk : ");
                String produkBrand = InputUtilities.readLine();
                System.out.print("Masukkan Nomor Produk : ");
                String produkNumber = InputUtilities.readLine();
                System.out.print("Masukkan Harga Rental Produk : ");
                int produkRentalPrice = InputUtilities.readInt();
                AppEnums.RentalInterval produkRentalInterval = InputUtilities.getRentalIntervalFromInput();

                produkPresenter.updateProduk(
                        produkPresenter.selectedProduk.getProdukId(),
                        produkName,
                        produkBrand,
                        produkNumber,
                        produkRentalPrice,
                        produkRentalInterval
                );
                System.out.println("Produk Berhasil Diupdate");
                System.out.println();
            }


        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void tambahProduk() {
        try {
            InputUtilities.cls();
            System.out.println("Tambah Produk");
            System.out.print("Masukkan Nama Produk : ");
            String produkName = InputUtilities.readLine();
            System.out.print("Masukkan Brand Produk : ");
            String produkBrand = InputUtilities.readLine();
            System.out.print("Masukkan Nomor Produk : ");
            String produkNumber = InputUtilities.readLine();
            System.out.print("Masukkan Harga Rental Produk : ");
            int produkRentalPrice = InputUtilities.readInt();
            AppEnums.RentalInterval produkRentalInterval = InputUtilities.getRentalIntervalFromInput();

            produkPresenter.addProduk(
                    -1,
                    produkName,
                    produkBrand,
                    produkNumber,
                    produkRentalPrice,
                    produkRentalInterval
            );
            System.out.println("Produk Berhasil Ditambahkan");
            System.out.println();

        } catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void tampilListProduk() {
        System.out.println();
        System.out.println("List Produk");
        produkPresenter.getAllProduk(null).display();
        System.out.println();
    }
}
