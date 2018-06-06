manageUser.getHtmlSuccess = function(response){
	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Manage User</span>'
			+'<div class="col-xs-6" style="float: right;">'
			+'<div class="col-xs-8"><input class="form-control" id="searchUpName"'
			+'placeholder="Search name, phone, email . . ." type="text"></div><div class="col-xs-4">'
			+'<button class="btn btn-success" id="toggleView">Toggle to Single Page View</button>'
			+'</div></div>')
	.appendTo(".displaycont");
	if(window.location.href.indexOf("#manageUserList") > 0){
		$("#toggleView").text("Toggle to Single Page View");
	}
	else if(window.location.href.indexOf("#manageUser") > 0){
		
		$("#toggleView").text("Toggle to List View");
	}
	manageUser.loadUsers();
	manageUser.pageEvents();
	CommonNamespace.stopLoader();
};

manageUser.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};

manageUser.loadUsers = function() {
	CommonNamespace.startLoader();
	$("#userList").empty();
	var data ={
			organizationId : loginOrg
	};
	console.log(getCookie("organizationId"));
	$.ajaxSetup({
		cache: false
	});
	$.ajax({
		url: constants.fetchAllUser,
		type: 'GET',
		contentType: 'application/json',
		data: data,
		dataType: 'JSON',
		success: function(response){
			$.each(response, function(index, value) {
				var cloneDiv=manageUser.dynamicElemets(value);
				$("#userList").prepend(cloneDiv);
			});
			CommonNamespace.stopLoader();
		},
		error: function(response){
			alert("e"+response);
			console.log("e"+response);
			$("#loginError").text("Some problem occured. Please try again later.");
			$("#loginError").show();
		}
	});
};

manageUser.dynamicElemets =function(value)
{
	var str='<div class="content'+value.userId+' col-md-12"><div class="emptyRow">'
	+'</div><div class="row content_record_name'+value.userId+'"><label class="">'+value.name+'</label></div>'
	+'<div class="row content_record_role'+value.userId+'"><label class="">'+value.role+'</label></div>'
	+'<div class="row content_record_userName'+value.userId+'"><label class="">'+value.userName+'</label></div>'
	+'<div class="row content_record_password'+value.userId+'"><label class="">'+value.password+'</label></div>'
	+'<div class="row content_record_phone'+value.userId+' phoneField"><label class="">'+value.phone+'</label></div>'
	+'<div class="row content_record_email'+value.userId+'"><label class="">'+value.email+'</label></div>'
	+'<div class="row content_record_save'+value.userId+'"><button type="button" onclick="manageUser.Edit(this)" name="content_save'+value.userId+'" id="'+value.userId+'" style="margin-left: 40px;margin-right: 20px;"class="btn btn-warning">Edit</button></div>'
	+'</div>';
	return str;
}

manageUser.Edit = function(element) {
	var str=$(".content_record_role"+$(element).prop("id")).text();
	var questionRadio = $("input[name='questionRadio'][value=\""+str.charAt(1)+"\"]").prop('checked', true);
	var userRadio = $("input[name='userRadio'][value=\""+str.charAt(3)+"\"]").prop('checked', true);
	var candidateRadio = $("input[name='candidateRadio'][value=\""+str.charAt(5)+"\"]").prop('checked', true);
	var roleStr="Q" + questionRadio +"U"+userRadio+"C"+candidateRadio;
	
	$("#content_record_name").val($(".content_record_name"+$(element).prop("id")).text());
	$(".content_record_role").html('<input type="text" class="formFields" id="userRole'+$(element).prop("id")+'" value="'+$(".content_record_role"+$(element).prop("id")).text()+'"/>');
	$("#content_record_userName").val($(".content_record_userName"+$(element).prop("id")).text());
	$("#content_record_password").val($(".content_record_password"+$(element).prop("id")).text());
	$("#content_record_phone").val($(".content_record_phone"+$(element).prop("id")).text());
	$("#content_record_email").val($(".content_record_email"+$(element).prop("id")).text());
	$("#content_record_save").html('<button type="button" id="newReset" style="margin-right: 20px;"'
			+'class="btn btn-warning">Reset</button>'
			+'<button type="button" id="editSaveUsers'+$(element).prop("id")+'" class="btn btn-success">Save</button>');

	$('#myModal1').modal({
	    show: 'true'
	}); 
	
	$("#editSaveUsers"+$(element).prop("id")).off().click(function(){
		var data= {
				userId :+$(element).prop("id"),
				name : $("#userName"+$(element).prop("id")).val(),
				role : $("#userRole"+$(element).prop("id")).val(),
				userName : $("#userUserName"+$(element).prop("id")).val(),
				password : $("#userPassword"+$(element).prop("id")).val(),
				phone : $("#userPhone"+$(element).prop("id")).val(),
				email : $("#userEmail"+$(element).prop("id")).val()
		};
		$.ajaxSetup({
			cache: false
		});
		$.ajax({
			url: constants.updateUser,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function(value){
				var cloneDiv=manageUser.dynamicElemets(value);
				$(".content"+ $(element).prop("id")).replaceWith(cloneDiv);
			},
			error: function(response){
				alert("e"+response);
				console.log(response);
			}
		});

	});

};


manageUser.saveUsers = function() {
	var questionRadio = $("input[name='questionRadio']:checked").val();
	var userRadio = $("input[name='userRadio']:checked").val();
	var candidateRadio = $("input[name='candidateRadio']:checked").val();
	var roleStr="Q" + questionRadio +"U"+userRadio+"C"+candidateRadio;
	alert(loginOrg);
	var data= {
			name : $("#userName").val(),
			role : roleStr,
			userName : $("#userUserName").val(),
			password : $("#userPassword").val(),
			phone : $("#userPhone").val(),
			email : $("#userEmail").val(),
			organizationId : loginOrg
	};
	$.ajaxSetup({
		cache: false
	});
	$.ajax({
		url: constants.saveUser,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		dataType: 'JSON',
		success: function(value){
			var cloneDiv=manageUser.dynamicElemets(value);
			$("#userList").prepend(cloneDiv);
			$("#userName").val("");
			$("#userRole").val("");
			$("#userUserName").val("");
			$("#userPassword").val("");
			$("#userPhone").val("");
			$("#userEmail").val("");
			$("#userName").focus()
		},
		error: function(response){
			alert("e"+response);
			$("#loginError").text("Some problem occured. Please try again later.");
			$("#loginError").show();
		}
	});
};

manageUser.pageEvents = function(){
	
	$("#toggleView").off().click(function(){
		if($("#toggleView").text()=="Toggle to List View")
			{
			CommonNamespace.changeHref("#manageUserList");
			CommonNamespace.getContainer();
			}
		else
			{
			CommonNamespace.changeHref("#manageUser");
			CommonNamespace.getContainer();
			}
	});
	$("#addUser").off().click(function(){
		manageUser.saveUsers();
	});
	
	var oldChar="";
	$("#searchUpName").keydown(function(e) {
		oldChar=$("#searchUpName").val();
	});
	
	$("#searchUpName").keyup(function(e) {
		if ($("#searchUpName").val() != "") {
            var val = $.trim($("#searchUpName").val()).replace(/ +/g, ' ').toLowerCase();
            var $rows = $("#userList").find("[class^=\"content\"]");
            $rows.filter(function(i, v) {
                var name = $(v).find("[class^=\"row content_record_name\"]").children("label").text().replace(/\s+/g, ' ').toLowerCase();
                var phone = $(v).find("[class^=\"row content_record_phone\"]").children("label").text().replace(/\s+/g, ' ').toLowerCase();
                var email = $(v).find("[class^=\"row content_record_email\"]").children("label").text().replace(/\s+/g, ' ').toLowerCase();
                if (name.indexOf(val) > -1 || phone.indexOf(val) > -1 || email.indexOf(val) > -1) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
            if ($("#ExamUpcoming").find(".examList").length === 0) {
            	$('<h5 style="text-align:center;">No Data Available!</h5>').appendTo("#ExamUpcoming");
            }
        } else if(oldChar!=""){
        	manageUser.loadUsers();
        }
    });
	
	
}

