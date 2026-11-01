
/**
 * Reprezentuje wartość oferty podlegającą porównywaniu z innymi wartościami.
 * <br>
 * Typ wykorzystywany przez system aukcyjny do określania kolejności
 * i wysokości składanych ofert.
 * <br>
 * Porównywanie wartości odbywa się poprzez metodę {@link Comparable#compareTo(Object)}.
 */
public interface OfferValue extends Comparable<OfferValue> {
}
