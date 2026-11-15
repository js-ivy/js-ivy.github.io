/**
 * Reprezentuje pojedynczy obiekt przekazywany podczas wykonywania podzadania.
 * Obiekt określa rodzaj przechowywanych danych i może zawierać wartość
 * typu {@link Integer}, {@link Character} albo wartość {@code null}.
 * <br>
 * Obiekt z wartością {@code null} jest pusty i nie zmienia bieżącego
 * wyniku podzadania.
 */
public interface TaskObjectInterface {

    /**
     * Zwraca rodzaj danych obiektu. Jest on zgodny z rodzajem podzadania,
     * które zwróciło ten obiekt.
     *
     * @return rodzaj danych obiektu
     */
    SubtaskType getType();

    /**
     * Zwraca wartość obiektu.
     * <br>
     * Dla typu {@link SubtaskType#INTEGER} wartość jest typu
     * {@link Integer}, a dla typu {@link SubtaskType#CHARACTER} typu
     * {@link Character}. W obu przypadkach może zostać zwrócone
     * {@code null}, oznaczające pusty obiekt.
     *
     * @return wartość obiektu albo {@code null}, jeżeli obiekt jest pusty
     */
    Object getValue();
}
