package data.sources;

import data.schemas.adt.DllUser;
import data.schemas.models.User;
import util.AppEnums;
import util.Encryption;

public class UserDataSource {

    public DllUser userList = new DllUser();

    public void initDataUser() {
        System.out.println("Data User Initialization");
        userList.insertSorted(
                new User(
                        1,
                        "admin",
                        Encryption.hashPassword("admin"),
                        "admin@gmail.com",
                        AppEnums.Roles.ADMIN
                )
        );
        userList.insertSorted(
                new User(
                        2,
                        "operator",
                        Encryption.hashPassword("operator"),
                        "operator@gmail.com",
                        AppEnums.Roles.OPERATOR
                )
        );

        System.out.println("Data User Initialized");
    }
}
