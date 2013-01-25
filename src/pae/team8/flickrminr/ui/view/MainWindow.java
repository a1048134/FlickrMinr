package pae.team8.flickrminr.ui.view;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;



public class MainWindow extends JFrame{
	private JTextField textFieldSearch;
	public MainWindow() {
		setTitle("FlickrMinr");
		getContentPane().setMaximumSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 164, 87, 149, 146, 29, 0};
		gridBagLayout.rowHeights = new int[]{10, 15, 97, 237, -138, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblFlickrId = DefaultComponentFactory.getInstance().createLabel("Flickr ID:");
		lblFlickrId.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblFlickrId.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblFlickrId = new GridBagConstraints();
		gbc_lblFlickrId.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlickrId.anchor = GridBagConstraints.EAST;
		gbc_lblFlickrId.gridx = 2;
		gbc_lblFlickrId.gridy = 1;
		getContentPane().add(lblFlickrId, gbc_lblFlickrId);
		
		textFieldSearch = new JTextField();
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 3;
		gbc_textFieldSearch.gridy = 1;
		getContentPane().add(textFieldSearch, gbc_textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnAnalyze = new JButton("Analyze");
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gbc_btnAnalyze.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAnalyze.anchor = GridBagConstraints.NORTH;
		gbc_btnAnalyze.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnalyze.gridx = 4;
		gbc_btnAnalyze.gridy = 1;
		getContentPane().add(btnAnalyze, gbc_btnAnalyze);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.BLUE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		
		JScrollPane scrollPaneResults = new JScrollPane();
		scrollPaneResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneResults = new GridBagConstraints();
		gbc_scrollPaneResults.anchor = GridBagConstraints.BASELINE;
		gbc_scrollPaneResults.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneResults.gridwidth = 5;
		gbc_scrollPaneResults.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPaneResults.gridx = 1;
		gbc_scrollPaneResults.gridy = 3;
		getContentPane().add(scrollPaneResults, gbc_scrollPaneResults);
	}

}
