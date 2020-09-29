package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
//        System.out.print("Ukuran matriks : ");
//        int n = s.nextInt();
//        double arr[][] = bacaMatriks(n);
//        System.out.print("\n");
//        tulisMatriks(arr);
//        System.out.print("Determinan : " + detMatriksEK(arr) + "\n");
//        double matKof[][] = matriksKofaktor(arr);
//        tulisMatriks(matKof);
//        double matInv[][] = inverseMatriks(arr);
//        System.out.print("\n");
//        tulisMatriks(matInv);

//        double[][] pp = {{1,2,3}, {4,5,6}, {7,8,9}};
//        double[][] qq = {{1},{2},{3}};
//
//        tulisMatriks(kaliMatriks(pp, qq));




        System.out.print("Masukkan banyaknya variabel peubah : ");
        int k = s.nextInt();
        System.out.print("Masukkan banyaknya data : ");
        int n = s.nextInt();
        System.out.println("Mulai membaca data");
        double[][] y = new double[n][1];
        double[][] x = new double[n][k];

        //mengisi x dan y
        for (int i = 0; i < n; ++i){
            int brs = i + 1;
            System.out.print("y data-" + brs + " : ");
            y[i][0] = s.nextDouble();
            for (int j = 0; j < k; ++j){
                int kol = j + 1;
                System.out.print("x" + kol + " data-" + kol + " : ");
                x[i][j] = s.nextDouble();
            }
            System.out.print("\n");
        }

        double[][] b = regLinBerganda(y, x);
        tulisMatriks(b);

    }

    public static double[][] bacaMatriks(int n){
        double arr[][] = new double[n][n];
        Scanner s = new Scanner(System.in);
        System.out.println("Mulai baca matriks : ");
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                arr[i][j] = s.nextDouble();
            }
        }
        return arr;
    }

    public static void tulisMatriks(double[][] arr){
        for (double[] brs : arr){
            for (double el : brs){
                System.out.print(el + " ");
            }
            System.out.print("\n");
        }
    }

    public static double[][] transposeMatriks(double[][] arr){
        double[][] mNew = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; ++i){
            for (int j = 0; j < arr.length; ++j){
                mNew[i][j] = arr[j][i];
            }
        }
        return mNew;
    }

    public static double[][] kaliMatriks(double[][] m1, double[][] m2){
        //menghasilkan matriks m1 * m2
        //Prekondisi : jumlah kolom m1 = jumlah baris m2
        double mNew[][] = new double[m1.length][m2[0].length];
        for (int i = 0; i < mNew.length; ++i){
            for (int j = 0; j < mNew[0].length; ++j){
                mNew[i][j] = 0;
                for (int k = 0; k < m2.length; ++k){
                    mNew[i][j] += (m1[i][k] * m2[k][j]);
                }
            }
        }
        return mNew;

    }

    public static double detMatriksEK(double[][] arr) {
        //determinan Matriks dengan metode Ekspansi Kofaktor
        double det = 0;
        if (arr.length == 2) {
            det += ((arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]));
        } else if (arr.length == 1){
            det += arr[0][0];
        } else {
            int i = 0;
            for (int j = 0; j < arr.length; ++j){
                double temp[][] = new double[arr.length - 1][arr.length - 1];
                int iTemp = 0;
                int jTemp = 0;
                //Mengisi temp
                for (int brs = 0; brs < arr.length; ++brs){
                    for (int kol = 0; kol < arr.length; ++kol){
                        if ((brs != i) && (kol != j)){
                            temp[iTemp][jTemp] = arr[brs][kol];
                            jTemp += 1;
                            if (jTemp == temp.length){
                                jTemp = 0;
                                iTemp += 1;
                            }
                        }
                    }
                }
                det += (Math.pow(-1, (i + j)) * arr[i][j] *  detMatriksEK(temp));
            }
        }
        return det;
    }

    public static double[][] matriksKofaktor(double[][] arr){
        double[][] mNew = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; ++i){
            for (int j = 0; j < arr.length; ++j){
                double temp[][] = new double[arr.length - 1][arr.length - 1];
                int iTemp = 0;
                int jTemp = 0;
                //Mengisi temp
                for (int brs = 0; brs < arr.length; ++brs){
                    for (int kol = 0; kol < arr.length; ++kol){
                        if ((brs != i) && (kol != j)){
                            temp[iTemp][jTemp] = arr[brs][kol];
                            jTemp += 1;
                            if (jTemp == temp.length){
                                jTemp = 0;
                                iTemp += 1;
                            }
                        }
                    }
                }
                mNew[i][j] = (Math.pow(-1, (i + j)) *  detMatriksEK(temp));
            }
        }
        return mNew;
    }

    public static double[][] matriksAdjoint(double[][] arr){
        return transposeMatriks(matriksKofaktor(arr));
    }

    public static double[][] inverseMatriks(double[][] arr){
        double det = detMatriksEK(arr);
        double[][] mNew = matriksAdjoint(arr);
        for (int i = 0; i < mNew.length; ++i){
            for (int j = 0; j < mNew.length; ++j){
                mNew[i][j] /= det;
            }
        }
        return mNew;
    }

    public static double[][] solveSPL(double[][] A, double[][] B){
        //SPL A . x = B -> x = inverse(A) . B
        double[][] inverseA = inverseMatriks(A);
        double[][] x = kaliMatriks(inverseA, B);
        return x;
    }

    public static double[][] regLinBerganda(double[][] y, double[][] x){
        //y adalah matriks berdimensi n x 1
        //x adalah matriks berdimensi n X k
        int k = x[0].length; // k adalah banyaknya peubah X
        int n = x.length; // n adalah banyaknya data yang dimasukkan oleh pengguna

        double[][] B = new double[k + 1][1];
        double[][] A = new double[k + 1][k + 1];

        //mengisi B
        for (int i = 0; i < B.length; ++i){
            B[i][0] = 0;
            for (int j = 0; j < n; ++j){
                if (i == 0){
                    B[i][0] += y[j][0];
                } else {
                    B[i][0] += (y[j][0] * x[j][i-1]);
                }
            }
        }

        //mengisi A
        for (int i = 0; i < A.length; ++i){
            for (int j = 0; j < A[0].length; ++j){
                A[i][j] = 0;
                if (i == 0){
                    if (j == 0){
                        A[i][j] = n;
                    } else {
                        for (int p = 0; p < n; ++p){
                            A[i][j] += x[p][j - 1];
                        }
                    }
                } else {
                    for (int p = 0; p < n; ++p){
                        if (j == 0){
                            A[i][j] += x[p][i - 1];
                        } else {
                            A[i][j] += (x[p][i - 1] * x[p][j-1]);
                        }
                    }
                }
            }
        }


        double[][] b = solveSPL(A, B);

        return b;


    }
}




