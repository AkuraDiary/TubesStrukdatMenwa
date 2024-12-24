package views.auth;

import presenter.UserPresenter;
import util.InputUtilities;
import views.AppRouter;

import java.io.IOException;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.LOGIN;
import static views.SharedComponents.appHeader;

public class AuthMenu {

    private final UserPresenter userPresenter;

    public AuthMenu(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }
    String inputUser;
    public void showLogin() {
        while (AppRouter.activeRoute == LOGIN) {
            InputUtilities.cls();
            appHeader();
            System.out.println("1. Login");
            System.out.println("0. EXIT");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        showLoginInputs();
                        break;
                    case "0":
                        AppRouter.navigateTo(AppRouter.AppRoute.EXIT);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void showLoginInputs() {
        InputUtilities.cls(); // clear terminal
        try {
            System.out.print("Masukkan username\t: ");
            String userIDEmail = InputUtilities.inputReader.readLine();
            System.out.print("Masukkan Password\t: ");
            String userPass = InputUtilities.inputReader.readLine();

            this.userPresenter.doLogin(userIDEmail, userPass);
            if (this.userPresenter.loggedInUser != null) {
                System.out.println("Login Berhasil");
                switch (this.userPresenter.loggedInUser.getRole()) {
                    case ADMIN:
                        AppRouter.navigateTo(AppRouter.AppRoute.ADMiN_MENU);
                        break;
                    case OPERATOR:
                        AppRouter.navigateTo(AppRouter.AppRoute.OPERATOR_MENU);
                        break;
                    default:
                        System.out.println("Not Found");
                        break;
                }
            }else {
                System.out.println("Login Gagal");
            }
        } catch (IOException e) {
            invalidChoice();
        }
        System.out.println("==============================");
        InputUtilities.pressEnter();
    }
}
