package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Clase que instancia JPanel y se encarga de crear el panel
 * para la ventana de menú inicial. Esta clase incluye 3 botones
 * de tipo JButton. Dos de ellos utilizan actionListeners que 
 * se implementan fuera de la clase.
 * 
 * @author enzo
 *
 */
@SuppressWarnings("serial")
public class LoadPanel extends JPanel {
	
	private final int width = 200;
	private final int height = 200;
	private final String titleString = "Silversphere";

	private JButton startButton; 
	private JButton loadButton; 
	
	public LoadPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setSize(width, height);
		setBorder(new EmptyBorder(new Insets(20, 60, 40, 60)));
		setBackground(Color.WHITE);
		
		JLabel title = new JLabel(titleString);
		add(title);
		add(Box.createRigidArea(new Dimension(0, 15)));
        
		JButton start = new JButton("Start");
		startButton = start;
		add(start);
        add(Box.createRigidArea(new Dimension(0, 5)));
        
		JButton load = new JButton("Load");
		loadButton = load;
		add(load);
        add(Box.createRigidArea(new Dimension(0, 5)));
        
		JButton quit = new JButton("Quit");
		add(quit);
        add(Box.createRigidArea(new Dimension(0, 5)));
        
        quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
	}
	
	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getLoadButton() {
		return this.loadButton;
	}
}
