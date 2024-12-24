package views;

public class AppRouter {
    public static void navigateTo(AppRoute target) {
        activeRoute = target;
    }
    public static AppRoute activeRoute;

    public enum AppRoute {
        // GLOBAL
        LOGIN,
        EXIT,
        // OPERATOR
        OPERATOR_MENU,

        //ADMIN
        ADMiN_MENU,

    }
}
