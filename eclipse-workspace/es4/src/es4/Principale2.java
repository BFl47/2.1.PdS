package es4;

import java.util.Random;

public class Principale2 {
	
	public static void stampaM(int[][] matrice) {
		int row = matrice.length;
		int col = matrice[0].length;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) 
				System.out.print(" " + matrice[i][j]);
			System.out.println();
		}
	}
	/*
	public static boolean bordi(int a, int b, int row, int col) {
		return a == 0 || b == 0 || a == row-1 || b == col-1;
	}
	
	public static boolean angoli(int a, int b, int row, int col) {
		return (a == 0 && b == 0) || (a == row-1 && b == col-1) || (a == 0 && b == col-1) || (a == row-1 && b == 0);
	}
	
	public static void stampaMagg(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if (angoli(i, j, row, col)) {
					if ((i == 0 && j == 0 && mat[i][j] > mat[i][j+1] && mat[i][j] > mat[i+1][j]) ||  
							(i == 0 && j == col-1 && mat[i][j] > mat[i][j-1] && mat[i][j] > mat[i+1][j]) ||
							(i == row-1 && j == 0 && mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i][j+1]) ||
							(i == row-1 && j == col-1 && mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i][j-1])
							)
						System.out.print(" " + mat[i][j]);
				}	
				else if (bordi(i, j, row, col)){
					if ((i == 0 && mat[i][j] > mat[i+1][j] && mat[i][j] > mat[i][j-1] && mat[i][j] > mat[i][j+1]) ||
							(j == 0 && mat[i][j] > mat[i][j+1] && mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i+1][j]) ||
							(j == col-1 && mat[i][j] > mat[i][j-1] && mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i+1][j]) ||
							(i == row-1 && mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i][j-1] && mat[i][j] > mat[i][j+1])
							)
						System.out.print(" " + mat[i][j]);
				}
				else {
					if (mat[i][j] > mat[i-1][j] && mat[i][j] > mat[i+1][j] && mat[i][j] > mat[i][j-1] && mat[i][j] > mat[i][j+1])
						System.out.print(" " + mat[i][j]);
					
				}	
			}
		}
	}
	*/
	public static void main(String[] args) {

		int[][] matrice; //dichiara variabile matrice
		matrice = new int[5][5]; //crea oggetto matrice
		
		//doppio ciclo sugli elementi della matrice
		for(int i = 0; i < 5; i++) {
			//Random r = new Random();
			for(int j = 0; j < 5; j++) 
				matrice[i][j] = (int) (Math.random() * 100);
				//r.nextInt(0, 100);
		}
		
		stampaM(matrice);
		System.out.println();
		//stampaMagg(matrice);


	}
	
}
