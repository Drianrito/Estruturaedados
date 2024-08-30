import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private No Sentinela;

    public Deque() {
        n = 0;
        Sentinela = new No();
        Sentinela.prox = Sentinela;
        Sentinela.ant = Sentinela;
    }
    private class No {
        private Item dado;
        private No prox;
        private No ant;
    }

    public void push_front(Item item) {
        //criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;

        //definir anterior e proximo do novo no
        tmp.ant = Sentinela;
        tmp.prox = Sentinela.prox;

        //ajustar a sentinela e o seguinte
        Sentinela.prox = tmp;
        tmp.prox.ant = tmp;
        ++n;
    }

    public void push_back(Item item) {
        //criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;

        //definir anterior e proximo do novo no
        tmp.ant = Sentinela.ant;
        tmp.prox = Sentinela;

        //ajustar a sentinela e o seguinte
        Sentinela.ant = tmp;
        tmp.ant.prox = tmp;
        ++n;
    }

    public Item pop_front() {
        No tmp = Sentinela.prox;
        Item meuDado = tmp.dado;

        //Atualizar o nó anterior para apontar para o proximo do que sera removido
        tmp.ant.prox = tmp.prox;

        //Atualizar o nó próximo para apontar para o anterior do que será removido
        tmp.prox.ant = tmp.ant;
        --n;

        return meuDado;
    }

    public Item pop_back() {
        No tmp = Sentinela.ant;
        Item meuDado = tmp.dado;

        //Atualizar o nó anterior para apontar para o próximo do que será removido
        tmp.ant.prox = tmp.prox;

        //Atualizar o nó próximo para apontar para o anterior do que será removido
        tmp.prox.ant = tmp.ant;
        --n;

        return meuDado;
    }

    public No first() {
        if (Sentinela == Sentinela.prox) return null;
        return Sentinela.prox;
    }

    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    public ListIterator<Item> iterator() {
        return new DequeIterator();
    }

    public class DequeIterator implements ListIterator<Item> {
        private No atual = Sentinela.prox;
        private int indice = 0;
        private No acessadoultimo = null;

        public boolean hasNext() { return indice < (n); }
        public boolean hasPrevious() { return indice > 0; }
        public int previousIndex() { return indice -1; }
        public int nextIndex() { return indice; }

        public Item next() {
            if (!hasNext()) return null;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            atual = atual.prox;
            indice++;

            return meuDado;
        }

        public Item previous() {
            if(!hasPrevious()) return null;
            atual = atual.ant;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            indice--;

            return meuDado;
        }

        public Item get() {
            if(atual == null) throw new IllegalStateException();
            return atual.dado;
        }

        public void set(Item x) {
            if(acessadoultimo == null) throw new IllegalStateException();
            acessadoultimo.dado = x;
        }

        public void remove() {
            if (acessadoultimo == null) throw new IllegalStateException();
            acessadoultimo.ant.prox = acessadoultimo.prox;
            acessadoultimo.prox.ant = acessadoultimo.ant;
            --n;

            if (atual == acessadoultimo) atual = acessadoultimo.prox;
            else indice--;

            acessadoultimo = null;
        }

        public void add(Item x) {
            //Inserir apos atual
            No tmp = new No();

            tmp.prox = atual.prox;
            tmp.ant = atual;

            tmp.prox.ant = tmp;
            atual.prox = tmp;
            n++;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();

            for (Item item : this) s.append(item + " ");
            return s.toString();
        }
    }



}
