package spring.sse.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@EnableScheduling
@Controller
public class SchdSseController {

	private final ServletContext context;

	@GetMapping("/sse")
	public String getSchedulerSsePage() {
		return "schedulerSsePage.html";
	}

	@Getter
	@Setter
	class EmitterTuple {
		private String id;
		private SseEmitter emitter;
	}

	@GetMapping("/sse/subscribe/{id}")
	public SseEmitter subscribe(@PathVariable String id) {
		List<EmitterTuple> sseEmitterList = (List<EmitterTuple>) context.getAttribute("sseEmitterList");
		if (sseEmitterList == null) {
			sseEmitterList = new ArrayList<>();
		}

		EmitterTuple tuple = new EmitterTuple();
		tuple.setId(id);
		tuple.setEmitter(new SseEmitter(-1L)); // no timeout
		sseEmitterList.add(tuple);
		context.setAttribute("sseEmitterList", sseEmitterList);

		return tuple.getEmitter();
	}

	@Scheduled(cron = "0/15 * * * * *")
	public void callFront() {
		List<EmitterTuple> sseEmitterList = (List<EmitterTuple>) context.getAttribute("sseEmitterList");
		if (sseEmitterList != null) {
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
