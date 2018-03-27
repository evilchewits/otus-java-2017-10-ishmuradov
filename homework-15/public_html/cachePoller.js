/**
 * WS sub-protocol format:
 * Client request: getStatistics
 * Server response: {
 *	 "DB save count": 2000,
 *   "DB load count": 47,
 *   "Cache misses" : 47,
 *   "Cache hits": 108
 * }
 */

socket.onmessage = function(event) {
	let data = JSON.parse(event.data);
	for ( let key in data) {
		document.querySelector('[data-statistics-key="' + key + '"]').textContent = data[key];
	}
};

setInterval(function() {
	socket.send("getStatistics");
}, pollingInterval);