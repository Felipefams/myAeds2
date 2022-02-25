import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class halley {
    public static class Pair {
        int year;
        int yearsLeft;
    }

    //cria uma lista e armazena os pares com a data e o tempo que falta pro proximo cometa dentro
    /*
    aqui tambem talvez ficaria melhor usando array, ja que pra chegar no valor desejado tem que percorrer a lista toda.
    mas como eu nunca fiz um array de pares, eu preferi fazer o feijao com arroz, e o tempo ta curto pra ficar testando
    coisa dmais tambem
    */
    public static List<Pair> halleyDates(int yearToStart, final int lastHarley) {
        List<Pair> datesList = new ArrayList<>();
        int count = 76 - (yearToStart - lastHarley);
        for (int i = yearToStart; i <= 3000; i++) {
            Pair pair = new Pair();
            pair.year = i;
	    pair.yearsLeft = count;
		System.out.println("Year - " + pair.year + "years left -" + pair.yearsLeft);
            if (count == 0) {
                pair.yearsLeft = 76;
                count = 76;
            }
            datesList.add(pair);
            count--;
        }
        return datesList;
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int yearToStart = 2020; //ano em que a contagem vai comecar
        int date = 1;//data desejada
        List<Pair> datesList = new ArrayList<>(halleyDates(yearToStart, 1986));
        while (date != 0) {
            date = fr.nextInt();
            final int finalDate = date;
            if (date != 0) {
                Pair tmp = datesList.stream().filter(x -> x.year > finalDate && x.yearsLeft == 76).findFirst().orElse(null);
LX016221058MU                System.out.println(tmp.year);
            }
        }
    }

    static class FastReader {
        //        BufferedReader br;
        StringTokenizer st;
        private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

        public static void setCharset(String charset_) {
            br = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset_)));
        }

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
