package com.company;

//ver1
//Di main program asumsi udah di isBujungSangkar
//Ini aku buat dua cara : row reduction sama kofaktor
//Di method detrowreduction aku pake method copyMatriks soalnya kalo cuma pake sama dengan matriks input-nya juga ikut tereduksi
//ADT matriksnya aku asumsiin matrix sama jumlah baris/kolom beda(bukan satu tipe data)
//ver2
//Benerin cara cofactor det bukan integer lagi
public class determinan{
    static void cofactor(double M[][],int N, double temp[][], int a, int b){
        int i =0;
        int j = 0;
        for (int k = 0 ; k < N ; k++){
            for (int l = 0 ; l < N; l++){
                if ((k != a) && (l!=b)){
                    temp[i][j++] = M[k][l];
                    if (j == N-1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public static double determinanCofactorWay(double M[][], int N){
        if (N==2){
            return ((M[0][0] * M[1][1]) - (M[0][1] * M[1][0])) ;
        }
        else{
            double det = 0;
            double temp[][] = new double[N-1][N-1];
            int tanda = 1;
            for (int k=0; k < N; k++){
                cofactor(M,N,temp,0,k);
                det += tanda * M[0][k] * determinanCofactorWay(temp,N-1);
                tanda = tanda * (-1);
            }
            return det;
        }

    }

    public static double determinanReductionRowWay(double M[][], int N){
        double temp[][] = new double[N][N];
        copyMatriks(M,temp,N);
        int count = rowReduction(M,N);
        double det = M[0][0];
        for (int i = 1; i < N; i++){
            det *= M[i][i];
        }
        copyMatriks(temp,M,N);
        if (count % 2 == 0){
            return det;
        }else{
            return -det;
        }
    }

    static int rowReduction(double M[][],int N){
        int i = 0, l = 0, idx;
        double pengurang;
        int count = 0;
        for (int j = 1; j < N; j++){
            for (int h = j; h < N; h++){
                idx = i+1;
                while ((M[i][l] == 0) && (idx < N)){
                    tukerRow(M,N,i,idx);
                    count++;
                    idx++;
                }
                if (idx == N){
                    continue;}
                pengurang = M[h][l] / M[i][l];
                for (int k = l; k < N; k++){
                    M[h][k] -= M[i][k] * pengurang;
                }
            }

            i++;
            l++;
        }
        return count;
    }

    static void tukerRow(double M[][], int N, int p, int q){
        double temp[] = new double[N];
        for (int j = 0; j < N; j++){
            temp[j] = M[p][j];
            M[p][j] = M[q][j];
            M[q][j] = temp[j];
        }
    }

    public static void copyMatriks(double M[][], double temp[][], int N){
        for (int row = 0 ; row < N ; row++){
            for (int col = 0; col < N; col++){
                temp[row][col] = M[row][col];
            }
        }
    }

    public static void cetakMatriks(double M[][], int N){
        for (int i =0; i < N; i ++){
            for (int j = 0; j < N; j++){
                System.out.print(M[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

//    public static void main(String[] args){
//        int N = 3;
//        double matrix[][] = {{2.0794f,8.0f,64.0f},{2.1973f,9.0f,81f},{2.2513f,9.5f,90.25f}};
//        System.out.print(determinanReductionRowWay(matrix, N));
//        System.out.print("\n");
//        System.out.print(determinanCofactorWay(matrix, N));
//
//    }
}
