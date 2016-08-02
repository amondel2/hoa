<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Gwynedd Chase Lansdale</title>
		<meta name="description" content="Gwynedd Chase Lansdale HOA Page">
		<meta name="keywords" content="Gwynedd Chase,Lansdale,Gwynedd,Penngrove,Ryan,HOA,Penngrove Terrace">
		<meta name="author" content="Aaron Mondelblatt">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'Favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<!--<asset:stylesheet src="mainjquery.css"/>-->
  		<asset:stylesheet src="mainapp.css"/>
  		<!--<asset:stylesheet src="mainbt.css"/>-->
		<asset:javascript src="main.js"/>
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
             	<li><a href="/home/contact">Contact Us</a></li>
             	<li><a href="/documents">HOA Documents</a></li>
             </sec:ifLoggedIn>
              	<sec:ifAllGranted roles="ROLE_BOARDMEMBER">
          		<li class="dropdown">
          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Messages
        				<span class="caret"></span></a>
        				<ul class="dropdown-menu">
          					<li><g:link controller="messageEditor" action="index">List</g:link></li>
          					<li><g:link controller="messageEditor" action="create">Create New</g:link></li>
        				</ul>
          		</li>
          		</sec:ifAllGranted>
            </ul>
          <ul class="nav navbar-nav navbar-right"><li>
          <sec:ifLoggedIn>
          	<a href="/profile" title="Edit Profile" class="navbar-brand">Welcome <glc:getUserFName /></a>
          	<g:link controller="logout" class="navbar-brand" elementId="logout"><g:message code='spring.security.ui.login.logout'/></g:link>
          </sec:ifLoggedIn>
         
          <sec:ifNotLoggedIn>
          	<g:link controller='login' action='auth'>Login</g:link>
       		</sec:ifNotLoggedIn>
       		</li></ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
    	<div class="starter-template">
        
		<g:layoutBody/>
		 
		 </div>
     </div>
     
     <footer class="navbar-fixed-bottom navbar-inverse">
     		<div class="container" style="color:#FFFFFF;">&copy; 2015<script>new Date().getFullYear()>2010&&document.write("-"+new Date().getFullYear());</script>, Gwynedd Chase HOA. <g:meta name="app.version"/>
     		</div>
    </footer>
	</body>
</html>
