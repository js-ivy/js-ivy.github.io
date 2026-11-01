
import java.util.Map;
import java.util.Set;

/**
 * Definiuje kontrakt systemu obsługującego licytację.
 * <br>
 * System rejestruje klientów, kontroluje ich uprawnienia, przyjmuje oferty
 * składane przez klientów i przechowuje aktualny stan licytacji.
 */
public interface AuctionSystemInterface {

    /**
     * Rejestruje klienta jako uczestnika systemu aukcyjnego.
     *
     * @param client klient przeznaczony do rejestracji
     */
    void registerClient(Client client);

    /**
     * Przyjmuje ofertę złożoną przez klienta.
     * <br>
     * Oferta o wartości {@code null} oznacza wycofanie klienta
     * z dalszego udziału w licytacji.
     *
     * @param auctionOffer oferta wraz z informacją o kliencie, który ją złożył
     */
    void makeOffer(AuctionOffer auctionOffer);

    /**
     * Zwraca aktualnie najwyższą zaakceptowaną wartość oferty.
     *
     * @return najwyższa zaakceptowana wartość oferty lub {@code null},
     *         jeżeli nie przyjęto żadnej oferty
     */
    OfferValue getHighestBid();

    /**
     * Zwraca klienta będącego aktualnym liderem licytacji.
     *
     * @return klient z najwyższą zaakceptowaną ofertą lub {@code null},
     *         jeżeli nie wyłoniono lidera
     */
    Client getAuctionLeader();

    /**
     * Zwraca aktualny stan licytacji.
     *
     * @return aktualny stan licytacji
     */
    AuctionStatus getAuctionStatus();

    /**
     * Zwraca zbiór wszystkich klientów zarejestrowanych w systemie.
     *
     * @return zbiór zarejestrowanych klientów
     */
    Set<Client> getRegisteredClients();

    /**
     * Zwraca zbiór klientów, którzy nie wycofali się z bieżącej licytacji.
     *
     * @return zbiór aktywnych uczestników licytacji
     */
    Set<Client> getActiveBidders();

    /**
     * Zwraca mapę zawierającą najwyższą ofertę złożoną przez każdego klienta.
     * <br>
     * Klient, który nie złożył żadnej oferty, nie posiada przypisanej
     * oferty w mapie.
     *
     * @return mapa klientów i ich najwyższych ofert
     */
    Map<Client, OfferValue> getHighestBids();
}
