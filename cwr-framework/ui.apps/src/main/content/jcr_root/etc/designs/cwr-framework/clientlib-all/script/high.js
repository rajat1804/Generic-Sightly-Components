$("a.accordionCategory").click(function (e) {

    $("a.accordionCategory").not(this).next('ul').removeClass('accordionActive');
	$("a.accordionCategory").not(this).next('ul').addClass('accordionInactive');

    if ($(this).parent().has("ul")) {
        e.preventDefault();
    }
	if($(this).next('ul').hasClass('accordionActive')){
        $(this).next('ul').addClass('accordionInactive');
		$(this).next('ul').removeClass('accordionActive');
    }
    else
        if($(this).next('ul').hasClass('accordionInactive')){
        $(this).next('ul').addClass('accordionActive');
		$(this).next('ul').removeClass('accordionInactive');

	}


});


$("a.accordionQuestion").click(function (e) {
    $("a.accordionQuestion").not(this).next('ul').removeClass('accordionActive');
	$("a.accordionQuestion").not(this).next('ul').addClass('accordionInactive');
    if ($(this).parent().has("ul")) {
        e.preventDefault();
    }
	if($(this).next('ul').hasClass('accordionActive')){
        $(this).next('ul').addClass('accordionInactive');
		$(this).next('ul').removeClass('accordionActive');
    }
    else
        if($(this).next('ul').hasClass('accordionInactive')){
        $(this).next('ul').addClass('accordionActive');
		$(this).next('ul').removeClass('accordionInactive');
    }
});