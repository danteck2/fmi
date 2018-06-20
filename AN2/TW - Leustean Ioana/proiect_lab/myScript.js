var c = 0;
var t;
var timer_is_on = 0;

window.onkeydown = myFunctionButon;
var nr = 0;

buton = document.createElement("button");
buton.style.top = "100px";
function myFunctionButon(event) {
	nr++;
	buton.innerHTML = event.key;
	document.getElementById("form").appendChild(buton);
	//buton.style.position="absolute";
	buton.style.top = "100px";
	buton.style.left = "100px";
	var a = event.key;
	if(a=="ArrowDown") {
		buton.style.top = parseInt(buton.style.top)+10+"px";
	}
	
	buton.style.left = event.pageX+"px";
	buton.style.top = event.pageY+"px";

}
function timedCount() {
    c = c + 1;
    t = setTimeout(function(){ timedCount() }, 1000);
}

function startCount() {
    if (!timer_is_on) {
        timer_is_on = 1;
        timedCount();
    }
}
function myFormAnswer() {
	clearTimeout(t);
    timer_is_on = 0;
	nume = document.getElementById("nume").value;
	prenume = document.getElementById("prenume").value;
	email = document.getElementById("email").value;
	mesaj = document.getElementById("mesaj").value;
   
   window.alert("Am primit \nNume: "+nume+" \nPrenume:"+prenume+" \nEmail: "+email+" \nTimp: "+c+"s");
}
function myStyle() {
	var x = document.getElementById("body");
	x.style.backgroundColor="#cac9c9";
}
function showHint(str) {
  var xhttp;
  if (str.length == 0) { 
    document.getElementById("txtHint").innerHTML = "";
    return;
  }
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("txtHint").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "gethint.php?q="+str, true);
  xhttp.send();   
}

$(document).ready(function(){
    $("#suggest").click(function(){
        $(this).fadeOut("slow");
    });
});

function move() {
  var elem = document.getElementById("myBar");   
  var width = 0;
  var id = setInterval(frame, 50);
  function frame() {
    if (width == 100) {
      clearInterval(id);
    } else {
      width++; 
      elem.style.width = width + '%'; 
    }
  }
}