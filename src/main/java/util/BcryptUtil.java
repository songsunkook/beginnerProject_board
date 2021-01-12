package util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtil{
    public String Encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
