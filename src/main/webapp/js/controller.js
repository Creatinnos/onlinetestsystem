var CommonNamespace = {};
var loginNamespace = {};
var instructionNamespace = {};
var adminHomeNamespace = {};
var addNewExamNamespace = {};
var manageCategoryNamespace = {};
var uploadNamespace = {};
var manageUser = {};
var error = {};
var manageCandidate= {};
var roleManagement = {};
var questionsNamespace = {};
var state = false;
var loginName;
var loginOrg;
CommonNamespace.getContainer = function() {
	
	CommonNamespace.startLoader();
	$(".menus").find('h4').css("color", "#E9F1F7");
	loginOrg=getCookie("organizationId");
	/*if(getCookie("userName")=="")
	{
		CommonNamespace.changeHref("#login");
		$("#loginName").text(getCookie("userName"));
		renewUserNameCookie();
	}
	else
	{
		$("#loginName").text(getCookie("userName"));
		renewUserNameCookie();
	}*/
	if(window.location.href.indexOf("#login") > 0 || window.location.href.indexOf("#") === -1){
		document.title = "Creatinnos | Login";
		$("#headerSec,.headerBlock").hide();
		
		CommonNamespace.ajaxGetRequest("view/login.html", '', '',
				loginNamespace.getHtmlSuccess, loginNamespace.getHtmlFailed);
	}else if(window.location.href.indexOf("#adminHome") > 0){
		document.title = "Creatinnos | Dashboard";
		$(".adminHomeMenu").css("color", "#e7dfc6");
		CommonNamespace.ajaxGetRequest("view/adminHome.html", '', '',
				adminHomeNamespace.getHtmlSuccess, adminHomeNamespace.getHtmlFailed); 
	}else if(window.location.href.indexOf("#addNewExam") > 0){
		document.title = "Creatinnos | Add New Exam";
		$(".addNewExamMenu").css("color", "#e7dfc6");
		CommonNamespace.ajaxGetRequest("view/addNewExam.html", '', '',
				addNewExamNamespace.getHtmlSuccess, addNewExamNamespace.getHtmlFailed);
	}else if(window.location.href.indexOf("#questions") > 0){
		document.title = "Creatinnos | Add Questions";
		$(".addNewExamMenu").css("color", "#e7dfc6");
		CommonNamespace.ajaxGetRequest("view/questions.html", '', '',
				questionsNamespace.getHtmlSuccess, questionsNamespace.getHtmlFailed);
	}else if(window.location.href.indexOf("#instructions") > 0){
		document.title = "Creatinnos | Instructions";
		CommonNamespace.ajaxGetRequest("view/examInstruction.html", '', '',
				instructionNamespace.getHtmlSuccess, instructionNamespace.getHtmlFailed);
	}else if(window.location.href.indexOf("#manageUserList") > 0){
		document.title = "Creatinnos | Manage User";
		CommonNamespace.ajaxGetRequest("view/manageUserList.html", '', '',
				manageUser.getHtmlSuccess, manageUser.getHtmlFailed);
	}else if(window.location.href.indexOf("#manageUser") > 0){
		document.title = "Creatinnos | Manage User";
		CommonNamespace.ajaxGetRequest("view/manageUser.html", '', '',
				manageUser.getHtmlSuccess, manageUser.getHtmlFailed);
	}else if(window.location.href.indexOf("#manageCandidateList") > 0){
		document.title = "Creatinnos | Candidate";
		CommonNamespace.ajaxGetRequest("view/manageCandidateList.html", '', '',
				manageCandidate.getHtmlSuccess, manageCandidate.getHtmlFailed);
	}else if(window.location.href.indexOf("#manageCandidate") > 0){
		document.title = "Creatinnos | Candidate";
		CommonNamespace.ajaxGetRequest("view/manageCandidate.html", '', '',
				manageCandidate.getHtmlSuccess, manageCandidate.getHtmlFailed);
	}
	else if(window.location.href.indexOf("#manageCategory") > 0){
		document.title = "Creatinnos | Category";
		CommonNamespace.ajaxGetRequest("view/manageCategory.html", '', '',
				manageCategoryNamespace.getHtmlSuccess, manageCategoryNamespace.getHtmlFailed);
	}else if(window.location.href.indexOf("#upload") > 0){
		document.title = "Creatinnos | Category";
		CommonNamespace.ajaxGetRequest("view/uploaddata.html", '', '',
				uploadNamespace.getHtmlSuccess, uploadNamespace.getHtmlFailed);
	}
	
	else{
		document.title = "Creatinnos | Error Page";
		CommonNamespace.ajaxGetRequest("view/error.html", '', '',
				error.getHtmlSuccess, error.getHtmlFailed);
		CommonNamespace.stopLoader();
		$("#loginName").text("");
	}
	$(function(){
		$('.button-checkbox').each(function(){
			var $widget = $(this),
			$button = $widget.find('button'),
			$checkbox = $widget.find('input:checkbox'),
			color = $button.data('color'),
			settings = {
				on: {
					icon: 'glyphicon glyphicon-check'
				},
				off: {
					icon: 'glyphicon glyphicon-unchecked'
				}
			};

			$button.on('click', function () {
				$checkbox.prop('checked', !$checkbox.is(':checked'));
				$checkbox.triggerHandler('change');
				updateDisplay();
			});

			$checkbox.on('change', function () {
				updateDisplay();
			});

			function updateDisplay() {
				var isChecked = $checkbox.is(':checked');
				// Set the button's state
				$button.data('state', (isChecked) ? "on" : "off");

				// Set the button's icon
				$button.find('.state-icon')
				.removeClass()
				.addClass('state-icon ' + settings[$button.data('state')].icon);

				// Update the button's color
				if (isChecked) {
					$button
					.removeClass('btn-default')
					.addClass('btn-' + color + ' active');
				}
				else
				{
					$button
					.removeClass('btn-' + color + ' active')
					.addClass('btn-default');
				}
			}
			function init() {
				updateDisplay();
				// Inject the icon if applicable
				if ($button.find('.state-icon').length == 0) {
					$button.prepend('<i class="state-icon ' + settings[$button.data('state')].icon + '"></i>?');
				}
			}
			$(".btn-pref .btn").click(function () {
				$(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
				// $(".tab").addClass("active"); // instead of this do the below 
				$(this).removeClass("btn-default").addClass("btn-primary");   
			});
			init();
		});
	});

	$(window).on('resize', function (){
		CommonNamespace.common();
	});
	CommonNamespace.pageEvents();
};

loginNamespace.getHtmlSuccess = function(response) {
	$('#mainContent').empty();
	$('#mainContent').append($(response)[1].outerHTML);
	if (CommonNamespace.isAvailable(sessionStorage.getItem("CTS_rememberMe")) && !$("#remember").is(':checked')) {
		  $("#remember").trigger('click');
		  $("#loginUser").val(sessionStorage.getItem('CTS_username'));
		  $("#loginPw").val(CommonNamespace.decrypt());
	}else{
		localStorage.removeItem('encrypt');
	}
	CommonNamespace.stopLoader();
	CommonNamespace.pageEvents();
};

CommonNamespace.pageEvents = function(){
	$("#orgLogin").off().click(function(){
		var userName = $("#orgLoginUser").val();
		var password = $("#orgLoginPw").val();
		
		if(userName !== "" && password !== "") {
			CommonNamespace.startLoader();
			$("#orgLoginError").hide();
			CommonNamespace.checkLogin(userName, password,"Organization");
		}else{
			if(userName === "" && password === "") {
				$("#orgLoginError").text("Please fill in all required fields");
			}else{
				if(userName === ""){
					$("#orgLoginError").text("Please Enter Username");
				}else if(password === ""){
					$("#orgLoginError").text("Please Enter Password");
				}				
			}
			$("#orgLoginError").show();
		}
	});
	$("#candidateLogin").off().click(function(){
		var userName = $("#candidateLoginUser").val();
		var password = $("#candidateLoginPw").val();
		if(userName !== "" && password !== "") {
			CommonNamespace.startLoader();
			$("#candidateLoginError").hide();
			CommonNamespace.checkLogin(userName, password,"Candidate");
		}else{
			if(userName === "" && password === "") {
				$("#candidateLoginError").text("Please fill in all required fields");
			}else{
				if(userName === ""){
					$("#candidateLoginError").text("Please Enter Username");
				}else if(password === ""){
					$("#candidateLoginError").text("Please Enter Password");
				}
			}
			$("#candidateLoginError").show();
		}
	});

	$("#register").off().click(function(){
		
		var regName = $("#regName").val();
		var regUserName = $("#regUserName").val();
		var phone_no = $("#phone_no").val();
		var email = $("#email").val();
		var company_name = $("#company_name").val();
		var regPw = $("#regPw").val();
		var regPwConfirm = $("#regPwConfirm").val();
		if(regUserName !== "" && phone_no !== "" && email !== "" & company_name !== "" && regPw !== "" && regPwConfirm !== "") {
			var valid = 'true';
			var phoneRegex = /^(?:(?:\+|0{0,2})91(\s*[\-]\s*)?|[0]?)?[789]\d{9}$/;
			var pwRegex = /^(?=.*[0-9])(?=.*[!@_#$%^&*])[a-zA-Z0-9!@_#$%^&*]{6,16}$/;
			if(validateEmail(email)){
				if(regPw === regPwConfirm){
					if(pwRegex.test(regPw)){
						if(phoneRegex.test(phone_no)){
							valid = 'true';
						}else{
							valid = 'false';
							$("#regError").text("Enter a valid Phone Number");
							$("#regError").show();
						}
					}else{
						valid = 'false';
						$("#regError").text("The password must be 6 to 16 characters in length. Must contain at least one letter and one number and a special character");
						$("#regError").show();
					}

				}else{
					valid = 'false';
					$("#regError").text("Password does not matches.");
					$("#regError").show();
				}
			}else{
				valid = 'false';
				$("#regError").text("Please enter a valid email address");
				$("#regError").show();
			}
			if(valid === "true"){
				CommonNamespace.startLoader();
				$("#regError").hide();
				var data = {
						"name" : regName,
						"userName" : regUserName,
						"password" : regPw,
						"email" : email,
						"phone" : phone_no,
						"organizationName" : company_name,
				};
				$.ajaxSetup({
					cache: false
				});
				$.ajax({
					url: constants.doRegister,
					type: 'POST',
					contentType: 'application/json',
					data: JSON.stringify(data),
					dataType: 'JSON',
					success: function(response){
						if(response==undefined)
							{
							$("#regError").text("User Already Exists");
							$("#regError").show();
							}
						else
							{
							CommonNamespace.checkLogin(response.userName, regPw,"Organization");
							}
						CommonNamespace.stopLoader();
					},
					error: function(){
						$("#regError").text("Some problem occured. Please try again later.");
						$("#regError").show();
						CommonNamespace.stopLoader();
					}
				});
			}
		}else{
			if(regUserName === "" && phone_no === "" && email === "" && company_name === "" && regPw === "" && regPwConfirm === "") {
				$("#regError").text("Please fill in all required fields");
			}else{
				msg = "";
				(regUserName === "") ? msg = msg + " Username" :  msg = "";
				(phone_no === "") ?  (msg === "" ) ? msg = msg + " Phone" : msg = msg + ", Phone" : msg = "";
				(email === "") ?  (msg === "" ) ? msg = msg + " Email" : msg = msg + ", Email" : msg = "";
				(company_name === "") ?  (msg === "" ) ? msg = msg + " Company" : msg = msg + ", Company" : msg = "";
				(regPw === "") ?  (msg === "" ) ? msg = msg + " Password" : msg = msg + ", Password" : msg = "";
				(regPwConfirm === "") ?  (msg === "" ) ? msg = msg + " Confirm Password" : msg = msg + ", Confirm Password" : msg = "";
				$("#regError").text("Please Enter" + msg);
			}
			$("#regError").show();
		}
	});

	function validateEmail(email) {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(String(email).toLowerCase());
	}

	$("#logout").off().click(function(){
		state = false;
		$("#menuSec").animate({
			'marginLeft':'-280px'
		});
		CommonNamespace.changeHref("#login");
		CommonNamespace.getContainer();
	});
	
	$("#sideMenus").off().click(function(){
		if(state){
			$("#menuSec").animate({
				'marginLeft':'-280px'
			});
		}else{
			$("#menuSec").animate({
				'marginLeft':'0px'
			});
		}
		state = state ? false : true;
		return state;
	});
	
	$(".menus").off().click(function(){
		var selectedMenu = $(this).find('h4').text().trim();
		if(selectedMenu === "Home") {
			CommonNamespace.changeHref("#adminHome");
		}else if(selectedMenu === "Add New Exam") {
			addNewExamNamespace.isEditMode=false;
			CommonNamespace.changeHref("#addNewExam");
      sessionStorage.setItem("CRT_AddNew", "new");
		}else if(selectedMenu === "Upcoming Exams") {
			CommonNamespace.changeHref("#addNewExam");
		}else if(selectedMenu === "Exam History") {
			CommonNamespace.changeHref("#addNewExam");
		}else if(selectedMenu === "View Results") {
			CommonNamespace.changeHref("#addNewExam");
		}else if(selectedMenu === "Activity Log") {
			CommonNamespace.changeHref("#addNewExam");
		}else if(selectedMenu === "Manage Candidates") {
			CommonNamespace.changeHref("#manageCandidateList");
		}else if(selectedMenu === "Manage Admin") {
			CommonNamespace.changeHref("#manageUserList");
		}else if(selectedMenu === "Manage Category") {
			CommonNamespace.changeHref("#manageCategory");
		}else if(selectedMenu === "Manage News &amp; Events") {
			CommonNamespace.changeHref("#addNewExam");
		}else if(selectedMenu === "Upload") {
			CommonNamespace.changeHref("#upload");
		}
		
		state = false;
		$(this).find('h4').css("color", "#E9F1F7");
		$(this).css("color", "#e7dfc6");
		$("#menuSec").animate({
			'marginLeft':'-280px'
		});
		CommonNamespace.getContainer();
	});
	
	$("#forgotPW").off().click(function(){
		$(".modal-body").empty();
		$(".modal-title").text("Forgot Password");
		$('<div class="row"><div class="col-md-12"><input type="text" class="form-control formFields col-md-10" id="forgotPwEmail" placeholder = "Enter your Email id">'+
				'<button class="btn btn-success col-md-2" style="margin:0px 10px;" id="sendForgotPwReq">Submit</button></div></div>').appendTo(".modal-body");
		$("#myModal").modal('show');
	});
};

CommonNamespace.checkLogin = function(userName, password,loginUserType) {
	var data = {
			"userName" : userName,
			"password" : password
	};
	var rememberMe = false;
    if ($("#remember").is(':checked')) {
        CommonNamespace.encrypt(password);
        rememberMe = true;
    }
    sessionStorage.setItem('CTS_username', userName);
    sessionStorage.setItem('CTS_rememberMe', rememberMe);
    
	$.ajaxSetup({
        cache: false
    });
	$.ajax({
        url: constants.doLogin,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'JSON',
        success: function(response){
        	if(response.responseMessage == "Success"){
        		$("#loginError").hide();
    			sessionStorage.setItem("CTS_username",response["organizationUsers"].name);
    			CommonNamespace.changeHref("#adminHome");
    			CommonNamespace.getContainer();
    			sessionStorage.setItem('CTS_organizationId', response["organizationUsers"].organizationId);
    			sessionStorage.setItem('CTS_userId', response["organizationUsers"].userId);
    			
    		}else{
    			if(loginUserType=="Organization")
    				{
	    				$("#orgLoginError").text("Invalid Username/Password");
						$("#orgLoginError").show();
    				}
    			else if(loginUserType=="Candidate")
				{
    				$("#candidateLoginError").text("Invalid Username/Password");
					$("#candidateLoginError").show();
				}
				else
    				{
    					$("#loginError").text("Invalid Username/Password");
    					$("#loginError").show();
    				}
    			CommonNamespace.stopLoader();
    		}
        },
        error: function(response){
        	console.log(response);
        	if(loginUserType=="Organization")
			{
				$("#orgLoginError").text("Some problem occured. Please try again later.");
				$("#orgLoginError").show();
			}
			else if(loginUserType=="Candidate")
			{
				$("#candidateLoginError").text("Some problem occured. Please try again later.");
				$("#candidateLoginError").show();
			}
        	CommonNamespace.stopLoader();
        }
    });
};


CommonNamespace.formatEventsCalDate = function(date,mode){
	 var d = new Date(date),
     month = '' + (d.getMonth() + 1),
     day = '' + d.getDate(),
     year = d.getFullYear();

	 if (month.length < 2) month = '0' + month;
	 if (day.length < 2) day = '0' + day;
	
	 if(mode === "disp"){
		 return [day, month, year].join('-');
	 }else{
		 return [year, month, day].join('-');
	 }
};

//Function to handle ajax GET request
CommonNamespace.ajaxGetRequest = function(requestUrl, dataType, param,
    successFunction, errorFunction) {
    $.ajaxSetup({
        cache: false
    });
    $.ajax({
        url: requestUrl,
        type: 'GET',
        dataType: dataType,
        data: param,
        beforeSend: CommonNamespace.startLoader(),
        success: successFunction,
        error: errorFunction
    });
};

CommonNamespace.ajaxPostRequest = function(requestUrl, contentType, data, dataType, successFunction, errorFunction) {
    $.ajaxSetup({
        cache: false
    });
    $.ajax({
        url: requestUrl,
        type: 'POST',
        contentType: contentType,
        data: data,
        dataType: dataType,
        success: successFunction,
        error: errorFunction
    });
};

CommonNamespace.encrypt = function(password) {
    var encryptedString = '';
    var encrypt = [];
    for (var i = 0; i < password.length; i++) {
        var data = password.charCodeAt(i) + 10;
     encrypt.push(data);
        encryptedString = encryptedString + "" + data;
    }
    sessionStorage.setItem("encrypt", JSON.stringify(encrypt.reverse()));
    return encryptedString;
};
CommonNamespace.decrypt = function() {
    var encrypt = JSON.parse(sessionStorage.getItem("encrypt")).reverse();
    var decrypt = '';
    $.each(encrypt, function(key, value) {
        decrypt = decrypt + String.fromCharCode(value - 10);
    });
    return decrypt;
};
CommonNamespace.isAvailable = function(data) {
   if (data === undefined || data === 'undefined' || data === null || data === 'null' || data === false || data === "false")
	   return false;
   else if (data === true || data === "true")
       return true;
   else
       return true;
};
//change Href
CommonNamespace.changeHref = function(pageHash) {
	window.location.hash = pageHash;
};

//Function to start the loader
CommonNamespace.startLoader = function() {
    $(".section-loader").css("display", "block");
};
//Function to stop the loader
CommonNamespace.stopLoader = function() {
    $(".section-loader").css("display", "none");
};

//Function to stop the loader
CommonNamespace.common = function() {
	$("#userId").text(sessionStorage.getItem("CTS_username").toUpperCase());
	$('<span class="caret"></span>').appendTo("#userId");
	$("#headerSec,.headerBlock").show();
	var layoutHeight  = $(window).height() - ($("#headerSec").outerHeight() + $(".headerBlock").outerHeight());
	$("#layoutContainer").height(layoutHeight);
	$(".menuSection").height(layoutHeight);
	
};
