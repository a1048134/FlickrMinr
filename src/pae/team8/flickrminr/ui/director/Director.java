package pae.team8.flickrminr.ui.director;

import pae.team8.flickrminr.ui.model.MainModel;
import pae.team8.flickrminr.ui.presenter.MainPresenter;
import pae.team8.flickrminr.ui.view.MainView;

public class Director {
	
	static MainPresenter main;
	
	public static void doInit(){
		initMain();
		main.doInit();
	}
	
	static void initMain(){
		if(main == null){
			main = new MainPresenter();
			main.addView("main", new MainView(main));
			main.addModel("main", new MainModel());
		}
	}
}
