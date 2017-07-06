package bocom.lucene.helloword;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

public class testAnalyaze {

	String enText = "Lucene";
	String zhText = "我们是中国人";
	
	Analyzer a1 = new ComplexAnalyzer();
	IKAnalyzer analyzer = new IKAnalyzer(); 
	@Test
	public void test1() {
		
		try {
			analyne(a1, enText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void analyne(Analyzer analyzer,String text) throws Exception {
		//TokenStream token = a1.tokenStream("con", text)
				TokenStream tokenStream = analyzer.tokenStream("content", text);
		
				displayTokens(tokenStream);
				
	}
	public static void displayTokens(TokenStream tokenStream) throws IOException {
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);
		
		tokenStream.reset();
		int position = 0;
		while (tokenStream.incrementToken()) {
			int increment = positionIncrementAttribute.getPositionIncrement();
			if(increment > 0) {
				position = position + increment;
				System.out.print(position + ":");
			}
		    int startOffset = offsetAttribute.startOffset();
		    int endOffset = offsetAttribute.endOffset();
		    String term = charTermAttribute.toString();
		    System.out.println("[" + term + "]" + ":(" + startOffset + "-->" + endOffset + "):" + typeAttribute.type());
		}
	}
}
