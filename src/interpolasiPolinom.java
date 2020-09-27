import java.util.Scanner;

public class interpolasiPolinom{
    public static float[] interpol(float XY[][], int N){
        float PolX[][] = buatPolinomX(XY,N);
        float PolY[][] = buatPolinomY(XY,N);
        cramer splcramer = new cramer();
        float a[] = splcramer.splCramer(PolX,PolY,N+1);
        return a;
    } 

    static float[][] buatPolinomX(float XY[][], int N){
        float PolX[][] = new float[N+1][N+1];
        float x;
        for (int i = 0; i <= N; i++){
            for (int j = 0; j <= N; j++){
                x = (float) Math.pow(XY[i][0], j);
                PolX[i][j] = x;
            }
        }
        return PolX;
    }

    static float[][] buatPolinomY(float XY[][], int N){
        float PolY[][] = new float[N+1][1];
        float y;
        for (int i = 0; i <= N; i++){
            y =  XY[i][1];           
            PolY[i][0] = y;
        }
        return PolY;
    }

    static float[][] inputXY(int N){
        Scanner input = new Scanner(System.in);
        float XY[][] = new float[N+1][2];
        for (int i = 0; i <= N; i++){
            for (int j = 0; j < 2; j++){
                XY[i][j] = input.nextFloat();
            }
        }
        return XY;
    }

    static float hitungPerkiraan(float a[], int N, float X){
        float hasil = 0;
        for (int i = 0; i <= N; i++){
            hasil += a[i] * (float) Math.pow(X,i);
        }
        return hasil;
    }


    public static void main(String[] args){
        int N = 2;
        float XY[][] = inputXY(N);
        float a[] = interpol(XY,N);
        float X = 9.2f;
        float hasil = hitungPerkiraan(a,N,X);
        System.out.print(hasil);

    }
}