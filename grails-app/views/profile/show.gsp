
<%@ page import="com.gcl.Profile" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic">
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    <asset:javascript src="profile/show.js" />
    <script type="text/javascript">
        var redirectURL = '<g:resource dir="profile" file="readOnlyHoaPayments" absolute="true" />';
    </script>
</head>
<body>

    <div id="show-profile" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <table class = "table table-striped">
            <tbody>
                <g:if test="${profileInstance?.firstName}">
                    <tr>
                        <td><g:message code="profile.firstName.label" default="First Name" /></td>
                        <td><g:fieldValue bean="${profileInstance}" field="firstName"/></td>
                    </tr>table
                </g:if>



                <g:if test="${profileInstance?.lastName}">
                    <tr>
                        <td><g:message code="profile.lastName.label" default="Last Name" /></td>
                        <td><g:fieldValue bean="${profileInstance}" field="lastName"/></td>
                    </tr>
                </g:if>


                <g:if test="${profileInstance?.phoneNumber}">
                    <tr>
                        <td><g:message code="profile.phoneNumber.label" default="Phone Number" /></td>
                        <td><g:fieldValue bean="${profileInstance}" field="phoneNumber"/></td>
                    </tr>
                </g:if>

            </tbody></table>

            <g:if  test="${profileInstance.homeId}">
            <label for="dueYear">Select a Year</label><g:select name="dueYear" id="dueYear" from="${getFirstPayment..endYear}" value="${endYear}" />
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
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
                        <tr id="readOnlyHoaPayments" hid="${profileInstance.homeId}">
                        </tr>
                    </tbody>
                </table>
            </div>

            <g:if  test="${fee && fee.size() > 0}">
                <h3>Outstanding Fees</h3>
                <div style="text-align:left;">
                    <g:each in='${fee}' var="f">

                        <ul>
                            <li>${f.description}
                                <ul>
                                    <li>Amount: ${'$' + f.amount}</li>
                                    <li> Due Date: <g:formatDate format="MM-dd-yyyy" date="${f.dueDate}" /></li>
                                </ul>
                            </li>
                        </ul>
                    </g:each>
                </div>
            </g:if>

        </g:if>





        <g:form url="[resource:profileInstance, action:'delete']" method="DELETE">
            <fieldset class="buttons">
                <g:link class="btn btn-primary btn-sm active" id="${profileInstance.id}"  action="edit"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            </fieldset>
        </g:form>
    </div>
</body>
</html>
