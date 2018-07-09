manageCategoryNamespace.getHtmlSuccess = function(response){

	$("#mainContent").empty();
	$('#mainContent').append($(response)[1].outerHTML);
	CommonNamespace.common();
	$(".displaycont").empty();
	$('<span class="headerContent">Manage Category</span>'
	).appendTo(".displaycont");
	$(document).ready(function () {
	});
	CommonNamespace.stopLoader();
	manageCategoryNamespace.pageEvents();
	manageCategoryNamespace.loadData();
};

manageCategoryNamespace.loadData =function (){

	var data= {
			organizationId : sessionStorage.getItem('CTS_organizationId')
	};
	$.ajaxSetup({
		cache: false
	});
	$.ajax({
		url: constants.fetchCategory,
		type: 'GET',
		contentType: 'application/json',
		data: data,
		dataType: 'JSON',
		success: function(response){
			console.log(response);
			$.each(response['category'], function(key, value) {
				$("#txtaCategory").find('div').removeClass("highlight");
				$("#txtaCategory").prepend('<div class="highlight" id="'+value["categoryId"]+'">'+value["categoryName"]+'</div>');
		    });
			$.each(response['subCategory'], function(key, value) {
				$("#txtaSubCategory").find('div').removeClass("highlight");
				$("#txtaSubCategory").prepend('<div class="highlight" categoryid="'+value["categoryId"]+'" id="'+value['subCategoryId']+'">'+value["subCategoryName"]+'</div>');
				
			});
			$.each(response['subject'], function(key, value) {
				$("#txtaSubject").find('div').removeClass("highlight");
				$("#txtaSubject").prepend('<div subcategoryid="'+value["subCategoryId"]+'" id="'+value['subjectId']+'">'+value['subject']+'</div>');
			});
			$("#txtaCategory").find('div.highlight').click();
			$("#txtaSubCategory").find('div.highlight').click();
		},
		error: function(response){
			alert("e"+response);
			console.log(response);

		}
	});

}

manageCategoryNamespace.pageEvents = function(){
	var selectedCategory=-1;
	var selectedSubCategory=-1;

	$("#btnCategory").off().click(function(){

		var data= {
				categoryName : $("#txtCategory").val(),
				organizationId : sessionStorage.getItem('CTS_organizationId') 
		};
		$.ajaxSetup({
			cache: false
		});
		$.ajax({
			url: constants.saveCategory,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function(value){
				$("#txtaCategory").find('div').removeClass("highlight");
				selectedCategory = value['categoryId'];
				$("#txtCategory").val("");
				$("#txtaCategory").prepend('<div class="highlight" id="'+selectedCategory+'">'+value['categoryName']+'</div>');
				console.log(value);
				searchCategory(selectedCategory);
				selectedSubCategory=-1;
				$("#txtaSubCategory").find('div').removeClass("highlight");
				searchSubCategory(selectedSubCategory);
			},
			error: function(response){
				alert("e"+response);
				console.log(response);

			}
		});

	});

	$("#btnSubCategory").off().click(function(){

		var data= {
				subCategoryName : $("#txtSubCategory").val(),
				categoryId : selectedCategory,
				organizationId : sessionStorage.getItem('CTS_organizationId')
		};
		$.ajaxSetup({
			cache: false
		});
		$.ajax({
			url: constants.saveSubCategory,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function(value){
				$("#txtaSubCategory").find('div').removeClass("highlight");
				selectedSubCategory = value['subCategoryId'];
				$("#txtSubCategory").val("");
				$("#txtaSubCategory").prepend('<div class="highlight" categoryid="'+selectedCategory+'" id="'+selectedSubCategory+'">'+value['subCategoryName']+'</div>');
				console.log(value);
				searchSubCategory(selectedSubCategory);
			},
			error: function(response){
				alert("e"+response);
				console.log(response);

			}
		});

	});

	$("#btnSubject").off().click(function(){

		var data= {
				subject : $("#txtSubject").val(),
				categoryId : selectedCategory,
				subCategoryId : selectedSubCategory,
				organizationId : sessionStorage.getItem('CTS_organizationId')
		};
		alert(selectedCategory+"--"+selectedSubCategory+"--"+sessionStorage.getItem('CTS_organizationId'));
		$.ajaxSetup({
			cache: false
		});
		$.ajax({
			url: constants.saveSubject,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function(value){
				$("#txtSubject").val("");
				$("#txtaSubject").prepend('<div subcategoryid="'+selectedSubCategory+'">'+value['subject']+'</div>');
				console.log(value);
			},
			error: function(response){
				alert("e"+response);
				console.log(response);

			}
		});

	});

	$("#txtaCategory").on('click', function(e){
		if($(e.target).prop("id")!='txtaCategory')
		{
			$(this).find('div').removeClass("highlight");
			$(e.target).addClass("highlight");
			selectedCategory=$(e.target).prop("id");
			searchCategory(selectedCategory);
			searchSubCategory(selectedSubCategory);
		}

	});
	function searchCategory(selectedCategory)
	{
		var val = selectedCategory;
		var $rows = $("#txtaSubCategory").find("div");
		$rows.removeClass("highlight");
		var lastAddedElement;
		$rows.filter(function(i, v) {
			console.log($(this));
			var title = $(this).attr("categoryid");
			console.log(title+"--"+val +"--"+(title==val));
            if (title==val){
                $(this).show();
                if(lastAddedElement==null || lastAddedElement==undefined)
                {
                lastAddedElement=$(this);
                }
            } else {
                $(this).hide();
            }
 		});
		$(lastAddedElement).addClass("highlight");
		selectedSubCategory=$(lastAddedElement).prop("id");
		searchSubCategory(selectedSubCategory);
	}
	
	$("#txtaSubCategory").on('click', function(e){
		if($(e.target).prop("id")!='txtaSubCategory')
		{
			$(this).find('div').removeClass("highlight");
			$(e.target).addClass("highlight");
			selectedSubCategory=$(e.target).prop("id");
			console.log(selectedSubCategory);
			searchSubCategory(selectedSubCategory);
		}
	});
	
	function searchSubCategory(selectedSubCategory)
	{
		var val = selectedSubCategory;
		var $rows = $("#txtaSubject").find("div");
		$rows.filter(function(i, v) {
			console.log($(this));
			var title = $(this).attr("subcategoryid");
			console.log(title+"--"+val +"--"+(title==val));
            if (title==val){
                $(this).show();
            } else {
                $(this).hide();
            }
            
		});
	}

};

manageCategoryNamespace.saveCategory  = function(){
	var category = $("#txtCategory").val();

};



manageCategoryNamespace.saveSubCategory  = function(){

};

manageCategoryNamespace.saveSubject  = function(){

};

manageCategoryNamespace.getHtmlFailed = function(){
	$("#loginError").text("Some problem occured. Please try again later.");
	$("#loginError").show();
	CommonNamespace.stopLoader();
};