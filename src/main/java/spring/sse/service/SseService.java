package spring.sse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.sse.vo.EmitterVo;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseService {

	private final ServletContext context;

	public SseEmitter subscribe(String id, String ipAddr) {
		List<EmitterVo> sseEmitterList = (List<EmitterVo>) context.getAttribute("sseEmitterList");
		if (sseEmitterList == null) {
			sseEmitterList = new ArrayList<>();
		}

		Optional<EmitterVo> opt = sseEmitterList.stream().filter(e -> e.getId().equals(id) && e.getIpAddr().equals(ipAddr)).findAny();
		log.info(id);
		if (opt.isPresent()) {
			log.info("present");
			return opt.get().getEmitter();
		}

		EmitterVo emitter = new EmitterVo();
		emitter.setId(id);
		emitter.setIpAddr(ipAddr);
		emitter.setEmitter(new SseEmitter(-1L)); // no timeout
		sseEmitterList.add(emitter);
		context.setAttribute("sseEmitterList", sseEmitterList);
		return emitter.getEmitter();
	}

	public void callTest() {
		List<EmitterVo> sseEmitterList = (List<EmitterVo>) context.getAttribute("sseEmitterList");
		if (sseEmitterList != null) {
			log.info(sseEmitterList.toString());

			sseEmitterList.forEach((x) -> {
				String id = x.getId();
				SseEmitter emitter = x.getEmitter();
				try {
					emitter.send("test data[id=" + id + "]");
//					emitter.complete();
				} catch (Exception e) {
					emitter.completeWithError(e);
				}
			});
		}

		log.info("call sse subscriber");
	}

}
