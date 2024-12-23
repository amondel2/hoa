<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic"/>
        <title>Lansdale Gwynedd Chase</title>
    </head>
    <body>
        <h1>Gwynedd Chase Community Contact & Payments</h1>
        <p class="lead">
        <div>
            <strong> E-mail</strong>
            <p><a href="mailto:gwyneddchaselansdale@gmail.com">gwyneddchaselansdale@gmail.com</a></p>
        </div>
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
