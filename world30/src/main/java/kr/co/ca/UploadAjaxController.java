package kr.co.ca;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.service.BoardService;
import kr.co.utils.Utils;

@Controller
public class UploadAjaxController {

	private String uploadPath="C:"+File.separator+"upload";
	
@Inject
	private BoardService bService;
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
	public List<String> getAttach(@PathVariable("bno") Integer bno){
		
		return bService.getAttach(bno);
		
	}
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/deletefile",method = RequestMethod.POST)
	public String deleteafile(String  filename) {      //filename은 이미지일때 썸네일 이미지임
		System.out.println(filename);
		
		filename.replace('/', File.separatorChar);
		
		//이미지 파일인지 판단
		int idx=filename.lastIndexOf(".");
		String format=filename .substring(idx+1);
		MediaType mType=Utils.getMediaType(format);
		//이미지 파일인지 판단후 파일 원본 이름 만들고 파일 삭제
		if(mType !=null) {
			String pre=filename.substring(0, 12);
			String suf=filename.substring(14);
			
			String oriname=pre+suf;
			
			File oriFile=new File(uploadPath+oriname);
			oriFile.delete();
		}
		
		
		File f=new File(uploadPath+filename);
		f.delete();
		
		bService.deleteAttachByFileName(filename);
		
		
		
		
		return "success";
		
		
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/displayfile",method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayfile(String filename){
		ResponseEntity<byte[]> entity=null;
		
		
		InputStream in=null;
		
		try {
			//미디어 타입인지만 확인하는 것 
			int idx=filename.lastIndexOf(".");
			String format=filename.substring(idx+1);
			
			MediaType mType=Utils.getMediaType(format);
			HttpHeaders headers=new HttpHeaders();
			//stream연결해 주는 코드
			 in=new FileInputStream(uploadPath+filename);
			
			if(mType!=null) {
				headers.setContentType(mType);
			}else {
				idx=filename.indexOf("_");
				String originalName=filename.substring(idx+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\""+new String(originalName.getBytes("utf-8"),"ISO-8859-1"));
				
				
				
				/*
				 * idx=filename.indexOf("_"); String originalname=filename.substring(idx+1);
				 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//
				 * APPLICATION_OCTET_STREAM 다운로드 해주게 하는 마인드 타입
				 * headers.add("Content-Disposition", "atttachment;filename=\"\""+new
				 * String(originalname.getBytes("utf-8"),"ISO-8859-1"));
				 */
			   
			}
			//이미지 소스에 데이타를 넣어줌
			 entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in) ,headers,HttpStatus.OK);
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		}finally {
			try {
				if (in!=null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		return entity;
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/uploadajax",method = RequestMethod.POST,produces="text/plain;charset=utf-8") //한글이 안 깨지고 저장
	public String uploadajax(MultipartHttpServletRequest request) throws Exception {
		
		MultipartFile file=request.getFile("file");
		String originalName=file.getOriginalFilename();
		
		String saveFileName=Utils.saveFile(originalName, file,uploadPath);
		
		
		return saveFileName;
	}
	
	
	
	
	@RequestMapping(value = "/uploadajax",method = RequestMethod.GET)
	public void uploadajax() {
		
	

		
	}
	
	
}
