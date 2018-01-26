var hash = false;
var jwtb = false;
var id_token;
var access_token;
var token_type;
var state;
var hash;
var bkgXMLReq;
var invXMLReq;
//checkHash();

function checkHash(){
    if(window.location.hash != hash) {
        hash = window.location.hash;
	hash = hash.substring(1);
        processHash(hash);
    } t=setTimeout("checkHash()",400);
}

function GetQueryStringParams(sParam)
{
    var sFragment = hash;
    var sURLVariables = sFragment.split('&');

    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}

function processHash(hash){

id_token = GetQueryStringParams("id_token");
access_token = GetQueryStringParams("access_token");
token_type = GetQueryStringParams("token_type");
state = GetQueryStringParams("state");

}


function callAPI()
{

if (this.readyState == 4 && this.status == 200) {
          document.getElementById("bol").innerHTML = this.responseText;
          document.getElementById("bol").className='authrest'  
        }
        else if (this.readyState == 4 && this.status == 403) {
          document.getElementById("bol").innerHTML = this.responseText;
          document.getElementById("bol").className='noauthrest'  
        }

    checkHash();
      document.getElementById("idtoken").innerHTML = id_token;
      document.getElementById("accesstoken").innerHTML = access_token;
//      document.getElementById("tokentype").innerHTML = token_type;
//      document.getElementById("state").innerHTML = state;

	jwtb="Bearer "+access_token;


	// Call the Booking API
	bkgXMLReq = new XMLHttpRequest();
	bkgXMLReq.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("booking").innerHTML = this.responseText;
		  document.getElementById("booking").className='authrest'  
	    }
		else if (this.readyState == 4 && this.status == 403) {
		  document.getElementById("booking").innerHTML = this.responseText;
		  document.getElementById("booking").className='noauthrest'  
		}
	  };
	//var vid = document.getElementById("decodedtoken").value;
	bkgXMLReq.open("GET", "https://api195331live.gateway.akana.com/booking", true );
	bkgXMLReq.setRequestHeader("Authorization", jwtb);
	bkgXMLReq.send(null);

	// Call the Invoices API

	invXMLReq = new XMLHttpRequest();
	invXMLReq.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("invoices").innerHTML = this.responseText;
		  document.getElementById("invoices").className='authrest'  
	    }
		else if (this.readyState == 4 && this.status == 403) {
		  document.getElementById("invoices").innerHTML = this.responseText;
		  document.getElementById("invoices").className='noauthrest'  
		}
	  };
	//var vid = document.getElementById("decodedtoken").value;
	invXMLReq.open("GET", "https://api195331live.gateway.akana.com/invoices", true );
	invXMLReq.setRequestHeader("Authorization", jwtb);
	invXMLReq.send(null);

}
