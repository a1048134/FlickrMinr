package pae.team8.flickrminr.ui.presenter;

import net.logicdevelopment.biscotti.Presenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pae.team8.flickrminr.App;
import pae.team8.flickrminr.analyzer.AnalyzerThread;
import pae.team8.flickrminr.analyzer.ResultSetter;
import pae.team8.flickrminr.data.DataSet;
import pae.team8.flickrminr.exceptions.MinrException;
import pae.team8.flickrminr.ui.model.MainModel;
import pae.team8.flickrminr.ui.view.MainView;

public class MainPresenter extends Presenter{
    static Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.analyzer.MainPresenter");

	private MainView view;
	private MainModel model;
	private ResultSetter setter;
	private DataSet _data;
	
	public void doInit(){
		view = (MainView) views.get("main");
		model = (MainModel) models.get("main");
		view.show();
		setter = new ResultSetter() {
		    public void setResult(DataSet result) {
		      _data = result;
		    }
		};
	}
	
	public void doAnalyze(){
		String id = view.getImageId();
		
		if("".equals(id)){
			view.showInfo("Please enter a photo id.");
		}else{			
			view.switchButton();
            try {
                _data = App._fc.fetchData(id);

            } catch (MinrException e) {
                view.showInfo(e.getMessage());
                view.switchButton();
                display();
                return;
            }

            model.setRealImage(_data.getRealImage());
            display();

            if(_data.getComments().size()<=0){
                view.showInfo("This Photo yielded no comments.");
				logger.debug("This Photo yieled no comments");
			}else{
				Thread t_analyze = new Thread(new AnalyzerThread(_data, setter));
				t_analyze.start();
				while(t_analyze.isAlive()){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        logger.error("Interrupted while waiting for analyzer thread.");
                    }
				}
                model.setData(_data);
			}
            view.switchButton();
            display();
		}
	}
}