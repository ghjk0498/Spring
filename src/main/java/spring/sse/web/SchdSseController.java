package spring.sse.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import spring.sse.service.SseService;

@RequiredArgsConstructor
@EnableScheduling
@Controller
public class SchdSseController {

	private final SseService sseService;

	@GetMapping("/sse")
	public String getSchedulerSsePage() {
		return "schedulerSsePage.html";
	}

	@GetMapping("/sse-next")
	public String getSchedulerSseNextPage() {
		return "schedulerSseNextPage.html";
	}

	@GetMapping("/sse/subscribe/{id}")
	public SseEmitter subscribe(@PathVariable String id, HttpServletRequest request) {
		SseEmitter sseEmitter = sseService.subscribe(id, request.getRemoteAddr());
		return sseEmitter;
	}

	@Scheduled(cron = "0/15 * * * * *")
	public void callFront() {
		sseService.callTest();
	}

}
