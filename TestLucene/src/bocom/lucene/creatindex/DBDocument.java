package bocom.lucene.creatindex;

import java.sql.ResultSet;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import com.broadvident.lucene.config.IndexerConfig;

public class DBDocument {
	/**
	 * 索引数据库记录
	 * @param writer 索引写入
	 * @param rs  记录集
	 * @param ic  索引器配置信息
	 * @return
	 */
	public static void buildDocument(IndexWriter writer,ResultSet rs,IndexerConfig ic) throws Exception{
		String pkField = ic.getPKFieldName().trim(); //主键字段名
		String idxField = ic.getIndexFiledName().trim(); //索引字段名
		String dataFileds = ic.getDataFiledName().trim();//数据字段名
		//按逗号拆分数据字段
		String[] fs = new String[]{};
		if(dataFileds!=null && !"".equals(dataFileds))
			fs = dataFileds.split(",");
		String pkid = "";
        String contents= "";
        
        int k=1;
        
		//循环每条数据库记录，并将索引并写入索引文件
		while(rs.next()){
			Document doc = new Document();
			pkid = rs.getString(pkField);
            //System.out.println(rs.getString(idxField));
			/*doc.add(new StringField("pkid",pkid, Store.YES));
			contents = rs.getString(idxField);
			doc.add(new TextField("contents",contents,Store.YES));
			for(int i=0;i<fs.length;i++){
				doc.add(new StringField(fs[i],rs.getString(fs[i]),Store.YES));
			}*/
			doc.add(new Field("pkid",pkid, Field.Store.YES, Field.Index.NOT_ANALYZED));
			contents = rs.getString(idxField);
			doc.add(new Field("contents",contents,Field.Store.YES,Field.Index.ANALYZED));
			for(int i=0;i<fs.length;i++){
				doc.add(new Field(fs[i],rs.getString(fs[i]), Field.Store.YES, Field.Index.NOT_ANALYZED));
			}
			writer.addDocument(doc);
			
			System.out.println(k++);
		}
		//保存断点数据
		/*if(!"".equals(pkid))
			BreakPointHelper.saveBreakPoint(ic.getBreakPoint(), pkid);
		 */
	}
}
