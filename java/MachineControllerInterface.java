
import java.util.List;

/**
 * Określa operacje wymagane od kontrolera jakości
 * produktów wytwarzanych przez Magiczną Maszynę.
 * <br>
 * Kontroler klasyfikuje produkty jako poprawne lub puste,
 * naprawia produkty wadliwe oraz gromadzi nadmiarowe dane
 * przeznaczone do zwrotu maszynie.
 * <br>
 * Produkty są dostarczane przez maszynę jako instancje
 * interfejsu MagicProductInterface. Kontroler nie powinien
 * tworzyć nowych produktów.
 */
public interface MachineControllerInterface {

    /**
     * Przetwarza pojedynczy produkt maszyny.
     * <br>
     * Produkt zawierający dokładnie jedną wartość zgodną
     * z oczekiwanym typem należy dodać do listy poprawnych
     * produktów.
     * <br>
     * Produkt niezawierający żadnych wartości należy dodać
     * do listy pustych produktów.
     * <br>
     * Jeżeli produkt zawiera kilka wartości, należy zachować
     * wartość zgodną z getExpectedType(), a pozostałe usunąć
     * z produktu i zgromadzić w odpowiednich listach danych
     * zwrotnych.
     * <br>
     * Jeżeli produkt zawiera wartości, ale żadna z nich
     * nie odpowiada oczekiwanemu typowi, należy usunąć
     * wszystkie wartości i dodać opróżniony produkt do listy
     * pustych produktów.
     * <br>
     * Listy poprawnych i pustych produktów zachowują
     * wyniki wcześniejszych wywołań.
     *
     * @param product produkt dostarczony przez maszynę
     */
    void process(MagicProductInterface product);

    /**
     * Zwraca listę wszystkich poprawnych produktów
     * zgromadzonych przez kontroler.
     *
     * @return lista poprawnych produktów
     */
    List<MagicProductInterface> getValidProducts();

    /**
     * Zwraca listę wszystkich pustych produktów
     * zgromadzonych przez kontroler, w tym produktów
     * opróżnionych podczas naprawy.
     *
     * @return lista pustych produktów
     */
    List<MagicProductInterface> getEmptyProducts();

    /**
     * Zwraca dane odzyskane podczas napraw produktów.
     * <br>
     * Metoda może być wywoływana wielokrotnie w trakcie
     * działania programu, również wtedy, gdy maszyna chce
     * odebrać odzyskane wartości przed zakończeniem
     * przekazywania wszystkich produktów.
     * <br>
     * Po odebraniu danych kontroler musi wyczyścić
     * wszystkie wewnętrzne listy wartości zwrotnych.
     * Dzięki temu kolejne wywołanie tej metody nie zwróci
     * ponownie wartości już odebranych przez maszynę.
     * <br>
     * Jeżeli od poprzedniego odebrania danych nie
     * odzyskano żadnych nowych wartości, metoda zwraca
     * obiekt ReturnData zawierający puste listy.
     *
     * @return zgromadzone wartości zwrotne pogrupowane
     *         według typu
     */
    ReturnData getReturnData();
}
