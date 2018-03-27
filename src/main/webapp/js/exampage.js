/**
 * 
 */

var completeArr = [];
$(document).ready(function() {
	findQuestions();

});
function findQuestions() {
	$.ajax({
		type : 'GET',
		url : "rest/question/fetchQuestion",
		dataType : "json",
		success : function(data) {
			$.each(data, function(index, value) {
				console.log(value["QUESTION"]);
				var type=value["CHOICETYPE"];
				var str="";
				if(type=='CHECKBOX')
				{

					var str='	<table id="radio" style="width: 100%;margin: 0 auto;">'+
					'<tr style="text-align: left;">'+
					'	<td colspan="2" id="question">'+
					'	'+value["QUESTION"]+
					'	</td>'+
					'</tr>';
					str=str+'<tr style="text-align: left;">';

					$.each(JSON.parse(value["CHOICE"]), function(index1, value1) {
						if(index1%2==0)
						{
							str=str+'<td id="choice"><span id="value"><input  type="checkbox" value='+value1+'/></span><span'+
							'		id="description">'+value1+'</span></td>';
						}
						else
						{
							str=str+
							'	<td id="choice"><span id="value"><input type="checkbox"/></span><span'+
							'		id="description">'+value1+'</span></td>'+
							'</tr>';
							str=str+'<tr style="text-align: left;">';
						}
					});
					str=str+"</tr></table></div>";;
				}
				else
				{
					var str='	<table id="radio" style="width: 100%;margin: 0 auto;">'+
					'<tr style="text-align: left;">'+
					'	<td colspan="2" id="question">'+
					'	'+value["QUESTION"]+
					'	</td>'+
					'</tr>';
					str=str+'<tr style="text-align: left;">';

					$.each(JSON.parse(value["CHOICE"]), function(index1, value1) {
						if(index1%2==0)
						{
							str=str+'<td id="choice"><span id="value"><input  type="radio"/></span><span'+
							'		id="description">'+value1+'</span></td>';
						}
						else
						{
							str=str+
							'	<td id="choice"><span id="value"><input type="radio"/></span><span'+
							'		id="description">'+value1+'</span></td>'+
							'</tr>';
							str=str+'<tr style="text-align: left;">';
						}
					});
					str=str+"</tr></table></div>";;
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