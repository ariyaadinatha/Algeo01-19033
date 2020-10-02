public class eliminasiGauss {
    public static float[][] gauss(int kolMaks, int barMaks, float matrix[][]) {
        // Tukar matrix jika index 0,0 bernilai 0
        if (matrix[0][0] == 0) {
            float temp1, temp2;
            for (int i = 0; i < kolMaks; i++) {
                temp1 = matrix[0][i];
                temp2 = matrix[1][i];

                matrix[0][i] = temp2;
                matrix[1][i] = temp1;
            }
        }
        // Pembagian Matrix indeks ke 0
        int index = 0;
        int indexAtas = 1;

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
                    matrix[indexAtas][l] = matrix[indexAtas][l] - constant * matrix[indexAtas - kurang][l];
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

        return matrix;
    }

    public static float[][] gaussJordan(int kolMaks, int barMaks, float matrix[][]) {
        gauss(kolMaks, barMaks, matrix);
        int indexAtas = 1;
        int barisAtas = 1;
        for (int i = 0; i < barMaks; i++) {
            for (int j = indexAtas; j < kolMaks - 1; j++) { // J dimulai dari setelah 1
                if (matrix[i][j] != 0) { // jika tidak 0 maka harus dikurangi
                    float constant = matrix[i][j];
                    for (int k = j; k < kolMaks; k++) { // dikurangi oleh 1 pada baris selanjutnya
                        float hasil = matrix[i][k] - constant * matrix[barisAtas][k];
                        matrix[i][k] = hasil;
                    }
                    if (barisAtas < barMaks - 1) { // menghindari out of range
                        barisAtas++;
                    }
                }
            }

            if (indexAtas < kolMaks - 1) { // menambah index
                indexAtas++;
            }
        }
        return matrix;
    }
}
