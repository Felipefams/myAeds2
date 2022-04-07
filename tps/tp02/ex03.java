import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ex03 {

    public static void main(String[] args) {
       MyList filmes = new MyList();
       filmes.mostrar();
    }

    public static class MyList{
        private Filme[] filmes;
        private int tamanho;

        public MyList(){
            int tamanhoPadrao = 50;
            filmes = new Filme[tamanhoPadrao];
        }

        public MyList(int tamanho){
            filmes = new Filme[tamanho + 1];
            this.tamanho = tamanho;
        }

        public void inserir(Filme x) throws Exception{
            if(filmes.length >= tamanho) {
                throw new Exception("Erro ao inserir, lista cheia ou nula");
            }
        }
        public void append(Filme x)throws Exception{
            if(filmes.length >= tamanho){
                throw new Exception("Erro ao inserir, lista cheia ou nula");
            }
        }
        public void mostrar(){
            for(Filme x : filmes){
                System.out.println(x);
            }
        }
    }

    public static class Filme {
        private String nome;
        private String tituloOriginal;
        private Date dataLancamento;
        private int duracao;
        private String genero;
        private String idiomaOriginal;
        private String situacao;
        private float orcamento;
        private String[] palavrasChave;

        public Filme(){};

        public Filme(String nome, String tituloOriginal, Date dataLancamento, int duracao, String genero, String idiomaOriginal, String situacao, float orcamento, String[] palavrasChave) {
            this.nome = nome;
            this.tituloOriginal = tituloOriginal;
            this.dataLancamento = dataLancamento;
            this.duracao = duracao;
            this.genero = genero;
            this.idiomaOriginal = idiomaOriginal;
            this.situacao = situacao;
            this.orcamento = orcamento;
            this.palavrasChave = palavrasChave;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTituloOriginal() {
            return tituloOriginal;
        }

        public void setTituloOriginal(String tituloOriginal) {
            this.tituloOriginal = tituloOriginal;
        }

        public Date getDataLancamento() {
            return dataLancamento;
        }

        public void setDataLancamento(Date dataLancamento) {
            this.dataLancamento = dataLancamento;
        }

        public int getDuracao() {
            return duracao;
        }

        public void setDuracao(int duracao) {
            this.duracao = duracao;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getIdiomaOriginal() {
            return idiomaOriginal;
        }

        public void setIdiomaOriginal(String idiomaOriginal) {
            this.idiomaOriginal = idiomaOriginal;
        }

        public String getSituacao() {
            return situacao;
        }

        public void setSituacao(String situacao) {
            this.situacao = situacao;
        }

        public float getOrcamento() {
            return orcamento;
        }

        public void setOrcamento(float orcamento) {
            this.orcamento = orcamento;
        }

        public String[] getPalavrasChave() {
            return palavrasChave;
        }

        public void setPalavrasChave(String[] palavrasChave) {
            this.palavrasChave = palavrasChave;
        }

        @Override
        public String toString() {
            var sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            return 	nome + ' ' + tituloOriginal + ' ' +sdf2.format(dataLancamento) + ' ' +duracao + ' ' +genero +
                    ' ' + idiomaOriginal + ' ' +situacao + ' ' + orcamento + ' ' + Arrays.toString(palavrasChave);
        }
    }
}
