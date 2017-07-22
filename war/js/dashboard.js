document.onreadystatechange = function() {
	showDashboard();
};

function showDashboard() {
	getUserDetails();
	getChallengeInfo();
}

function getUserDetails() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		var state = xhttp.readyState;
		if (state == 0 || state == 1 || state == 2 || state == 3) {
			document.getElementById('loader-4').style.display = 'inline-block';
			document.getElementById('loader-12').style.display = 'inline-block';


		} 
		
		xhttp.onload = function() {
			document.getElementById('loader-4').style.display = 'none';
			document.getElementById('loader-12').style.display = 'none';

			var userDetail = JSON.parse(xhttp.responseText);
			show(userDetail);
		};
	}
	// xhttp.open("GET","
	// https://full-learn.appspot.com/api/learn/stats/userId/25c01d98-46f7-408a-87b1-e0339551a5ed",true);
	xhttp.open("GET", '/user_stats', true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}

function show(userDetail) {

	fourWeekAvg = document.getElementById('change_time_4').innerHTML = showTime(userDetail.fourWeekAvg * 4);
	twelveWeekAvg = document.getElementById('change_time_12').innerHTML = showTime(userDetail.twelveWeekAvg * 12);

}

function showTime(givenMins) {
	if (isNaN(givenMins)) {
		return '';
	}
	const
	minsPerHour = 60;
	let
	respString = '';
	var hours = Math.floor(givenMins / minsPerHour);
	var mins = givenMins % minsPerHour;
	if (hours > 0) {
		respString = hours + 'hrs ';
	}
	if (mins > 0) {
		respString += mins + 'mins';
	}
	return respString;
}
function changeColor(fourWeekAvg) {
	document.getElementById('color_for_four').innerHTML = getColor(fourWeekAvg);

}
function getColor(avgMins) {
	if (avgMins > 150) {
		return 'green';
	} else if (avgMins <= 150 && avgMins >= 75) {
		return 'orange';
	} else {
		return 'red';
	}
}
function getChallengeInfo() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		var state = xhttp.readyState;
		if (state == 0 || state == 1 || state == 2 || state == 3) {
			document.getElementById('loader').style.display = 'inline-block';

		} 
		xhttp.onload = function() {
			document.getElementById('loader').style.display = 'none';
			var userDetails = JSON.parse(xhttp.responseText);
			console.log(userDetails);
			getDetails(userDetails);
		};
		
	}
	xhttp.open("GET", '/challenge', true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}

function getDetails(userDetails) {

	thisWeek = document.getElementById('change_time_0').innerHTML = showTime(userDetails.minutes);

}
