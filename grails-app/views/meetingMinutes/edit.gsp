<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic" />
        <g:set var="entityName" value="${message(code: 'meetingMinutes.label', default: 'MeetingMinutes')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <ckeditor:resources/>
    </head>
    <body>
        <a href="#edit-meetingMinutes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-meetingMinutes" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.meetingMinutes}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.meetingMinutes}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.meetingMinutes}" method="PUT">
                <g:hiddenField name="version" value="${this.meetingMinutes?.version}" />
                 <fieldset class="form">
                     <g:datePicker name="meetDate" value="${this.meetingMinutes?.meetDate}" precision="day"
                                   relativeYears="[-1..1]"/>
                     <ckeditor:editor name="minutes" height="400px" width="80%">
                        ${this.meetingMinutes?.minutes}
                    </ckeditor:editor>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>