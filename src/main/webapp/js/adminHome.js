adminHomeNamespace.getHtmlSuccess = function(response){
	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	adminHomeNamespace.loadContents();
	CommonNamespace.stopLoader();
};

adminHomeNamespace.loadContents = function(){
	$("#newsSec").empty();
	$(".displaycont").empty();
	$('<span class="headerContent">Dashboard</span>'+
		'<button class="btn btn-success startBtn" id="addNewExam">Add New Exam</button>').appendTo(".displaycont");
	adminHomeNamespace.loadExamList();
	if(constants.events.length > 0){
		for(var j=0; j < constants.events.length ; j++) {
			$('<div class="examList row">'+
					'<h5>' + constants.events[j].Event + '</h5>'+
					'<h5 class="greyed">' + constants.events[j].PostedOn + ' | ' + constants.events[j].PostedBy + '</h5>'+
				'</div>').appendTo("#newsSec");
		};
	}else{
		$('<h5 style="text-align:center;">No Data Available!</h5>').appendTo("#newsSec");
	}
	adminHomeNamespace.pageEvents();
};

adminHomeNamespace.loadExamList = function(){
	$("#ExamUpcoming").empty();
	var eventData = [];

	var data ={
			organizationId :sessionStorage.getItem('CTS_organizationId') 
	}
    $.ajax({
    	url: constants.fetchOranizationExam,
        type: "GET",
        data: data,
        dataType: 'JSON',
        success: function(response){
        	if(response.length > 0){
        		for(var i=0; i < response.length ; i++) {
        			var duration = response[i].examDuration.split(":")[0] + " Hour(s) " + response[i].examDuration.split(":")[1] + " Minutes";
        			var examDate = CommonNamespace.formatEventsCalDate(response[i].examStartDate,"disp") + " to " + CommonNamespace.formatEventsCalDate(response[i].examEndDate,"disp") + " | " + duration;
        			var examAvailability = '<div class="examList row" id=' + response[i].examId+ '>';
        			var progressClass = "";
        			var symbolTitle = "";
        			adminHomeNamespace.getDates(eventData,response[i].examStartDate,response[i].examEndDate,response[i].examName,duration);
        			if(response[i].progress === "Y"){
        				progressClass = "glyphicon glyphicon-ok-circle green";
        				symbolTitle = "Completed";
        			}else if(response[i].progress === "E"){
        				progressClass = "glyphicon glyphicon-stop red";
        				examAvailability = '<div class="examList row greyed">';
        				symbolTitle = "Exam Over";
        			}else{
        				progressClass = "glyphicon glyphicon-exclamation-sign warning";
        				symbolTitle = "In Complete";
        			}
        			
        			var examCont = examAvailability +
        			'<h4 class="examTitle col-md-7"><span title = "'+ symbolTitle +'" class="' + progressClass+ '"></span>' + response[i].examName + '</h4>'+
        			'<h5 class="col-md-5" style="margin-top: 14px;">' + examDate + '</h5>'+
        			'</div>';
        			
        			$(examCont).appendTo("#ExamUpcoming");
        			constants.ExamInfo.push(response[i]);
        		};
        	}else{
        		$('<h5 style="text-align:center;"  class="noData">No Data Available!</h5>').appendTo("#ExamUpcoming");
        	}
        	
        }
      }).done(function(response) {
    	  adminHomeNamespace.loadEventsForDynamicElement();
      }).fail(function(jqXHR, textStatus) {
          alert('Exam Fetch Failed');
          console.log(jqXHR);
          
      });
        	               
	$(document).ready(function(){
		$("#calendar").zabuto_calendar({
			 today: true,
			data: eventData,
			 modal: true,
			 action: function () {
	                return adminHomeNamespace.myDateFunction(this.id, false, eventData);
	            },
			 legend: [
		                {type: "text", label: "New Exam Starts", badge: "00"},
		                {type: "block", label: "Exam Scheduled"}
		            ]
		});
	});

};

adminHomeNamespace.myDateFunction = function(id, fromModal, eventData) {
        if (fromModal) {
        	$("#myModal").modal('hide');
        }
        var arr = [];
        $(".modal-body").empty();
        var date = $("#" + id).data("date");
        arr = jQuery.grep(eventData, function(a,i) {
        	if(a.date === date) {
        		return a;
        	}
        });
        $(".modal-title").text("Exam(s) on " + CommonNamespace.formatEventsCalDate(date,"disp"));
        var unique = arr.filter(function(elem, index, self) {
            return index === self.indexOf(elem);
        });
        for(var i=0;i<unique.length;i++){
        	var eleData = $('<div class="examList row" style="padding: 0px 10px;">'+
        			'<h5>'+ unique[i].name +' | '+ unique[i].duration +'</h5>'+
        			'</div>');
        	$(eleData).appendTo(".modal-body");
        }
        var hasEvent = $("#" + id).data("hasEvent");
        if (!hasEvent && !fromModal) {
            return false;
        }
    $("#myModal").modal('show');
};
	

adminHomeNamespace.getDates = function(eventData,startDate, stopDate, examName, examDuration) {
    var dt = new Date(startDate);
    var edt = new Date(stopDate);
    while (dt <= edt) {
    	var formatedDate = CommonNamespace.formatEventsCalDate(dt,"events");
    	var badgestate = "";
    	if(formatedDate === startDate){
    		badgestate = true;
    	}else{
    		badgestate = false;
    	}
    	var eventObj = {
    			date : formatedDate,
    			badge : badgestate,
    			name : examName,
    			duration : examDuration
    	};
    	eventData.push(eventObj);
        dt.setDate(dt.getDate() + 1);
    }
    return eventData;
};


adminHomeNamespace.pageEvents = function(){
	$("#addNewExam").off().click(function(){
		sessionStorage.setItem("CRT_AddNew", "new");
		CommonNamespace.changeHref("#addNewExam");
		CommonNamespace.getContainer();
	});
	
	$("#searchUpExams").off().keyup(function(e) {
		if ($("#searchUpExams").val() != "") {
            var val = $.trim($("#searchUpExams").val()).replace(/ +/g, ' ').toLowerCase();
            var $rows = $("#ExamUpcoming").find(".examList");
            $rows.filter(function(i, v) {
                var title = $(this).find(".examTitle").text().replace(/\s+/g, ' ').toLowerCase();
                if (title.indexOf(val) > -1) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
            if ($("#ExamUpcoming").children('div:visible').length === 0) {
            	if(!$("#ExamUpcoming").children().hasClass("noData")){
            		$('<h5 style="text-align:center;" class="noData">No Data Available!</h5>').appendTo("#ExamUpcoming");
            	}
            }else{
            	$("#ExamUpcoming .noData").remove();
            }
        } else {
        	adminHomeNamespace.loadExamList();
        }
    });

};

adminHomeNamespace.loadEventsForDynamicElement =function()
{
	$("#ExamUpcoming .examList").off().click(function(){
		sessionStorage.setItem("CRT_AddNew", "edit");
		sessionStorage.setItem("CRT_ExamID", $(this).attr('id'));
		CommonNamespace.changeHref("#addNewExam");
		addNewExamNamespace.isEditMode=true;
		CommonNamespace.getContainer();
	});

}

adminHomeNamespace.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};