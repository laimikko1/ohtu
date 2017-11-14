package ohtu.services;

import ohtu.domain.User;


import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AuthenticationService {

    private UserDao userDao;
@Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        return !checkUsername(username) || !checkPassword(password);

    }

    private boolean checkPassword(String password) {
        if (!checkThatLengthAtleast(password, 8)) {
            return false;
        }

        return containsNumber(password) || !onlyLetter(password);
    }

    private boolean checkUsername(String username) {
        return onlyLetter(username) && checkThatLengthAtleast(username, 3);
    }

    // Regular expression
    static boolean onlyLetter(String str) {
        boolean kirjaimia = str.matches("[a-zA-Z]+");
        return kirjaimia;
    }

    static boolean checkThatLengthAtleast(String checked, int length) {
        return checked.length() >= length;
    }

    static boolean containsNumber(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }


}
