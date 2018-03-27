$(document).ready(function() {
	document.getElementById('loginContent').style.display='block';
	$('.nav-tabs > li > a').click(function() {
		$('.nav-tabs > li.active').removeClass('active');
		$(this).parent().addClass('active');
		if($(this).html() == 'Register')
		{
			$(".register").css({display:'block'});
		}
		else
		{
			$(".register").css({display:'none'});
		}
	});
	$("#loginbtn").click(function(){
		var username=$("#usernametxt").val();
		var password=$("#passwordtxt").val();
		if(username != undefined && password != undefined && username == 'admin' && password == 'admin')
			{
			window.location.replace("http://localhost:11958/onlinetestsystem/examinar.html");
			}
		else
			{
			alert("Invalid Login");
			}
	});
	$("#writeExam").click(function(){
			window.location.replace("http://localhost:11958/onlinetestsystem/exampage.html");
		
	});
	
	
});