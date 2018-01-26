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
      document.getElementById("idtoken").innerHTML = id_token;
      document.getElementById("accesstoken").innerHTML = access_token;
      document.getElementById("tokentype").innerHTML = token_type;
      document.getElementById("state").innerHTML = state;
}


function callFrAuthenticate()
{
usiidtoken = document.getElementById("idtoken").innerHTML;
frXMLReqReq = new XMLHttpRequest();
frXMLReqReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("frauth").innerHTML = this.responseText;
      sessionStorage.frtoken = this.responseText;
    }
    else if (this.readyState == 4 && this.status == 403) {
      document.getElementById("frauth").innerHTML = this.responseText;
      document.getElementById("frauth").style.color="red"
    }
  };
//var vid = document.getElementById("decodedtoken").value;
frXMLReqReq.open("POST", "https://iam-cdt.maerskline.com/openam/json/realms/maersk-users/authenticate?authIndexType=module&authIndexValue=UsiJwtAuthentication", true );
frXMLReqReq.setRequestHeader("oidc_id_token", usiidtoken);
//frXMLReqReq.withCredentials=true;
frXMLReqReq.send(null);
}



function getFrToken()
{

csrf = JSON.parse(sessionStorage.frtoken).tokenId;
frXMLOAuthReq = new XMLHttpRequest();
frXMLOAuthReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      //document.getElementById("fraccess").innerHTML = this.responseText;
      console.log (" Inside 200" + this.getResponseHeader("location"));
      console.log (" ResponseText " + this.responseText);
      //  window.location = this.getResponseHeader("location");
    }
    else if (this.status == 302) {
      console.log (" Inside 302" + this.getResponseHeader("location"));
      window.location = this.getResponseHeader("location");
      }
    else if (this.readyState == 4 && this.status == 403) {
      document.getElementById("fraccess").innerHTML = this.responseText;
      document.getElementById("fraccess").style.color="red"
    }
  };

//var vid = document.getElementById("decodedtoken").value;
frXMLOAuthReq.open("POST", "https://iam-cdt.maerskline.com/openam/oauth2/realms/maersk-users/authorize", true);
frXMLOAuthReq.withCredentials=true;
frXMLOAuthReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//frXMLOAuthReq.setRequestHeader("oidc_id_token", usiidtoken);
frXMLOAuthReq.send('scope=openid%20profile&redirect_uri=https%3A%2F%2Fiam-cdt.maerskline.com%2Fspa%2Findex.html&csrf='+csrf+'&decision=allow&response_type=id_token%20token&client_id=agent101&nonce=hkjcasdblscertsdgf');
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
                  'redirect_uri': 'https://iam-cdt.maerskline.com/spa/index.html',
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


  function callLocationAPI()
{
  fridtoken = document.getElementById("idtoken").innerHTML;
jwtb = "Bearer "+fridtoken ;
cityXMLReq = new XMLHttpRequest();
cityXMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("cities").innerHTML = this.responseText;
    document.getElementById("cities").className='authrest'
    }
  else if (this.readyState == 4) {
    document.getElementById("cities").innerHTML = this.responseText;
    document.getElementById("cities").className='noauthrest'
  }
  };
	 cityXMLReq.open("GET", "https://api159165live.gw-us-east.akana.com/", true);
//	cityXMLReq.open("GET", "https://api205063live.gateway.akana.com/maeu/locations/cities?cityprefix=az", true );
	cityXMLReq.setRequestHeader("Authorization",jwtb );
	cityXMLReq.send(null);
}
