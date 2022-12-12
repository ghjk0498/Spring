package spring.sse.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseService {

	private final ServletContext context;

	public SseEmitter subscribe(String id) {
		Map<String, SseEmitter> sseEmitterMap = (Map<String, SseEmitter>) context.getAttribute("sseEmitterMap");
		if (sseEmitterMap == null) {
			sseEmitterMap = new ConcurrentHashMap<>();
		}

//		if (sseEmitterMap.containsKey(id)) {
//			return sseEmitterMap.get(id);
//		}

		SseEmitter emitter = new SseEmitter(100000L);
		emitter.onCompletion(() -> {
			Map<String, SseEmitter> sseEmitterMap2 = (Map<String, SseEmitter>) context.getAttribute("sseEmitterMap");
			sseEmitterMap2.remove(id);
		});

		try {
			emitter.send("init");
		} catch (IOException e1) {
			log.error("SSE IOException");
		}

		sseEmitterMap.put(id, emitter);
		context.setAttribute("sseEmitterMap", sseEmitterMap);

		return emitter;
	}

	public void callTest() {
		Map<String, SseEmitter> sseEmitterMap = (Map<String, SseEmitter>) context.getAttribute("sseEmitterMap");
		if (sseEmitterMap != null) {
			sseEmitterMap.forEach((id, emitter) -> {
				try {
					emitter.send("test data[id=" + id + "]");
				} catch (Exception e) {
					emitter.completeWithError(e);
				}
			});
		}

		log.info("call sse subscriber");
	}

}
