
import java.util.ListIterator;
import java.util.NoSuchElementException;
public class DequeSearch<Key, Item>implements Iterable<Item> {
    private int n; //contador de elementos
    private No Sentinela;// nó artificial para marcar inicio e fim

    public DequeSearch(){
        n = 0;
        Sentinela = new No();
        Sentinela.prox = Sentinela;
        Sentinela.ant = Sentinela;
    }
    private class No{
        private Item dado;
        private Key chave;
        private No prox;
        private No ant;
    }
    public void push_front(Key key, Item item){
        //criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        //definir anterior e o proximo do novo no
        tmp.ant = Sentinela;
        tmp.prox = Sentinela.prox;

        //ajustar a sentinela e o seguinte
        Sentinela.prox = tmp;
        tmp.prox.ant= tmp;
        n++;
    }

    public void push_back(Key key, Item item){
        //criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        //definir anterior e o proximo do novo no
        tmp.ant = Sentinela.ant;
        tmp.prox = Sentinela;

        //ajustar a sentinela e o anterior
        Sentinela.ant = tmp;
        tmp.ant.prox = tmp;
        n++;
    }
    public boolean contains(Key key){
        if(key == null) throw new IllegalArgumentException("Argument to contains() is null");
        return get(key) != null;
    }
    public Item get(Key key){
        if(key == null ) throw new IllegalArgumentException("Argument to get() is null");
        for (No x = Sentinela.prox; x != Sentinela; x = x.prox){
            if (key.equals(x.chave)) return x.dado;
        }
        return null;
    }
    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("Argument to delete() is null");
        delete(Sentinela.prox, key);
    }
    private void remove(No tmp){
        tmp.ant.prox = tmp.prox;
        //Atualizar o no proximo para apontar para o anterior do que sera removido
        tmp.prox.ant = tmp.ant;
        --n;
    }
    private void delete(No x, Key key){
        if(x == Sentinela) return;
        if(key.equals(x.chave)){
            remove(x);
            return;
        }
        delete(x.prox,key);
    }

}
