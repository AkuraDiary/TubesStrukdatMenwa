import data.schemas.models.Produk;
import data.schemas.models.Transaksi;
import data.sources.*;
import repositories.ProdukRepository;
import repositories.UserRepository;

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

    //PRESENTERS

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

        System.out.println("DI Initialization Done");
    }

    public void initDatasources() {
        productDataSource.initDataProduct();
        customerDataSource.initializeDataCustomer();
        userDataSource.initDataUser();
    }
}
