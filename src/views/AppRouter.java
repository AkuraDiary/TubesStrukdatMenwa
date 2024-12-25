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
        MASTER_CUSTOMER,
        TRANSAKSI,
        PEMBAYARAN,
        PENGEMBALIAN,

        //ADMIN
        ADMIN_MENU,
        MASTER_OPERATOR,
        MASTER_LAPORAN,
        MASTER_APPROVE_TRANSAKSI,
        MASTER_PRODUK

    }
}
