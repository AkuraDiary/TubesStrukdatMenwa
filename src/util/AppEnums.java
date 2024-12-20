package util;

public class AppEnums {
    public enum StatusTransaksi {
        Pending,
        Accepted,
        Rejected,
        Running,
        Due,
        Done
    }

    public enum RentalInterval {
        Hour,
        Day,
        Week,
        Month
    }

    public enum Roles {
        ADMIN,
        OPERATOR
    }

}
