public class tmp {
    static class No {
        public char elemento; // Conteudo do no.
        public No esq; // No da esquerda.
        public No dir; // No da direita.
        public No2 outro;

        /**
         * Construtor da classe.
         * 
         * @param elemento Conteudo do no.
         */
        No(char elemento) {
            this.elemento = elemento;
            this.esq = this.dir = null;
            this.outro = null;
        }

        /**
         * Construtor da classe.
         * 
         * @param elemento Conteudo do no.
         * @param esq      No da esquerda.
         * @param dir      No da direita.
         */
        No(char elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
            this.outro = null;
        }
    }

    static class No2 {
        public Filme elemento; // Conteudo do no.
        public No2 esq; // No da esquerda.
        public No2 dir; // No da direita.

        /**
         * Construtor da classe.
         * 
         * @param elemento Conteudo do no.
         */
        No2(Filme elemento) {
            this.elemento = elemento;
            this.esq = this.dir = null;
        }

        /**
         * Construtor da classe.
         * 
         * @param elemento Conteudo do no.
         * @param esq      No2 da esquerda.
         * @param dir      No2 da direita.
         */
        No2(Filme elemento, No2 esq, No2 dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    static class ArvoreArvore {
        private No raiz; // Raiz da arvore.
        static int comp = 0;

        /**
         * Construtor da classe.
         */
        public ArvoreArvore() {
            raiz = null;
            inserir('D');
            inserir('R');
            inserir('Z');
            inserir('X');
            inserir('V');
            inserir('B');
            inserir('F');
            inserir('P');
            inserir('U');
            inserir('I');
            inserir('G');
            inserir('E');
            inserir('J');
            inserir('L');
            inserir('H');
            inserir('T');
            inserir('A');
            inserir('W');
            inserir('S');
            inserir('O');
            inserir('M');
            inserir('N');
            inserir('K');
            inserir('C');
            inserir('Y');
            inserir('Q');
        }

        public void inserir(char letra) {
            try {
                raiz = inserir(letra, raiz);
            } catch (Exception e) {
                MyIO.println(e.getMessage());
            }
        }

        private No inserir(char x, No i) throws Exception {
            if (i == null) {
                i = new No(x);

            } else if (x < i.elemento) {
                i.esq = inserir(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir!");
            }

            return i;
        }

        public void inserir(Filme x) {
            try {
                inserir(x, raiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void inserir(Filme x, No i) throws Exception {
            if (i == null) {
                throw new Exception("Erro ao inserir: caractere invalido!");

            } else if (x.tituloOriginal.charAt(0) < i.elemento) {
                inserir(x, i.esq);

            } else if (x.tituloOriginal.charAt(0) > i.elemento) {
                inserir(x, i.dir);

            } else {
                i.outro = inserir(x, i.outro);
            }
        }

        private No2 inserir(Filme x, No2 i) throws Exception {
            if (i == null) {
                i = new No2(x);

            } else if (x.tituloOriginal.compareTo(i.elemento.tituloOriginal) < 0) {
                i.esq = inserir(x, i.esq);

            } else if (x.tituloOriginal.compareTo(i.elemento.tituloOriginal) > 0) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir: elemento existente!");
            }

            return i;
        }
        
        public boolean mostrar(String s) {
            MyIO.print("raiz ");
            return mostrar(raiz, s);
        }
    
        public boolean mostrar(No i, String s) {
            boolean resultado = false;
            if (i != null) {
                resultado = pesquisar(s);

                if (resultado) {
                    return resultado;
                }
                MyIO.print(" ESQ ");
                resultado = mostrar(i.esq, s);
                if (resultado) {
                    return resultado;
                }
                MyIO.print(" DIR ");
                resultado = mostrar(i.dir, s);
                if (resultado) {
                    return resultado;
                }
            }
            return resultado;
        }

        public void mostrar(No2 i) {
            if (i != null) {
                mostrar(i.esq);
                //System.out.println(i.elemento);
                mostrar(i.dir);
            }
        }

        public boolean pesquisar(String elemento) {
            return pesquisar(raiz, elemento);
        }

        private boolean pesquisar(No no, String x) {
            boolean resp;
            if (no == null) {
                //MyIO.print("NAO");
                resp = false;

            } else if (x.charAt(0) < no.elemento) {
                MyIO.print(" esq");
                resp = pesquisar(no.esq, x);

            } else if (x.charAt(0) > no.elemento) {
                MyIO.print(" dir");
                resp = pesquisar(no.dir, x);

            } else {
                resp = pesquisarSegundaArvore(x, no.outro);
            }
            return resp;
        }

        private boolean pesquisarSegundaArvore(String x, No2 i) {
            boolean resp;
            if (i == null) {
                comp++;
                resp = false;
            } else if (x.compareTo(i.elemento.tituloOriginal) == 0) {
                comp += 2;
                resp = true;
            } else if (x.compareTo(i.elemento.tituloOriginal) < 0) {
                comp += 3;
                resp = pesquisarSegundaArvore(x, i.esq);

            } else {
                comp += 3;
                resp = pesquisarSegundaArvore(x, i.dir);

            }
            return resp;
        }
    }
}
