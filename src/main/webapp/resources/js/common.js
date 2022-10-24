function getSseEventSource(id) {
	const eventSource = new EventSource("/sse/subscribe/" + id);

	eventSource.onmessage = event => {
		console.log(new Date() + "\nDefault: " + event.data);
	}
	eventSource.onerror = error => {
		console.log(error);
		if (error.eventPhase == eventSource.CLOSED) {
			es.close();
		}

//		location.reload();
	}

	return eventSource;
}