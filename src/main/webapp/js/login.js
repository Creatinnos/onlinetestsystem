$(document).ready(function() {
	document.getElementById('loginContent').style.display='block';
	$('.nav-tabs > li > a').click(function() {
		$('.nav-tabs > li.active').removeClass('active');
		$(this).parent().addClass('active');
		if($(this).html() == 'Register')
		{
			$(".register").css({display:'block'});
		}
		else
		{
			$(".register").css({display:'none'});
		}
	});
});