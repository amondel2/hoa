<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <asset:javascript src="profile/show.js" />
            <script type="text/javascript">
                var redirectURL = '<g:resource dir="profile" file="readOnlyHoaPayments" absolute="true" />';
                redirectURL = redirectURL.replace('static/','');
            </script>
    </head>
    <body>
        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_BOARDMEMBER">
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

            </ul>
        </div>
        </sec:ifAnyGranted >
        <div id="show-profile" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="profile" />
            <ol class="property-list profile">
                <li class="fieldcontain">
                    <span id="email-label" class="property-label">E-mail</span>
                    <div class="property-value" aria-labelledby="email-label">${profile.user.email}</div>
                </li>
            </ol>
                   <g:if  test="${profile.homeId}">
                        <label for="dueYear">Select a Year</label><g:select name="dueYear" id="dueYear" from="${getFirstPayment..endYear}" value="${endYear}" />

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Amount Owed</th>
                                        <th>Jan</th>
                                        <th>Feb</th>
                                        <th>Mar</th>
                                        <th>Apr</th>
                                        <th>May</th>
                                        <th>June</th>
                                        <th>July</th>
                                        <th>Aug</th>
                                        <th>Sep</th>
                                        <th>Oct</th>
                                        <th>Nov</th>
                                        <th>Dec</th>
                                    </tr>
                                </thead>
                                <tbody >
                                    <tr id="readOnlyHoaPayments" hid="${profile.homeId}">
                                    </tr>
                                </tbody>
                            </table>


                        <g:if  test="${fee && fee.size() > 0}">
                            <h3>Outstanding Fees</h3>
                            <div style="text-align:left;">
                                <g:each in='${fee}' var="f">
                                    <div> Due Date: <g:formatDate format="MM-dd-yyyy" date="${f.dueDate}" /> ${f.toString()}</div>
                                </g:each>
                            </div>
                        </g:if>

                    </g:if>

                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.profile}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_BOARDMEMBER">
                        <g:form resource="${this.profile}" method="DELETE">
                         <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                         </g:form>
                    </sec:ifAnyGranted >
                </fieldset>

        </div>
    </body>
</html>
