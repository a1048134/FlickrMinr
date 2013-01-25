package pae.team8.flickrminr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pae.team8.flickrminr.flickr.Connector;
import pae.team8.flickrminr.flickr.FlickrConnector;
import pae.team8.flickrminr.nlp.NlpEvaluator;
import pae.team8.flickrminr.ui.UI;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App{
	static Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.analyzer.App");

	static SplashScreen mySplash;                   // instantiated by JVM we use it to get graphics
	static Graphics2D splashGraphics;               // graphics context for overlay of the splash image
	static Rectangle2D.Double splashTextArea;       // area where we draw the text
	static Rectangle2D.Double splashProgressArea;   // area where we draw the progress bar
	static Font font;                               // used to draw our text

	private static String _apiKey;
	public static NlpEvaluator _nlp;
	public static Connector _fc;
	public static Properties _props = new Properties();

	public static void main(String[] args){
		App app= new App();
		UI.ShowUI();
	}

	public App(){
		loadProperties("application.properties");
		_apiKey = _props.getProperty("apiKey");
		splashInit();
		appInit();
		if (mySplash != null){
			mySplash.close();
		}		
	}
	private static void loadProperties(String filename){
		logger.debug("Reading properties form file.");
		try {
            if(filename.equals("")) filename = "/application.properties";
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filename));
			_props.load(stream);
			stream.close();
			logger.debug("Properties loaded.");
		} catch (IOException e) {
			logger.error("Could not load properties file!");
			System.exit(1);
		}    	
	}

	/**
	 * just a stub to simulate a long initialization task that updates
	 * the text and progress parts of the status in the Splash
	 */
	public static void appInit(){
		logger.debug("Initializing Application.");
		
		splashText("Loading Properties");
		splashProgress(30);
		
		logger.debug("Initializing Flickr API.");
		splashText("Initializing Flickr API");
		try{
			Thread t_fc = new Thread(){
				public void run(){
					_fc = new FlickrConnector(_apiKey);
					_fc.Connect();
				}
			};
			t_fc.start();
			int i=0;
			while(t_fc.isAlive()){
				if(i % 2 == 0){
					splashText("Initializing Flickr API ...");
				}else{
					splashText("Initializing Flickr API");
				}
				Thread.sleep(1000);
				i++;
			}
		}
		catch (InterruptedException ex){
			logger.error("Could not initialize Flickr API");
		}
		splashProgress(50);
		
		logger.debug("Initializing Stanford NLP");
		splashText("Initializing Stanford NLP");
		try{
			Thread t_nlp = new Thread(){
				public void run(){
					_nlp = new NlpEvaluator();
				}
			};
			t_nlp.start();
			int i=0;
			while(t_nlp.isAlive()){
				if(i % 2 == 0){
					splashText("Initializing Stanford NLP ...");
				}else{
					splashText("Initializing Stanford NLP");
				}
				Thread.sleep(500);
				i++;
			}
		}catch (InterruptedException ex){
			logger.error("Could not initialize Stanford NLP");
		}
		splashProgress(90);        

		splashText("Starting App");        
		splashProgress(100);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted when waiting to start application.");
        }
    }

	/**
	 * Prepare the global variables for the other splash functions
	 */
	public static void splashInit(){
		// the splash screen object is created by the JVM, if it is displaying a splash image

		mySplash = SplashScreen.getSplashScreen();
		// if there are any problems displaying the splash image
		// the call to getSplashScreen will returned null

		if (mySplash != null)
		{
			// get the size of the image now being displayed
			Dimension ssDim = mySplash.getSize();
			int height = ssDim.height;
			int width = ssDim.width;

			// stake out some area for our status information
			splashTextArea = new Rectangle2D.Double(100., height * 0.60, width * .5, 20.);
			splashProgressArea = new Rectangle2D.Double(108., height* 0.67, width * .5, 5. );

			// create the Graphics environment for drawing status info
			splashGraphics = mySplash.createGraphics();
			font = new Font("Dialog", Font.BOLD, 12);
			splashGraphics.setFont(font);

			// initialize the status info
			splashText("Starting");
			splashProgress(0);
		}
	}

	/**
	 * Display text in status area of Splash.  Note: no validation it will fit.
	 * @param str - text to be displayed
	 */
	public static void splashText(String str){
		if (mySplash != null && mySplash.isVisible()){

			splashGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
			splashGraphics.fill(splashTextArea);
			splashGraphics.setComposite(AlphaComposite.SrcOver);

			// draw the text
			splashGraphics.setPaint(Color.BLACK);
			splashGraphics.drawString(str, (int)(splashTextArea.getX() + 10),(int)(splashTextArea.getY() + 15));

			// make sure it's displayed
			mySplash.update();
		}
	}
	/**
	 * Display a (very) basic progress bar
	 * @param pct how much of the progress bar to display 0-100
	 */
	public static void splashProgress(int pct){
		if (mySplash != null && mySplash.isVisible()){

			// Note: 3 colors are used here to demonstrate steps
			// erase the old one
			splashGraphics.setPaint(Color.white);
			splashGraphics.fill(splashProgressArea);
			splashGraphics.draw(splashProgressArea);

			// Calculate the width corresponding to the correct percentage
			int x = (int) splashProgressArea.getMinX();
			int y = (int) splashProgressArea.getMinY();
			int wid = (int) splashProgressArea.getWidth();
			int hgt = (int) splashProgressArea.getHeight();

			int doneWidth = Math.round(pct*wid/100.f);
			doneWidth = Math.max(0, Math.min(doneWidth, wid-1));  // limit 0-width

			// fill the done part one pixel smaller than the outline
			splashGraphics.setPaint(Color.GREEN);
			splashGraphics.fillRect(x, y+1, doneWidth, hgt-1);

			// make sure it's displayed
			mySplash.update();
		}
	}
}
