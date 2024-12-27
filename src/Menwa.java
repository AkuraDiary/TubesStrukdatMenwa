import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.admin.*;
import views.auth.AuthMenu;
import views.operator.*;

public class Menwa {
    private Di di;

    // Menus
    Menwa(Di di) {
        // init the application here
        this.di = di;
        onCreate();
        onViewCreated();
    }

    CreateTransaksi createTransaksi;
    MasterCustomerMenu masterCustomerMenu;
    PembayaranMenu pembayaranMenu;
    PengembalianMenu pengembalianMenu;
    AuthMenu authMenu;
    AdminMainMenu adminMainMenu;
    OperatorMainMenu operatorMainMenu;
    MasterOperatorMenu masterOperatorMenu;
    MasterProdukMenu masterProdukMenu;
    AdminLaporanMenu adminLaporanMenu;
    AdminTransaksiMenu adminTransaksiMenu;

    private void onCreate() {

        this.createTransaksi = new CreateTransaksi(this.di.produkPresenter, this.di.customerPresenter, this.di.transaksiPresenter);
        this.masterCustomerMenu = new MasterCustomerMenu(this.di.customerPresenter);
        this.masterOperatorMenu = new MasterOperatorMenu(this.di.userPresenter);
        this.pembayaranMenu = new PembayaranMenu(this.di.transaksiPresenter);
        this.pengembalianMenu = new PengembalianMenu(this.di.transaksiPresenter);

        // setup the menus here
        this.authMenu = new AuthMenu(this.di.userPresenter);
        this.adminMainMenu = new AdminMainMenu(this.di.userPresenter);

        this.operatorMainMenu = new OperatorMainMenu(this.di.userPresenter);
        this.masterProdukMenu = new MasterProdukMenu(this.di.produkPresenter);
        this.adminLaporanMenu = new AdminLaporanMenu(this.di.laporanPresenter);
        this.adminTransaksiMenu = new AdminTransaksiMenu(this.di.transaksiPresenter);

    }

    private void onViewCreated() {
        // initial route move to Login
        AppRouter.navigateTo(AppRouter.AppRoute.LOGIN);
    }

    public void run() {
        while (true) {
            try {
                switch (AppRouter.activeRoute) {
                    case LOGIN:
                        this.authMenu.showLogin();
                        break;
                    case ADMIN_MENU:
                        adminMainMenu.showAdminMainMenu();
                        break;
                    case OPERATOR_MENU:
                        operatorMainMenu.showOperatorMainMenu();
                        break;
                    case MASTER_OPERATOR:
                        masterOperatorMenu.showMasterOperatorMenu();
                        break;
                    case MASTER_PRODUK:
                        masterProdukMenu.showMasterProdukMenu();
                        break;
                    case MASTER_LAPORAN:
                        adminLaporanMenu.showLaporanMenu();
                        break;
                    case MASTER_APPROVE_TRANSAKSI:
                        adminTransaksiMenu.showAdminTransaksiMenu();
                        break;
                    case MASTER_CUSTOMER:
                        // operatorMainMenu.showOperatorMainMenu();
                        masterCustomerMenu.showMasterCustomerMenu();
                        break;
                    case TRANSAKSI:
                        createTransaksi.showCreateTransaksiMenu();
                        break;
                    case PEMBAYARAN:
                        pembayaranMenu.showPembayaranMenu();
                        break;
                    case PENGEMBALIAN:
                        pengembalianMenu.showPengembalianMenu();
                        break;
                    case EXIT:
                        Formatter.formatMessageOutput("Terimakasih telah menggunakan aplikasi kami");
                        Formatter.formatMessageOutput("Exiting");
                        System.exit(0);
                        break;
                    default:
                        Formatter.formatMessageOutput("Invalid Route");
                        break;
                }

            } catch (Exception e) {
                // in case something goes wroong, like the user press CTRL+ c
                Formatter.formatMessageOutput("Woops, something went wrong : " + e.getMessage());
                InputUtilities.closeReader();
                AppRouter.navigateTo(AppRouter.AppRoute.EXIT);
            }
        }
    }

}
