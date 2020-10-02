package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MatriksFile {

    public static String currDir(){
        return System.getProperty("user.dir") + "\\src\\com\\company";
    }

    public static int[] ukuranMatriksFile(String filename){
        //mengreturn [brs, kol]
        int brs = 0;
        int kol = 0;
        File file = new File(currDir() + "\\" + filename);
        int[] ukuran = new int[2];

        try {
            Scanner readerBrs = new Scanner(file);
            Scanner readerKol = new Scanner(file);



            while (readerKol.hasNextDouble()){
                kol += 1;
                readerKol.nextDouble();

            }

            while (readerBrs.hasNextLine()){
                readerBrs.nextLine();
                brs += 1;
            }

            kol /= brs;
        } catch (FileNotFoundException e){
            ;
        }
        
        ukuran[0] = brs;
        ukuran[1] = kol;
        
        return ukuran;

    }

    public static double[][] fileToMatriks(String filename){
        File file = new File(currDir() + "\\" + filename);
        int[] ukuran = ukuranMatriksFile(filename);
        int brs = ukuran[0];
        int kol = ukuran[1];
        double[][] arrFile = new double[brs][kol];
        try {
            Scanner reader = new Scanner(file);

            int ii = 0;
            int jj = 0;
            for (int i = 0; i < brs; ++i){
                for (int j = 0; j < kol; ++j){
                    arrFile[i][j] = reader.nextDouble();
                }
            }
        } catch (FileNotFoundException e){
            ;
        }

        return arrFile;

    }

    public static int menuInputMatriks(){
        int choice;
        boolean choiceExist = false;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("MENU INPUT");
            System.out.println("1. Input dari file");
            System.out.println("2. Input dari keyboard");
            if (choiceExist){
                System.out.println("Masukkan nomor pilihan input yang tersedia!");
            }
            System.out.print("Nomor pilihan input : ");
            choice = s.nextInt();
            choiceExist = true;
        } while ((choice != 1) && (choice != 2)) ;

        return choice;
    }

    public static boolean fileExist(String filename){
        File file = new File(currDir() + "\\" + filename);

        try{
            Scanner s = new Scanner(file);
            return true;
        } catch (FileNotFoundException E){
            return false;
        }
    }

    public static String inputFileName(){
        Scanner s = new Scanner(System.in);
        String filename;
        boolean filenameExist = false;
        do {
            if (filenameExist){
                System.out.println("Nama file tidak ditemukan");
            }
            System.out.print("Masukkan nama file : ");
            filename = s.nextLine();
            filenameExist = true;
        } while(!fileExist(filename));

        return filename;
    }

    public static boolean inputFromFile(){
        return menuInputMatriks() == 1;
    }

    public static boolean saveToFile(){
        int choice;
        boolean choiceExist = false;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("MENU SAVE");
            System.out.println("Apakah anda ingin save hasil jawaban ke file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            if (choiceExist){
                System.out.println("Masukkan nomor pilihan input yang tersedia!");
            }
            System.out.print("Nomor pilihan input : ");
            choice = s.nextInt();
            choiceExist = true;
        } while ((choice != 1) && (choice != 2)) ;

        return choice == 1;
    }

    public static String createFile(){
        boolean fileCreated = false;
        File file;
        String filename = "";
        Scanner s = new Scanner(System.in);

        while (!fileCreated){
            System.out.print("Masukkan nama file : ");
            filename = s.nextLine();
            try {
                file = new File(currDir() + "\\" + filename);
                if (file.createNewFile()){
                    fileCreated = true;
                } else {
                    System.out.println("Nama file tersebut sudah ada, masukkan nama yang lain");
                }
            } catch (IOException e){
                ;
            }
        }

        return filename;
    }

    public static void matriksToFile(String filename, double[][] arr){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            for (double[] brs : arr){
                for (double el : brs){
                    writer.write(el + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void splCramerToFile(String filename, double[] arr){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            if (arr == null){
                writer.write("Matriks tidak memiliki solusi unik");
            } else {
                for (int i = 0; i < arr.length; ++i){
                    writer.write("x" + (i + 1) + " = " + arr[i] + "\n");
                }
            }
            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void splInverseToFile(String filename, double[][] arr){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            if (Double.isNaN(arr[0][0])){
                writer.write("Matriks tidak memiliki solusi unik");
            } else {
                for (int i = 0; i < arr.length; ++i){
                    writer.write("x" + (i + 1) + " = " + arr[i][0] + "\n");
                }
            }
            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void determinanToFile(String filename, double detEK, double detRB){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            writer.write("Determinan dengan metode ekspansi kofaktor : " + detEK + "\n");
            writer.write("Determinan dengan metode reduksi baris : " + detRB + "\n");
            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void regLinToFile(String filename, double[][] b, double hasil){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            writer.write("y = ");

            for (int i = 0; i < b.length; ++i){
                if (i == 0){
                    writer.write(b[i][0] + " ");

                } else {
                    writer.write("+ " + b[i][0] + "x" + (i + 1) + " ");
                }
            }
            writer.write("\n");

            writer.write("Hasil taksir : " + hasil+ "\n");

            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void interpolToFile(String filename, double hasil, double[] solusi){

        int N = solusi.length;
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            //menulis solusi persamaan polinom
            int idx = interpolasiPolinom.idxFirst(solusi, N);
            for (int i = N; i >= 0; i--){
                if (solusi[i] != 0){
                    if ((solusi[i] > 0) && (i != idx)){
                        writer.write("+");
                    }
                    if(solusi[i] == 1){
                        writer.write("x^" + i + " ");
                    }
                    else{
                        writer.write(solusi[i] + "x^" + i + " ");
                    }
                }
            }

            writer.write("\n");

            //menulis hasil taksir
            writer.write("Hasil taksir" + hasil + "\n");
            writer.close();
        } catch (IOException e){
            ;
        }
    }

    public static void splGaussToFile(String filename, String[] solusi){
        try {
            FileWriter writer = new FileWriter(currDir() + "\\" + filename);
            if (solusi == null){
                writer.write("Sistem persamaan linear tidak memiliki solusi\n");
            } else {
                for(int i = 0; i < solusi.length; ++i){
                    writer.write("x" + (i + 1) + " = " + solusi[i] + "\n");
                }
            }
            writer.close();
        } catch (IOException e){
            ;
        }
    }
}
