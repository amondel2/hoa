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
  		<asset:stylesheet src="mainjquery.css"/>
  		<asset:stylesheet src="mainapp.css"/>
		<asset:javascript src="main.js"/>
		<asset:stylesheet src="bootstrap-datepicker3.min.css"/>
		<asset:javascript src="bootstrap-datepicker.min.js" />
		<g:layoutHead/>
	</head>
	<body>
		<span id="logoutLink" style="display:none">
                        <g:link elementId='_logout' controller='logout'/>
                        <a href="${request.contextPath}${securityConfig.logout.afterLogoutUrl}" id="_afterLogout"></a>
        </span>
		<nav class="navbar navbar-inverse navbar-fixed-top">
      	<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
             <sec:ifLoggedIn>
             	<li><a href="/board">Board</a></li>
				 <sec:ifAllGranted roles="ROLE_USER">
				 	<li><a href="/home/contactList">Contact List</a></li>
				 </sec:ifAllGranted>
             	<li><a href="/home/contact">Contact Us</a></li>
             	<li><a href="/home/cal">Calendar</a></li>
             	<li><a href="/documents">HOA Documents</a></li>
             </sec:ifLoggedIn>
              	<sec:ifAllGranted roles="ROLE_BOARDMEMBER">
          		<li class="dropdown">
          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Messages
        				<span class="caret"></span></a>
        				<ul class="dropdown-menu">
          					<li><g:link controller="messages" action="index">List</g:link></li>
          					<li><g:link controller="messages" action="create">Create New</g:link></li>
        				</ul>
          		</li>
          		<li class="dropdown">
          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Administration
        				<span class="caret"></span></a>
        				<ul class="dropdown-menu">
          					<li><g:link controller="house" action="index">House</g:link></li>
          					<li><g:link controller="financial" action="index">Financial Overview</g:link></li>
          					<li><g:link controller="financial" action="admin">Hoa Manager</g:link></li>
          					<li><g:link controller="vendor" action="index">Contractors</g:link></li>
          					<li><g:link controller="bank" action="index">Bank</g:link></li>
          					<li><g:link controller="vendorFin" action="index">Contractor Manager</g:link></li>
          					<li><g:link controller="fee" action="index">Fees</g:link></li>
        				</ul>
          		</li>
          		</sec:ifAllGranted>
            </ul>
          <ul class="nav navbar-nav navbar-right">
          <sec:ifLoggedIn>
			  <li class="dropdown">
				  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome <glc:getUserFName />
					  <span class="caret"></span></a>
				  <ul class="dropdown-menu">
					  <li><a href="/profile">Show Profile</a></li>
					  <li><g:link elementId="logout" controller="logout"><g:message code='spring.security.ui.login.logout'/></g:link></li>
				  </ul>
			  </li>
          </sec:ifLoggedIn>
         
          <sec:ifNotLoggedIn>
			  <li>
          	<g:link controller='login' action='auth'>Login</g:link>
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
     
     <footer class="navbar-fixed-bottom navbar-inverse">
     		<div class="container" style="color:#FFFFFF;">&copy; 2015<script>new Date().getFullYear()>2010&&document.write("-"+new Date().getFullYear());</script>, Gwynedd Chase HOA. <g:meta name="info.app.version"/>
     		</div>
    </footer>
	</body>
</html>
