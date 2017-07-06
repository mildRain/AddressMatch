package bocom.lucene.creatindex;

public class Startup {

	public static void main(String[] args) {

		DBIndexer indexer = new DBIndexer();
		indexer.fullIndex("jhglz1");
	}

}
