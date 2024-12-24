import util.Formatter;
import util.InputUtilities;
import views.AppRouter;
import views.auth.AuthMenu;

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
    private void onCreate() {

        // setup the menus here
        this.authMenu = new AuthMenu(this.di.userPresenter);
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
                        System.out.println("TODO ADMIN MENU");
                        // this.adminMenu.showMenu();
                        break;
                    case OPERATOR_MENU:
                        System.out.println("TODO OPERATOR MENU");
                        // this.operatorMenu.showMenu();
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
