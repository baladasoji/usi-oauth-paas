var hash = false;
var jwtb = false;
var id_token;
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

}


function populateTokens()
{

checkHash();
      document.getElementById("fridtoken").innerHTML = id_token;
      jwtb="Bearer "+id_token;


      // Call the Booking API
      bkgXMLReq = new XMLHttpRequest();
      bkgXMLReq.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("decodedfrtoken").innerHTML = this.responseText;
          }
        };
      //var vid = document.getElementById("decodedtoken").value;
      bkgXMLReq.open("GET", "https://iam-cdt.maerskline.com/spa/usi", true );
      bkgXMLReq.setRequestHeader("Authorization", jwtb);
      bkgXMLReq.send(null);
}


function callLocationAPI()
{
//jwtb = "Bearer "+id_token ;
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
//	cityXMLReq.open("GET", "https://apit.maerskline.com/maeu/locations/cities?cityprefix=hn", true );
	cityXMLReq.setRequestHeader("Authorization",jwtb );
	cityXMLReq.send(null);
}
