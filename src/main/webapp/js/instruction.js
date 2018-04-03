
var countDownDate = new Date().getTime()+5000;
$(document).ready(function(){
	myFunction();
	var timer=setInterval(function(){myFunction()},1000);
	function myFunction() {
		var now = new Date().getTime();
		var distance = countDownDate - now;
		console.log(distance);
		/*var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));*/
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		$("#timer").html(minutes + "m " + seconds + "s ");

		// If the count down is over, write some text 
		if (distance < 0) {
			clearInterval(timer);
			$("#timer").css({display:'none'});
			$("#startTest").css({display:'inline'});
		
		}

	}
	
	$("#startTest").click(function(){
		window.location.replace("http://localhost:11958/onlinetestsystem/exampage.html");
	});

});

