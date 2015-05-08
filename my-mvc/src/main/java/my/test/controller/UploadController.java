package my.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@RequestMapping("upload")
@Controller
public class UploadController {
	
	@RequestMapping("file")
	public void uploadFile(@RequestParam("file")MultipartFile file){
		
		System.out.println(file.getSize());
	}
}
