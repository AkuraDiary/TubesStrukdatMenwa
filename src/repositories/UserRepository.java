package repositories;

import data.schemas.adt.DllUser;
import data.schemas.models.User;
import data.schemas.nodes.NodeUser;
import data.sources.UserDataSource;
import util.Encryption;

public class UserRepository {
    UserDataSource userDataSource;
    public User selectedUser = null;
    private User loggedInUser = null;


    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void saveUser(
            User user
    ) {
        userDataSource.userList.insertSorted(user);
    }

    public void updateUser(
            User newUserData
    ) {
        userDataSource.userList.deleteById(newUserData.getId());
        userDataSource.userList.insertSorted(newUserData);
    }

    public void deleteUser(
            int id
    ) {
        userDataSource.userList.deleteById(id);
    }

    public void getUserLogin(
            String username,
            String password
    ) {
        NodeUser head = userDataSource.userList.getHead();
        while (head != null) {
            if (head.getData().getUsername().equals(username) && head.getData().getPassword().equals(Encryption.hashPassword(password))) {
                loggedInUser = head.getData();
            }
            head = head.getNext();
        }
        loggedInUser = null;
    }

    public void selectUserById(int id) {
        selectedUser = userDataSource.userList.searchById(id);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public DllUser getAllUsers() {
        return userDataSource.userList;
    }

}
