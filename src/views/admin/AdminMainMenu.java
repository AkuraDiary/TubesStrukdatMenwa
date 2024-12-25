package views.admin;

import presenter.UserPresenter;
import util.InputUtilities;
import views.AppRouter;

import java.io.IOException;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.ADMIN_MENU;

public class AdminMainMenu {

    private final UserPresenter userPresenter;

    public AdminMainMenu(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    String inputUser;
    public void showAdminMainMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == ADMIN_MENU) {
            System.out.println("Main Menu Admin");
            System.out.println("1. Master Operator");
            System.out.println("2. Laporan");
            System.out.println("3. Approve Transaksi");
            System.out.println("4. Master Produk");
            System.out.println("0. Logout");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_OPERATOR);
                        break;
                    case "2":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_LAPORAN);
                        break;
                    case "3":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_APPROVE_TRANSAKSI);
                        break;
                    case "4":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_PRODUK);
                        break;
                    case "0":
                        userPresenter.doLogout();
                        AppRouter.navigateTo(AppRouter.AppRoute.LOGIN);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (IOException e) {
                invalidChoice();
            }

        }

    }
}
