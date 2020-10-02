package com.company;

import java.util.Scanner;

public class interpolasiPolinom{
    public static double[] interpol(double XY[][], int N){
        double PolX[][] = buatPolinomX(XY,N);
        double PolY[][] = buatPolinomY(XY,N);
        cramer splcramer = new cramer();
        double a[] = splcramer.splCramer(PolX,PolY,N+1);
        return a;
    }

    static double[][] buatPolinomX(double XY[][], int N){
        double PolX[][] = new double[N+1][N+1];
        double x;
        for (int i = 0; i <= N; i++){
            for (int j = 0; j <= N; j++){
                x = (double) Math.pow(XY[i][0], j);
                PolX[i][j] = x;
            }
        }
        return PolX;
    }

    static double[][] buatPolinomY(double XY[][], int N){
        double PolY[][] = new double[N+1][1];
        double y;
        for (int i = 0; i <= N; i++){
            y =  XY[i][1];
            PolY[i][0] = y;
        }
        return PolY;
    }

    static double[][] inputXY(int N){
        Scanner input = new Scanner(System.in);
        double XY[][] = new double[N+1][2];
        for (int i = 0; i <= N; i++){
            for (int j = 0; j < 2; j++){
                XY[i][j] = input.nextDouble();
            }
        }
        return XY;
    }

    static double hitungPerkiraan(double a[], int N, double X){
        double hasil = 0;
        for (int i = 0; i <= N; i++){
            hasil += a[i] * (double) Math.pow(X,i);
        }
        return hasil;
    }

    public static void cetakFungsi(double Solusi[], int N){
        int idx = idxFirst(Solusi,N);
        System.out.print("f(x) = ");
        for (int i = N; i >= 0; i--){
            if (Solusi[i] != 0){
                if ((Solusi[i] > 0) && (i != idx)){
                    System.out.print("+");
                }
                if(Solusi[i] == 1){
                    System.out.print("x^" + i + " ");
                }
                else{
                    System.out.print(Solusi[i] + "x^" + i + " ");
                }
            }
        }
    }

    static int idxFirst(double Solusi[], int N){
        int idx = 0;
        for (int i = N; i >= 0; i--){
            if (Solusi[i] != 0){
                idx = i;
                break;
            }
        }
        return idx;
    }


//    public static void main(String[] args){
//        int N = 2;
//        double XY[][] = inputXY(N);
//        double a[] = interpol(XY,N);
//        double X = 9.2f;
//        double hasil = hitungPerkiraan(a,N,X);
//        System.out.print(hasil);
//
//    }
}
