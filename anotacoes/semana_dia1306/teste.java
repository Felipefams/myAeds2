public class teste {

    public static void mostrar(No raiz) {
        mostrar("", raiz);
    }

    public static void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; ++i) {
                if (no.prox[i] != null) {
                    System.out.println("Estou em (" + no.elemento + ")e vou para (" + no.prox[i].elemento + ")");
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public static void contar(char c, No no, int count) {
        if(no.elemento == c) count++;
        for (int i = 0; i < no.prox.length; ++i) {
            if (no.prox[i] != null) {
                contar(no.elemento, no.prox[i], count);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArvoreTrie at = new ArvoreTrie();
        at.inserir("bear");
        at.inserir("bell");
        at.inserir("bid");
        at.inserir("bull");
        at.inserir("buy");
        at.inserir("sell");
        at.inserir("stock");
        at.inserir("stop");
        mostrar(at.raiz);
        contar('a', at.raiz, 0);
    }

    static class No {
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

    static class ArvoreTrie {
        private No raiz;

        public ArvoreTrie() {
            raiz = new No();
        }

        public boolean pesquisar(String s) throws Exception {
            return pesquisar(s, raiz, 0);
        }

        public boolean pesquisar(String s, No no, int i) throws Exception {
            boolean resp;
            if (no.prox[s.charAt(i)] == null) {
                resp = false;
            } else if (i == s.length() - 1) {
                resp = (no.prox[s.charAt(i)].folha == true);
            } else if (i < s.length() - 1) {
                resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
            } else {
                throw new Exception("Erro ao pesquisar!");
            }
            return resp;
        }

        public void inserir(String s) throws Exception {
            inserir(s, raiz, 0);
        }

        private void inserir(String s, No no, int i) throws Exception {
            System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
            if (no.prox[s.charAt(i)] == null) {
                System.out.print("--> criando filho(" + s.charAt(i) + ")");
                no.prox[s.charAt(i)] = new No(s.charAt(i));

                if (i == s.length() - 1) {
                    System.out.print("(folha)");
                    no.prox[s.charAt(i)].folha = true;
                } else {
                    inserir(s, no.prox[s.charAt(i)], i + 1);
                }

            } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
                inserir(s, no.prox[s.charAt(i)], i + 1);

            } else {
                throw new Exception("Erro ao inserir!");
            }
        }

        public void mostrar() {
            mostrar("", raiz);
        }

        public void mostrar(String s, No no) {
            if (no.folha == true) {
                System.out.println("Palavra: " + (s + no.elemento));
            } else {
                for (int i = 0; i < no.prox.length; i++) {
                    if (no.prox[i] != null) {
                        System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                        mostrar(s + no.elemento, no.prox[i]);
                    }
                }
            }
        }

        public int contarAs() {
            int resp = 0;
            if (raiz != null) {
                resp = contarAs(raiz);
            }
            return resp;
        }

        public int contarAs(No no) {
            int resp = (no.elemento == 'A') ? 1 : 0;

            if (no.folha == false) {
                for (int i = 0; i < no.prox.length; i++) {
                    if (no.prox[i] != null) {
                        resp += contarAs(no.prox[i]);
                    }
                }
            }
            return resp;
        }
    }

}