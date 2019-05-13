import java.security.SecureRandom;
import java.math.BigInteger;

//CREDIT TO:  http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
public class AlphaNumericCodeGenerator{
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(30, random).toString(32);
    }
}