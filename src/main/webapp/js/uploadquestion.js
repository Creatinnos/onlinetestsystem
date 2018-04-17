/**
 *
 **/



var completeArr=[];
$(document).ready(function(){
	$("#countValue").val(0);
	$("#containerMiddleContent").append(""+$("#hiddenCheckboxDiv").html());
	$(".leftpanTemplate").click(function(){
		var template=$(this).prop("id");
		var count =$("#countValue").val();
		console.log(template);
		if(template!=undefined && template=='templateCheck')
		{
			for(var i=0;i<count;i++)
			{
				$("#containerMiddleContent").prepend(""+$("#hiddenRadioDiv").html());				
			}	
		}
		else
		{
			for(var i=0;i<count;i++)
			{
				$("#containerMiddleContent").prepend(""+$("#hiddenCheckboxDiv").html());				
			}
		}		
	});
	$("#uploadQuestions").click(function(){
		completeArr=[];
		var i=1;
		$('#containerMiddleContent').children().each(function () {
			var tableDiv=$(this).children();
			if($(tableDiv).prop('tagName') =='FIELDSET')
			{
				tableDiv=$(tableDiv).children();
			}
			var temp={};
			var question=$(tableDiv).children().children().children("#question").children('textarea').val();
			if(question!=undefined  && question!=""  )
			{
				temp["type"]=$(tableDiv).attr("id");

				temp["question"]=question;
				var choice=[];
				var correctChoice=false;
				$(tableDiv).children().children().children("td[id^='choice']").each(function(){

					var isChecked = $(this).children("#value").children("input").prop('checked');
					var choiceTemp={}
					choiceTemp["choice"]=($(this).children("#description").children("input").val());
					if(isChecked)
					{
						choiceTemp["answer"]=true;
						correctChoice=true;
					}
					else
						{
						choiceTemp["answer"]=false;
						}
					choice.push(choiceTemp);
					i++;
				});
				temp["choice"]=choice;
				if(correctChoice==false)
				{
					alert("No Answer selected for Question No");
					return false;
				}
				else
				{
					i=1;
					completeArr.push(temp);
				}
			}
		});
		
		if(completeArr.length <=0)
		{
			console.log("Nothing to persist");
			return false;
		}
		else
		{
			var x=JSON.stringify(completeArr);
			console.log(x);
			searchViaAjax(x);
		}
		
	});


	$("#increaseCount").click(function(){
		var count =$("#countValue").val();
		if(count!=undefined && count!='')
			$("#countValue").val((parseInt(count)+1));
		else
			$("#countValue").val(0);
	});

	$("#decreaseCount").click(function(){
		var count =$("#countValue").val();
		if(count!=undefined && count!='' && parseInt(count)!=0)
			$("#countValue").val((parseInt(count)-1));
		else
			$("#countValue").val(0);
	});

	$("#fetchQuestions").click(function(){

		// similar behavior as an HTTP redirect
		window.location.replace(window.location.origin+"/"+projectName+"/DisplayQuestions.html");

	});

});

function searchViaAjax(search1) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : remoteUrl+"/"+projectName+"/rest/question/saveList",
		data : search1,
		timeout : 100000,
		success : function(data) {
			alert("Uploaded Successfully");
			console.log("SUCCESS: ", data);
		},
		error : function(e) {
			alert("Uploaded Error");
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");

		}
	});

}


function findQuestions() {
	alert("ff");
	$.ajax({
		type: 'GET',
		url: remoteUrl+"/"+projectName+"/rest/question/fetchQuestion",
		dataType: "json",
		success: function(data){
			$.each(data, function( index, value ) {
				console.log(value["QUESTION"]);
				alert( value[index]);
			});
		},
		error : function(e) {
			alert("Fetch Error");
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");

		}

	});
}

function findAll() {
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList
	});
}

function findById(id) {
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			renderDetails(data);
		}
	});
}

function addWine() {
	console.log('addWine');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: "rest/wines",
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Wine created successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus+"errorThrown"+errorThrown);
		}
	});
}

function updateWine() {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#wineId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Wine updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateWine error: ' + textStatus);
		}
	});
}

function deleteWine() {
	console.log('deleteWine');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#wineId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Wine deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteWine error');
		}
	});
}

//Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	return JSON.stringify({
		"id": "test",
		"name": "test",
		"grapes": "test",
		"country": "test",
		"region": "test",
		"year": "test",
		"description": "test"
	},{
		"id": "test",
		"name": "test",
		"grapes": "test",
		"country": "test",
		"region": "test",
		"year": "test",
		"description": "test"
	});
}