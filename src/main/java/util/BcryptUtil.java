package util;

public interface BcryptUtil {
    String Encrypt(String password);
    boolean checkPassword(String password, String hashed);
}
