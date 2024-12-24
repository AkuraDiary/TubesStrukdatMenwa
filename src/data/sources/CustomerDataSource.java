package data.sources;

import data.schemas.adt.DllCustomer;
import data.schemas.models.Customer;

public class CustomerDataSource {
    public DllCustomer customerList = new DllCustomer();

    public void initializeDataCustomer(){
        System.out.println("Data Customer Initialization");
        customerList.insertSorted(new Customer(1, "Budi Santoso", "budi.santoso@example.com", "KTP12345678", "081234567890", "Jl. Merpati No. 10, Jakarta"));
        customerList.insertSorted(new Customer(2, "Siti Aminah", "siti.aminah@example.com", "KTP87654321", "081987654321", "Jl. Kenari No. 20, Surabaya"));
        customerList.insertSorted(new Customer(3, "Andi Wijaya", "andi.wijaya@example.com", "KTP11223344", "081556677889", "Jl. Anggrek No. 30, Bandung"));
        customerList.insertSorted(new Customer(4, "Dewi Lestari", "dewi.lestari@example.com", "KTP55667788", "081445566778", "Jl. Melati No. 40, Yogyakarta"));
        customerList.insertSorted(new Customer(5, "Eko Prasetyo", "eko.prasetyo@example.com", "KTP99001122", "081333444555", "Jl. Mawar No. 50, Medan"));
        customerList.insertSorted(new Customer(6, "Rina Wahyuni", "rina.wahyuni@example.com", "KTP22334455", "081222333444", "Jl. Cemara No. 60, Palembang"));
        customerList.insertSorted(new Customer(7, "Agus Suryono", "agus.suryono@example.com", "KTP33445566", "081111222333", "Jl. Flamboyan No. 70, Semarang"));
        customerList.insertSorted(new Customer(8, "Nina Kartika", "nina.kartika@example.com", "KTP44556677", "081999888777", "Jl. Teratai No. 80, Malang"));
        customerList.insertSorted(new Customer(9, "Hendra Gunawan", "hendra.gunawan@example.com", "KTP55667788", "081888777666", "Jl. Dahlia No. 90, Denpasar"));
        customerList.insertSorted(new Customer(10, "Ratna Puspita", "ratna.puspita@example.com", "KTP66778899", "081777666555", "Jl. Bougenville No. 100, Makassar"));
        customerList.insertSorted(new Customer(11, "Fajar Satria", "fajar.satria@example.com", "KTP77889900", "081666555444", "Jl. Cempaka No. 110, Pekanbaru"));
        customerList.insertSorted(new Customer(12, "Lia Andriani", "lia.andriani@example.com", "KTP88990011", "081555444333", "Jl. Kemuning No. 120, Batam"));
        customerList.insertSorted(new Customer(13, "Yoga Aditya", "yoga.aditya@example.com", "KTP99001122", "081444333222", "Jl. Akasia No. 130, Balikpapan"));
        customerList.insertSorted(new Customer(14, "Anisa Rahmawati", "anisa.rahmawati@example.com", "KTP11223344", "081333222111", "Jl. Kamboja No. 140, Banjarmasin"));
        customerList.insertSorted(new Customer(15, "Rizki Maulana", "rizki.maulana@example.com", "KTP22334455", "081222111000", "Jl. Jati No. 150, Padang"));
        customerList.insertSorted(new Customer(16, "Putri Ayu", "putri.ayu@example.com", "KTP33445566", "081111000999", "Jl. Pinus No. 160, Samarinda"));
        customerList.insertSorted(new Customer(17, "Adi Saputra", "adi.saputra@example.com", "KTP44556677", "081999888777", "Jl. Tanjung No. 170, Pontianak"));
        customerList.insertSorted(new Customer(18, "Maya Sari", "maya.sari@example.com", "KTP55667788", "081888777666", "Jl. Kelapa No. 180, Jambi"));
        customerList.insertSorted(new Customer(19, "Taufik Hidayat", "taufik.hidayat@example.com", "KTP66778899", "081777666555", "Jl. Bambu No. 190, Manado"));
        customerList.insertSorted(new Customer(20, "Desi Amelia", "desi.amelia@example.com", "KTP77889900", "081666555444", "Jl. Palem No. 200, Ambon"));
        customerList.insertSorted(new Customer(21, "Hadi Kurniawan", "hadi.kurniawan@example.com", "KTP88990011", "081555444333", "Jl. Mangga No. 210, Kupang"));
        customerList.insertSorted(new Customer(22, "Intan Permata", "intan.permata@example.com", "KTP99001122", "081444333222", "Jl. Jambu No. 220, Banda Aceh"));
        System.out.println("Data Customer Initialized");
    }

}
