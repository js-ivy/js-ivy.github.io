/** Informuje, że zaproponowany znak znajduje się za nisko w alfabecie. */
public class TooLowException extends CipherSearchException {

    /**
     * @param encryptedCharacter sprawdzany znak zaszyfrowany
     * @param proposedCharacter zaproponowany znak jawny
     */
    public TooLowException(char encryptedCharacter, char proposedCharacter) {
        super(
            "Zaproponowany znak jest za niski.",
            encryptedCharacter,
            proposedCharacter
        );
    }
}
