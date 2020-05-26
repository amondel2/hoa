<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="basic"/>
        <title>Finacial Controller</title>
    %{--<asset:stylesheet src="bootstrap-dialog.css"/>--}%
    <asset:stylesheet src="jquery.contextMenu.css"/>
    %{--<asset:javascript src="bootstrap-dialog.js" />--}%
    <asset:javascript src="jquery.contextMenu.js" />
</head>
<body>
    <div>
        <div style="margin-bottom:4px;">
            <button class="btn btn-primary btn-sm active" id="feeAddbtn" data-toggle="modal" data-target="#feeAdd">Bulk Add new Monthly Fees</button>
            <label for="dueYear">Select a Year</label><g:select name="dueYear" from="${firstYear..lastYear}" value="${viewYear}" />
        </div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>House Number</th>
                        <th>Amount Owed</th>
                        <th><gcl:renderCheckAllBox header="Jan" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Feb" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Mar" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Apr" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="May" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="June" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="July" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Aug" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Sep" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Oct" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Nov" orient="vert"/></th>
                <th><gcl:renderCheckAllBox header="Dec" orient="vert"/></th>
                </tr>
                </thead>
                <tbody>
                    <g:each in="${hms}" var="hm">
                        <tr>
                            <g:set var="houseNumber" value="${hm.number}" />
                            <td><gcl:renderCheckAllBox header="${houseNumber}" orient="horz"/></td>
                    <td id="${houseNumber}amountOwed"><gcl:renderAmountOwed house="${hm}" />

                    <g:each in="${0..11}" var="i">
                        <td class="contextMenu">
                        <gcl:renderMonthlyBox hm="${hm}" month="${i}" year="${viewYear}" />

                        </td>
                    </g:each>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>


    </div>


        <div class="modal" tabindex="-1" role="dialog" id="feeAdd"  aria-labelledby="bfeeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="bfeeModalLabel">Bulk Add</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="feeAddFrm">
                            <div><label for="startMonth">Select a month</label><g:select name="startMonth" from="${1..12}" value="${currMonth}" /></div>
                            <div><label for="startYear">Select a Year</label><g:select name="startYear" from="${(currYear-2)..endYear}" value="${currYear}" /></div>
                            <div><label for="numMonths">Enter the Number of months</label><input type="number" min="1" step="1" required="" id="numMonths" name="numMonths" /></div>
                            <div><label for="housetype">Enter House Type</label><gcl:renderHouseTypes /></div>
                            <div><label for="amount">Enter the amount</label><input type="number" min="80" step="0.01" required="" id="amount" name="amount" /></div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="feeAddSaveBtn" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal" tabindex="-1" role="dialog" id="feeAddSingle"  aria-labelledby="feeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="feeModalLabel">Single Fee Add</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="feeAddSinglefrm">
                            <label for="amountSingle">Enter the amount</label><input type="number" min="80" step="0.01" required="" id="amountSingle" name="amount" />
                            <input type="hidden" name="months" value="" />
                            <input type="hidden" name="year" value="" />
                            <input type="hidden" name="hnid" value="" />
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="SingfeeAddSaveBtn" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
            </div>
        </div>
        </div>
        <div class="modal" tabindex="-1" role="dialog" id="missPayment"  aria-labelledby="missModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="missModalLabel">Missing Payment</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="missPaymentfrm">
                            <label for="misspayamount">Enter the amount</label><input type="number" min="1" step="1" required="" id="misspayamount" name="amount" />
                            <input type="hidden" id="mphmdm" name="hmdm" value="" />
                            <input type="hidden" id="mphmhn" name="hmhn" value="" />
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="missPaymentbtn" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal" tabindex="-1" role="dialog" id="lateFee"  aria-labelledby="lateFeeLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="lateFeeLabel">Late Fee</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="lateFeefrm">
                            <label for="lateFeeamount">Enter the amount</label><input type="number" min="25" step="1" value="25" required="" id="lateFeeamount" name="amount" />
                            <input type="hidden" id="lfhmdm" name="hmdm" value="" />
                            <input type="hidden" id="lfhmhn" name="hmhn" value="" />
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="lateFeetbtn" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

<asset:javascript src="fin/admin.js" />
</body>
</html>