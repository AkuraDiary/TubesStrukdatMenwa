package presenter;

import data.schemas.adt.DllProduk;
import data.schemas.adt.DllTransaksi;
import data.schemas.models.Customer;
import data.schemas.models.Transaksi;
import data.schemas.models.User;
import data.schemas.nodes.NodeProduk;
import repositories.CustomerRepository;
import repositories.ProdukRepository;
import repositories.TransaksiRepository;
import repositories.UserRepository;
import util.AppEnums;

import java.util.Date;

public class TransaksiPresenter {

    UserRepository userRepository;
    CustomerRepository customerRepository;
    TransaksiRepository transaksiRepository;
    ProdukRepository produkRepository;

    public TransaksiPresenter(UserRepository userRepository, TransaksiRepository transaksiRepository, ProdukRepository produkRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.transaksiRepository = transaksiRepository;
        this.produkRepository = produkRepository;
        this.customerRepository = customerRepository;
    }

    DllTransaksi listSelectedTransaksi = new DllTransaksi();
    Transaksi selectedTransaksi = null;

    public void cookTransaksi(
            int rental_duration,
            Date rental_start,
            Date rental_end,
            int idCustomer,
            DllProduk listProduk
    ) {

        Customer customer = customerRepository.getAllCustomers().searchById(idCustomer);
        Transaksi transaksiData = new Transaksi(
                -1,
                rental_duration,
                rental_start,
                rental_end,
                AppEnums.StatusTransaksi.Pending,
                listProduk.getHead().getData().getProdukRentalInterval(),
                userRepository.getLoggedInUser(),
                customer
        );
        transaksiData.setListProduk(listProduk);
        updateStatusProduk(listProduk);
        transaksiRepository.addTransaksi(transaksiData);
    }

    public void selectTransaksi(int id){
        transaksiRepository.selectTransaksi(id);
        selectedTransaksi = transaksiRepository.selectedTransaksi;
    }

    private void updateStatusProduk(DllProduk listProduk) {
        NodeProduk current = listProduk.getHead();
        while (current != null) {
            current.getData().setProdukStatus(AppEnums.ProdukStatus.Rented);
            produkRepository.updateProduk(current.getData());
            current = current.getNext();
        }
    }

    private void getListTransaksiFiltered(
            Date tanggalTransaksi,
            AppEnums.StatusTransaksi statusTransaksi,
            int idCustomer,
            int idUser,
            AppEnums.RentalInterval rentalInterval
    ) {
        if (tanggalTransaksi != null) {
            transaksiRepository.selectTransaksisByDate(tanggalTransaksi);
        } else if (statusTransaksi != null) {
            transaksiRepository.selectTransaksiByStatus(statusTransaksi);
        } else if (idCustomer != -1) {
            transaksiRepository.selectTransaksisByCustomer(idCustomer);
        } else if (idUser != -1) {
            transaksiRepository.selectTransaksiByUserId(idUser);
        } else {
            transaksiRepository.selectTransaksiByInterval(rentalInterval);
        }
        listSelectedTransaksi = transaksiRepository.selectedTransaksiList;
    }


}