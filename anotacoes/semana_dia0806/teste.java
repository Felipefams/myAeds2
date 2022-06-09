//tentativa de fazer a pesquisa na arvore trie
public class teste {

    class No {
        public char elemento;
        public final int tamanho = 255;
        public No[] prox;
        public boolean folha;

        public No() {
            this(' ');
        }

        public No(char elemento) {
            this.elemento = elemento;
            prox = new No[tamanho];
            for (int i = 0; i < tamanho; i++)
                prox[i] = null;
            folha = false;
        }

        public static int hash(char x) {
            return (int) x;
        }
    }

    public boolean search(No no, String s, int i) {
        boolean ans;
        if(no.prox[s.charAt(i) - 1] == null)
            ans = false;
        else if( i == s.length() - 1)
            ans = (no.prox[s.charAt(i)].folha);
        else if(i < s.length() - 1)
            ans = search(no.prox[s.charAt(i)], s, i+1);
        else throw new Exception("Erro ao pesquisar");
        return ans;
    }

    public static void main(String[] args) {

    }
}