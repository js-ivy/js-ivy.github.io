/**
 * Bazowy typ wyjątków służących urządzeniu do przekazywania kierunku
 * dalszych poszukiwań.
 */
public class CipherSearchException extends Exception {

    /** Sprawdzany znak zaszyfrowany. */
    private final char encryptedCharacter;

    /** Zaproponowany znak jawny. */
    private final char proposedCharacter;

    /**
     * Tworzy wyjątek opisujący sprawdzoną propozycję.
     *
     * @param message opis odpowiedzi urządzenia
     * @param encryptedCharacter sprawdzany znak zaszyfrowany
     * @param proposedCharacter zaproponowany znak jawny
     */
    public CipherSearchException(
        String message,
        char encryptedCharacter,
        char proposedCharacter
    ) {
        super(message);
        this.encryptedCharacter = encryptedCharacter;
        this.proposedCharacter = proposedCharacter;
    }

    /** @return sprawdzany znak zaszyfrowany */
    public char getEncryptedCharacter() {
        return encryptedCharacter;
    }

    /** @return zaproponowany znak jawny */
    public char getProposedCharacter() {
        return proposedCharacter;
    }
}
