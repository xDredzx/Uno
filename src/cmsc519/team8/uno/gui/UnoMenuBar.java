package cmsc519.team8.uno.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UnoMenuBar extends JMenuBar {

	/**
	 * UID for JToolBar
	 */
	private static final long serialVersionUID = -665122809628935394L;

	/**
	 * Create the panel.
	 */
	public UnoMenuBar(final JPanel gamePanel) {
		super();

		// add game option menu
		JMenu menu = new JMenu("Game");

		// restart button
		JMenuItem newGame = new JMenuItem("New Game");
		// quit button
		JMenuItem close = new JMenuItem("Exit");

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((UnoGamePanel) gamePanel).restart();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(newGame);
		menu.add(close);

		// help option
		JMenuItem help = new JMenuItem("Help");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					String location = "/cmsc519/team8/uno/data/UnoManual.txt";
					BufferedReader br = new BufferedReader(
							new InputStreamReader(this.getClass()
									.getResourceAsStream(location)));
					JTextArea textArea = new JTextArea(40, 50);
					String line = br.readLine();
					String newline = "\n";
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);

					JScrollPane scroll = new JScrollPane(textArea,
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

					while ((line = br.readLine()) != null) {
						if (line.trim().length() == 0) {
							textArea.append(newline);
						}
						textArea.append(line + newline);
					}
					br.close();

					JOptionPane.showMessageDialog(null, scroll, "UNO Help",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// do something if the file is not found
				}
			}
		});

		add(menu);
		add(help);
	}

}