<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-profile" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.profile}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.profile}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.profile}" method="POST">
                <fieldset class="form">
                    <f:all bean="profile" except="home,user" />
                    <div class="fieldcontain required">
                        <label for="email">E-Mail</label>
                        <input id="email" required="required" name="email" value="${profile?.user?.email ?: user?.email}" type="email" minlength="6">
                    </div>
                     <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_BOARDMEMBER">
                                        <div class="form-group">
                                            <label for="House">
                                                Select a House
                                            </label>
                                            <g:select name="home" from="${hl}" value="${profile.homeId}" optionKey="id" />
                                        </div>
                     </sec:ifAnyGranted>
                     <input type="hidden" style="display:none" id="user" name="user.id" required="" value="${profile.user ? profile.user.id : user.id }" />
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
