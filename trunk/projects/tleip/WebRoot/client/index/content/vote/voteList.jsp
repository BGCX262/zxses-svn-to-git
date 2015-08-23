<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<style>
.panelInclude1 {
	border-bottom: 1px solid #52C5D6;
	border-left: 1px solid #52C5D6;
	border-right: 1px solid #52C5D6;
	border-top: 1px solid #52C5D6;
	clear: left;
	font-size: 12px;
	margin: 0 auto;
	padding-bottom: 20px;
	padding-top: 20px;
}
</style>
<link rel="stylesheet" type="text/css"
			href="${basePath }client/index/content/vote/app.platform.vote.v3.css" />
<script type="text/javascript" src="${basePath}client/index/content/vote/jsfiles/voteIndex.js"></script>
<script type="text/javascript">
	$(function () {
		$("#ballot_id").click(function(){
			validateSubmit($("#voteId"),VoteForm);
		});
	});
	
	function query(){
       window.open("${basePath }front/vote.do?method=queryVoteList");
	}
</script>
<div id="voteList" class="panelInclude1">
	<form target="_blank" action="${basePath }front/vote.do?method=ballotVoteTitle" method="post" name="VoteForm">
		&nbsp;&nbsp;&nbsp;&nbsp;<button class="icon_vote">&nbsp;</button>${voteTitle.voteName } 
		<br/>
		<c:forEach items="${voteTitle.voteItemses}" var="items" varStatus="status">
			<span style="padding-left: 40px;">
				<input${voteTitle.voteType=="1"?" type=\"radio\"":" type=\"checkbox\"" }" onclick="" value="${items.itemId}" name="itemsIds"/>
				<span class="option">${items.itemName }</span>
			</span>
		<br/>
		</c:forEach>
		<br/>
		<input type="hidden" id="voteId" name="voteId" value="${voteTitle.voteId }"/>
		<div align="center">
			<input type="button" id="ballot_id" name="ballot" value="投票" class="uniformButton"/>
			&nbsp;
			<a target="_blank" href="${basePath }front/vote.do?method=queryVoteList">
			</a>
			<input type="button" name="ballot" value="查看" class="uniformButton" onclick="query();"/>
		</div>
	</form>
</div>
<%--<div id="content" class="panelInclude">
	<div id="body">
		<c:if test="${voteTitle != null}">
			<form method="post" action="${basePath }front/vote.do?method=ballotVoteTitle"  id="form_vote" name="form_vote">
			<input type="hidden" name="voteFlag" value="<%=voteFlag%>"/>
			<input type="hidden" name="voteId" value="${voteTitle.voteId }"/>
			<div id="main">
				<div id="question_361707" class="question result">
					<div class="title f14px">
						<button class="icon_vote">&nbsp;</button>${voteTitle.voteName } 
					</div>
					<div class="content line">
					<div class="nodetail"></div>
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<c:forEach items="${voteTitle.voteItemses}" var="items" varStatus="status">
								<tr>
									<td width="430" style="padding-right:20px;">
										<label><input${voteTitle.voteType=="1"?" type=\"radio\"":" type=\"checkbox\"" }" onclick="" value="${items.itemId}" name="itemsIds"/>
										<span class="option">${items.itemName }</span></label>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</div>
				<div class="submit line" align="center">
					<input type="submit" onclick="return validateSubmit();" value="提  交" class="uniformButton" id="submit_vote"${isVote=="true"?" disabled=\"disabled\"":"" }/>
				</div>
			</form>
		</c:if>
		<c:if test="${voteTitle == null}">
			目前没有投票！
		</c:if>
	</div>
</div>--%>