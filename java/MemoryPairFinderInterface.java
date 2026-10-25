import java.util.Map;

/**
 * Definiuje operacje wyszukiwania par obiektów zawierających
 * identyczne dane znakowe.
 * <br>
 * Dwa obiekty uznaje się za identyczne pod względem zawartości,
 * jeżeli każdy znak występuje w obu obiektach tyle samo razy,
 * niezależnie od kolejności jego występowania.
 * <br>
 * Implementacja nie może polegać na metodach
 * {@link Object#equals(Object)} ani {@link Object#hashCode()}
 * w celu ustalenia czy zawartość dwóch obiektów jest identyczna.
 * <br>
 * Obiekty są przekazywane pojedynczo, a kontroler zapamiętuje
 * obiekty, dla których nie znaleziono jeszcze pary.
 * <br>
 * Każdy obiekt może należeć najwyżej do jednej pary.
 * Pary są zapisywane zgodnie z kolejnością otrzymania kart:
 * kluczem jest karta otrzymana wcześniej, a wartością karta
 * otrzymana później.
 *
 */
public interface MemoryPairFinderInterface {

    /**
     * Przetwarza pojedynczy obiekt i próbuje znaleźć dla niego parę.
     * <br>
     * Znaki obiektu należy odczytywać do momentu, w którym metoda
     * {@code readNextCharacter()} zwróci {@code null}. Nie należy
     * zakładać, że zawartość obiektu będzie można odczytać ponownie.
     * <br>
     * Jeżeli wśród wcześniej przetworzonych obiektów znajduje się
     * obiekt o identycznej zawartości, oba obiekty zostają połączone
     * w parę. W przeciwnym razie obiekt zostaje zapamiętany i może
     * zostać dopasowany do obiektu przekazanego w przyszłości.
     *
     * @param card obiekt, który należy przetworzyć
     */
    void process(MemoryCardInterface card);

    /**
     * Zwraca mapę par odnalezionych od początku działania.
     * <br>
     * Każda para występuje w mapie tylko raz. Kluczem jest obiekt,
     * który został przekazany do metody {@code process()} wcześniej,
     * a wartością obiekt otrzymany później.
     * <br>
     * Przykładowo, jeżeli najpierw przekazano kartę A, a następnie
     * kartę B o identycznej zawartości, mapa zawiera wpis:
     *
     * <pre>
     * A → B
     * </pre>
     *
     * Mapa nie zawiera odwrotnego wpisu {@code B → A}.
     * <br>
     * Pobranie par nie usuwa informacji o obiektach, które
     * nadal oczekują na znalezienie partnera.
     * <br>
     * Jeżeli para nie została znaleziona nie powinna być zwracana.
     * <br>
     * Przykładowo, jeżeli przekazano kartę C, ale nie ma jeszcze 
     * dla niej pary zwracana mapa nie powinna zawierać rekordu:
     * 
     * <pre>
     * C → null
     * </pre>
     *
     * @return mapa gotowych par, w której klucz jest kartą otrzymaną
     *         wcześniej, a wartość kartą otrzymaną później
     *         
     */
    Map<MemoryCardInterface, MemoryCardInterface> getPairs();
}
