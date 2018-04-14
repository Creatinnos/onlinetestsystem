$(document).ready(function() {
	document.getElementById('loginContent').style.display='block';
	$("#registerbtn").css({display:'none'});
	$('.nav-tabs > li > a').click(function() {
		$('.nav-tabs > li.active').removeClass('active');
		$(this).parent().addClass('active');
		if($(this).html() == 'Register')
		{
			$("#usernametxt").val("");
			$("#passwordtxt").val("");
			$("#registerbtn").css({display:'inline'});

			$(".register").css({display:'block'});
			$("#loginbtn").css({display:'none'});
		}
		else
		{
			var username=$("#usernametxt").val("admin");
			var password=$("#passwordtxt").val("admin");
			$("#loginbtn").css({display:'inline'});
			$("#registerbtn").css({display:'none'});
			$(".register").css({display:'none'});
		}
	});
	$("#loginbtn").click(function(){
		var username=$("#usernametxt").val();
		var password=$("#passwordtxt").val();
		loginCheck(username,password);
	});
	$("#registerbtn").click(function(){
		register();
	});
	$("#writeExam").click(function(){
		alert(window.location.origin);
		window.location.replace(window.location.origin+"/"+projectName+"/exampage.html");
	});

	function register() {
		var completeArr={}
		if($("#passwordtxt").val()!=undefined && $("#passwordRetypetxt").val()!=undefined 
				&& $("#passwordtxt").val()!=$("#passwordRetypetxt").val())
		{
			alert("password not matching");
		}
		else
		{
			var username=$("#usernametxt").val();
			var password=$("#passwordtxt").val();
			completeArr["userName"] =username;
			completeArr["password"] =password;
			completeArr["email"] =$("#emailtxt").val();
			completeArr["phoneNumber"] =$("#phoneNumbertxt").val();
			completeArr["companyName"] =$("#companyNametxt").val();
			var x=JSON.stringify(completeArr);
			$.ajax({
				type : "POST",
				contentType : "application/json",
				crossDomain: true,
				dataType: "jsonp",  
				url : remoteUrl+"/"+projectName+"rest/register",
				data : x,
				timeout : 100000,
				success : function(data) {
					loginCheck(username,password);
				},
				error : function(e) {
					alert("Login Error");
					console.log("ERROR: ", e);

				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}
	}
	function loginCheck(username,password) {
		
		var completeArr={}
		completeArr["userName"] =username;
		completeArr["password"] =password;
		var x=JSON.stringify(completeArr);
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : remoteUrl+"/"+projectName+"/rest/login",
			headers: {  'Access-Control-Allow-Origin': 'htt://site allowed to access' },
			data : x,
		    // The name of the callback parameter, as specified by the YQL service
		    timeout : 100000,
			success : function(data) {
				if(data=='Login Success')
				{
					window.location.replace(window.location.origin+"/"+projectName+"/ExamInstruction.html");
				}
				else
				{
					alert("Invalid Login");
				}
			},
			error : function(e) {
				alert("Login Error");
				console.log("ERROR: ", e);

			},
			done : function(e) {
				console.log("DONE");

			}
		});

	}

});