package data.sources;

import data.schemas.adt.DllCustomer;
import data.schemas.adt.DllProduk;
import data.schemas.adt.DllTransaksi;
import data.schemas.models.Customer;
import data.schemas.models.Transaksi;
import data.schemas.models.User;
import util.AppEnums;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaksiDataSource {
    public DllTransaksi transaksiList = new DllTransaksi();

    public void initDataTransaksis(
            DllProduk produkList,
            DllCustomer customers,
            User user
    ) {
        System.out.println("Init Data Transaksi");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Transaksi transaksi = new Transaksi(
                1,
                2,
                LocalDateTime.parse("20-11-2024 13:30:00", formatter),
                LocalDateTime.parse("22-11-2024 15:30:00", formatter),
                AppEnums.StatusTransaksi.Due,
                AppEnums.RentalInterval.Day,
                user,
                customers.searchById(4)
        );
        DllProduk listProduk = new DllProduk();
        listProduk.insertSorted(produkList.searchById(4));
        listProduk.insertSorted(produkList.searchById(2));
        transaksi.setListProduk(listProduk);
        transaksiList.insertSortedByStartDate(
                transaksi
        );

        // Randomize to 5 transaksi
        for (int i = 2; i < 6; i++){
            transaksi = new Transaksi(
                    i,
                    2,
                    LocalDateTime.parse("1"+i+"-11-2024 13:30:00", formatter),
                    LocalDateTime.parse("1"+(i+2)+"-11-2024 15:30:00", formatter),
                    AppEnums.StatusTransaksi.Due,
                    AppEnums.RentalInterval.Day,
                    user,
                    customers.searchById(4)
            );
            listProduk = new DllProduk();
            listProduk.insertSorted(produkList.searchById(i));
            listProduk.insertSorted(produkList.searchById(i));
            transaksi.setListProduk(listProduk);
            transaksiList.insertSortedByStartDate(
                    transaksi
            );
        }

        System.out.println("Data Transaksi Initialized");

    }
}
