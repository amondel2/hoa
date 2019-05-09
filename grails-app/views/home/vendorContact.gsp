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
            <thead><tr><th>Name</th><th>Phone Number</th><th>Account Number</th><th>E-Mail</th><th>Address</th>
                   </tr></thead>
            <tbody>
                <g:each in="${usersList}" var="pf">
                    <tr>
                        <td>
                            ${pf.name}
                        </td>
                        <td>
                            ${pf.phone}
                        </td>
                        <td>
                            ${pf.accountNumber}
                        </td>
                        <td>
                            ${pf.email}
                        </td>
                        <td>
                            ${pf.getAddress()}
                        </td>
                    </tr>

                </g:each>

            </tbody>

        </table>



	</div>
	</body>
</html>
