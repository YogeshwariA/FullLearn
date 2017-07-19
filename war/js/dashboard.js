function showFeeds() {

	document.getElementById('feedsDiv').style.display = 'block';
}

function getUserDetails() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		xhttp.onload = function() {
			console.log("hai");
			var userDetail = JSON.parse(xhttp.responseText);
			show(userDetail);
		};
	}
	// xhttp.open("GET","
	// https://full-learn.appspot.com/api/learn/stats/userId/25c01d98-46f7-408a-87b1-e0339551a5ed",true);
	xhttp.open("GET", "/user_stats", true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}

function show(userDetail) {
	console.log(userDetail.fourWeekAvg);
	fourWeekAvg = document.getElementById("time_change1").innerHTML = showTime(userDetail.fourWeekAvg);
	twelveWeekAvg = document.getElementById("time_change2").innerHTML = showTime(userDetail.twelveWeekAvg);
}

function showTime(givenMins) {
	if(isNaN(givenMins)) {
		return '';
	}
	const minsPerHour = 60;
	var hours= Math.floor(givenMins / minsPerHour);
	var mins = givenMins % minsPerHour;
	
	return hours + 'hrs ' + mins + 'mins';
}

function getColor(avgMins) {
	if(avgMins>150){
		return "green";
	}
	else if(avgMins<=150 && avgMins>=75){
		return "orange";
	}
	else{
		return "red";
	}	
}
