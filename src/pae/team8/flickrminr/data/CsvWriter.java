package pae.team8.flickrminr.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: klexx
 * Date: 23.01.13
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class CsvWriter {
    Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.analyzer.NlpAnalyzer");
    private FileWriter writer;

    public CsvWriter(String filename, boolean append){
        try {
            writer = new FileWriter(filename, append);
            initCSVFile();
        } catch (IOException e) {
            logger.error("Could not open file " + filename + " to write.");
        }
    }
    public void writeToCSV(String id, LinkedHashMap<String, Integer> lines){
        if(lines != null){
            try {
                writer.write(id);
                writer.write(",");
                int i=0;
                for(Map.Entry<String, Integer> line : lines.entrySet()){
                    writer.write(String.valueOf(line.getValue()));
                    if(i < 3){
                        writer.write(",");
                    }
                    i++;
                }
                writer.write("\n");
                writer.flush();
            } catch (IOException e) {
                logger.error("Could not write to file.");
            }
        }
    }

    private void initCSVFile(){
        try {
            writer.write("ID");
            writer.write(",");
            writer.write("SC");
            writer.write(",");
            writer.write("WC");
            writer.write(",");
            writer.write("NC");
            writer.write(",");
            writer.write("LC");
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            logger.error("Could not initialize file.");
        }
    }
    public void close(){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
           logger.error("Exception when trying to close file. " + e);
        }
    }
}
