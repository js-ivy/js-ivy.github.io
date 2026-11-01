/**
 * Reprezentuje ofertę wraz z informacją o kliencie, który ją złożył.
 *
 * @param client klient składający ofertę
 * @param offer oferta złożona przez klienta; wartość {@code null}
 *              oznacza wycofanie się klienta z licytacji
 */
public record AuctionOffer(Client client, Offer offer) {}