// redirect to '../.adt/DllTransaksi.java'
// redirect to '../.nodes/NodeTransaksi.java'

package data.schemas.models;

import data.schemas.adt.DllProduk;

import data.schemas.nodes.NodeProduk;
import util.AppEnums;
import util.Formatter;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    int id_transaksi, rental_duration, rental_fine=0, rental_due;
    long total_price =0L;
    User pic;
    Customer customer;
    LocalDateTime rental_start, rental_end;
    AppEnums.StatusTransaksi rental_status;
    AppEnums.RentalInterval rental_interval;

    DllProduk listProduk;

    public DllProduk getListProduk() {
        return listProduk;
    }

    public void setListProduk(DllProduk listProduk) {
        this.listProduk = listProduk;
        calculateTotalPrice();
    }

    @Override
    public String toString() {
        calculateTotalPrice();
        calcluatedue();
        return "Transaksi\n" +
                "id_transaksi: " + id_transaksi +
                "\nrental_duration: " + rental_duration +
                "\nrental_fine: " + Formatter.formatRupiah((double) this.rental_fine) +
                "\nrental_due: " + this.rental_due +
                "\ntotal_price: " + Formatter.formatRupiah((double) this.total_price) +
                "\nrental_start: " + rental_start +
                "\nrental_end: " + rental_end +
                "\nrental_status: " + rental_status +
                "\nrental_interval: " + rental_interval;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public int getRental_fine() {
        calcluatedue();
        return this.rental_fine;
    }

    public void setRental_fine(int rental_fine) {
        this.rental_fine = rental_fine;
    }

    public void calculateTotalPrice() {
        // calculate total price based on accumulation of product price in list product with the rental duration
        long total = 0;
        NodeProduk current = listProduk.getHead();
//        System.out.println(current == null);
        while (current != null) {
            int productPrice = current.getData().getProdukRentalPrice();
//            System.out.println("Product Price: " + productPrice);
            total += productPrice;
            current = current.getNext();
        }
//        System.out.println("Rental Duration: " + rental_duration);
//        System.out.println("Total: " + total);
        total_price = (long) total * rental_duration;
//        System.out.println("Total Price: " + total_price);
    }

    public void calcluatedue() {
        calculateTotalPrice();
        // calculate rental due based on rental duration, current date and rental start date
        LocalDateTime now = LocalDateTime.now();
        boolean isDue = switch (rental_interval) {
            case Day -> now.getDayOfMonth() > rental_start.getDayOfMonth();
            case Week -> now.getDayOfMonth() > rental_start.getDayOfMonth() + 7;
            case Month -> now.getMonthValue() > rental_start.getMonthValue();
            case Hour -> now.getHour() > rental_start.getHour();
        };// adjust within the interval
        if (isDue) {
            rental_status = AppEnums.StatusTransaksi.Due;
            this.rental_due =
                    switch (rental_interval) {
                        case Day -> now.getDayOfMonth() - rental_start.getDayOfMonth();
                        case Week -> (now.getDayOfMonth() - rental_start.getDayOfMonth()) / 7;
                        case Month -> now.getMonthValue() - rental_start.getMonthValue();
                        case Hour -> now.getHour() - rental_start.getHour();

                    };
        } else {
            this.rental_due = 0;
        }
        // calculate rental fine based on rental due
        // fine is calculated by this formula: fine = due * 10% of total price
        this.rental_fine = (int) (rental_due * 0.1 * total_price);
    }

    public int getRental_due() {
        calcluatedue();
        return rental_due;
    }

    public void setRental_due(int rental_due) {
        this.rental_due = rental_due;
    }


    public long getTotal_price() {
        calculateTotalPrice();
        return total_price;
    }

    public void setTotal_price(long total_price) {
        this.total_price = total_price;
    }

    public LocalDateTime getRental_start() {
        return rental_start;
    }

    public void setRental_start(LocalDateTime rental_start) {
        this.rental_start = rental_start;
    }

    public LocalDateTime getRental_end() {
        return rental_end;
    }

    public void setRental_end(LocalDateTime rental_end) {
        this.rental_end = rental_end;
    }

    public AppEnums.StatusTransaksi getRental_status() {
        calcluatedue();
        return rental_status;
    }

    public void setRental_status(AppEnums.StatusTransaksi rental_status) {
        this.rental_status = rental_status;
    }

    public AppEnums.RentalInterval getRental_interval() {
        return rental_interval;
    }

    public void setRental_interval(AppEnums.RentalInterval rental_interval) {
        this.rental_interval = rental_interval;
    }

    public Transaksi(
            int id_transaksi,
            int rental_duration,
            LocalDateTime rental_start,
            LocalDateTime rental_end,
            AppEnums.StatusTransaksi rental_status,
            AppEnums.RentalInterval rental_interval,
            User pic, Customer customer
    ) {
        this.id_transaksi = id_transaksi;
        this.rental_duration = rental_duration;
        this.rental_start = rental_start;
        this.rental_end = rental_end;
        this.rental_status = rental_status;
        this.rental_interval = rental_interval;
        this.pic = pic;
        this.customer = customer;
        listProduk = new DllProduk();

        calculateTotalPrice();
        calcluatedue();
    }


    public User getPic() {
        return pic;
    }

    public void setPic(User pic) {
        this.pic = pic;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
