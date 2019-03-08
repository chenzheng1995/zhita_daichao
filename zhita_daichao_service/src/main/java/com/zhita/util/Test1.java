package com.zhita.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class Test1 {

	public static void main(String[] args) throws IOException {
		getDataFromExcel("C:\\Users\\Administrator\\Desktop\\旧版.xls");

	}
	
	public static void getDataFromExcel(String filePath) throws IOException
    {
        //String filePath = "E:\\123.xlsx";
        
        //判断是否为excel类型文件 
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx"))
        {
            System.out.println("文件不是excel类型");
        }
        
        FileInputStream fis =null;
        Workbook wookbook = null;
        Sheet sheet =null;
        try
        {
            //获取一个绝对地址的流
              fis = new FileInputStream(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        try 
        {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿
             
        } 
        catch (Exception ex) 
        {
            //ex.printStackTrace();
            try
            {
                //2007版本的excel，用.xlsx结尾
            	 fis = new FileInputStream(filePath);
                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        

        Map<String, PictureData>  maplist=null;
              
        sheet = wookbook.getSheetAt(0);  
            // 判断用07还是03的方法获取图片  
        if (filePath.endsWith(".xls")) {  
            maplist = getPictures1((HSSFSheet) sheet);  
        } else if(filePath.endsWith(".xlsx")){  
            maplist = getPictures2((XSSFSheet) sheet);  
        }  
        try {
			printImg(maplist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //得到一个工作表
        
       
        
        //获得表头
        Row rowHead = sheet.getRow(0);
        
        //判断表头是否正确
        System.out.println(rowHead.getPhysicalNumberOfCells());
        if(rowHead.getPhysicalNumberOfCells() != 5)
        {
            System.out.println("表头的数量不对!");
        }
        
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        
        //要获得属性
       int studentid=0;
       String studentname="";
       String grade="";
       String classes="";
       String pic="";
       //获得所有数据
        for(int i = 1 ; i <= totalRowNum ; i++)
        {
            //获得第i行对象
            Row row = sheet.getRow(i);
            
            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell((short)0);
            studentid = (int) cell.getNumericCellValue();
            
            //获得一个数字类型的数据
            //studentname = (int) cell.getNumericCellValue();
            cell = row.getCell((short)1);
            studentname =cell.getStringCellValue().toString();
            
            cell = row.getCell((short)2);
            grade =cell.getStringCellValue().toString();
            
            cell = row.getCell((short)3);
            classes =cell.getStringCellValue().toString();
            
            cell = row.getCell((short)3);
            classes =cell.getStringCellValue().toString();
            
            System.out.println("学号："+studentid+",姓名："+studentname+",年级："+grade+",班级："+classes+",证件照："+pic);
        }
        for (Entry<String, PictureData> entry : maplist.entrySet()) {  
        	
        	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
        	
        }  
    }
  /**
   * 获取图片和位置 (xls)
   * @param sheet
   * @return
   * @throws IOException
   */
  public static Map<String, PictureData> getPictures1 (HSSFSheet sheet) throws IOException {
	    Map<String, PictureData> map = new HashMap<String, PictureData>();
	    List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
	    for (HSSFShape shape : list) {
	        if (shape instanceof HSSFPicture) {
	            HSSFPicture picture = (HSSFPicture) shape;
	            HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
	            PictureData pdata = picture.getPictureData();
	            String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
	            map.put(key, pdata);
	        }
	    }
	    return map;
	}
   
  /**
   * 获取图片和位置 (xlsx)
   * @param sheet
   * @return
   * @throws IOException
   */
  public static Map<String, PictureData> getPictures2 (XSSFSheet sheet) throws IOException {
      Map<String, PictureData> map = new HashMap<String, PictureData>();
      List<POIXMLDocumentPart> list = sheet.getRelations();
      for (POIXMLDocumentPart part : list) {
          if (part instanceof XSSFDrawing) {
              XSSFDrawing drawing = (XSSFDrawing) part;
              List<XSSFShape> shapes = drawing.getShapes();
              for (XSSFShape shape : shapes) {
                  XSSFPicture picture = (XSSFPicture) shape;
                  XSSFClientAnchor anchor = picture.getPreferredSize();
                  CTMarker marker = anchor.getFrom();
                  String key = marker.getRow() + "-" + marker.getCol();
                  map.put(key, picture.getPictureData());
              }
          }
      }
      return map;
  }
  //图片写出
  public static void printImg(Map<String, PictureData> sheetList) throws IOException {  
      
        //for (Map<String, PictureData> map : sheetList) {  
            Object key[] = sheetList.keySet().toArray();  
            for (int i = 0; i < sheetList.size(); i++) {  
                // 获取图片流  
                PictureData pic = sheetList.get(key[i]);  
                // 获取图片索引  
                String picName = key[i].toString();  
                // 获取图片格式  
                String ext = pic.suggestFileExtension();  
                  
                byte[] data = pic.getData();  
                 
            //图片保存路径 
                FileOutputStream out = new FileOutputStream("D:\\img\\pic" + picName + "." + ext);  
                out.write(data);  
                out.close();  
            }  
       // }  
          
    }  


}
