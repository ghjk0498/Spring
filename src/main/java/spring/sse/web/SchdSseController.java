package spring.sse.web;

import java.util.Map;

import javax.servlet.ServletContext;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import spring.sse.service.SseService;

@RequiredArgsConstructor
@EnableScheduling
@Controller
public class SchdSseController {

	private final ServletContext context;

	private final SseService sseService;

	@GetMapping("/sse")
	public String getSchedulerSsePage() {
		return "schedulerSsePage.html";
	}

	@GetMapping("/sse-next")
	public String getSchedulerSseNextPage() {
		return "schedulerSseNextPage.html";
	}

	@GetMapping(value = "/sse/subscribe/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(@PathVariable String id) {
		SseEmitter sseEmitter = sseService.subscribe(id);
		return sseEmitter;
	}

	@GetMapping(value = "/sse/subscribe2/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe2(@PathVariable String id) {
		SseEmitter sseEmitter = sseService.subscribe(id);
		return sseEmitter;
	}

	@Scheduled(cron = "0/15 * * * * *")
	public void callFront() {
		sseService.callTest();
	}
	@GetMapping("/callTest")
	@ResponseBody
	public String callTest() {
		sseService.callTest();
		return "call";
	}

	@GetMapping("/sseCheck")
	@ResponseBody
	public String sseCheck() {
		Map<String, SseEmitter> sseEmitterMap = (Map<String, SseEmitter>) context.getAttribute("sseEmitterMap");
		String result = String.valueOf(sseEmitterMap.size()) + "\n" + sseEmitterMap.toString();
		return result;
	}

}
