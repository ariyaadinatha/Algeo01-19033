//ver1
//Ini aku asumsiin udah ada isBujungSangkar sebelum pemanggilan method
//Solusinya aku buat array jadi masih butuh method buat men-display solusi yang sesuai spek
//method splCramer aku bikin nge-return array solusi-nya jadi kalo kosong berarti solusinya tidak unik
//Pesan errornya juga aku tulis di sini kalo mau dipindah ke method display solusi juga boleh
//ver2
//Benerin error di splCramer
//Ganti tidak ada solusi dengan array null bukan nol lagi

public class cramer{

    public static float[] splCramer(float X[][], float B[][], int N){
        determinan det = new determinan();
        float solution[] = new float[N];
        float detX = det.determinanCofactorWay(X,N);
        if (detX == 0){
            float temp[][] = null;
        }else{
            float temp[][] = new float[N][N];
            for (int j = 0; j < N; j++){
                det.copyMatriks(gantiX(X, B, N, j), temp, N);
                solution[j] = det.determinanCofactorWay(temp,N) / detX;
            }   
        } 
        return solution;
    }
    static float[][] gantiX(float X[][], float B[][], int N, int j){
        determinan det = new determinan();
        float temp[][] = new float[N][N];
        det.copyMatriks(X,temp,N);
        for (int i = 0; i < N; i++ ){
            temp[i][j] = B[i][0];
        }
        return temp;
    }
    public static void main(String[] args){
        int N = 3;
        float X[][] = {{-1,2,-3},{2,0,1},{3,-4,4}};
        float B[][] = {{1},{0},{2}};
        float T[] = splCramer(X,B,N);
        for (int i = 0; i < N; i++ ){
            System.out.print(T[i]);
            System.out.print(" ");
        }
        if (T == null){
            System.out.print("Matriks tidak memiliki solusi unik.");
        }
    }
}