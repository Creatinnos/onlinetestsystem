error.getHtmlSuccess = function(response){
	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Error</span>').appendTo(".displaycont");
	CommonNamespace.stopLoader();
};

error.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};