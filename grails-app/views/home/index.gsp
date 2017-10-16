<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Lansdale Gwynedd Chase</title>
	</head>
	<body>
		<h1>Gwynedd Chase Community</h1>
        <p class="lead">
				<g:if test='${flash.message}'>
					<div class='login_message'>${flash.message}</div>
				</g:if>
				<h3>Find us on <a href="https://www.facebook.com/groups/GwyneddChaseLansdale" target="_blank">Facebook</a></h3>
				<sec:ifNotLoggedIn>
		        	Please register (via login link) and help us get to know our Residents!
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<g:if test="${messages && messages?.size() > 0}">
					  <a href="#" class="list-group-item active">
							<strong>Messages and Announcements</strong>
  					</a>
					<div class="list-group">
				<g:each in="${messages}" var="msg">
					<a  class="list-group-item list-group-item-action">${msg.text}</a>
				</g:each>

				</div>
					<h2>Last Meeting Minutes</h2>
				<div style="text-align: left;">
					${raw(meetmins)}
				</div>
					</g:if>
				</sec:ifLoggedIn>
		        <br>
				
		        If have questions please send us an        		         
		        <a href="mailto:gwyneddchaselansdale@gmail.com">email!</a>
		       
		</p>
	
		 <g:if test="${param?.autologout}">
		       <script type="text/javascript">
					$(document).ready(function(){
						$("#logout").trigger("click");
					});
				</script> 	
		  </g:if>
	</body>
</html>
