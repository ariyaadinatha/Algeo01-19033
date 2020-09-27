//Di main program asumsi udah di isBujungSangkar
//Ini aku buat dua cara : row reduction sama kofaktor
//Di method detrowreduction aku pake method copyMatriks soalnya kalo cuma pake sama dengan matriks input-nya juga ikut tereduksi
//ADT matriksnya aku asumsiin matrix sama jumlah baris/kolom beda(bukan satu tipe data)
public class determinan{  
    static void cofactor(float M[][],int N, float temp[][], int a, int b){
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

    public static float determinanCofactorWay(float M[][], int N){
        if (N==1){
            return (M[0][0]) ;
        }
        else{
            int det = 0;
            float temp[][] = new float[N-1][N-1];
            int tanda = 1;
            for (int k=0; k < N; k++){
                cofactor(M,N,temp,0,k);
                det += (tanda * M[0][k] * determinanCofactorWay(temp,N-1));
                tanda = -tanda;
            }
            return det;
        }
        
    }

    public static float determinanReductionRowWay(float M[][], int N){
        float temp[][] = new float[N][N];
        copyMatriks(M,temp,N);
        int count = rowReduction(M,N);
        float det = M[0][0];
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

    static int rowReduction(float M[][],int N){
        int i = 0, l = 0, idx;
        float pengurang;
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

    static void tukerRow(float M[][], int N, int p, int q){
        float temp[] = new float[N];
        for (int j = 0; j < N; j++){
            temp[j] = M[p][j];
            M[p][j] = M[q][j];
            M[q][j] = temp[j];
        }
    }

    public static void copyMatriks(float M[][], float temp[][], int N){
        for (int row = 0 ; row < N ; row++){
            for (int col = 0; col < N; col++){
                temp[row][col] = M[row][col];
            }
        }
    }

    public static void cetakMatriks(float M[][], int N){
        for (int i =0; i < N; i ++){
            for (int j = 0; j < N; j++){
                System.out.print(M[i][j]);
                System.out.print(" ");
            }
        System.out.print("\n");
        }
    }

    public static void main(String[] args){
        int N = 4;
        float matrix[][] = {{1,-1,2,-1},{2,1,-2,-2},{-1,2,-4,1},{0,2,0,1}};
        System.out.print(determinanReductionRowWay(matrix, N));
        System.out.print("\n");
        System.out.print(determinanCofactorWay(matrix, N));
        System.out.print("\n");
        cetakMatriks(matrix,N);
    }
}