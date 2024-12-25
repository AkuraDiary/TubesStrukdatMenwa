import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.admin.AdminLaporanMenu;
import views.admin.AdminMainMenu;
import views.admin.MasterOperatorMenu;
import views.admin.MasterProdukMenu;
import views.auth.AuthMenu;
import views.operator.OperatorMainMenu;

public class Menwa {
    private Di di;

    // Menus
    Menwa(Di di) {
        // init the application here
        this.di = di;
        onCreate();
        onViewCreated();
    }

    AuthMenu authMenu;
    AdminMainMenu adminMainMenu;
    OperatorMainMenu operatorMainMenu;
    MasterOperatorMenu masterOperatorMenu;
    MasterProdukMenu masterProdukMenu;
    AdminLaporanMenu adminLaporanMenu;

    private void onCreate() {

        // setup the menus here
        this.authMenu = new AuthMenu(this.di.userPresenter);
        this.adminMainMenu = new AdminMainMenu(this.di.userPresenter);

        this.operatorMainMenu = new OperatorMainMenu(this.di.userPresenter);
        this.masterOperatorMenu = new MasterOperatorMenu(this.di.userPresenter);
        this.masterProdukMenu = new MasterProdukMenu(this.di.produkPresenter);
        this.adminLaporanMenu = new AdminLaporanMenu(this.di.laporanPresenter);

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
                    case ADMiN_MENU:
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
