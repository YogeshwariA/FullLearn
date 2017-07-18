function showFeeds() {

	document.getElementById('feedsDiv').style.display = 'block';
}

function getUserDetails() {
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		xhttp.onload = function() {
			console.log("hai");
			var userDetail = JSON.parse(xhttp.responseText);
			console.log(userDetail);
		};
	}
	xhttp.open("GET"," https://full-learn.appspot.com/api/learn/stats/userId/25c01d98-46f7-408a-87b1-e0339551a5ed",true);
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send();
}
