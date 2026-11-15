/**
 * Definiuje mechanizm równoległego wykonywania niezależnych podzadań
 * urządzenia odnalezionego podczas ekspedycji.
 */
public interface ParallelProcessorInterface {

    /**
     * Uruchamia wszystkie podzadania dostarczone przez wskazany obiekt.
     * <br>
     * Metoda tworzy dokładnie tyle wątków, ile zwraca
     * {@link TaskProviderInterface#getThreadCount()}, i pobiera tyle samo
     * podzadań za pomocą {@link TaskProviderInterface#getSubtask()}. Każde
     * podzadanie jest przetwarzane przez osobny wątek, niezależnie od
     * pozostałych. Wszystkie wątki należy najpierw uruchomić, a następnie
     * zaczekać na każdy z nich za pomocą metody {@link Thread#join()}.
     * <br>
     * Dla każdego podzadania pierwsze wywołanie
     * {@link SubtaskInterface#getNext(Object)} otrzymuje {@code null}.
     * Następnie należy obliczać narastającą sumę liczb albo konkatenację
     * znaków, zależnie od rodzaju podzadania. Pusty obiekt nie zmienia wyniku,
     * a wynik pozostaje {@code null} do chwili otrzymania pierwszej wartości.
     * Przetwarzanie kończy się, gdy {@code getNext()} zwróci {@code null}.
     * <br>
     * Podzadania są niezależne, dlatego nie wymagają współdzielonego
     * akumulatora ani synchronizacji dostępu do danych.
     *
     * @param provider dostawca liczby wątków i niezależnych podzadań
     */
    void process(TaskProviderInterface provider);
}
