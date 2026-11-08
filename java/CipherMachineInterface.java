/**
 * Reprezentuje urządzenie odnalezione podczas fabrycznej ekspedycji.
 * Implementacja tego interfejsu jest dostarczana przez system testujący.
 */
public interface CipherMachineInterface {

    /**
     * Zwraca zaszyfrowaną wiadomość przechowywaną przez urządzenie.
     * Znaki nienależące do {@link CipherDecoderInterface#ALPHABET}
     * nie są zaszyfrowane.
     *
     * @return zaszyfrowana wiadomość
     */
    String getEncryptedMessage();

    /**
     * Sprawdza propozycję odszyfrowania pojedynczego znaku.
     * <br>
     * Zakończenie metody bez wyjątku oznacza trafienie. Wyjątki wskazują,
     * w której części alfabetu znajduje się poprawna wartość.
     *
     * @param encryptedCharacter znak występujący w zaszyfrowanej wiadomości
     * @param proposedCharacter proponowany znak jawny
     * @throws TooLowException gdy propozycja znajduje się przed poprawnym
     *                         znakiem w alfabecie
     * @throws TooHighException gdy propozycja znajduje się za poprawnym
     *                          znakiem w alfabecie
     */
    void checkCharacter(char encryptedCharacter, char proposedCharacter)
        throws TooLowException, TooHighException;
}
