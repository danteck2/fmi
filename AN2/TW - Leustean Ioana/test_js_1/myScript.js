function myFunction() {
	nume = document.getElementById("nume").value;
	prenume = document.getElementById("prenume").value;
	email = document.getElementById("email").value;
	mesaj = document.getElementById("mesaj").value;
   
   window.alert("Nume: "+nume+" \nPrenume:"+prenume+" \nEmail: "+email+" \nMesaj: "+mesaj);
}
function selectColor() {
	
	var x = document.getElementById("select");
	var y = x.options;
	
   if(y[0].selected == true) {
	   var x = document.getElementsByTagName("p");
		//for(int i=0; i<x.length; i++)
			x[0].style.color="#f00";
		x[1].style.color="#f00";
		x[2].style.color="#f00";
		x[3].style.color="#f00";
		x[4].style.color="#f00";
		
   }
	

   //window.alert(y[0].value+y[1].value+y[2].value);
   
}
$(document).ready(function(){
    $("p").click(function(){
        $(this).hide();
    });
});