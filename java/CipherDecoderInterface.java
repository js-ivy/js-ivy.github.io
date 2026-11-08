import java.util.Map;

/**
 * Definiuje kontrakt dekodera wiadomości zapisanych przez urządzenie
 * odnalezione podczas fabrycznej ekspedycji.
 * <br>
 * Urządzenie nie udostępnia gotowego szyfru. Dla każdego zaszyfrowanego
 * znaku należy proponować znaki jawne, korzystając z metody
 * {@link CipherMachineInterface#checkCharacter(char, char)}. Błędna
 * propozycja powoduje zgłoszenie wyjątku wskazującego kierunek dalszych
 * poszukiwań, natomiast trafienie kończy się bez wyjątku.
 * <br>
 * Obowiązujący alfabet znaków jawnych ma kolejność:
 * {@value #ALPHABET}. Znaki spoza tego alfabetu nie są zaszyfrowane
 * i należy przepisać je do wyniku bez zmian.
 */
public interface CipherDecoderInterface {

    /** Alfabet znaków jawnych w kolejności używanej przez urządzenie. */
    String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Odszyfrowuje wiadomość udostępnioną przez urządzenie.
     * <br>
     * Dla zaszyfrowanych znaków, których mapowanie nie jest jeszcze znane,
     * należy odnaleźć właściwy znak jawny. Wyjątek {@link TooLowException}
     * oznacza, że propozycja znajduje się przed szukanym znakiem w
     * {@link #ALPHABET}, a {@link TooHighException} oznacza, że znajduje się
     * za nim. Brak wyjątku oznacza poprawne dopasowanie.
     * <br>
     * Mapowania znalezione podczas wcześniejszych wywołań należy zachować.
     * Przed ponownym użyciem znanego mapowania trzeba sprawdzić je za pomocą
     * urządzenia. Jeżeli urządzenie je odrzuci, należy zgłosić
     * {@link DecodingException}.
     * <br>
     * Jeżeli odpowiedzi urządzenia wykluczą wszystkie znaki alfabetu,
     * należy zgłosić {@code DecodingException}, przekazując jako przyczynę
     * ostatni otrzymany {@link CipherSearchException}. Nieudane wywołanie
     * nie może zmienić mapy zapamiętanej przez dekoder.
     *
     * @param machine urządzenie udostępniające zaszyfrowaną wiadomość
     *                i sprawdzające propozycje znaków
     * @return odszyfrowana wiadomość
     * @throws DecodingException gdy szyfr jest niespójny albo nie można
     *                           odnaleźć poprawnego mapowania
     */
    String decode(CipherMachineInterface machine) throws DecodingException;

    /**
     * Zwraca mapę szyfru odnalezionego podczas wszystkich zakończonych
     * powodzeniem wywołań {@link #decode(CipherMachineInterface)}.
     * <br>
     * Kluczem jest znak zaszyfrowany, a wartością odpowiadający mu znak
     * jawny. Zmiany wprowadzone w zwróconej mapie nie mogą wpływać na stan
     * dekodera.
     *
     * @return kopia mapy odnalezionego szyfru
     */
    Map<Character, Character> getCipher();
}
