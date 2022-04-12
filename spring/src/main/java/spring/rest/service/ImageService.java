package spring.rest.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import spring.rest.Test;

@Service
public class ImageService {

	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
	
	@Autowired
	ServletContext servletContext;
	
	public void restGet(String url) throws IOException {
		WebClient webClient = WebClient.create();
		Mono<Test> mono = webClient.get()
				 .uri(url)
				 .accept(MediaType.APPLICATION_JSON)
				 .retrieve()
				 .bodyToMono(Test.class);
		
		Test test = mono.block();
		
		imageDownload(test.getTitle(), test.getImage());
	}
	
	public void imageDownload(String title, String imgUrl) throws IOException {
		URL url = new URL(imgUrl);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		int n = 0;
		while ((n = in.read(buf)) != -1) {
			out.write(buf, 0, n);
		}
		
		byte[] response = out.toByteArray();
		out.close();
		in.close();
		
		String imagePath = servletContext.getRealPath("/resources/images");
		logger.debug("imagePath : " + imagePath);
		File fileDir = new File(imagePath);
		if (!fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		
		String ext = imgUrl.substring(imgUrl.lastIndexOf("."));
		File fileData = new File(imagePath, title + ext);
		if (!fileData.exists()) {
			FileOutputStream fos = new FileOutputStream(fileData);
			fos.write(response);
			fos.close();
		}
	}
	
}
