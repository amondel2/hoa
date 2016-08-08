var closeAllowed = true

var feeAdd = {
        message: $("#feeAdd").html(),
        onhide: function(dialogRef){
        	if(!closeAllowed){
                return false;
            }
        },
        title: 'Add New Month fees',
        buttons: [{
            label: 'Close',
            action: function(dialogRef) {
                dialogRef.close();
            }
        },{
            label: 'Create',
            action: function(dialogRef) {
            	closeAllowed = false;
            	var myForm = (dialogRef.getModalBody()).find("form");
            	if(myForm[0].checkValidity()) {
            		var frmDate = myForm.serialize();
            		$.ajax({
            			url: "financial/createDues",
            			data: frmDate,
            			method: "POST"
            			
            		}).done(function(data){
            			if(data.status == true) {
            				alert("Items Saved");
            			} else {
            				alert("Issues " + data.message);
            			}
            			closeAllowed = true;
            			dialogRef.close();
            		}).fail(function(){
            			alert("unkown Error");
            			closeAllowed = true;
            			dialogRef.close();
            		});
            	} else {
            		closeAllowed = true;
            	}
            	
            }
            	
            }
        ]
    };

var feeAddSingle = {
        message: $("#feeAddSingle").html(),
        onhide: function(dialogRef){
        	if(!closeAllowed){
                return false;
            }
        },
        title: 'Add New Month fees',
        buttons: [{
            label: 'Close',
            action: function(dialogRef) {
                dialogRef.close();
            }
        },{
            label: 'Create',
            action: function(dialogRef) {
            	closeAllowed = false;
            	var myForm = (dialogRef.getModalBody()).find("form");
            	if(myForm[0].checkValidity()) {
            		var frmDate = myForm.serialize();
            		$.ajax({
            			url: "financial/createDuesSingle",
            			data: frmDate,
            			method: "POST"
            			
            		}).done(function(data){
            			if(data.status == true) {
            				alert("Items Saved");
            			} else {
            				alert("Issues " + data.message);
            			}
            			closeAllowed = true;
            			dialogRef.close();
            		}).fail(function(){
            			alert("unkown Error");
            			closeAllowed = true;
            			dialogRef.close();
            		});
            	} else {
            		closeAllowed = true;
            	}
            	
            }
            	
            }
        ]
    };

function createDm(frmDate,e){
	$.ajax({
		url: "financial/createHMSingle",
		data: frmDate,
		method: "POST"
		
	}).done(function(data){
		$("#" + e.attr('hn') + "amountOwed").html(data.amount);
		$(e).parent().html(data.output);
	}).fail(function(){
		alert("ERROR");
	});
}

$(document).ready(function(){
	//Fix FireFox Cahcing
	$.each($("input[type=checkbox]"),function(index,value){
		if( $(value).attr("checked") == "checked" ){
			$(value).prop("checked",true);
		} else {
			$(value).prop("checked",false);
		}
	});
	$("#feeAddbtn").on('click',function(){ BootstrapDialog.show(feeAdd); });
	$("#feeAddFrm").on('submit',function(e){e.preventDefault();});
	$(document).on('change',".dueMonthCheckBox",function(e) {
		var d = $(e.currentTarget);
		var frmDate = {'hmdm':d.attr('hmdm'),'hmhn':d.attr('hmhn'),isPaid:d.prop("checked")};
		$.ajax({
			url: "financial/changePaid",
			data: frmDate,
			method: "POST"
			
		}).done(function(data){
			if(data.status) {
				$("#" + d.attr('hn') + "amountOwed").html(data.amount);
			} else {
				BootstrapDialog.show(feeAddSingle); 
			}
		});
	});
	$(document).on('click',".dueMonthAddButton",function(e) {
		var d = $(e.currentTarget);
		var frmDate = {'months':d.attr('month'),'year':d.attr('year'),'hm':d.attr('hn')};
		$.ajax({
			url: "financial/findDm",
			data: frmDate,
			method: "GET"
			
		}).done(function(data){
			if(data.hasDM) {
				var frmDate = {'dmId':data.dmId,'hnid':d.attr('hnid')};
				createDm(frmDate,d);
			} else {
				//tell user to create using builk create
				alert("Use Bulk Create as no other houses have this Due created yet");
			}
		});
	});
});
