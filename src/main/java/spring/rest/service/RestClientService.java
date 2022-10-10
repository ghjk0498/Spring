package spring.rest.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.rest.mapper.RestMapper;

@Service
public class RestClientService {

	private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);
	
	@Autowired
	ServletContext servletContext;
	
	public List<RestVO> getAllRestVO(String uri) throws IOException {
		Mono<List<RestVO>> mono = WebClient.builder()
				.build()
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RestVO>>() {})
				.log();
		
		List<RestVO> restVOList = mono.block();
		logger.info(restVOList.toString());
		return restVOList;
	}
	
	public RestVO getRestVOById(String uri) throws IOException {
		Mono<RestVO> mono = WebClient.builder()
				.build()
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(RestVO.class);
		RestVO restVO = mono.block();
		return restVO;
	}
	
	public void postRestVO(String uri, RestVO restVO) {
		WebClient.builder()
			.build()
			.post()
			.uri(uri)
			.body(BodyInserters.fromValue(restVO))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(RestVO.class)
			.block();
	}

	public void putRestVO(String uri, RestVO restVO) {
		WebClient.builder()
			.build()
			.put()
			.uri(uri)
			.body(BodyInserters.fromValue(restVO))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(RestVO.class)
			.block();
	}

	public void deleteRestVO(String uri) {
		WebClient.builder()
			.build()
			.delete()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(RestVO.class)
			.block();
	}

//	
//	public void imageDownload(String title, String imgUrl) throws IOException {
//		URL url = new URL(imgUrl);
//		InputStream in = new BufferedInputStream(url.openStream());
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		
//		byte[] buf = new byte[1024];
//		int n = 0;
//		while ((n = in.read(buf)) != -1) {
//			out.write(buf, 0, n);
//		}
//		
//		byte[] response = out.toByteArray();
//		out.close();
//		in.close();
//		
//		String imagePath = servletContext.getRealPath("/resources/images");
//		logger.debug("imagePath : " + imagePath);
//		File fileDir = new File(imagePath);
//		if (!fileDir.isDirectory()) {
//			fileDir.mkdirs();
//		}
//		
//		String ext = imgUrl.substring(imgUrl.lastIndexOf("."));
//		File fileData = new File(imagePath, title + ext);
//		if (!fileData.exists()) {
//			FileOutputStream fos = new FileOutputStream(fileData);
//			fos.write(response);
//			fos.close();
//		}
//	}

}
