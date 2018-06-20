var x = document.getElementsByTagName("li");
var y = document.getElementsByClassName("click");

for(var i=0; i<x.length; i++){
	if(x[i].contains("click")) {
		x[i].addEventListener("click", alert1);
	}
	else {
		x[i].addEventListener("click", alert2);
	}
	
}
function alert1(){
	window.alert("Nr de li-uri: "+y.length);
}
function alert2(){
	window.alert("NU");
}

window.onclick = myFunctionButon;
var nr = 0;

buton = document.createElement("button");
buton.style.top = "100px";
function myFunctionButon(event) {
	nr++;
	buton.innerHTML = nr;
	document.body.appendChild(buton);
	buton.style.position="absolute";
	buton.style.top = "100px";
	buton.style.left = "100px";
	var a = event.key;
	buton.style.left = event.pageX+"px";
	buton.style.top = event.pageY+"px";
	setTimeout(function() {
    buton.remove();}, 10000);
}