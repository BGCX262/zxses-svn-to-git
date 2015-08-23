function validateSubmit(voteIdObj,voteForm){
	//是否选择投票选项验证
	var num = 0;
	num = $("input[name=itemsIds]:checked").length;
	if(num == 0){
		$.alert("请选择投票选项！");
		return;
	}

	//ajax登录验证
	$.ajax( {
		url : $.basePath + "front/vote.do?method=checkVote",
		data : {
			voteId : voteIdObj.val()
		},
		async : true,
		type : "POST",
		success : function(data) {
			if (data) {
				if(data == "unLogin"){
					$.alert("只有登录用户可投票，请登录!");
					return;
				}else if(data == "havaBallot"){
					$.alert("已投票，不可重复投票!");
				}else if(data == "noBallot"){
					VoteForm.submit();
				}
			}
		}
	});
}