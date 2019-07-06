function sendRequestToServer(){
	document.getElementById("actionBtn").disabled = true;
	try
	{
		var httpMethod = "GET";
	    var xhr = null;
	    if (window.ActiveXObject) {
	        xhr = new ActiveXObject("Microsoft.XMLHTTP");   //handling done for IE
	    }
	    else
	        xhr = new XMLHttpRequest();

	    var baseUrl = "http://localhost:8080/getResults";
	    
	    console.log("sendRequestToServer url: " + baseUrl);

	    xhr.open(httpMethod, baseUrl, true);

	 	   xhr.onreadystatechange = function() {
	        if (xhr.readyState !== 4) {
	            return;
	        }
	      
	        console.log("Request response is " + xhr.status +  "and request failed due to " + xhr.response);
	        if(xhr.status="200")
	        document.getElementById("actionBtn").disabled = false;
	        var userData = JSON.parse(xhr.response);
	        show(userData);
	        
	        
	    };

	    xhr.send(null);
	}
	catch(e){
	    log.error(finalUrl+" sendRequestToOneXPortal failed: "+e.message);
	}
	
}