package pae.team8.flickrminr.nlp;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.DefaultPaths;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pae.team8.flickrminr.data.CommentResults;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NlpEvaluator implements Evaluator {
	Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.nlp.NlpEvaluator");
	StanfordCoreNLP pipeline;
	private int linkCount=0;

	public NlpEvaluator(){
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
		props.put("ner.model", DefaultPaths.DEFAULT_NER_MUC_MODEL);
		pipeline = new StanfordCoreNLP(props);
	}

	private String pullLinks(String text) {
        ArrayList links = new ArrayList();

        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while(m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")){
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            links.add(urlStr);
            linkCount++;
        }

        String strippedText = Jsoup.parse(text).text();
        logger.info(strippedText);
        return strippedText;
    }
    @Override
    public CommentResults evalData(String text) {
        LinkedHashMap<String, Integer> results = null;

        String strippedText = pullLinks(text);

        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        int sentenceCount=sentences.size();
        int wordCount=0;
        int nerCount=0;

        ArrayList<String> nerItems = new ArrayList<String>();

        for(CoreMap sentence: sentences) {
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                String ne = token.get(NamedEntityTagAnnotation.class);
                if(!"O".equals(ne)){
                    nerItems.add(ne);
                    nerCount++;
                }
                wordCount++;
            }
        }

        double score = Math.floor( (sentenceCount + wordCount + nerCount) / 3 );
        if(score >= 10){
            results = new LinkedHashMap<String, Integer>();
            results.put("SC", sentenceCount);
            results.put("WC", wordCount);
            results.put("NC", nerCount);
            results.put("LC", this.linkCount);
        }
        linkCount=0;
        return new CommentResults(results);
    }
}