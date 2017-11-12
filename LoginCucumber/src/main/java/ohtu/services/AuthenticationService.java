package ohtu.services;

import ohtu.domain.User;

import java.util.ArrayList;
import java.util.List;

import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

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
        if (!checkUsername(username) || !checkPassword(password)) {
            return true;
        }

        return false;
    }

    private boolean checkPassword(String password) {
        if (!checkThatLengthAtleast(password, 8)) {
            return false;
        }

        if (!containsNumber(password) && onlyLetter(password)) {
            return false;
        }

        return true;
    }

    private boolean checkUsername(String username) {
        if (!onlyLetter(username) || !checkThatLengthAtleast(username, 3)) {
            return false;
        }
        return true;
    }

    // Regular expression
    static boolean onlyLetter(String str) {
        //Labmda - stackoverflowsta löysin, koitin eka regexpressil mut tää oli niin kaunis että itketti
        boolean kirjaimia = str.chars().allMatch(Character::isLetter);
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
