/** Informuje, że zaproponowany znak znajduje się za wysoko w alfabecie. */
public class TooHighException extends CipherSearchException {

    /**
     * @param encryptedCharacter sprawdzany znak zaszyfrowany
     * @param proposedCharacter zaproponowany znak jawny
     */
    public TooHighException(char encryptedCharacter, char proposedCharacter) {
        super(
            "Zaproponowany znak jest za wysoki.",
            encryptedCharacter,
            proposedCharacter
        );
    }
}
