
/**
 * Reprezentuje ofertę złożoną przez klienta w trakcie licytacji.
 */
public interface Offer {

    /**
     * Zwraca wartość proponowaną w ofercie.
     * <br>
     * Wartość {@code null} oznacza wycofanie klienta z dalszego udziału
     * w licytacji.
     *
     * @return wartość oferty lub {@code null} w przypadku wycofania
     */
    OfferValue getOfferValue();
}
