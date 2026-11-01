
/**
 * Określa możliwe stany licytacji.
 */
public enum AuctionStatus {

    /**
     * Licytacja jest w toku i przyjmuje kolejne oferty.
     */
    INPROGRESS,

    /**
     * Licytacja została zakończona i nie przyjmuje kolejnych ofert.
     */
    FINISHED
}
