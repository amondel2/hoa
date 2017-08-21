<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Lansdale Gwynedd Chase</title>
	</head>
	<body>
		<h1>Gwynedd Chase Community</h1>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead><tr><th>First Name</th><th>Last Name</th><th>Email Name</th><th>Address</th>
                   <th>Phone Number</th></tr></thead>
            <tbody>
                <g:each in="${usersList}" var="pf">
                    <tr>
                        <td>
                            ${pf.firstName}
                        </td>
                        <td>
                            ${pf.lastName}
                        </td>
                        <td>
                            ${pf.showEmailInfo ? pf.user.email : ''}
                        </td>
                        <td>
                            ${pf.showAddressInfo ? pf.home.toString() : ''}
                        </td>
                        <td>
                            ${pf.showPhone ? pf.phoneNumber : ''}
                        </td>
                    </tr>

                </g:each>

            </tbody>

        </table>



	</div>
	</body>
</html>
