import java.util.Map;

/**
 * Definiuje współdzieloną linię montażową Fabryki Cudów.
 * Z obiektu może jednocześnie korzystać wiele wątków dostawców i monterów.
 */
public interface AssemblyLineInterface {

    /**
     * Przygotowuje pusty magazyn o podanej wspólnej pojemności.
     * System testujący wywołuje tę metodę dokładnie raz, przed pozostałymi
     * operacjami, i przekazuje dodatnią wartość.
     *
     * @param capacity maksymalna łączna liczba części w magazynie
     */
    void initialize(int capacity);

    /**
     * Dostarcza atomowo całą partię części. Jeżeli w magazynie nie ma miejsca
     * na całą partię, wątek czeka. Po zamknięciu dostaw metoda kończy się bez
     * zmiany stanu magazynu.
     *
     * @param type rodzaj dostarczanych części
     * @param amount liczba części w partii
     * @throws InterruptedException gdy oczekujący wątek zostanie przerwany
     */
    void deliver(ComponentType type, int amount) throws InterruptedException;

    /**
     * Montuje podzespół po zgromadzeniu wszystkich części wymaganych przez
     * jego recepturę. Sprawdzenie i pobranie kompletu jest atomowe.
     *
     * @param type rodzaj montowanego podzespołu
     * @return kolejny unikalny numer podzespołu albo {@code null}, jeśli
     *         dostawy są zamknięte i brakuje części do montażu
     * @throws InterruptedException gdy oczekujący wątek zostanie przerwany
     */
    Long assemble(ProductType type) throws InterruptedException;

    /**
     * Kończy przyjmowanie dostaw i budzi wszystkie oczekujące wątki.
     * Operacja nie usuwa części z magazynu. System testujący wywołuje tę
     * metodę dokładnie raz.
     */
    void closeDeliveries();

    /**
     * Zwraca aktualny stan magazynu, zawierający wpis dla każdego rodzaju
     * części.
     *
     * @return mapa liczby przechowywanych części
     */
    Map<ComponentType, Integer> getStock();

    /**
     * Zwraca liczbę zmontowanych podzespołów, zawierającą wpis dla każdego
     * rodzaju produktu.
     *
     * @return mapa liczby zmontowanych podzespołów
     */
    Map<ProductType, Integer> getAssembledProducts();

    /**
     * Zwraca łączną liczbę części znajdujących się w magazynie.
     *
     * @return aktualna liczba części
     */
    int getStoredCount();

    /**
     * Zwraca liczbę podzespołów zmontowanych od uruchomienia linii.
     *
     * @return liczba ukończonych podzespołów
     */
    int getAssembledCount();
}
