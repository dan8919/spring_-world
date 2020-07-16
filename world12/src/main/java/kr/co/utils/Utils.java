package kr.co.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	
	
	
	
	
	public static String makeDir(String uploadPath) {
		
		String[] paths=Utils.makeDirName();
		File f=new File(uploadPath+paths[2]);
		if(f.exists()) {
			return paths[2];
		}
		
		for(String path:paths) {
			File dirPath=new File(uploadPath+path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		
	   return paths[2];
		
	}
	
	
	
	
	
	public static String[] makeDirName() {
		
		int[] arr=Utils.getDateInfo();
		
		String yearPath = File.separator+arr[0]; // \2020년이 들어감
		String monthPath=yearPath+File.separator+String.format("%02d", arr[1]);  //%02d 2자리 들가게 하고 앞에 비어있으면 0으로 채움
		String datePath=monthPath+File.separator+String .format("%02d", arr[2]);
	
	    String[] paths = {yearPath,monthPath,datePath};
	    
	    return paths;
	}
	
	
	
	
	
	public static int[] getDateInfo() {
		Calendar cal=Calendar.getInstance();
		
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DATE);
		
		int[] arr= {year,month,date};
		
		return arr;
	
	
	
	}
	
	
	
	
	
	
	//첫번째 날 : saveFile그냥 upload 폴더에 저장하게 함
	
	public static String saveFile(String originalName,MultipartFile file,String uploadPath)throws Exception {
		
		String newName = Utils.makeNewName(originalName);
		
		String datePath=Utils.makeDir(uploadPath);   //\2020\07\09   추가 두번째날(해당날자에 대응되는 폴더에 저장)
		
		File target = new File(uploadPath+datePath, newName );// 추가 두번째날(해당날자에 대응되는 폴더에 저장)
		FileCopyUtils.copy(file.getBytes(), target);//target의 자리에는 file객체 대신 문자열을 직접 입력해도 상관없다.
		
		
		boolean isimgFile=isImg(originalName);
		
		if(isimgFile) {
		return	makeThubnail(uploadPath,datePath,newName);
		}else {
			String beforChangeName=datePath+File.separator+newName;
		return	beforChangeName.replace(File.separatorChar, '/');  //=>바꾸기   왜 바꾸는지????브라우저 URL에서 사용하는건 '/'이기 때문에
		}
		
		
	
	}
	
	
	
	public static String makeThubnail(String uploadPath,String datePath,String newName) throws Exception {
		
		File f1=new File(uploadPath+datePath,newName);
		BufferedImage sourceImg=ImageIO.read(f1);
		BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT,100);//100크기로 만들겠다.
		
		String thumbnailName=uploadPath+datePath+File.separator+"s_"+newName;//썸네일 이름
		File newFile=new File(thumbnailName);//썸네일 파일
		
		int idx=newName.lastIndexOf(".");  //파일 타입 알아내기위해
		String format=newName.substring(idx+1).toUpperCase();
		ImageIO.write(destImg,format,newFile);//ImageIO.write()이용하여 이미지 파일을 생성 write(RenderedImage im, String formatName, File output)


		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');//브라우저에서 인식할수 있는 형태로 =>바꾸기 !  왜 바꾸는지????브라우저 URL에서 사용하는건 '/'이기 때문에
		
	}
	
	
	public static boolean isImg(String filename) {
		
		int idx=filename.lastIndexOf(".");
		String  format=filename.substring(idx+1);
		
		//spring famewor 의 mediatype
		
		
		/*
		 * Map<String,MediaType>map=new HashMap<String,MediaType>();
		 * map.put("JPEG",MediaType.IMAGE_JPEG); map.put("JPG",MediaType.IMAGE_JPEG);
		 */
		 
		
		
		  Map<String, MediaType> map = new HashMap<String, MediaType>();
		  
		 map.put("JPG", MediaType.IMAGE_JPEG);
		  
		  map.put("JPEG", MediaType.IMAGE_JPEG);
		  
		  map.put("PNG", MediaType.IMAGE_PNG);
		  
		  map.put("GIF", MediaType.IMAGE_GIF);
		 
		
		MediaType mType=map.get(format.toUpperCase());
		
		if(mType!=null) {
			return true;
		}else {
			return false;
			
		}
		
		
		
	}
	
	
	
	
	public static String makeNewName(String originalName) {
		UUID uid=UUID.randomUUID();
		String newName=uid.toString()+"_"+ originalName;
		
		return newName;
	}

	public static String toKor(String msg) {
		try {
			return new String(msg.getBytes("8859_1"),"UTF-8");	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}





	public static MediaType getMediaType(String format) {
		  Map<String, MediaType> map = new HashMap<String, MediaType>();
		  
			 map.put("JPG", MediaType.IMAGE_JPEG);
			  
			  map.put("JPEG", MediaType.IMAGE_JPEG);
			  
			  map.put("PNG", MediaType.IMAGE_PNG);
			  
			  map.put("GIF", MediaType.IMAGE_GIF);
			 
			
			MediaType mType=map.get(format.toUpperCase());
		return mType;
	}
}
