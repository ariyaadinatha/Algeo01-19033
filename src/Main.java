import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int kolMaks = 4;
        int barMaks = 3;
        float[][] matrix = { { 2, 3, -1, 5 }, { 4, 4, -3, 3 }, { -2, 3, -1, 1 } };
        System.out.println(Arrays.deepToString(matrix));
        // System.out.println(matrix[1][1]);

        // Pembagian Matrix indeks ke 0
        int index = 0;
        int indexAtas = 1;
        int beres = 0;

        for (int i = 0; i < barMaks; i++) {
            // int barConstant = 1;
            float constant = matrix[indexAtas][index];
            // matrix[barConstant][0];
            for (int j = index; j < kolMaks; j++) {
                // Pembagian pada index pertama
                if ((i == j) && (matrix[i][j] != 1)) {
                    float headVariable = matrix[i][i];
                    for (int k = 0; k < kolMaks; k++) {
                        matrix[i][k] = matrix[i][k] / headVariable;
                    }
                }

                // System.out.println(constant);

                matrix[indexAtas][j] = matrix[indexAtas][j] - constant * matrix[index][j];
                // System.out.println(indexAtas + "Matrix : " + matrix[indexAtas][j]);
                System.out.println(Arrays.deepToString(matrix));

            }
            // System.out.println(Arrays.deepToString(matrix));
            if (indexAtas < kolMaks - 2) {
                indexAtas++;
            }
            // Perbaiki ini, masih hardcode
            beres++;
            System.out.println("Index : " + index + " Index atas : " + indexAtas + " Beres : " + beres);
        }

        // System.out.println(Arrays.deepToString(matrix));

    }
}
