import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static entities.MyIO.*;

public class Exercicios {
    public static boolean checkScalene(double x, double y, double z) {
        return x != y && x != z && y != z;
    }

    public static boolean checkEquilateral(double x, double y, double z) {
        return x == y && x == z && y == z;
    }

    public static boolean checkIso(double x, double y, double z) {
        return x == y || x == z || y == z;
    }

    public static void slide105q01() {
        double x = readDouble();
        double y = readDouble();
        double z = readDouble();
        if (checkEquilateral(x, y, z)) {
            System.out.println("Equilateral");
        } else if (checkIso(x, y, z)) {
            System.out.println("Iso");
        } else {
            System.out.println("scalene");
        }

    }

    public static void slide106q01() {
        List<Integer> list = new ArrayList<>();
        int x = readInt();
        int y = readInt();
        int z = readInt();
        int max = 0;
        int min = Integer.MAX_VALUE;
        Collections.addAll(list, x, y, z);
        for (Integer n : list) {
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
    }

    public static void slide106q02() {
        int x = 10;
        int maior = 0;
        while (x-- > 0) {
            int z = readInt();
            maior = Math.max(z, maior);
        }
        System.out.println(maior);
    }

    public static void slide106q03() {
        int x = readInt();
        int y = readInt();
        if (x > 45 ^ y > 45) {
            int sum = x + y;
        } else if (x > 20 && y > 20) {
            int maior = Math.max(x, y);
        } else if (x < 10 && y != 0 || (y < 10 && x != 0)) {
            int div = x / y;
        }
    }

    public static void slide106q04() {
        int m = readInt();
        int v = readInt();
        System.out.println((m > v) ? "mandandte" : "visitante");
        if (m == v)
            System.out.println("empate");
    }

    public static void slide107q01() {
        int n = readInt();
        int p = readInt();
        System.out.println((p < (n * 4) / 10) ? "SIM" : "NAO");
    }

    public static void slide107q02() {
        double n = readDouble();
        double p = readDouble();
        System.out.println(Math.cbrt(Math.min(n, p)) + "\n" + Math.log(Math.min(n, p)));
    }

    public static void slide107q03() {
        int x = 10;
        int maior = 0;
        int menor = Integer.MAX_VALUE;
        while (x-- > 0) {
            int z = readInt();
            maior = Math.max(z, maior);
            menor = Math.min(z, menor);
        }
        System.out.println(maior);
    }

    public static void slide124q01() {
        int x = 5;
        int n = 0;
        while (x-- > 0) {
            int s = readInt();
            n = n + s;
        }
        final int avg = n / x;
        System.out.println(avg);
    }

    public static void slide125q01() {
        int x = 0;
        while (x++ < 10) {
            System.out.println(x);
        }
    }

    public static void slide125q02() {
        int n = readInt();
        int x = 0;
        while (x++ < n) {
            if (x % 2 != 0) {
                System.out.println(x);
            }
            n++;
        }
    }

    public static void slide125q03() {
        int n = readInt();
        int x = 0;
        int z = 1;
        System.out.println(1);
        while (x++ < n + 1) {
            if (x % 2 == 0) {
                z = z + 4;
                System.out.println(z);
            } else {
                z = z + 7;
                System.out.println(z);
            }
        }
    }

    public static void slide126q01() {
        int n = readInt();
        List<Double> list = new ArrayList<>();
        int x = 0;
        final double uAvg = (double) (n * 6) / 10;
        final double high = (double) (n * 9) / 10;
        while (x++ < 20) {
            double a = readDouble();
            list.add(a);
        }
        long reproved = list.stream().filter(z -> z < uAvg).count();
        long best = list.stream().filter(z -> z > high).count();
        System.out.println("reproved:" + reproved + "\nbest:" + best);
    }

    public static void slide126q02() {
        int aux = 0;
        int i = 0;
        int fib = 1;
        int n = readInt();
        while (i <= n) {
            System.out.print(aux + " ");
            int sumOfPrevTwo = aux + fib;
            aux = fib;
            fib = sumOfPrevTwo;
            i++;
        }
    }

    public static void slide221q01() {
        int rows = readInt();
        int columns = readInt();
        int[][] matrix = new int[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix[x][y] = readInt();
            }
        }
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                System.out.println(matrix[x][y]);
            }
            System.out.println();
        }
    }

    public static void slide221q02() {
        int rows = readInt();
        int columns = readInt();
        int[][] matrix = new int[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix[x][y] = readInt();
            }
        }
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                System.out.println(matrix[y][x]);
            }
            System.out.println();
        }
    }

    //com esse tanto de for, a complexidade vai nas alturas
    public static void slide221q03() {
        int rows = readInt();
        int columns = readInt();
        int[][] matrix1 = new int[rows][columns];
        int[][] matrix2 = new int[rows][columns];
        int[][] matrixResult = new int[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix1[x][y] = readInt();
            }
        }
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix2[x][y] = readInt();
            }
        }
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrixResult[x][y] = matrix1[x][y] + matrix2[x][y];
                System.out.println(matrixResult[x][y]);
            }
        }
    }

    public static void slide222q01() {
        int n = readInt();
        int[][] matrix = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                matrix[x][y] = readInt();
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (x == y) {
                    System.out.println(matrix[x][y]);
                }
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (x + y == n) {
                    System.out.println(matrix[x][y]);
                }
            }
        }
    }

    public static void slide222q02() {
        int rows = readInt();
        int columns = readInt();
        double[][] matrix = new double[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix[x][y] = readDouble();
            }
        }
        double sum = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                sum += matrix[x][y];
            }
        }
        final double avg = sum / (rows + columns);
    }

    public static void slide229q01() {
        String s = readString();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
    }

    public static void slide229q02() {
        String c = readString();
        boolean b = false;
        int occur = 0;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == 'A') {
                occur = i;
                b = true;
                break;
            }
        }
        System.out.println((b) ? occur : "There's no occurrence");
    }

    public static void slide229q03() {
        int c = 0, l = 0, v = 0, cons = 0, nl = 0;

        String s = readString();

        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) > 'A') && (s.charAt(i) < 'Z') && (s.charAt(i) > 'a') && (s.charAt(i) > 'z')) {
                l++;
                if ((s.charAt(i) == 'A') || (s.charAt(i) == 'a') || (s.charAt(i) > 'E') || (s.charAt(i) > 'e') || (s.charAt(i) > 'I') ||
                        (s.charAt(i) > 'i') || (s.charAt(i) > 'O') || (s.charAt(i) > 'o' || (s.charAt(i) > 'U') || (s.charAt(i) > 'u'))) {
                    v++;
                } else {
                    cons++;
                }
            } else {
                nl++;
            }
            c++;
        }
        System.out.println(l + "\n" + v + "\n" + cons + "\n" + nl + "\n" + c);
    }

    public static void main(String[] args) {

    }
}
