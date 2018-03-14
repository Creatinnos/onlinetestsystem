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
			var temp={};
			temp["type"]=$(this).children().attr("id");
			temp["question"]=$(this).children().children().children().children("#question").children('textarea').val();
			$(this).children().children().children().children("td[id^='choice']").each(function(){

				temp["choice"+i]="\""+$(this).children("#description").children("input").val()+"\"";
				i++;
			})
			temp["answer"]="answer";
			i=1;
			completeArr.push(temp);
		});

		var x=JSON.stringify(completeArr);
		console.log(x);
		searchViaAjax(x);
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
		window.location.replace("http://localhost:11958/onlinetestsystem/DisplayQuestions.html");
		
	});
	
});

function searchViaAjax(search1) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "rest/question/saveList",
		data : search1,
		dataType : 'json',
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
		url: "rest/question/fetchQuestion",
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