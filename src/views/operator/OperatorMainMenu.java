package views.operator;

import presenter.UserPresenter;
import util.InputUtilities;
import views.AppRouter;

import java.io.IOException;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.ADMiN_MENU;

public class OperatorMainMenu {
    private final UserPresenter userPresenter;

    public OperatorMainMenu(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    String inputUser;
    public void showOperatorMainMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == ADMiN_MENU) {
            System.out.println("Main Menu Admin");
            System.out.println("1. Master Customer");
            System.out.println("2. Create Transaksi");
            System.out.println("3. Pembayaran");
            System.out.println("4. Pengembalian");
            System.out.println("0. Logout");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_CUSTOMER);
                        break;
                    case "2":
                        AppRouter.navigateTo(AppRouter.AppRoute.TRANSAKSI);
                        break;
                    case "3":
                        AppRouter.navigateTo(AppRouter.AppRoute.PEMBAYARAN);
                        break;
                    case "4":
                        AppRouter.navigateTo(AppRouter.AppRoute.PENGEMBALIAN);
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
