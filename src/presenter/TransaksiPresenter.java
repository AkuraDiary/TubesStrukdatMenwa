package presenter;

import data.schemas.adt.DllProduk;
import data.schemas.adt.DllTransaksi;
import data.schemas.models.Customer;
import data.schemas.models.Transaksi;
import data.schemas.nodes.NodeProduk;
import repositories.CustomerRepository;
import repositories.ProdukRepository;
import repositories.TransaksiRepository;
import repositories.UserRepository;
import util.AppEnums;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransaksiPresenter {

    UserRepository userRepository;
    CustomerRepository customerRepository;
    TransaksiRepository transaksiRepository;
    ProdukRepository produkRepository;

    public TransaksiPresenter(UserRepository userRepository, TransaksiRepository transaksiRepository,
            ProdukRepository produkRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.transaksiRepository = transaksiRepository;
        this.produkRepository = produkRepository;
        this.customerRepository = customerRepository;
    }

    public DllTransaksi listSelectedTransaksi = new DllTransaksi();
    public Transaksi selectedTransaksi = null;

    public void cookTransaksi(
            int rental_duration,
            LocalDateTime rental_start,
            int idCustomer,
            DllProduk listProduk
    ) {

        AppEnums.RentalInterval rentalInterval = listProduk.getHead().getData().getProdukRentalInterval();
        LocalDateTime rental_end = calcuateRentalEnd(rental_start, rental_duration, rentalInterval);
        Customer customer = customerRepository.getAllCustomers().searchById(idCustomer);

        System.out.println("Rental Start : " + rental_start);
        System.out.println("Rental Duration : " + rental_duration);
        System.out.println("Rental End : " + rental_end);

        Transaksi transaksiData = new Transaksi(
                -1,
                rental_duration,
                rental_start,
                rental_end,
                AppEnums.StatusTransaksi.Pending,
                rentalInterval,
                userRepository.getLoggedInUser(),
                customer
        );
        transaksiData.setListProduk(listProduk);
        updateRentProductStatus(listProduk, AppEnums.ProdukStatus.Rented);
        transaksiRepository.addTransaksi(transaksiData);
    }

    private LocalDateTime calcuateRentalEnd(LocalDateTime rentalStart, int rentalDuration, AppEnums.RentalInterval rentalInterval) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return switch (rentalInterval) {
            case Hour -> rentalStart.plusHours(rentalDuration);
            case Day -> rentalStart.plusDays(rentalDuration);
            case Week -> rentalStart.plusWeeks(rentalDuration);
            case Month -> rentalStart.plusMonths(rentalDuration);
            // case null -> throw new IllegalStateException("Unexpected value: " + rentalInterval);
        };
    }

    public void selectTransaksi(int id) {
        transaksiRepository.selectTransaksi(id);
        selectedTransaksi = transaksiRepository.selectedTransaksi;
    }

    private void updateRentProductStatus(DllProduk listProduk, AppEnums.ProdukStatus status) {
        NodeProduk current = listProduk.getHead();
        while (current != null) {
            current.getData().setProdukStatus(status);
            produkRepository.updateProduk(current.getData());
            current = current.getNext();
        }
    }

    public void updateTransaksi(int idTransaksi, Transaksi transaksi) {
        transaksiRepository.selectTransaksi(idTransaksi);
        transaksiRepository.updateTransaksi(idTransaksi, transaksi);

        if (transaksi.getRental_status() == AppEnums.StatusTransaksi.Rejected || transaksi.getRental_status() == AppEnums.StatusTransaksi.Done) {
            updateRentProductStatus(transaksi.getListProduk(), AppEnums.ProdukStatus.Available);
        }
    }


    public void getListTransaksiFiltered(
            Date tanggalTransaksi,
            AppEnums.StatusTransaksi statusTransaksi,
            int idCustomer,
            int idUser, 
            AppEnums.RentalInterval rentalInterval) {

        if (tanggalTransaksi != null) {
            transaksiRepository.selectTransaksisByDate(tanggalTransaksi);
        } else if (statusTransaksi != null) {
            transaksiRepository.selectTransaksiByStatus(statusTransaksi);
        } else if (idCustomer != -1) {
            transaksiRepository.selectTransaksisByCustomer(idCustomer);
        } else if (idUser != -1) {
            transaksiRepository.selectTransaksiByUserId(idUser);
        } else if(rentalInterval != null){
            transaksiRepository.selectTransaksiByInterval(rentalInterval);
        }else {
            transaksiRepository.getAllTransaksi();
        }

        listSelectedTransaksi = transaksiRepository.getSelectedListTransaksi();
    }

}
