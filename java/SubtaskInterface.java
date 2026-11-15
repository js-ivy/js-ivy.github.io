/**
 * Reprezentuje niezależne podzadanie wykonywane przez jeden wątek.
 * Podzadanie tworzy jednorodną sekwencję liczb albo znaków i przekazuje
 * kolejny obiekt tylko wtedy, gdy otrzyma poprawny wynik dotychczasowego
 * przetwarzania.
 */
public interface SubtaskInterface {

    /**
     * Zwraca rodzaj danych występujących w podzadaniu.
     *
     * @return rodzaj sekwencji przetwarzanej przez podzadanie
     */
    SubtaskType getType();

    /**
     * Zwraca kolejny obiekt podzadania.
     * <br>
     * Pierwsze wywołanie musi otrzymać {@code null}. Każde następne
     * wywołanie otrzymuje bieżący wynik: sumę wszystkich dotychczasowych
     * liczb dla typu {@link SubtaskType#INTEGER} albo napis złożony ze
     * wszystkich dotychczasowych znaków dla typu
     * {@link SubtaskType#CHARACTER}.
     * <br>
     * Puste obiekty nie zmieniają wyniku. Dopóki nie pojawi się pierwsza
     * niepusta wartość, wynikiem pozostaje {@code null}. Zwrócenie
     * {@code null} przez tę metodę oznacza zakończenie podzadania.
     *
     * @param previousResult wynik uzyskany po przetworzeniu wszystkich
     *                       wcześniejszych obiektów albo {@code null}
     * @return kolejny obiekt lub {@code null}, gdy podzadanie się zakończyło
     */
    TaskObjectInterface getNext(Object previousResult);
}
