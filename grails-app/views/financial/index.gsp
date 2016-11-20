<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Finacial Controller</title>
		<asset:stylesheet src="bootstrap-dialog.css"/>
		<asset:stylesheet src="bootstrap-datepicker3.min.css"/>
		<asset:javascript src="bootstrap-dialog.js" />
		<asset:javascript src="bootstrap-datepicker.min.js" />
	</head>
	<body>
		<div>
			<g:form action="index" id="datefilterfrm">
		    <div class="input-daterange input-group" id="datepicker">
		    		<span class="input-group-addon">From</span>
        			<input type="text" class="input-sm form-control" value="${startDate}" id="fromdate" name="fromdate" />
        			<span class="input-group-addon">to</span>
        			<input type="text" class="input-sm form-control" value="${endDate}" id="todate" name="todate" />
        			<span class="input-group-addon"><button class="btn btn-primary btn-sm active" id="dateFilter">submit</button></span>
    		</div>
    		</g:form>
			 <div class="table-responsive">
			  <table class="table table-bordered table-striped">
			   <thead>
			      <tr>
			<th>Amount Owed To HOA</th>
			<th>Amount Owed In Dues</th>
			<th>Amount Owed In Fees</th>
			<th>Total Amount Collected</th>
			<th>Total Amount Dues Collected</th>
			<th>Total Amount Fees Collected</th>
			<th>Amount Owed To Contractors</th>
			<th>Total Amount Paid To Contractors</th>
			<th>Money in the Bank</th>
			</thead><tbody>
				<tr>
					<td>${totalOwed}</td>
					<td>${hoaOwed}</td>
					<td>${fees}</td>
					<td>${collected}</td>
					<td>${hoaPaid}</td>
					<td>${feesPaid}</td>
					<td>${debt}</td>
					<td>${totalPaid}</td>
					<td>${moneyInBank}</td>
				</tr>
			</tbody></table>
			</div>
		</div>
		<asset:javascript src="fin/index.js" />
	</body>
</html>