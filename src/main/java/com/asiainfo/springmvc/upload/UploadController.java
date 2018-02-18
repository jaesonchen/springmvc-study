package com.asiainfo.springmvc.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月26日  下午9:46:58
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
public class UploadController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/upload")
	public String upload() {
		logger.debug("upload() is executed!");
		return "upload";
	}
	
	@RequestMapping("/upload/submit")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request, Model model) {
		
		logger.debug("upload submit()  is executed!");
		if (file.isEmpty()) {
			return "upload";
		}
		
		String path = request.getSession().getServletContext().getRealPath("upload");
		logger.debug("upload path={}", path);
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
		try {
            file.transferTo(targetFile);
        } catch (Exception ex) {
            logger.error("error on transfer to target folder:{}\n", path, ex);
        }
        model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
        model.addAttribute("fileName", fileName);
		return "filelist";
	}
	
	@RequestMapping("/download")
	public void download(@RequestParam("name") String fileName, 
			HttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("download()  is executed, filename={}", fileName);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		String path = request.getSession().getServletContext().getRealPath("upload");
		logger.debug("download path={}", path);
		
		InputStream in = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			in = new FileInputStream(new File(path + File.separator + fileName));
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception ex) {
			logger.error("error on transfer to client\n", ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {}
		}
	}
}
