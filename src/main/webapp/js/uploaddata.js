uploadNamespace.getHtmlSuccess = function(response){

	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Upload</span>'
	).appendTo(".displaycont");
	$(document).ready(function () {
	});
	CommonNamespace.stopLoader();
	uploadNamespace.pageEvents();
};

uploadNamespace.pageEvents = function(){
	 $('#btnUpload').on('click', function() {
		    var formData=new FormData(document.getElementById("fileForm"));
		    console.log(formData);
	        formData.append("orgId",sessionStorage.getItem('CTS_organizationId'));
	        $.ajax({
	            url: constants.uploadExcel,
	            type: "POST",
	            data: formData,//new FormData(document.getElementById("fileForm")),
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false
	          }).done(function(data) {
	              
	        	  alert("File Uploaded");
	        	  
	          }).fail(function(jqXHR, textStatus) {
	              //alert(jqXHR.responseText);
	              alert('File upload failed ...');
	              console.log(jqXHR);
	              
	          });
	        
	    });
	  $('#file').on('fileselect', function(event, numFiles, label) {
		  	console.log(numFiles);
	        console.log(label);
	        $('#lableName').text(label);      
	    });
	  
	  $(document).on('change', '#file', function() {
		    var input = $(this),
		        numFiles = input.get(0).files ? input.get(0).files.length : 1,
		        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		    input.trigger('fileselect', [numFiles, label]);
		});
};
uploadNamespace.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};