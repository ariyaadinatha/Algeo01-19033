import java.util.Arrays;

public class gauss {
    public static void main(String[] args) {
        int kolMaks = 4; // banyak kolom
        int barMaks = 3; // banyak barus
        float[][] matrix = { { 2, 3, -1, 5 }, { 4, 4, -3, 3 }, { -2, 3, -1, 1 } }; // matriks masukan
        System.out.println("Matrix awal : " + Arrays.deepToString(matrix)); // mencetak matrix

        // Pembagian Matrix indeks ke 0
        int index = 0;
        int indexAtas = 1;
        // while (matrix[barMaks - 2][kolMaks - 2] != 0) {
        for (int i = 0; i < barMaks - 1; i++) {
            // Pembagian pada index pertama, jika index kolom dan baris sama dan hasilnya
            // tidak 1 maka akan dilakukan pembagian
            if (matrix[i][i] != 1) {
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
                    System.out.println("Index atas : " + indexAtas + " l : " + l + " j : " + j); // debugging index
                    System.out.print(
                            matrix[indexAtas][l] + " - " + constant + " * " + matrix[indexAtas - kurang][l] + " = "); // debuggingaritmatika
                    matrix[indexAtas][l] = matrix[indexAtas][l] - constant * matrix[indexAtas - kurang][l];
                    System.out.println(matrix[indexAtas][l]); // debugging hasil
                    System.out.println(Arrays.deepToString(matrix)); // Debugging
                    System.out.println(" ");
                }

                if (indexAtas < barMaks - 1) {
                    indexAtas++;
                    kurang++;
                }
            }

            index++;
        }
        System.out.println("Matrix akhir : " + Arrays.deepToString(matrix));
    }
}
