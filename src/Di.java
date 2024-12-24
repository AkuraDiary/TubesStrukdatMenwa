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
        productDataSource = new ProductDataSource();
        customerDataSource = new CustomerDataSource();
        laporanDataSource = new LaporanDataSource();
        transaksiDataSource = new TransaksiDataSource();
        userDataSource = new UserDataSource();

        produkRepository = new ProdukRepository(productDataSource);
        userRepository = new UserRepository(userDataSource);
    }
}
