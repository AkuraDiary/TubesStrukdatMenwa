package presenter;

import data.schemas.adt.DllUser;
import data.schemas.models.User;
import repositories.UserRepository;
import util.AppEnums;

public class UserPresenter {
    UserRepository userRepository;

    public UserPresenter(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User selectedUser = null;
    public User loggedInUser = userRepository.getLoggedInUser();

    public void doLogin(String username, String password) {
        userRepository.getUserLogin(username, password);
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
            int id, String username, String password, String email, AppEnums.Roles role
    ) {
        userRepository.updateUser(new User(id, username, password, email, role));
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
