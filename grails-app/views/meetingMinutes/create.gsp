<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic" />
        <g:set var="entityName" value="${message(code: 'meetingMinutes.label', default: 'MeetingMinutes')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        		<script src="https://cdn.jsdelivr.net/npm/quill@2/dist/quill.js"></script>
<link
  href="https://cdn.jsdelivr.net/npm/quill@2/dist/quill.snow.css"
  rel="stylesheet"
/>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>

                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-meetingMinutes" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <g:form name="mtgMinsFrm" resource="${this.meetingMinutes}" method="POST">
                <fieldset class="form">
                    <g:datePicker name="meetDate" value="${new Date()}" precision="day"
                                   relativeYears="[-1..1]"/>

                    <div id="minutesEdit" name="minutesEdit" height="400px" width="80%">${raw(this.meetingMinutes?.minutes)}</div>
                     <textarea id="minutes" name="minutes" class="ui-helper-hidden"></textarea>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
        <script>
        const toolbarOptions = [
          ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
          ['blockquote', 'code-block'],
          ['link', 'formula'],

          [{ 'header': 1 }, { 'header': 2 }],               // custom button values
          [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
          [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
          [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
          [{ 'direction': 'rtl' }],                         // text direction

          [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
          [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

          [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
          [{ 'font': [] }],
          [{ 'align': [] }],
        ];
            $('#mtgMinsFrm').on('submit', function() {

                      $("#minutes").val(quill.getSemanticHTML());
                      return true;
                  });


 const quill = new Quill("#minutesEdit", {
  modules: {
     toolbar: toolbarOptions
   },
    theme: "snow",
  });

        </script>
    </body>
</html>
