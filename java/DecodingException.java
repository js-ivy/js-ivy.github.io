/**
 * Informuje, że wiadomości nie można poprawnie odszyfrować z powodu
 * niespójnych odpowiedzi urządzenia.
 */
public class DecodingException extends Exception {

    /**
     * @param message opis błędu dekodowania
     * @param cause odpowiedź urządzenia, która ujawniła niespójność
     */
    public DecodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
