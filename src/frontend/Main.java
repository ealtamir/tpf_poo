package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import backend.GameMapDeserializer;
import backend.GameMapParser;
import frontend.LoadPanel;


public class Main {
	
	private JFrame gameScreen;
	
	public static void main(String[] args) {
		new Main("TP POO"); 
	}
	
	public JFrame getGameScreen() {
		return gameScreen;
	}
	
	public Main(String gameName) {
		gameScreen = new JFrame(gameName);
		LoadPanel lpanel = new LoadPanel();
		gameScreen.add(lpanel);
		gameScreen.setSize(lpanel.getWidth() , lpanel.getHeight());
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen.setVisible(true);
		gameScreen.setResizable(false);
		centerScreen(gameScreen);
		
		lpanel.getStartButton().addActionListener(
			new GameStarter(gameScreen, new GameMapParser())
		);
		lpanel.getLoadButton().addActionListener(
			new GameStarter(gameScreen, new GameMapDeserializer())
		);
		
	}
	
	private void centerScreen(JFrame screen) {
		Toolkit toolkit = screen.getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    screen.setLocation(size.width/2 - screen.getWidth()/2,
	                  size.height/2 - screen.getHeight()/2);
	}
}
