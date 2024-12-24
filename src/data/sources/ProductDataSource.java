package data.sources;

import data.schemas.adt.DllProduk;
import data.schemas.models.Produk;
import util.AppEnums;

public class ProductDataSource {
    public DllProduk productList = new DllProduk();

    public void initDataProduct() {
        System.out.println("Data Product Initialization");
        productList.insertSorted(new Produk(1, "Toyota Avanza", "Toyota", "TA-2023", 50000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(2, "Honda Brio", "Honda", "HB-2022", 60000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(3, "Daihatsu Xenia", "Daihatsu", "DX-2021", 55000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(4, "Mitsubishi Pajero", "Mitsubishi", "MP-2020", 120000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(5, "Suzuki Ertiga", "Suzuki", "SE-2023", 65000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(6, "Nissan Serena", "Nissan", "NS-2022", 80000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(7, "Mazda CX-3", "Mazda", "MC3-2021", 85000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(8, "BMW Seri 3", "BMW", "B3-2023", 200000, AppEnums.RentalInterval.Month));
        productList.insertSorted(new Produk(9, "Audi A6", "Audi", "AA6-2023", 180000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(10, "Mercedes-Benz GLA", "Mercedes-Benz", "MBG-2022", 250000, AppEnums.RentalInterval.Month));
        productList.insertSorted(new Produk(11, "Hyundai Santa Fe", "Hyundai", "HSF-2023", 90000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(12, "Kia Seltos", "Kia", "KS-2022", 75000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(13, "Volkswagen Tiguan", "Volkswagen", "VT-2021", 100000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(14, "Subaru Forester", "Subaru", "SF-2020", 110000, AppEnums.RentalInterval.Month));
        productList.insertSorted(new Produk(15, "Volvo XC40", "Volvo", "VX40-2023", 300000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(16, "Lexus UX", "Lexus", "LUX-2023", 250000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(17, "Jeep Compass", "Jeep", "JC-2022", 200000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(18, "Chevrolet Trailblazer", "Chevrolet", "CT-2021", 270000, AppEnums.RentalInterval.Month));
        productList.insertSorted(new Produk(19, "Porsche Cayenne", "Porsche", "PC-2023", 500000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(20, "Ford Everest", "Ford", "FE-2023", 140000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(21, "Honda CR-V", "Honda", "HCRV-2022", 120000, AppEnums.RentalInterval.Month));
        productList.insertSorted(new Produk(22, "Toyota Fortuner", "Toyota", "TF-2021", 180000, AppEnums.RentalInterval.Week));
        productList.insertSorted(new Produk(23, "GMC Acadia", "GMC", "GA-2022", 160000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(24, "Land Rover Discovery", "Land Rover", "LRD-2023", 350000, AppEnums.RentalInterval.Day));
        productList.insertSorted(new Produk(25, "Tesla Model Y", "Tesla", "TMY-2023", 200000, AppEnums.RentalInterval.Day));

        System.out.println("Data Product Initialized");

    }
}
