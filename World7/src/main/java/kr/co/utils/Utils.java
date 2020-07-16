package kr.co.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static String saveFile(String originalName,MultipartFile file)throws Exception {
		
		String newName = Utils.makeNewName(originalName);	
		File target = new File("C:"+File.separator+"upload", newName );
		FileCopyUtils.copy(file.getBytes(), target);//target의 자리에는 file객체 대신 문자열을 직접 입력해도 상관없다.
		return newName;		
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
}
