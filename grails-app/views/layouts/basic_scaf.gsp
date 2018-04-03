<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<title>Gwynedd Chase Lansdale</title>
		<meta name="description" content="Gwynedd Chase Lansdale HOA Page">
		<meta name="keywords" content="Gwynedd Chase,Lansdale,Gwynedd,Penngrove,Ryan,HOA,Penngrove Terrace">
		<meta name="author" content="Aaron Mondelblatt">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'Favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
	<script
			src="http://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<asset:javascript src="jquery-ui.js" />
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:stylesheet src="jquery-ui-structure.css"/>
	<asset:stylesheet src="jquery-ui.theme.css"/>
	<asset:stylesheet src="main.css"/>
	<asset:stylesheet src="grails.css"/>
	<asset:stylesheet src="bootstrap.css"/>
	<asset:stylesheet src="open-iconic-bootstrap.min.css" />
        <ckeditor:resources/>
		<g:layoutHead/>
	</head>
	<body>
		<span id="logoutLink" style="display:none">
                        <g:link elementId='_logout' controller='logout'/>
                        <a href="${request.contextPath}${securityConfig.logout.afterLogoutUrl}" id="_afterLogout"></a>
        </span>
		<nav class="navbar navbar-dark bg-dark fixed-top navbar-expand-md">
		<div class="container">
			<button type="button" class="navbar-toggler collapsed" data-toggle="collapse"
					data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span>
				&#x2630;</button>
			<div id="navbar"
				 class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
            <li class="nav-item"><a href="/" class="nav-link">Home</a></li>
             <sec:ifLoggedIn>
             	<li class="nav-item"><a href="/board" class="nav-link">Board</a></li>
				 <sec:ifAllGranted roles="ROLE_USER">
				 	<li class="nav-item"><a class="nav-link" href="/home/contactList">Contact List</a></li>
				 </sec:ifAllGranted>
				 <li class="nav-item"><a class="nav-link" href="/meetingMinutes/archive/">Meeting Minutes Archive</a></li>
             	<li class="nav-item"><a class="nav-link" href="/home/contact">Contact Us</a></li>
             	<li class="nav-item"><a class="nav-link" href="/home/cal">Calendar</a></li>
             	<li class="nav-item"><a  class="nav-link" href="/documents">HOA Documents</a></li>
             </sec:ifLoggedIn>
              	<sec:ifAllGranted roles="ROLE_BOARDMEMBER">
					<li class="dropdown nav-item">
						<a class="dropdown-toggle nav-link" data-toggle="dropdown" href="#">Administration

						<span class="caret"></span></a>
						<ul class="dropdown-menu">
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="house" action="index">House</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="financial" action="index">Financial Overview</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="financial" action="admin">Hoa Manager</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="vendor" action="index">Contractors</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="bank" action="index">Bank</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="vendorFin" action="index">Contractor Manager</g:link></li>
          					<li class="nav-item dropdown-item"><g:link class="nav-link" controller="fee" action="index">Fees</g:link></li>
							<li class="nav-item dropdown-item"><g:link class="nav-link" controller="meetingMinutes" action="index">Last Meeting Minutes</g:link></li>
							<li class="nav-item dropdown-item"><g:link class="nav-link" controller="messages" action="index">Manage Messages</g:link></li>
        				</ul>
          		</li>
          		</sec:ifAllGranted>
            </ul>
				<ul class="nav navbar-nav ml-auto">
          <sec:ifLoggedIn>
			  <li class="dropdown  nav-link">
				  <a class="dropdown-toggle  nav-link" data-toggle="dropdown" href="#">Welcome <glc:getUserFName />
					  <span class="caret"></span></a>
			  <ul class="dropdown-menu">
				  <li class="nav-item dropdown-item"><a class="nav-link" href="/profile">Show Profile</a></li>
					  <li class="nav-item dropdown-item"><g:link class="nav-link" elementId="logout" controller="logout"><g:message code='spring.security.ui.login.logout'/></g:link></li>
				  </ul>
			  </li>
          </sec:ifLoggedIn>
         
          <sec:ifNotLoggedIn>
			  <li class="nav-item">
          	<g:link class="nav-link" controller='login' action='auth'>Login</g:link>
			  </li>
       		</sec:ifNotLoggedIn>
       		</ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
    	<div class="starter-template">
        
		<g:layoutBody/>
		 
		 </div>
     </div>

	<footer class="navbar-fixed-bottom navbar-dark bg-dark">
     		<div class="container" style="color:#FFFFFF;">&copy; 2015<script>new Date().getFullYear()>2010&&document.write("-"+new Date().getFullYear());</script>, Gwynedd Chase HOA. <g:meta name="info.app.version"/>
     		</div>
    </footer>
	</body>
</html>
