window.onkeydown = myFunctionButon;
var nr = 0;

buton = document.createElement("button");
buton.style.top = "100px";
function myFunctionButon(event) {
	nr++;
	buton.innerHTML = event.key;
	document.body.appendChild(buton);
	buton.style.position="absolute";
	buton.style.top = "100px";
	buton.style.left = "100px";
	var a = event.key;
	if(a=="ArrowDown") {
		buton.style.top = parseInt(buton.style.top)+10+"px";
	}
	
	buton.style.left = event.pageX+"px";
	buton.style.top = event.pageY+"px";

}