var hash = false;
var jwtb = false;
var id_token;
var access_token;
var token_type;
var state;
var hash;
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


function populateTokens()
{

checkHash();
      document.getElementById("usitoken").innerHTML = id_token;

      jwtb="Bearer "+id_token;


    	// Call the Booking API
    	bkgXMLReq = new XMLHttpRequest();
    	bkgXMLReq.onreadystatechange = function() {
    	    if (this.readyState == 4 && this.status == 200) {
            document.getElementById("decodedusitoken").innerHTML = this.responseText;
    	    }
    	  };
    	//var vid = document.getElementById("decodedtoken").value;
    	bkgXMLReq.open("GET", "https://iam-cdt.maerskline.com/spa/usi", true );
    	bkgXMLReq.setRequestHeader("Authorization", jwtb);
    	bkgXMLReq.send(null);
}


function callFrAuthenticate()
{
usiidtoken = document.getElementById("usitoken").innerHTML;
frXMLReqReq = new XMLHttpRequest();
frXMLReqReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      sessionStorage.frtoken = this.responseText;
	oauth2SignIn();
    }
  };
//var vid = document.getElementById("decodedtoken").value;
frXMLReqReq.open("POST", "https://iam-cdt.maerskline.com/openam/json/realms/maersk-users/authenticate?authIndexType=module&authIndexValue=UsiJwtAuthentication", true );
frXMLReqReq.setRequestHeader("oidc_id_token", usiidtoken);
//frXMLReqReq.withCredentials=true;
frXMLReqReq.send(null);
}



function oauth2SignIn() {
    var oauth2Endpoint = 'https://iam-cdt.maerskline.com/openam/oauth2/realms/maersk-users/authorize';
    var form = document.createElement('form');
    form.setAttribute('method', 'POST'); // Send as a GET request.
    form.setAttribute('action', oauth2Endpoint);

	var response_type = 'id_token token' ;

csrf = JSON.parse(sessionStorage.frtoken).tokenId;
    // Parameters to pass to OAuth 2.0 endpoint.
    var params = {'client_id': 'agent101',                        //YOUR_CLIENT_ID,
                  'redirect_uri': 'https://iam-cdt.maerskline.com/spa/frindex.html',
                  'scope': 'openid profile',
                  'csrf' : csrf ,
                  'decision' : 'allow',
                  'nonce' : 'asldrfjilkjwerf',
                  'response_type': response_type,
				  };

    // Add form parameters as hidden input values.
    for (var p in params) {
      var input = document.createElement('input');
      input.setAttribute('type', 'hidden');
      input.setAttribute('name', p);
      input.setAttribute('value', params[p]);
      form.appendChild(input);
    }

    // Add form to page and submit it to open the OAuth 2.0 endpoint.
    document.body.appendChild(form);
    form.submit();
  }
