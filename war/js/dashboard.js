document.onreadystatechange = function() {
	showDashboard();
};

function showDashboard() {
	getUserDetails();

	getChallengeInfo(0);
	getChallengeInfo(4);
	getChallengeInfo(12);
}
const
monthNames = [ "Jan", "Feb", "Mar", "Apl", "May", "June", "July", "Aug", "Sep","Oct", "Nov", "Dec" ];

function getUserDetails() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		var state = xhttp.readyState;
		if (state == 0 || state == 1 || state == 2 || state == 3) {
			/*document.getElementById('loader-4').style.display = 'inline-block';
			document.getElementById('loader-12').style.display = 'inline-block';*/

		}

		xhttp.onload = function() {
			/*document.getElementById('loader-4').style.display = 'none';
			document.getElementById('loader-12').style.display = 'none';*/
			try {
				var userDetail = JSON.parse(xhttp.responseText);
				document.getElementById('color_for_overview').classList.add('gray');

				show(userDetail);
				
			} catch (err) {
				document.getElementById('error-4').innerHTML = err.message;
			}

		};
	}
	// xhttp.open("GET","
	// https://full-learn.appspot.com/api/learn/stats/userId/25c01d98-46f7-408a-87b1-e0339551a5ed",true);
	xhttp.open("GET", '/user_stats', true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}

function show(userDetail) {

	fourWeekAvg = document.getElementById('fourWeekAvg').innerHTML = showTime(userDetail.fourWeekAvg);

	twelveWeekAvg = document.getElementById('twelveWeekAvg').innerHTML = showTime(userDetail.twelveWeekAvg);

}

function showTime(givenMins) {
	if (isNaN(givenMins)) {
		return '';
	}
	const minsPerHour = 60;
	let respString = '';
	var hours = Math.floor(givenMins / minsPerHour);
	var mins = givenMins % minsPerHour;
	if (hours > 0) {
		respString = hours + 'hrs ';
	}
	if (mins > 0) {
		respString += mins + 'mins';
	} else {
		respString = mins + ' mins'
	}
	return respString;
}

function getChallengeInfo(week) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		var state = xhttp.readyState;
		if (state == 0 || state == 1 || state == 2 || state == 3) {
			document.getElementById('loader_' + week).style.display = 'inline-block';

		}
		xhttp.onload = function() {
			document.getElementById('loader_' + week).style.display = 'none';
			try {
				var userDetails = JSON.parse(xhttp.responseText);
				console.log(userDetails);
				getDetails(userDetails, week);
				changeColor(userDetails, week);
			} catch (err) {
				document.getElementById('error-0').innerHTML = err.message;
				console.error(err.stack);

			}

		};

	}
	xhttp.open("GET", '/challenge?week=' + week, true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}
function getStartDate(startDate, endYear) {
	var dateStart = startDate.getDate();
	var startMonth = monthNames[startDate.getMonth()];
	var startYear = startDate.getFullYear();
	var startDateString = '';
	if (endYear == startYear) {
		startDateString = dateStart + ' ' + startMonth;
		return startDateString;
	} else {
		startDateString = dateStart + ' ' + startMonth + ' ' + startYear;
		return startDateString;
	}

}
function getEndDate(endDate) {
	var dateEnd = endDate.getDate();
	var endMonth = monthNames[endDate.getMonth()];
	var endYear = endDate.getFullYear();
	var endDateString = dateEnd + ' ' + endMonth + ' ' + endYear;
	return endDateString;
}
function getDetails(userDetails, week) {
	try {
		var startDate = new Date(userDetails.startDate);
		var endDate = new Date(userDetails.endDate);
		var startDateString = getStartDate(startDate, endDate.getFullYear());
		var endDateString = getEndDate(endDate);
		if ((startDate.getDate() == endDate.getDate())
				&& (monthNames[startDate.getMonth()] == monthNames[endDate
						.getMonth()])
				&& (startDate.getFullYear() == endDate.getFullYear())) {
			var date = "Today";
		} else {
			var date = startDateString + " - " + endDateString;
		}
		document.getElementById('date-' + week).innerHTML = date;
		thisWeek = document.getElementById('change_time_' + week).innerHTML = showTime(userDetails.minutes);

	} catch (err) {
		console.error('Error while building the date : ' + err.stack);
	}
}
function changeColor(userDetails, week) {

	document.getElementById('color_for_' + week).classList.add(getColor(userDetails.minutes));

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
