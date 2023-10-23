package Lab7;

import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };

        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);

        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;

        MyData matC = new MyData(matC_r, matC_c);

        // Q4 code here
        Thread t1 = new Thread(new MatrixMulThread(0, 0, matA, matB, matC));
        Thread t2 = new Thread(new MatrixMulThread(0, 1, matA, matB, matC));
        Thread t3 = new Thread(new MatrixMulThread(1, 0, matA, matB, matC));
        Thread t4 = new Thread(new MatrixMulThread(1, 1, matA, matB, matC));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Q5 code here
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    MyData datA;
    MyData datB;
    MyData datC;

    MatrixMulThread(int tRow, int tCol,
            MyData a, MyData b, MyData c) {
        // Q1 code here
        this.processing_col = tCol;
        this.processing_row = tRow;
        this.datA = a;
        this.datB = b;
        this.datC = c;

    }

    // Q2 code here
    public void run() {
        // Q3 code here
        for (int i = 0; i < datA.data.length; i++) {
            datC.data[processing_row][processing_col] += (datA.data[processing_row][i] * datB.data[i][processing_col]);
        }
        System.out.println("perform sum of multiplication of assigned row and col");
    }
}

class MyData {
    int[][] data;

    MyData(int[][] m) {
        data = m;
    }

    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data)
            Arrays.fill(aRow, 9);
        // 9 will be overwritten anyway
    }

    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}
