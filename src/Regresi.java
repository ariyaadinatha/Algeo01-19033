package com.company;

public class Regresi {
    public static double[][] regLinBerganda(double[][] arr){


        int kol = arr[0].length;
        int k = kol - 1; // k adalah banyaknya peubah X
        int n = arr.length; // n adalah banyaknya data yang dimasukkan oleh pengguna

        double[][] x = new double[n][k];
        double[][] y = new double[n][1];

        for (int i = 0; i < n; ++i){
            for (int j = 0; j < k; ++j){
                x[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < n; ++i){
            y[i][0] = arr[i][kol - 1];
        }

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

        double[][] AB = new double[k + 1][k + 2];

        for (int i = 0; i < AB.length; ++i){
            for (int j = 0; j < AB[0].length - 1; ++j){
                AB[i][j] = A[i][j];
            }
        }

        for (int i = 0; i < AB.length; ++i){
            AB[i][AB[0].length - 1] = B[i][0];
        }

        double[][] b = Gauss.splGaussJordan(AB);

        return b;


    }
}
