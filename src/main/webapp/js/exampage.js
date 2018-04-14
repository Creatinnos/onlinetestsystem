/**
 * 
 */

var completeArr = [];
$(document).ready(function() {

	findQuestions();
	//$('[id ^=qchoice][id $=EditMode]').click(function(){
	$('#containerMiddleContent').on('click', '[id ^="qchoice_"]', function(){
		var s=$(this).prop("id").split("_");
		$("#left_qs_"+s[2]).css({"color":"red"});
		$("#left_qs_"+s[2]).addClass( "selected" );
	});

	$("#myModal").on('shown.bs.modal', function(){
		$(this).find('#modalClose').focus();
	});

	$("#submitTest").on('click', function(){
		getAnsweredQuestions();
	});

	$("#finishTest").click(function(){
		$('#sectionContent').children('span').each(function(){
			var str=$(this).prop("class");
			if(str.indexOf("selected")>=0)
			{
				getAnsweredQuestions();
			}
			else
			{
				$('#myModal').modal('show');
			}
		})

	});


});

function getAnsweredQuestions()
{

	completeArr=[];
	var i=1;
	$('#containerMiddleContent').children().each(function () {
		var tableDiv=$(this).children();
		if($(tableDiv).prop('tagName') =='FIELDSET')
		{
			tableDiv=$(tableDiv).children();
		}
		var temp={};
		var selectedAnswer=[];
		var question=$(tableDiv).children().children().children('[id ^="question"]');
		console.log(question);
		if(question!=undefined  && question!=""  )
		{
			temp["type"]=$(tableDiv).attr("id");
			temp["question"]=question.prop("id");
			i=1;
			$(tableDiv).children().children().children("td[id^='choice']").each(function(){

				var isChecked = $(this).children("#value").children("input").prop('checked');
				if(isChecked)
				{
					selectedAnswer.push("choice"+i);
				}
				i++;
			});
			if(selectedAnswer.length >0)
			{
				temp["selectedAnswer"]=selectedAnswer;
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
		
	}
	


}
function findQuestions() {
	$.ajax({
		type : 'GET',
		url : remoteUrl+"/"+projectName+"/rest/question/fetchQuestion",
		success : function(data) {
			$.each(jQuery.parseJSON(data), function(index, value) {
				$("#sectionContent").append(
						'<span id="left_qs_'+value["Id"]+'"'
						+'style="width: 40px; height: 50px; padding-left: 15px; padding-top: 15px; margin: 10px; border-style: double;; border-width: 1px; display: inline-block; float: left">'
						+value["Id"]+'</span>');
				var type=value["ChoiceType"];
				var str="";
				if(type=='CHECKBOX')
				{
					console.log(type);
					var str='	<div id="question_div_'+value["Id"]+'" style="padding:25px"> <table id="checkbox" style="width: 100%;margin: 0 auto;">'+
					'<tr style="text-align: left;">'+
					'	<td colspan="2" id="question_'+value["Id"]+'">'+
					'	'+value["Question"]+
					'	</td>'+
					'</tr>';
					str=str+'<tr style="text-align: left;">';

					$.each(value["Choice"], function(index1, value1) {

						if(index1%2==0)
						{
							str=str+'<td id="choice"><span id="value"><input id="qchoice_'+index1+'_'+value["Id"]+'" type="checkbox" value='+value1+'/></span><span'+
							'		id="description">'+value1+'</span></td>';
						}
						else
						{
							str=str+
							'	<td id="choice"><span id="value"><input id="qchoice_'+index1+'_'+value["Id"]+'" type="checkbox"/></span><span'+
							'		id="description">'+value1+'</span></td>'+
							'</tr>';
							str=str+'<tr style="text-align: left;">';
						}
					});
					str=str+"</tr></table></div>";
				}
				else
				{
					var str='<div style="padding:25px" id="question_div_'+value["Id"]+'> <fieldset group="group'+index+'"><table id="radio" style="width: 100%;margin: 0 auto;">'+
					'<tr style="text-align: left;">'+
					'	<td colspan="2" id="question_'+value["Id"]+'">'+
					'	'+value["Question"]+
					'	</td>'+
					'</tr>';
					str=str+'<tr style="text-align: left;">';

					$.each(value["Choice"], function(index1, value1) {
						if(index1%2==0)
						{
							str=str+'<td id="choice"><span id="value"><input id="qchoice_'+index1+'_'+value["Id"]+'" name="group'+index+'" type="radio"/></span><span'+
							'		id="description">'+value1+'</span></td>';
						}
						else
						{
							str=str+
							'	<td id="choice"><span id="value"><input id="qchoice_'+index1+'_'+value["Id"]+'" name="group'+index+'" type="radio"/></span><span'+
							'		id="description">'+value1+'</span></td>'+
							'</tr>';
							str=str+'<tr style="text-align: left;">';
						}
					});
					str=str+'</tr></table></fieldset><div>';

				}
				$("#containerMiddleContent").append(str);

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