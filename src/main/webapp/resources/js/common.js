function getSseEventSource(id) {
	const eventSource = new EventSource("http://localhost:8080/sse/subscribe/" + id);

	eventSource.onmessage = event => {
		console.log(new Date() + "\nDefault: " + event.data);
	}
	eventSource.onerror = error => {
		console.log(error);

//		location.reload();
	}

	return eventSource;
}