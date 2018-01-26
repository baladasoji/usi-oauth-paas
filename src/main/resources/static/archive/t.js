var hash = false;
var jwtb = false;
var id_token;
var access_token;
var token_type;
var state;
var hash;
var XMLReq;
var bkgXMLReq;
var invXMLReq;
var cpXMLReq;
var cgXMLReq;
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


function getVendorInvoice()
{

checkHash();
      document.getElementById("idtoken").innerHTML = id_token;
      document.getElementById("accesstoken").innerHTML = access_token;
      document.getElementById("tokentype").innerHTML = token_type;
      document.getElementById("state").innerHTML = state;
/*dd
jwtb="Bearer "+access_token;
XMLReq = new XMLHttpRequest();
XMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("usitoken").innerHTML = this.responseText;
    }
  };
//var vid = document.getElementById("decodedtoken").value;


XMLReq.open("GET", "https://autht.maerskline.com/usi", true );
XMLReq.setRequestHeader("Authorization", jwtb);
XMLReq.send(null);

bkgXMLReq = new XMLHttpRequest();
bkgXMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("booking").innerHTML = this.responseText;
    }
  };
//var vid = document.getElementById("decodedtoken").value;
bkgXMLReq.open("GET", "https://api195331live.gw-us-east.akana.com/booking", true );
bkgXMLReq.setRequestHeader("Authorization", jwtb);
bkgXMLReq.send(null);

invXMLReq = new XMLHttpRequest();
invXMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("invoices").innerHTML = this.responseText;
    }
  };
//var vid = document.getElementById("decodedtoken").value;
invXMLReq.open("GET", "https://api195331live.gw-us-east.akana.com/invoices", true );
invXMLReq.setRequestHeader("Authorization", jwtb);
invXMLReq.send(null);

cpXMLReq = new XMLHttpRequest();
cpXMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("cityprefix").innerHTML = this.responseText;
    }
    else if (this.readyState == 4 && this.status == 403) {
      document.getElementById("cityprefix").style.color="red"
      document.getElementById("cityprefix").innerHTML = this.responseText;
    }
  };
//var vid = document.getElementById("decodedtoken").value;
cpXMLReq.open("GET", "https://api188190live.gw-eu-east.akana.com/MAEU/locations/cities?cityprefix=az", true );
cpXMLReq.setRequestHeader("Authorization", jwtb);
cpXMLReq.send(null);


cgXMLReq = new XMLHttpRequest();
cgXMLReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("citygeo").innerHTML = this.responseText;
    }
    else if (this.readyState == 4 && this.status == 403) {
      document.getElementById("citygeo").innerHTML = this.responseText;
      document.getElementById("citygeo").style.color="red"
    }
  };
//var vid = document.getElementById("decodedtoken").value;
cgXMLReq.open("GET", "https://api188190live.gw-eu-east.akana.com/MAEU/locations/details/0QU63OKMO5YN0", true );
cgXMLReq.setRequestHeader("Authorization", jwtb);
cgXMLReq.send(null);
*/
}


function callFrAuthenticate()
{
usiidtoken = document.getElementById("idtoken").innerHTML;  
frXMLReqReq = new XMLHttpRequest();
frXMLReqReq.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("frauth").innerHTML = this.responseText;
    }
    else if (this.readyState == 4 && this.status == 403) {
      document.getElementById("frauth").innerHTML = this.responseText;
      document.getElementById("frauth").style.color="red"
    }
  };
//var vid = document.getElementById("decodedtoken").value;
frXMLReqReq.open("POST", "http://iam-cdt.maerskline.com/openam/json/realms/maersk-users/authenticate?authIndexType=module&authIndexValue=openidmodule", true );
frXMLReqReq.setRequestHeader("oidc-id-token", usiidtoken);
frXMLReqReq.send(null);
}
