package by.bsuir.giis.model;

public class Matrix {

	public static float[][] mult(float[][] f, float[][] s) {
		if (f[0].length != s.length) {
			return null;
		}

		int a = f.length;
		int b = f[0].length;
		int c = s[0].length;
		float[][] d = new float[a][c];

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < c; j++) {
				float summand = 0f;
				for (int k = 0; k < b; k++) {
					summand += f[i][k] * s[k][j];
				}
				d[i][j] = summand;
			}
		}
		return d;
	}

	public static Jama.Matrix createPoint(int... a) {

		double[] row = new double[a.length + 1];
		for (int i = 0; i < row.length; i++) {
			row[i] = 0;

		}
		row[a.length] = 1;
		double[][] array = new double[1][a.length + 1];
		array[0] = row;

		Jama.Matrix point = new Jama.Matrix(array, 1, a.length + 1);

		for (int i = 0; i < a.length; i++)
			point.set(0, i, a[i]);

		return point;
	}
}
