package bocom.lucene.until;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.omg.CORBA.portable.ValueBase;

public class File2DocumentUntil {

	public static Document File2Document(String path) throws FileNotFoundException {
		File file = new File(path);
		Document doc = new Document();
		doc.add(new TextField("name", file.getName(),Store.YES));
		doc.add(new TextField("size", String.valueOf(file.length()),Store.YES));
		doc.add(new TextField("path", file.getPath(),Store.YES));
		//doc.add(new TextField("content", "博康智能技术有限公司",Store.YES));
		doc.add(new TextField("content", readFileContent(file),Store.YES));
		//doc.add(new TextField("contents", new FileReader(file)));

		return doc;
	}

	private static String readFileContent(File file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuffer content = new StringBuffer();

			for (String line = null; (line = reader.readLine()) != null;) {
				content.append(line).append("\n");
			}

			System.out.println(content.toString());
			return content.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void printDocumentInfo(Document doc) {
		
		System.out.println("-----------------");
		System.out.println("name    = "+doc.get("name")); 
		System.out.println("size    = "+doc.get("size")); 
		System.out.println("path    = "+doc.get("path")); 
		System.out.println("contents = "+doc.get("content")); 
		
	}
}
