var closeAllowed = true


$('#feeAddSaveBtn').on('click', function (e) {
    var button = $(e.relatedTarget);
    var frmDate = $("#feeAddFrm").serialize();
    $.ajax({
        url: "createDues",
        data: frmDate,
        method: "POST"

    }).done(function(data){
        if(data.status == true) {
            alert("Items Saved");
        } else {
            alert("Issues " + data.message);
        }
        closeAllowed = true;
        $('#feeAdd').modal('hide');
        window.location.reload();
    }).fail(function(){
        alert("unkown Error");
        closeAllowed = true;
        $('#feeAdd').modal('hide');
    });

});

$('#missPaymentbtn').on('click', function (e) {
    var button = $(e.relatedTarget);
    closeAllowed = false;
    var myForm = $("#missPaymentfrm");
    if(myForm[0].checkValidity()) {
        var frmDate = myForm.serialize();
        $.ajax({
            url: "missPayment",
            data: frmDate,
            method: "POST"

        }).done(function(data){
            if(data.status == true) {
                alert("Items Saved");
            } else {
                alert("Issues " + data.message);
            }
            closeAllowed = true;
            window.location.reload();
        }).fail(function(){
            alert("unkown Error");
            closeAllowed = true;
            $('#missPayment').modal('hide');
        });
    } else {
        closeAllowed = true;
        $('#missPayment').modal('hide');
    }


});


$('#lateFeetbtn').on('click', function (e) {

    closeAllowed = false;
    var myForm = $("#lateFeefrm");
    if(myForm[0].checkValidity()) {
        var frmDate = myForm.serialize();
        $.ajax({
            url: "createLateFee",
            data: frmDate,
            method: "POST"

        }).done(function(data){
            if(data.status == true) {
                alert("Late Fee Saved");
            } else {
                alert("Issues " + data.message);
            }
            closeAllowed = true;
            window.location.reload();
        }).fail(function(){
            alert("unkown Error");
            closeAllowed = true;
            $('#lateFee').modal('hide');
        });
    } else {
        closeAllowed = true;
        $('#lateFee').modal('hide');
    }

});


$('#SingfeeAddSaveBtn').on('click', function (e) {

            	closeAllowed = false;
            	var myForm = $("#feeAddSinglefrm");
            	if(myForm[0].checkValidity()) {
            		var frmDate = myForm.serialize();
            		$.ajax({
            			url: "createDuesSingle",
            			data: frmDate,
            			method: "POST"
            			
            		}).done(function(data){
            			if(data.status == true) {
            				alert("Items Saved");
            			} else { 
            				alert("Issues " + data.message);
            			}
            			closeAllowed = true;
            			window.location.reload();
            		}).fail(function(){
            			alert("unkown Error");
            			closeAllowed = true;
                        $('#feeAddSingle').modal('hide');
            		});
            	} else {
            		closeAllowed = true;
                    $('#feeAddSingle').modal('hide');
            	}

    });


function saveCheckBox(d,isLast,isAsyn) {
	if(typeof isLast == "undefined") {isLast = true}
	if(typeof isAsyn == "undefined") {isAsyn = true}
	
	var frmDate = {'hmdm':d.attr('hmdm'),'hmhn':d.attr('hmhn'),isPaid:d.prop("checked")};
	$.ajax({
		url: "changePaid",
		data: frmDate,
		method: "POST",
		async: isAsyn
		
	}).done(function(data){
		if(isLast) {
			if(data.status) {
				$("#" + d.attr('hn') + "amountOwed").html(data.amount.toFixed(2));
			} else {
				alert("Change Not Saved");
			}
		}
	});
}

function modify_hoz_checkbox(d,setting) {
	var myElem =  $(d).parent().parent().parent().parent().parent();
	var container = myElem.parent();
	container;
	var offset = -1;
	var chc = container.children();
	var len = chc.length;
	var i = 0;
	while(offset == -1  && i < len){
	  if(chc[i] == myElem[0]){
	   offset = i; 
	  }
	  i++;
	}
	if(offset > -1) {
	var myList = $(chc[offset]).children().find("input[type='checkbox']")
	var len =  myList.length - 1;
	$.each(myList,function(index,value){
		modify_box($(value),setting, index == len,false); 
	});
	}
}


function modify_vert_checkbox(d,setting) {
	var myElem =  $(d).parent().parent().parent().parent();
	var container = myElem.parent();
	var offset = -1;
	var chc = container.children();
	var len = chc.length;
	var i = 0;
	while(offset == -1  && i < len){
	  if(chc[i] == myElem[0]){
	   offset = i; 
	  }
	  i++;
	}
	if(offset > -1) {
	$.each(container.parent().parent().find("tbody").children(),function(index,value){
		modify_box($($(value).children()[offset]).find("input[type='checkbox']"),setting,true,true);
	});
	}
}

function createDm(frmDate,e){
	$.ajax({
		url: "createHMSingle",
		data: frmDate,
		method: "POST"
		
	}).done(function(data){
		$("#" + e.attr('hn') + "amountOwed").html(data.amount.toFixed(2));
		$(e).parent().html(data.output.toString());
	}).fail(function(){
		alert("ERROR");
	});
}

function deleteDm(frmDate,e){
	$.ajax({
		url: "deleteDm",
		data: frmDate,
		method: "POST"
		
	}).done(function(data){
		$("#" + e.attr('hn') + "amountOwed").html(data.amount.toFixed(2));
		$(e).parent().html(data.output);
	}).fail(function(){
		alert("ERROR");
	});
}

function modify_box(box,val,isLast,asyn) {
	 if(box && box instanceof Object) {
			// box = box.first()
			box.prop("checked",val);
			saveCheckBox(box,isLast,asyn);
	} 
}

$.contextMenu({
    selector: 'td:has(input[type="checkbox"])',
    items: {
        "Toggle_Check": {name: "Toggle Check",callback: function(key, opt){ 
        	var elem = $(this).children("input:first");
        	if(elem.prop("checked")) {
        		modify_box(elem,false,true,true);
        	} else {
        		modify_box(elem,true,true,true);
        	}
        }},
        "Delete": {name: "Delete", callback: function(key, opt){
        	var elem = $(this).children("input:first");
        	var frmdata = {"year": elem.attr("year"), "month":elem.attr("month"),"hmhn":elem.attr("hmhn"),"hmdm":elem.attr("hmdm")};
        	deleteDm(frmdata,elem);
         }
        },
        "ParialPayment": {name: "Parial Payment", callback: function(key, opt){
                var elem = $(this).children("input:first");
                $("#mphmdm").val($(elem).attr('hmdm'));
                $("#mphmhn").val($(elem).attr('hmhn'));
               	$("#missPayment").modal("show");
                modify_box(elem,true,true,true);
            }
        },
        "AddLateFine": {name: "Add Late Fee", callback: function(key, opt){
                var elem = $(this).children("input:first");
                $("#lfhmdm").val($(elem).attr('hmdm'));
                $("#lfhmhn").val($(elem).attr('hmhn'));
                $("#lateFee").modal("show");

            }
        }
    }
});

$(document).ready(function(){
	// Fix FireFox Cahcing
	$.each($("input[type=checkbox]"),function(index,value){
		if( $(value).attr("checked") == "checked" ){
			$(value).prop("checked",true);
		} else {
			$(value).prop("checked",false);
		}
	});

	$("#dueYear").on("change",function(){
		// change the page to get a different year
		window.location.href = window.location.origin +  window.location.pathname + "?year=" + $("#dueYear").val();
	});
	
	// $("#feeAddbtn").on('click',function(){ BootstrapDialog.show(feeAdd); });
	
	$("#feeAddFrm").on('submit',function(e){e.preventDefault();});
	$(document).on('change',".dueMonthCheckBox",function(e) {
		var d = $(e.currentTarget);
		saveCheckBox(d);
	});
	
	// $(document).on('click',".dueMonthAddButton",function(e) {
     //    $("#feeAddSingle").modal("show");
	// });

    $('#feeAddSingle').on('show.bs.modal', function (e) {
        var d = $(e.relatedTarget);
        $("#feeAddSinglefrm").find("[name=year]").val(d.attr('year'));
        $("#feeAddSinglefrm").find("[name=months]").val(d.attr('month'));
        $("#feeAddSinglefrm").find("[name=hnid]").val(d.attr('hnid'));
    });
	
	$(document).on('click',".checkAllBtn",function(e) {
		var d = $(e.currentTarget);
		if(d.attr("orient") == "vert") {
			modify_vert_checkbox(d,true);
		} else {
			modify_hoz_checkbox(d,true);
		}
	});
		
	$(document).on('click',".checkNoneBtn",function(e) {
		var d = $(e.currentTarget);
		if(d.attr("orient") == "vert") {
			modify_vert_checkbox(d,false);
		} else {
			modify_hoz_checkbox(d,false);
		}
		
	});
});
