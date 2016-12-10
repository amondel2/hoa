function generateReadOnlyHoa(year) {
    var hoaDiv = $("#readOnlyHoaPayments");
    if (!hoaDiv || hoaDiv.length <= 0) {
        return;
    }
    var houseId = hoaDiv.attr("hid");
    if (!houseId || houseId.length <= 0) {
        return;
    }
    $.ajax({
        url: redirectURL,
        data: {'hid': houseId, 'year': year},
        method: "GET",
        cache: false
    }).done(function (data) {

        var htmlData = "<td>" + data[12] + "</td>";
        if (data && Object.keys(data).length > 0) {
            for (var i = 0; i < 12; i++) {
                var item = data[i];
                htmlData += "<td>";
                if (item == -1) {
                    htmlData += "N/A";
                } else if (item == 0 || !item) {
                    htmlData += "<span class='glyphicon glyphicon glyphicon-remove'></span>";
                } else {
                    htmlData += "<span class='glyphicon glyphicon glyphicon-ok'></span>";
                }
                htmlData += "</td>";
            }
        }
        $(hoaDiv).html(htmlData);
    });
}

$(document).ready(function () {
    generateReadOnlyHoa(null);
    $("#dueYear").on("change", function () {
        generateReadOnlyHoa($("#dueYear").val());
    });

});