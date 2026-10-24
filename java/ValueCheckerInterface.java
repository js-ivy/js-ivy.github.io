
/**
 * Abstrakcyjna klasa definiująca kontrakt automatu kontroli jakości
 * wykorzystywanego w Fabryce Cudów.
 * <br>
 * Automat przetwarza kolejne liczby całkowite, przechowuje
 * wynik ich przetwarzania oraz może wstrzymać przyjmowanie
 * kolejnych danych.
 * <br>
 * Klasy dziedziczące po tej klasie powinny implementować
 * wszystkie zadeklarowane operacje zgodnie z ich dokumentacją.
 */
public abstract class ValueCheckerInterface {

    /**
     * Przekazuje automatowi kolejną liczbę do przetworzenia.
     * <br>
     * Sposób przetwarzania zależy od parzystości liczby
     * oraz aktualnego stanu wewnętrznego automatu.
     * <br>
     * Dla liczb parzystych przetworzenie polega na podziale
     * przez obecny dzielnik i dodaniu wyniku do sumy.
     * <br>
     * Dla liczb nieparzystych przetworzenie polega na 
     * zwiększeniu dzielnika o jeden i dodaniu liczby do sumy.
     * <br>
     * Jeżeli automat nie przyjmuje danych, wywołanie tej
     * metody nie może zmienić jego stanu.
     *
     * @param liczba liczba całkowita przekazana do automatu
     */
    public abstract void input(int liczba);

    /**
     * Zwraca aktualny wynik przetwarzania danych.
     * <br>
     * Wywołanie tej metody nie powinno wpływać 
     * na stan wewnętrzny automatu.
     *
     * @return aktualna suma wyników przetwarzania
     */
    public abstract int output();

    /**
     * Przywraca automat do stanu początkowego.
     * <br>
     * Po wykonaniu tej operacji wszystkie wartości 
     * wewnętrzne powinny wrócić do stanu z początku działania.
     */
    public abstract void reset();

    /**
     * Wznawia pracę automatu bez usuwania dotychczasowego wyniku.
     * <br>
     * Dotychczasowa suma musi pozostać niezmieniona.
     */
    public abstract void continueProcessing();
}
