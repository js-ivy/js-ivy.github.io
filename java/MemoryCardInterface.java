/**
 * Reprezentuje obiekt udostępniający swoje dane w postaci
 * sekwencji znaków.
 * <br>
 * Znaki należy odczytywać pojedynczo, wywołując metodę
 * {@link #readNextCharacter()} tak długo, aż zwróci ona
 * wartość {@code null}.
 * <br>
 * Kolejność zwracanych znaków nie ma znaczenia podczas
 * ustalania czy dwa obiekty zawierają identyczne dane.
 * <br>
 * Implementacje tego interfejsu nie muszą nadpisywać metod
 * {@link Object#equals(Object)} ani {@link Object#hashCode()}.
 * Tożsamość obiektu należy zatem odróżniać wykorzystując 
 * zawartość przechowywanych przez niego danych.
 *
 */
public interface MemoryCardInterface {

    /**
     * Zwraca kolejny nieodczytany znak przechowywany w obiekcie.
     * <br>
     * Każde wywołanie zwraca kolejny znak. Gdy wszystkie znaki
     * zostaną odczytane, metoda zwraca {@code null}.
     * <br>
     * Metoda zmienia stan obiektu. Nie należy zakładać,
     * że po zakończeniu odczytu będzie można rozpocząć
     * odczytywanie danych od początku.
     *
     * @return kolejny znak lub {@code null}, jeśli nie ma już
     *         żadnych znaków do odczytania
     */
    Character readNextCharacter();
}
