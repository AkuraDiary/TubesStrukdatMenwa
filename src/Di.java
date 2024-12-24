import data.schemas.models.Produk;
import data.schemas.models.Transaksi;
import data.schemas.models.User;
import data.sources.*;
import presenter.*;
import repositories.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Di {
    // DATASOURCES
    public ProductDataSource productDataSource;
    public CustomerDataSource customerDataSource;
    public LaporanDataSource laporanDataSource;
    public TransaksiDataSource transaksiDataSource;
    public UserDataSource userDataSource;

    //REPOSITORIES
    public ProdukRepository produkRepository;
    public UserRepository userRepository;
    public TransaksiRepository transaksiRepository;
    public CustomerRepository customerRepository;
    public LaporanRepository laporanRepository;

    //PRESENTERS
    public UserPresenter userPresenter;
    public CustomerPresenter customerPresenter;
    public TransaksiPresenter transaksiPresenter;
    public LaporanPresenter laporanPresenter;
    public ProdukPresenter produkPresenter;

    //MENUS



    public Di() {
        System.out.println("DI Initialization");

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
        customerPresenter = new CustomerPresenter(customerRepository);
        produkPresenter = new ProdukPresenter(produkRepository);
        laporanPresenter = new LaporanPresenter(userRepository,transaksiRepository ,laporanRepository);
        transaksiPresenter = new TransaksiPresenter(userRepository, transaksiRepository,produkRepository,customerRepository);


        System.out.println("DI Initialization Done");
    }

    public void initDatasources() {
        productDataSource.initDataProduct();
        customerDataSource.initializeDataCustomer();
        userDataSource.initDataUser();
    }
}
