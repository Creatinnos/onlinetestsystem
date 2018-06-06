manageCandidate.getHtmlSuccess = function(response){
	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Candidates</span>'
			+'<div class="col-xs-6" style="float: right;">'
			+'<div class="col-xs-8"><input class="form-control" id="searchUpName"'
			+'placeholder="Search name, phone, email . . ." type="text"></div><div class="col-xs-4">'
			+'<button class="btn btn-success" id="toggleView">Toggle to Single Page View</button>'
			+'</div></div>')
	.appendTo(".displaycont");
	if(window.location.href.indexOf("#manageCandidateList") > 0){
		$("#toggleView").text("Toggle to Single Page View");
	}
	else if(window.location.href.indexOf("#manageCandidate") > 0){
		
		$("#toggleView").text("Toggle to List View");
	}
	manageCandidate.loadCandidates();
	manageCandidate.pageEvents();
};

manageCandidate.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();


};


manageCandidate.loadCandidates = function() {
	CommonNamespace.startLoader();
	$("#candidateList").empty();
	var data;
	$.ajaxSetup({
		cache: false
	});
	$.ajax({
		url: constants.fetchAllCandidates,
		type: 'GET',
		contentType: 'application/json',
		data: JSON.stringify(data),
		dataType: 'JSON',
		success: function(response){
			$.each(response, function(index, value) {
				var cloneDiv=manageCandidate.dynamicElemets(value);
				$("#candidateList").prepend(cloneDiv);
			});
			CommonNamespace.stopLoader();
		},
		error: function(response){
			alert("e"+response);
			$("#loginError").text("Some problem occured. Please try again later.");
			$("#loginError").show();
		}
	});
};

manageCandidate.dynamicElemets =function(value)
{
	var str='<div class="content'+value.candidateId+' col-md-12"><div class="emptyRow">'
	+'</div><div class="row content_record_name'+value.candidateId+'"><label class="" for="newExamHours">'+value.name+'</label></div>'
	+'<div class="row content_record_fatherName'+value.candidateId+'"><label class="" for="newExamHours">'+value.fatherName+'</label></div>'
	+'<div class="row content_record_motherName'+value.candidateId+'"><label class="" for="newExamHours">'+value.motherName+'</label></div>'
	+'<div class="row content_record_dob'+value.candidateId+' dateField"><label class="" for="newExamHours">'+value.dob+'</label></div>'
	+'<div class="row content_record_address'+value.candidateId+'"><label class="" for="newExamHours">'+value.address+'</div>'
	+'<div class="row content_record_phone'+value.candidateId+' phoneField"><label class="" for="newExamHours">'+value.phone+'</label></div>'
	+'<div class="row content_record_email'+value.candidateId+'"><label class="" for="newExamHours">'+value.email+'</label></div>'
	+'<div class="row content_record_save'+value.candidateId+'" style="         text-align: center;   width: 13%;"><button type="button" onclick="manageCandidate.Edit(this)" name="content_save'+value.candidateId+'" id="'+value.candidateId+'" class="btn btn-warning">Edit</button></div>'
	+'</div>';
	return str;
}

manageCandidate.Edit = function(element) {
	$(".content_record_name"+$(element).prop("id")).html('<input type="text" class="formFields" id="canName'+$(element).prop("id")+'" value="'+$(".content_record_name"+$(element).prop("id")).text()+'"/>');
	$(".content_record_fatherName"+$(element).prop("id")).html('<input type="text" class="formFields" id="canFatherName'+$(element).prop("id")+'" value="'+$(".content_record_fatherName"+$(element).prop("id")).text()+'"/>');
	$(".content_record_motherName"+$(element).prop("id")).html('<input type="text" class="formFields" id="canMotherName'+$(element).prop("id")+'" value="'+$(".content_record_motherName"+$(element).prop("id")).text()+'"/>');
	$(".content_record_dob"+$(element).prop("id")).html('<input type="text" class="formFields" id="canDob'+$(element).prop("id")+'" value="'+$(".content_record_dob"+$(element).prop("id")).text()+'"/>');
	$(".content_record_address"+$(element).prop("id")).html('<textarea id="canAddress'+$(element).prop("id")+'" class="formFields col-xs-6 form-control" rows="2">'+$(".content_record_address"+$(element).prop("id")).text()+'</textarea>');
	$(".content_record_phone"+$(element).prop("id")).html('<input type="text" class="formFields" id="canPhone'+$(element).prop("id")+'" value="'+$(".content_record_phone"+$(element).prop("id")).text()+'"/>');
	$(".content_record_email"+$(element).prop("id")).html('<input type="text" class="formFields" id="canEmail'+$(element).prop("id")+'" value="'+$(".content_record_email"+$(element).prop("id")).text()+'"/>');
	$(".content_record_save"+$(element).prop("id")).html('<button type="button" id="newReset" style="margin-left: 20px;margin-right: 20px;"'
			+'class="btn btn-warning">Reset</button>'
			+'<button type="button" id="editSaveCandidate'+$(element).prop("id")+'" class="btn btn-success">Save</button>');

	$("#editSaveCandidate"+$(element).prop("id")).off().click(function(){
		var data= {
				candidateId :+$(element).prop("id"),
				name : $("#canName"+$(element).prop("id")).val(),
				fatherName : $("#canFatherName"+$(element).prop("id")).val(),
				motherName : $("#canMotherName"+$(element).prop("id")).val(),
				dob : $("#canDob"+$(element).prop("id")).val(),
				address : $("#canAddress"+$(element).prop("id")).val(),
				phone : $("#canPhone"+$(element).prop("id")).val(),
				email : $("#canEmail"+$(element).prop("id")).val()
		};
		$.ajaxSetup({
			cache: false
		});
		$.ajax({
			url: constants.updateCandidates,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function(value){
				var cloneDiv=manageCandidate.dynamicElemets(value);
				$(".content"+ $(element).prop("id")).replaceWith(cloneDiv);
			},
			error: function(response){
				alert("e"+response);
				console.log(response);
			}
		});

	});

};


manageCandidate.saveCandidates = function() {
	var data= {
			name : $("#canName").val(),
			fatherName : $("#canFatherName").val(),
			motherName : $("#canMotherName").val(),
			dob : $("#canDob").val(),
			address : $("#canAddress").val(),
			phone : $("#canPhone").val(),
			email : $("#canEmail").val()
	};
	$.ajaxSetup({
		cache: false
	});
	$.ajax({
		url: constants.saveCandidates,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		dataType: 'JSON',
		success: function(value){
			var cloneDiv=manageCandidate.dynamicElemets(value);
			$("#candidateList").prepend(cloneDiv);
			$("#canName").val("");
			$("#canFatherName").val("");
			$("#canMotherName").val("");
			$("#canDob").val("");
			$("#canAddress").val("");
			$("#canPhone").val("");
			$("#canEmail").val("");
			$("#canName").focus()
		},
		error: function(response){
			alert("e"+response);
			$("#loginError").text("Some problem occured. Please try again later.");
			$("#loginError").show();
		}
	});
		
};

manageCandidate.pageEvents = function(){
	
	$("#toggleView").off().click(function(){
	
		if($("#toggleView").text()=="Toggle to List View")
			{
			CommonNamespace.changeHref("#manageCandidateList");
			CommonNamespace.getContainer();
			}
		else
			{
			CommonNamespace.changeHref("#manageCandidate");
			CommonNamespace.getContainer();
			}
	});
	$("#saveCandidate").off().click(function(){
		manageCandidate.saveCandidates();
	});
	$("#searchUpName").off().keyup(function(e) {
		if ($("#searchUpName").val() != "") {
            var val = $.trim($("#searchUpName").val()).replace(/ +/g, ' ').toLowerCase();
            var $rows = $("#candidateList").find("[class^=\"content\"]");
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
        } else {
        	manageCandidate.loadCandidates();
        }
    });
	
}



