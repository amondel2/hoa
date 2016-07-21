<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Lansdale Gwynedd Chase</title>
	</head>
	<body>
		<h1>Gwynedd Chase Community</h1>
		<h3>Find us on <a href="https://www.facebook.com/groups/GwyneddChaseLansdale" target="_blank">Facebook</a></h3>
        <p class="lead">
		        Please register (via login link) and help us get to know our Residents!
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
