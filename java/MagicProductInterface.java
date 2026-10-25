/**
 * Reprezentuje produkt wytworzony przez Magiczną Maszynę.
 * <br>
 * Interfejs określa oczekiwany typ danych oraz udostępnia
 * operacje odczytu i modyfikacji czterech możliwych wartości:
 * Integer, Double, Character oraz String.
 * <br>
 * Prawidłowy produkt zawiera dokładnie jedną wartość
 * zgodną z oczekiwanym typem. Wartość null oznacza brak
 * danych w danym polu.
 * <br>
 * Instancje tego interfejsu są dostarczane przez maszynę.
 * Nie należy tworzyć własnych implementacji.
 */
public interface MagicProductInterface {

    /**
     * Zwraca oczekiwany typ danych produktu.
     *
     * @return oczekiwany typ danych
     */
    DataType getExpectedType();

    /**
     * Zwraca przechowywaną liczbę całkowitą.
     *
     * @return liczba całkowita lub null
     */
    Integer getIntegerValue();

    /**
     * Ustawia liczbę całkowitą.
     *
     * @param integerValue liczba całkowita lub null
     */
    void setIntegerValue(Integer integerValue);

    /**
     * Zwraca przechowywaną liczbę zmiennoprzecinkową.
     *
     * @return liczba zmiennoprzecinkowa lub null
     */
    Double getDoubleValue();

    /**
     * Ustawia liczbę zmiennoprzecinkową.
     *
     * @param doubleValue liczba zmiennoprzecinkowa lub null
     */
    void setDoubleValue(Double doubleValue);

    /**
     * Zwraca przechowywany znak.
     *
     * @return znak lub null
     */
    Character getCharacterValue();

    /**
     * Ustawia przechowywany znak.
     *
     * @param characterValue znak lub null
     */
    void setCharacterValue(Character characterValue);

    /**
     * Zwraca przechowywany napis.
     *
     * @return napis lub null
     */
    String getStringValue();

    /**
     * Ustawia przechowywany napis.
     *
     * @param stringValue napis lub null
     */
    void setStringValue(String stringValue);
}