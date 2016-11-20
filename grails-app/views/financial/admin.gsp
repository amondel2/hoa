<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Finacial Controller</title>
		<asset:stylesheet src="bootstrap-dialog.css"/>
		<asset:stylesheet src="jquery-contextMenu.css"/>
		<asset:javascript src="bootstrap-dialog.js" />
		<asset:javascript src="jquery.contextMenu.js" />
	</head>
	<body>
		<div>
			<div style="margin-bottom:4px;">
				<button class="btn btn-primary btn-sm active" id="feeAddbtn">Bulk Add new Monthly Fees</button>
				<label for="dueYear">Select a Year</label><g:select name="dueYear" from="${firstYear..lastYear}" value="${viewYear}" />
			</div>
			 <div class="table-responsive">
			  <table class="table table-bordered table-striped">
			   <thead>
			      <tr>
			        <th>House Number</th>
					<th>Amount Owed</th>
			        <th><glc:renderCheckAllBox header="Jan" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="Feb" orient="vert"/></th>
					<th><glc:renderCheckAllBox header="Mar" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="Apr" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="May" orient="vert"/></th>
					<th><glc:renderCheckAllBox header="June" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="July" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="Aug" orient="vert"/></th>
					<th><glc:renderCheckAllBox header="Sep" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="Oct" orient="vert"/></th>
			        <th><glc:renderCheckAllBox header="Nov" orient="vert"/></th>
					<th><glc:renderCheckAllBox header="Dec" orient="vert"/></th>
			      </tr>
			    </thead>
				<tbody>	
				<g:each in="${hms}" var="hm">
					<tr>					
						<g:set var="houseNumber" value="${hm.number}" />
						<td><glc:renderCheckAllBox header="${houseNumber}" orient="horz"/></td>
						<td id="${houseNumber}amountOwed">${hm.calculateAmountOwed()}</td>
						<g:each in="${0..11}" var="i">
							<td>
								<glc:renderMonthlyBox hm="${hm}" month="${i}" year="${viewYear}" /> 
								
							</td>
						</g:each>
					</tr>
				</g:each>
				</tbody>
			  </table>
			</div> 
						

		</div>

		<div style="display:none;">
		<div id="feeAdd">
			<form id="feeAddFrm">
				<label for="startMonth">Select a month</label><g:select name="startMonth" from="${1..12}" value="${currMonth}" />
				<label for="startYear">Select a Year</label><g:select name="startYear" from="${(currYear-2)..endYear}" value="${currYear}" />
				<label for="numMonths">Enter the Number of months</label><input type="number" min="1" step="1" required="" id="numMonths" name="numMonths" />
				<label for="amount">Enter the amount</label><input type="number" min="80" step="0.01" required="" id="amount" name="amount" />
			</form>
		</div>
		<div id="feeAddSingle">
			<form id="feeAddSingle">
				<label for="amountSingle">Enter the amount</label><input type="number" min="80" step="0.01" required="" id="amountSingle" name="amount" />
				<input type="hidden" name="months" value="" />
				<input type="hidden" name="year" value="" />
				<input type="hidden" name="hnid" value="" />
			</form>
		</div>
		</div>
		
		<asset:javascript src="fin/admin.js" />
	</body>
</html>