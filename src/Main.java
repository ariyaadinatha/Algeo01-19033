package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);

        System.out.println("Tubes Algeo - 19033");
        System.out.println("******************************");

        boolean quit = false;

        while (!quit){
            menu();
            System.out.println("-----------------------");
            System.out.print("Nomor pilihan fitur : ");
            int menuChoice = s.nextInt();

            System.out.print("\n");

            // ukuran matriks
            int m; //baris
            int n; //kolom

            //matriks
            double[][] matriks;

            //solusi
            double[][] solusiMatriks; //solusi dalam bentuk matriks
            double[] solusiArray; // solusi dalam bentuk array
            String[] solusiSPL;

            //file related
            String filename;

            switch(menuChoice){

                case 1 :
                    submenu1();
                    System.out.println("-----------------------");
                    System.out.print("Nomor pilihan fitur : ");
                    int submenu1Choice = s.nextInt();

                    switch(submenu1Choice){
                        case 1 :
                            if (MatriksFile.inputFromFile()){
                                matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                            } else {
                                System.out.print("m : ");
                                m = s.nextInt();
                                System.out.print("n : ");
                                n = s.nextInt();
                                matriks = Matriks.bacaMatriks(m, n);
                            }

                            solusiSPL = Gauss.solveSPLGauss(matriks);

                            if (MatriksFile.saveToFile()){
                                filename = MatriksFile.createFile();
                                MatriksFile.splGaussToFile(filename, solusiSPL);
                            }

                            break;

                        case 2 :
                            if (MatriksFile.inputFromFile()){
                                matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                            } else {
                                System.out.print("m : ");
                                m = s.nextInt();
                                System.out.print("n : ");
                                n = s.nextInt();
                                matriks = Matriks.bacaMatriks(m, n);
                            }

                            solusiSPL = Gauss.solveSPLGaussJordan(matriks);

                            if (MatriksFile.saveToFile()){
                                filename = MatriksFile.createFile();
                                MatriksFile.splGaussToFile(filename, solusiSPL);
                            }

                            break;

                        case 3 :
                            if (MatriksFile.inputFromFile()){
                                matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                            } else {
                                System.out.print("m : ");
                                m = s.nextInt();
                                System.out.print("n : ");
                                n = s.nextInt();
                                matriks = Matriks.bacaMatriks(m, n);
                            }
                            solusiMatriks = Inverse.solveSPLInverse(matriks);
                            System.out.println("Solusi sistem persamaan linear");
                            for (int i = 0; i < solusiMatriks.length; ++i){
                                System.out.println("x" + (i + 1) + " = " + ((float)  solusiMatriks[i][0]));
                            }

                            if (MatriksFile.saveToFile()){
                                filename = MatriksFile.createFile();
                                MatriksFile.splInverseToFile(filename, solusiMatriks);
                            }

                            break;

                        case 4 :
                            if (MatriksFile.inputFromFile()){
                                matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                                m = matriks.length;
                                n = matriks[0].length;
                            } else {
                                System.out.print("m : ");
                                m = s.nextInt();
                                System.out.print("n : ");
                                n = s.nextInt();
                                matriks = Matriks.bacaMatriks(m, n);
                            }

                            double[][] xCramer = new double[m][n - 1];
                            double[][] bCramer = new double[m][1];

                            for (int i = 0; i < m; ++i){
                                for (int j = 0; j < n - 1; ++j){
                                    xCramer[i][j] = matriks[i][j];
                                }
                                bCramer[i][0] = matriks[i][n - 1];
                            }

                            double[] solutionCramer = cramer.splCramer(xCramer, bCramer, m);
                            System.out.println("Solusi sistem persamaan linear");

                            if (solutionCramer == null){
                               System.out.print("Matriks tidak memiliki solusi unik.");
                            }
                            else {
                                for (int i = 0; i < solutionCramer.length; ++i){
                                    System.out.println("x" + (i + 1) + " = " + ((float)  solutionCramer[i]));
                                }
                            }

                            if (MatriksFile.saveToFile()){
                                filename = MatriksFile.createFile();
                                MatriksFile.splCramerToFile(filename, solutionCramer);
                            }

                            break;
                    }

                    break;

                case 2 :
                    if (MatriksFile.inputFromFile()){
                        matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                        n = matriks.length;
                    } else {
                        System.out.print("n : ");
                        n = s.nextInt();
                        m = n;
                        System.out.println("Mulai membaca masukkan matriks");
                        matriks = Matriks.bacaMatriks(m, n);
                    }

                    double detEK = determinan.determinanCofactorWay(matriks, n);
                    double detRB = determinan.determinanReductionRowWay(matriks, n);

                    System.out.println("Determinan dengan metode ekspansi kofaktor : " + detEK);
                    System.out.println("Determinan dengan metode reduksi baris : " + detRB);

                    if (MatriksFile.saveToFile()){
                        filename = MatriksFile.createFile();
                        MatriksFile.determinanToFile(filename, detEK, detRB);
                    }

                    break;

                case 3 :

                    if (MatriksFile.inputFromFile()){
                        matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                        n = matriks.length;
                    } else {
                        System.out.print("n : ");
                        n = s.nextInt();
                        m = n;
                        System.out.println("Mulai membaca masukkan matriks");
                        matriks = Matriks.bacaMatriks(m, n);
                    }
                    System.out.println("Hasil matriks balikan : ");
                    Matriks.tulisMatriks(Inverse.inverseMatriks(matriks));
                    if (MatriksFile.saveToFile()){
                        filename = MatriksFile.createFile();
                        MatriksFile.matriksToFile(filename, matriks);
                    }

                    break;

                case 4 :
                    if (MatriksFile.inputFromFile()){
                        matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                        n = matriks.length-1;

                    } else {
                        System.out.print("n : ");
                        n = s.nextInt();
                        matriks = interpolasiPolinom.inputXY(n);
                    }

                    double[][] XYIP = matriks;

                    double[] aIP = interpolasiPolinom.interpol(XYIP, n);

                    interpolasiPolinom.cetakFungsi(aIP, n);
                    double hasilIP;
                    double XIP;
                    while (true){
                        System.out.print("x : ");
                        XIP = s.nextDouble();
                        hasilIP = interpolasiPolinom.hitungPerkiraan(aIP, n, XIP);
                        
                        System.out.println(hasilIP);
                        System.out.print("Apakah ingin menaksir nilai yang lain? (Y/N) : ");
                        char lagi = s.next().charAt(0);
                        if (lagi == 'N'){
                            break;
                        }
                    }

                    if (MatriksFile.saveToFile()){
                        filename = MatriksFile.createFile();
                        MatriksFile.interpolToFile(filename, hasilIP, aIP);
                    }

                    break;

                case 5 :
                    if (MatriksFile.inputFromFile()){
                        matriks = MatriksFile.fileToMatriks(MatriksFile.inputFileName());
                        m = matriks.length;
                        n = matriks[0].length;
                    } else {
                        System.out.print("Masukkan banyaknya variabel peubah : ");
                        n = s.nextInt() + 1;
                        System.out.print("Masukkan banyaknya data : ");
                        m = s.nextInt();

                        System.out.println("Mulai membaca data");
                        System.out.println("Data dibaca menyamping dengan format");
                        System.out.println("x1 x2 ... xk y");

                        matriks = Matriks.bacaMatriks(m, n);
                    }


                    double[] xTaksir = new double[n - 1];
                    System.out.print("Masukkan nilai variabel yang ingin di taksir");
                    for (int i = 0; i < xTaksir.length; ++i){
                        System.out.print("x" + (i + 1) + " = ");
                        xTaksir[i] = s.nextDouble();
                    }

                    double[][] b = Regresi.regLinBerganda(matriks);

                    System.out.print("y = ");

                    for (int i = 0; i < b.length; ++i){
                        if (i == 0){
                            System.out.print(b[i][0] + " ");

                        } else {
                            System.out.print("+ " + b[i][0] + "x" + (i + 1) + " ");
                        }
                    }

                    System.out.print("\n");

                    double hasilTaksir = 0;

                    for (int i = 0; i < b.length; ++i){
                        if (i == 0){
                            hasilTaksir += b[i][0];
                        } else {
                            hasilTaksir += (b[i][0] * xTaksir[i - 1]);
                        }
                    }

                    System.out.println("Hasil taksir : " + hasilTaksir);

                    if (MatriksFile.saveToFile()){
                        filename = MatriksFile.createFile();
                        MatriksFile.regLinToFile(filename, b, hasilTaksir);
                    }

                    break;
                case 6 :
                    quit = true;
                    break;

                default :
                    System.out.println("Silahkan masukkan nomor fitur yang tersedia");
                    break;
            }

            System.out.print("\n");

        }


    }

    public static void menu(){
        System.out.println("MENU FITUR UTAMA");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi linear berganda");
        System.out.println("6. Keluar");
    }

    public static void submenu1(){
        System.out.println("SUBMENU SISTEM PERSAMAAN LINEAR");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
    }


}




