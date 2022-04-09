import entities.Filme;

public class Lista{
    private Filme[] array;
    private int n;

    public Lista () {
        this(6);
    }

    public Lista (int tamanho){
        array = new Filme[tamanho];
        n = 0;
    }

    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Filme x) throws Exception {
        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        }
        //levar elementos para o fim do array
        if (n >= 0) System.arraycopy(array, 0, array, 1, n);

        array[0] = x;
        n++;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Filme x) throws Exception {
        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(Filme x, int pos) throws Exception {
        //validar insercao
        if(n >= array.length || pos < 0 || pos > n){
            throw new Exception("Erro ao inserir!");
        }
        //levar elementos para o fim do array
        System.arraycopy(array, pos, array, pos + 1, n - pos);
        array[pos] = x;
        n++;
    }

    /**
     * Remove um elemento da primeira posicao da lista e movimenta
     * os demais elementos para o inicio da mesma.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Filme removerInicio() throws Exception {
        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }
        Filme resp = array[0];
        n--;
        if (n >= 0) System.arraycopy(array, 1, array, 0, n);
        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Filme removerFim() throws Exception {
        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }

    /**
     * Remove um elemento de uma posicao especifica da lista e
     * movimenta os demais elementos para o inicio da mesma.
     * @param pos Posicao de remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Filme remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }
        Filme resp = array[pos];
        n--;
        if (n - pos >= 0) System.arraycopy(array, pos + 1, array, pos, n - pos);
        return resp;
    }
    public void mostrar (){
        System.out.print("[ ");
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * Procura um elemento e retorna se ele existe.
     * @param x int elemento a ser pesquisado.
     * @return <code>true</code> se o array existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Filme x) {
        boolean retorno = false;
        for (int i = 0; i < n && !retorno; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}
