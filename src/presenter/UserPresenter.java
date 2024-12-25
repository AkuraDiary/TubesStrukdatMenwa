package presenter;

import data.schemas.adt.DllUser;
import data.schemas.models.User;
import repositories.UserRepository;
import util.AppEnums;
import util.Encryption;

public class UserPresenter {
    UserRepository userRepository;

    public UserPresenter(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User selectedUser = null;
    public User loggedInUser = null;

    public void doLogin(String username, String password) {
        userRepository.getUserLogin(username, password);
        loggedInUser = userRepository.getLoggedInUser();
    }

    public void doLogout() {
        userRepository.doLogout();
    }

    public void saveUser(
            int id, String username, String password, String email, AppEnums.Roles role
    ) {
        userRepository.saveUser(new User(id, username, password, email, role));
    }

    public void updateUser(
            int id, String username, String password, String email
    ) {
        userRepository.updateUser(
                new User(
                        id,
                        (username == null || username.isEmpty()) ? selectedUser.getUsername() : username,
                        (password == null || password.isEmpty()) ? selectedUser.getPassword() : Encryption.hashPassword(password),
                        (email == null || email.isEmpty()) ? selectedUser.getEmail() : email,
                        AppEnums.Roles.OPERATOR
                )
        );
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public void selectUser(int id) {
        userRepository.selectUserById(id);
        selectedUser = userRepository.selectedUser;
    }

    public DllUser getAllOperator() {
        userRepository.selectAllOperator();
        return userRepository.listKaryawan;
    }
}
