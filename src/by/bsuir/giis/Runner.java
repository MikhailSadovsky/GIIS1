package by.bsuir.giis;

import by.bsuir.giis.view.MainFrame;

public class Runner {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

}
