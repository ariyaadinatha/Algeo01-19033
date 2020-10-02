package com.company;

//ver1
//Ini aku asumsiin udah ada isBujungSangkar sebelum pemanggilan method
//Solusinya aku buat array jadi masih butuh method buat men-display solusi yang sesuai spek
//method splCramer aku bikin nge-return array solusi-nya jadi kalo kosong berarti solusinya tidak unik
//Pesan errornya juga aku tulis di sini kalo mau dipindah ke method display solusi juga boleh
//ver2
//Benerin error di splCramer
//Ganti tidak ada solusi dengan array null bukan nol lagi

public class cramer{

    public static double[] splCramer(double X[][], double B[][], int N){

        determinan det = new determinan();
        double solution[] = new double[N];
        double detX = det.determinanReductionRowWay(X,N);
        if (detX == 0){
            double temp[] = null;
            return temp;
        }else{
            double temp[][] = new double[N][N];
            for (int j = 0; j < N; j++){
                det.copyMatriks(gantiX(X, B, N, j), temp, N);
                solution[j] = det.determinanReductionRowWay(temp,N) / detX;
            }
            return solution;
        }
        
    }
    static double[][] gantiX(double X[][], double B[][], int N, int j){
        determinan det = new determinan();
        double temp[][] = new double[N][N];
        det.copyMatriks(X,temp,N);
        for (int i = 0; i < N; i++ ){
            temp[i][j] = B[i][0];
        }
        return temp;
    }
//    public static void main(String[] args){
//        int N = 3;
//        double X[][] = {{-1,2,-3},{2,0,1},{3,-4,4}};
//        double B[][] = {{1},{0},{2}};
//        double T[] = splCramer(X,B,N);
//        for (int i = 0; i < N; i++ ){
//            System.out.print(T[i]);
//            System.out.print(" ");
//        }
//        if (T == null){
//            System.out.print("Matriks tidak memiliki solusi unik.");
//        }
//    }
}