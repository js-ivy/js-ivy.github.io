import java.util.List;

/**
 * Zawiera dane zwracane maszynie po kontroli obiektów.
 * <br>
 * Wartości usunięte podczas naprawy są grupowane według
 * typu. Każda lista jest niezależna od pozostałych.
 * <br>
 * Obiekt zwrotny nie zawiera obiektów zaakceptowanych
 * ani pustych. Przechowuje wyłącznie odzyskane wartości.
 *
 * @param integers odzyskane wartości typu Integer
 * @param doubles odzyskane wartości typu Double
 * @param characters odzyskane wartości typu Character
 * @param strings odzyskane wartości typu String
 */
public record ReturnData(
    List<Integer> integers,
    List<Double> doubles,
    List<Character> characters,
    List<String> strings
) {}