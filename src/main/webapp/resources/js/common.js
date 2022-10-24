function getSseEventSource(id) {
	const eventSource = new EventSource("/sse/subscribe/" + id);

	eventSource.onmessage = event => {
		console.log(new Date() + "\nDefault: " + event.data);
	}
	eventSource.onerror = error => {

		if (error.eventPhase == eventSource.CLOSED) {
			console.log("EventSource closed");
			eventSource.close();
		} else {
			console.log(error);
		}

//		location.reload();
	}

	return eventSource;
}