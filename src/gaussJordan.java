import java.util.Arrays;

public class gaussJordan {
    public static void main(String[] args) {
        // Menetapkan Matrix
        int kolMaks = 4; // banyak kolom
        int barMaks = 3; // banyak baris
        float[][] matrix = { { 2, 3, -1, 5 }, { 4, 4, -3, 3 }, { -2, 3, -1, 1 } }; // input matriks
        System.out.println("Matrix awal : " + Arrays.deepToString(matrix)); // mencetak matrix

        // ***Pemanggilan fungsi Gauss*** //
        // Pembagian Matrix indeks ke 0
        int index = 0;
        int indexAtas = 1;
        // while (matrix[barMaks - 2][kolMaks - 2] != 0) {
        for (int i = 0; i < barMaks - 1; i++) {
            // Pembagian pada index pertama, jika index kolom dan baris sama dan hasilnya
            // tidak 1 maka akan dilakukan pembagian
            if (matrix[i][i] != 1 && matrix[i][i] != 0) {
                float headVariable = matrix[i][i];
                for (int k = 0; k < kolMaks; k++) {
                    matrix[i][k] = matrix[i][k] / headVariable;
                }
            }

            // Pengurangan pada index selanjutnya
            int kurang = 1;
            for (int j = indexAtas; j < barMaks; j++) {
                float constant = matrix[indexAtas][index];
                for (int l = 0; l < kolMaks; l++) {
                    // System.out.println("Index atas : " + indexAtas + " l : " + l + " j : " + j);
                    // // debugging index
                    // System.out.print(
                    // matrix[indexAtas][l] + " - " + constant + " * " + matrix[indexAtas -
                    // kurang][l] + " = "); // debuggingaritmatika
                    matrix[indexAtas][l] = matrix[indexAtas][l] - constant * matrix[indexAtas - kurang][l];
                    // System.out.println(matrix[indexAtas][l]); // debugging hasil
                    // System.out.println(Arrays.deepToString(matrix)); // Debugging
                    // System.out.println(" ");
                }

                if (indexAtas < barMaks - 1) {
                    indexAtas++;
                    kurang++;
                }
            }

            index++;
        }
        if (matrix[barMaks - 1][barMaks - 1] != 1 && matrix[barMaks - 1][barMaks - 1] != 0) {
            float headVariable = matrix[barMaks - 1][barMaks - 1];
            for (int k = 0; k < kolMaks; k++) {
                matrix[barMaks - 1][k] = matrix[barMaks - 1][k] / headVariable;
            }
        }

        System.out.println("Matrix Gauss : " + Arrays.deepToString(matrix));

        // ***Akhir dari pemanggilan fungsi Gauss***//

        // Memulai fungsi Jordan
        indexAtas = 1;
        int barisAtas = 1;
        for (int i = 0; i < barMaks; i++) {
            for (int j = indexAtas; j < kolMaks - 1; j++) { // J dimulai dari setelah 1
                if (matrix[i][j] != 0) {
                    float constant = matrix[i][j];
                    for (int k = j; k < kolMaks; k++) {
                        // System.out.println("i : " + i + " j : " + j + " k : " + k); // debugging
                        // index
                        float hasil = matrix[i][k] - constant * matrix[barisAtas][k];
                        // System.out.println(
                        // matrix[i][k] + " - " + constant + " * " + matrix[barisAtas][k] + " = " +
                        // hasil); // debugging aritmatika
                        matrix[i][k] = hasil;
                        // System.out.println(Arrays.deepToString(matrix)); // mencetak hasil matrix
                        // System.out.println(" "); // enter
                    }
                    // System.out.println("Loop K beres"); // debugging Loop K
                    if (barisAtas < kolMaks - 2) {
                        barisAtas++;
                    }
                }

            }
            // System.out.println("Loop J beres"); // debugging Loop J
            if (indexAtas < kolMaks - 1) {
                indexAtas++;
            }
        }
        System.out.println("Matrix Gauss Jordan : " + Arrays.deepToString(matrix));

    }

}
