<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic"/>
		<title>Finacial Controller</title>
	</head>
	<body>
		<div>
			<div>
				<label for="dueYear">Select a Year</label><g:select name="dueYear" from="${firstYear..lastYear}" value="${viewYear}" />
			</div>
			 <div class="table-responsive">
			  <table class="table table-bordered table-striped">
			   <thead>
			      <tr>
			        <th>House Number</th>
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
				<tbody>	
				<g:each in="${hms}" var="hm">
					<tr>					
						<g:set var="houseNumber" value="${hm.number}" />
						<td>${"${houseNumber}"}</td>
						<td id="${hm.number}amountOwed">${hm.calculateAmountOwed()}</td>
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
				
			</form>
		</div>
		<div id="feeAddSingle">
			<form id="feeAddSingle">
				<label for="amountSingle">Enter the amount</label><input type="number" min="80" step="0.01" required="" id="amountSingle" name="amountSingle" />
			</form>
		</div>
		</div>
		<button class="btn btn-primary btn-sm active" id="feeAddbtn">Bulk Add new Monthly Fees</button>
		<asset:javascript src="fin/index.js" />
	</body>
</html>