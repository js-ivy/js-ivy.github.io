/**
 * Dostarcza niezależne podzadania przeznaczone do równoległego wykonania.
 * Każde wywołanie metody {@link #getSubtask()} zwraca jedno podzadanie, które
 * ma zostać obsłużone przez osobny wątek.
 */
public interface TaskProviderInterface {

    /**
     * Zwraca liczbę wątków, które należy utworzyć, a zarazem liczbę
     * dostępnych podzadań.
     *
     * @return wymagana liczba wątków i podzadań
     */
    int getThreadCount();

    /**
     * Zwraca kolejne podzadanie. Metodę należy wywołać dokładnie tyle razy,
     * ile wskazuje {@link #getThreadCount()}. Każde zwrócone podzadanie ma być
     * obsługiwane przez osobny wątek.
     *
     * @return niezależne podzadanie
     */
    SubtaskInterface getSubtask();
}
