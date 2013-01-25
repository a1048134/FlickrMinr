package pae.team8.flickrminr.ui.view;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import net.logicdevelopment.biscotti.View;
import pae.team8.flickrminr.ui.panel.ImagePanel;
import pae.team8.flickrminr.ui.presenter.MainPresenter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainView extends JPanel implements View, ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldSearch;
	private JFrame displayFrame;
	private JPanel panelInfo;
	private JScrollPane scrollPaneResults;
	private JButton btnAnalyze;
	private JTextField imageId;
	private MainPresenter presenter;
	private JTextField textFieldStatus;
	private ImagePanel panelImage;
    private JLabel lblFlickrId;

	public MainView(MainPresenter presenter) {
		this.presenter = presenter;
	}

	public void show() {
		displayFrame = new JFrame("FlickrMinr");
		displayFrame.setResizable(false);
		Container cp = displayFrame.getContentPane();

		cp.setMaximumSize(new Dimension(800, 600));
		cp.setMinimumSize(new Dimension(800, 600));		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{3, 225, 146, 119, 119, 119, 0};
		gridBagLayout.rowHeights = new int[]{10, 15, 5, 206, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		cp.setLayout(gridBagLayout);

		lblFlickrId = DefaultComponentFactory.getInstance().createLabel("Flickr ID:");
		lblFlickrId.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblFlickrId.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblFlickrId = new GridBagConstraints();
		gbc_lblFlickrId.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlickrId.anchor = GridBagConstraints.EAST;
		gbc_lblFlickrId.gridx = 2;
		gbc_lblFlickrId.gridy = 1;
		displayFrame.getContentPane().add(lblFlickrId, gbc_lblFlickrId);

		textFieldSearch = new JTextField();
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 3;
		gbc_textFieldSearch.gridy = 1;
		cp.add(textFieldSearch, gbc_textFieldSearch);
		textFieldSearch.setColumns(10);

		btnAnalyze = new JButton("Analyze");
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gbc_btnAnalyze.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAnalyze.gridwidth = 2;
		gbc_btnAnalyze.anchor = GridBagConstraints.NORTH;
		gbc_btnAnalyze.insets = new Insets(0, 0, 5, 0);
		gbc_btnAnalyze.gridx = 4;
		gbc_btnAnalyze.gridy = 1;
		cp.add(btnAnalyze, gbc_btnAnalyze);
		btnAnalyze.addActionListener(this);

		panelImage = new ImagePanel(new BufferedImage(1, 1, 1));
		panelImage.setBorder(new LineBorder(new Color(255, 255, 255)));
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.anchor=GridBagConstraints.CENTER;
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 5);
		gbc_panelInfo.gridx = 1;
		gbc_panelInfo.gridy = 2;
		cp.add(panelImage,gbc_panelInfo);
		
		scrollPaneResults = new JScrollPane();
		scrollPaneResults
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneResults = new GridBagConstraints();
		gbc_scrollPaneResults.gridwidth = 5;
		gbc_scrollPaneResults.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneResults.gridx = 1;
		gbc_scrollPaneResults.gridy = 3;
		cp.add(scrollPaneResults, gbc_scrollPaneResults);

		cp.add(this);
		displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		displayFrame.setSize(new Dimension(800, 600));		
		
		displayFrame.setVisible(true);

	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(displayFrame, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void showInfo(String message) {
		JOptionPane.showMessageDialog(displayFrame, message, "Info",
				JOptionPane.WARNING_MESSAGE);
	}

	public String getImageId() {
		return textFieldSearch.getText();
	}

	public void close() {
		displayFrame.setVisible(false);
	}

	public void switchButton() {
		boolean enable;
		if(btnAnalyze.isEnabled())
			enable = false;
		else
			enable = true;		
		btnAnalyze.setEnabled(enable);
	}

	public void actionPerformed(ActionEvent e) {
		presenter.submit();
        presenter.doAnalyze();
	}
	
	public void setRealImage(BufferedImage img){
		panelImage.loadImage(img);
	}
}
