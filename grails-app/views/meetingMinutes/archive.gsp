<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="basic"/>
    <title>Lansdale Gwynedd Chase</title>
    <asset:javascript src="mmarchive.js" />
</head>
<body>
<h1>Meeting Minutes Archive</h1>
<h3>Meeting Minutes from: <g:formatDate date="${meetminDate}" type="date" dateStyle="MEDIUM" /></h3>
<div style="text-align: left;">
    ${raw(meetmins)}
</div>
<div>
<form method="post" id="movemmfrm">
<input type="hidden" name="mid" id="mid" value="${mid}" />
<input type="hidden" name="curr" id="curr" value="${curr}" />
<input type="hidden" name="newCurr" id="newCurr" value="" />
    <input type="hidden" name="totalEntries"  value="${totalEntries}" />
</form>
    <g:if test="${curr.toInteger() > 1}">
        <button id="back">Go Back</button>
    </g:if>
    <g:if test="${curr.toInteger() < totalEntries}">
        <button id="forward">Go Next</button>
    </g:if>
</div>
</body>
</html>
