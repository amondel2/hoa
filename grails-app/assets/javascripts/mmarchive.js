$(document).ready(function(){
	$("#back").on('click',function(){
		movemm(-1);
	});
	$("#forward").on('click',function(){
		movemm(1);
	});
});

function movemm(dir) {
	var curr = $("#curr").val();
	$("#newCurr").val(parseInt(curr) + dir);
	$("#movemmfrm").submit();
}