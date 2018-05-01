addNewExamNamespace.getHtmlSuccess = function(response){
	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Add New Exam</span><span class="timer" id="timer"></span>').appendTo(".displaycont");
	 $(function () {
		 $('.datepicker').datepicker({
			 format: 'DD/MM/YYYY'
		 });
	  });
	CommonNamespace.stopLoader();
	addNewExamNamespace.pageEvents();
};

addNewExamNamespace.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};

addNewExamNamespace.pageEvents = function(){
	$("#addNewExam").off().click(function(){
		console.log("clicked");
		CommonNamespace.changeHref("#addNewExam");
		state = false;
		$(this).find('h4').css("color", "#FFF");
		$(this).css("color", "#feffd4");
		$("#menuSec").animate({
			'marginLeft':'-280px'
		});
		CommonNamespace.getContainer();
	});
};
