package com.company;

public class Gauss {
    public static double[][] splGauss(double[][] arr) {
        int nBrs = arr.length;
        int nKol = arr[0].length;

        for (int i = 0; i < arr.length; ++i) {

            // mencari elemen kepala yang tidak nol
            // indeks untuk elemen kepala yang valuenya tidak nol
            int iZ = i;
            int jZ = i;
            boolean foundNotZero = false;

            for (int brs = i; brs < nBrs; ++brs) {
                for (int kol = i; kol < nKol; ++kol) {
                    if (arr[brs][kol] != 0) {
                        foundNotZero = true;
                        iZ = brs;
                        jZ = kol;
                        break;
                    }
                }
                if (foundNotZero) {
                    break;
                }
            }

            if (!foundNotZero) {
                break;
            }

            // menukar baris, jika indeks baris yang elemen kepalanya tidak nol != i
            // contoh
            // 0 0 1 2 3
            // 0 1 0 3 4
            // 0 0 3 2 2
            // 0 0 4 5 6
            // saat i = 0; disini iZ = 1, dan jZ = 1, karena iZ != i, maka harus ditukar
            // antara baris indeks 1 dengan baris indeks i

            // tempat penyimpanan elemen sementara
            double temp;

            // menukar baris
            if (iZ != i) {
                for (int j = 0; j < nKol; ++j) {
                    temp = arr[i][j];
                    arr[i][j] = arr[iZ][j];
                    arr[iZ][j] = temp;
                }
                iZ = i;
            }

            // pembagian baris indeks i, sehingga elemen kepala bernilai 1
            double pembagi = arr[iZ][jZ];
            for (int j = jZ; j < nKol; ++j) {
                arr[i][j] /= pembagi;
            }

            // mengurang baris dibawahnya, sehingga elemen yang berada dibawah elemen
            // kepala, bernilai 0 semua
            for (int brs = i + 1; brs < nBrs; ++brs) {
                double c = arr[brs][jZ];
                for (int kol = jZ; kol < nKol; ++kol) {
                    arr[brs][kol] -= (arr[iZ][kol] * c);
                }
            }

        }

        return arr;
    }

    public static double[][] splGaussJordan(double[][] arr) {
        arr = splGauss(arr);

        int nBrs = arr.length;
        int nKol = arr[0].length;

        int i = 0;
        int j = 0;

        while ((i < nBrs) && (j < nKol)) {
            if (arr[i][j] == 1) {
                for (int brs = 0; brs < nBrs; ++brs) {
                    if (brs != i) {
                        double c = arr[brs][j];
                        for (int kol = j; kol < nKol; ++kol) {
                            arr[brs][kol] -= (arr[i][kol] * c);
                        }
                    }
                }
                i += 1;
            } else {
                j += 1;
            }
        }
        return arr;
    }

    public static String[] solveSPLGauss(double[][] arr){
        //input matriks eselon baris tereduksi
        arr = splGaussJordan(arr);
        int nBrs = arr.length;
        int nKol = arr[0].length;
        String[] solutionFinal = new String[nKol - 1];
        boolean[] allZero = new boolean[nBrs];
        for (int i = 0; i < allZero.length; ++i){
            allZero[i] = true;
        }
        boolean haveSolution = true;
        //mengecek apakah tidak memiliki solusi
        for (int i = 0; i < nBrs; ++i){

            //mencari baris yang semua x nya nol tapi y nya tidak nol
            boolean xNol = true;
            for (int j = 0; j < nKol - 1; ++j){
                if (arr[i][j] != 0){
                    System.out.println("ok");
                    allZero[i] = false;
                    xNol = false;
                    break;
                }
            }

            if (xNol){
                //check apakah y nya nol atau tidak
                if (arr[i][nBrs - 1] != 0){
                    haveSolution = false;
                    break;
                }
            }
        }

        if (!haveSolution){ //kasus tidak punya solusi
            System.out.println("Sistem persamaan linear tidak memiliki solusi");
            solutionFinal = null;
        } else {
            //ciri ciri dari matriks yang memiliki banyak solusi adalah
            //baris arr < (kolom arr - 1)



            boolean[] solusiAngka = new boolean[nKol -1];
            for (int i = 0; i < (nKol - 1); ++i){
                solusiAngka[i] = false;
            }

            //mencari kolom mana saja yg memiliki leading entry
            for(int i = 0; i < nBrs; ++i){
                for (int j = 0; j < (nKol - 1); ++j){
                    if (arr[i][j] == 1){
                        solusiAngka[j] = true;
                        break;
                    }
                }
            }

            String[] solusi = new String[nKol - 1];
            for (int i = 0; i < solusi.length; ++i){
                solusi[i] = "";
            }

            char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

            //jika suatu kolom tidak memiliki leading entry
            //sudah pasti nilai dari variabel pada kolom itu bukan angka

            int alphCount = 0;
            for (int i = 0; i < solusi.length; ++i){
                if (!solusiAngka[i]){
                    solusi[i] = Character.toString(alphabet[alphCount]);
                    alphCount += 1;
                }
            }

            for(int i = 0; i < nBrs; ++i){
                if (!allZero[i]){
                    int leadingEntry = -1;
                    for (int j = 0; j < (nKol - 1); ++j){
                        if (arr[i][j] == 1){
                            leadingEntry = j;
                            break;
                        }
                    }

                    solusi[leadingEntry] = solusi[leadingEntry] + (arr[i][nKol - 1] != 0 ? ((arr[i][nKol - 1]) + " ") : "");

                    for (int j = leadingEntry + 1; j < (nKol - 1); ++j){
                        if (arr[i][j] != 0){
                            if (arr[i][j] > 0){
                                solusi[leadingEntry] += ("- " + (arr[i][j] * -1) + solusi[j] + " ");
                            } else {
                                solusi[leadingEntry] += ("+ " + (arr[i][j]) + solusi[j] + " ");
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < solusi.length; ++i){
                System.out.println("x" + (i + 1) + " = " + solusi[i]);
            }
            solutionFinal = solusi;
        }
        return solutionFinal;
    }

    public static String[] solveSPLGaussJordan(double[][] arr){
        //input matriks eselon baris tereduksi
        int nBrs = arr.length;
        int nKol = arr[0].length;
        String[] solutionFinal = new String[nKol - 1];
        boolean[] allZero = new boolean[nBrs];
        for (int i = 0; i < allZero.length; ++i){
            allZero[i] = true;
        }
        boolean haveSolution = true;
        //mengecek apakah tidak memiliki solusi
        for (int i = 0; i < nBrs; ++i){

            //mencari baris yang semua x nya nol tapi y nya tidak nol
            boolean xNol = true;
            for (int j = 0; j < nKol - 1; ++j){
                if (arr[i][j] != 0){
                    System.out.println("ok");
                    allZero[i] = false;
                    xNol = false;
                    break;
                }
            }

            if (xNol){
                //check apakah y nya nol atau tidak
                if (arr[i][nBrs - 1] != 0){
                    haveSolution = false;
                    break;
                }
            }
        }

        if (!haveSolution){ //kasus tidak punya solusi
            System.out.println("Sistem persamaan linear tidak memiliki solusi");
            solutionFinal = null;
        } else {
            //ciri ciri dari matriks yang memiliki banyak solusi adalah
            //baris arr < (kolom arr - 1)



            boolean[] solusiAngka = new boolean[nKol -1];
            for (int i = 0; i < (nKol - 1); ++i){
                solusiAngka[i] = false;
            }

            //mencari kolom mana saja yg memiliki leading entry
            for(int i = 0; i < nBrs; ++i){
                for (int j = 0; j < (nKol - 1); ++j){
                    if (arr[i][j] == 1){
                        solusiAngka[j] = true;
                        break;
                    }
                }
            }

            String[] solusi = new String[nKol - 1];
            for (int i = 0; i < solusi.length; ++i){
                solusi[i] = "";
            }

            char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

            //jika suatu kolom tidak memiliki leading entry
            //sudah pasti nilai dari variabel pada kolom itu bukan angka

            int alphCount = 0;
            for (int i = 0; i < solusi.length; ++i){
                if (!solusiAngka[i]){
                    solusi[i] = Character.toString(alphabet[alphCount]);
                    alphCount += 1;
                }
            }

            for(int i = 0; i < nBrs; ++i){
                if (!allZero[i]){
                    int leadingEntry = -1;
                    for (int j = 0; j < (nKol - 1); ++j){
                        if (arr[i][j] == 1){
                            leadingEntry = j;
                            break;
                        }
                    }

                    solusi[leadingEntry] = solusi[leadingEntry] + (arr[i][nKol - 1] != 0 ? ((arr[i][nKol - 1]) + " ") : "");

                    for (int j = leadingEntry + 1; j < (nKol - 1); ++j){
                        if (arr[i][j] != 0){
                            if (arr[i][j] > 0){
                                solusi[leadingEntry] += ("- " + (arr[i][j] * -1) + solusi[j] + " ");
                            } else {
                                solusi[leadingEntry] += ("+ " + (arr[i][j]) + solusi[j] + " ");
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < solusi.length; ++i){
                System.out.println("x" + (i + 1) + " = " + solusi[i]);
            }
            solutionFinal = solusi;
        }
        return solutionFinal;
    }


//    public static void solusiParametrik(double[][] arr) {
//        boolean isSimetris = true;
//        boolean isInvalid = false;
//        boolean isBanyakSolusi = false;
//
//        int nBrs = arr.length;
//        int nKol = arr[0].length;
//
//        // mengecheck apakah matrix simetris
//        for (int i = 0; i < nBrs; i++) {
//            if (arr[i][i] != 1) {
//                isSimetris = false;
//            }
//        }
//
//        // mengecheck apakah jawaban SPL tidak memiliki solusi
//        for (int i = 0; i < nBrs; i++) {
//            if (arr[i][nKol - 1] != 0) {
//                int banyak = 0;
//                // isBanyakSolusi = true;
//                // jika ada hasil yang 0 akan dicari
//                for (int j = 0; j < nKol - 1; j++) {
//                    if (arr[i][j] == 0) {
//                        // hasil yang 0 memiliki variabel
//                        banyak += 1;
//                    }
//                }
//                if (banyak == nKol - 1) {
//                    isInvalid = true; // maka matrix tidak valid
//                }
//            }
//
//            // jika ada hasil yang 0
//            else if (arr[i][nKol - 1] == 0) {
//                for (int j = 0; j < nKol - 1; j++) {
//                    // dan SPL tidak 0
//                    if (arr[i][j] != 0) {
//                        isBanyakSolusi = true; // maka ada banyak solusi
//                    }
//                }
//            }
//        }
//
//        if (isInvalid == true) {
//            System.out.println("Solusi tidak ada");
//        } else if (isBanyakSolusi == true) {
//            char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
//                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
//            System.out.println("Solusi banyak"); // berlaku di gauss
//        } else if (isSimetris == true && isBanyakSolusi == false) {
//            System.out.println("Memiliki 1 solusi"); // berlaku di jordan
//            for (int i = 0; i < nBrs; i++) {
//                System.out.println("X" + i + " = " + arr[i][nKol - 1]);
//            }
//        }
//
//    }

}
