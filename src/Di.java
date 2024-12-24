import data.schemas.models.Produk;
import data.schemas.models.Transaksi;
import data.schemas.models.User;
import data.sources.*;
import presenter.UserPresenter;
import repositories.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Di {
    // DATASOURCES
    ProductDataSource productDataSource;
    CustomerDataSource customerDataSource;
    LaporanDataSource laporanDataSource;
    TransaksiDataSource transaksiDataSource;
    UserDataSource userDataSource;

    //REPOSITORIES
    ProdukRepository produkRepository;
    UserRepository userRepository;
    TransaksiRepository transaksiRepository;
    CustomerRepository customerRepository;
    LaporanRepository laporanRepository;

    //PRESENTERS
    UserPresenter userPresenter;

    //MENUS

    //INPUTS
    BufferedReader inputReader;

    public Di() {
        System.out.println("DI Initialization");
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        productDataSource = new ProductDataSource();
        customerDataSource = new CustomerDataSource();
        laporanDataSource = new LaporanDataSource();
        transaksiDataSource = new TransaksiDataSource();
        userDataSource = new UserDataSource();

        produkRepository = new ProdukRepository(productDataSource);
        userRepository = new UserRepository(userDataSource);
        transaksiRepository = new TransaksiRepository(transaksiDataSource);
        customerRepository = new CustomerRepository(customerDataSource);
        laporanRepository = new LaporanRepository(laporanDataSource);

        userPresenter = new UserPresenter(userRepository);

        System.out.println("DI Initialization Done");
    }

    public void initDatasources() {
        productDataSource.initDataProduct();
        customerDataSource.initializeDataCustomer();
        userDataSource.initDataUser();
    }
}
