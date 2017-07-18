function goToAnywhereWorksSignIn() {
	const clientId = '29354-ad35e115205caf42fd0c391bf226ea70';
	const redirectUrl = "http://localhost:8888/oauth/callback";
	window.location.href = 'https://access.anywhereworks.com/o/oauth2/auth?response_type=code&client_id='
			+ clientId
			+ '&access_type=offline'
			+ '&scope=awapis.identity&redirect_uri=' + redirectUrl;
}
function getUserDetails() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		xhttp.onload = function() {
			
			
		};
	}
	xhttp.open("get", "", true);
	xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
	

}