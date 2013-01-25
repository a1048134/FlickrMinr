package pae.team8.flickrminr.analyzer;

import pae.team8.flickrminr.data.DataSet;

public class AnalyzerThread extends Thread{
	private DataSet _data;
	private ResultSetter _setter;
	public AnalyzerThread(DataSet data, ResultSetter setter){
		_data = data;
		_setter = setter;
	}
	public void run(){
        NlpAnalyzer a = new NlpAnalyzer();
        _setter.setResult(a.doAnalyze(_data));
    }
}