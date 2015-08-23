function checkSearchSubmit() {
	var word = $("#word");
	if($.trim(word.val()) == ""){
		return false;
	}
	return true;
}

