$(".input-daterange").datepicker({
    todayBtn: true,
    clearBtn: true,
    calendarWeeks: true,
    autoclose: true,
    todayHighlight: true
});

$("#dateFilter").on("click",function(){
	$("#datefilterfrm").submit();
});