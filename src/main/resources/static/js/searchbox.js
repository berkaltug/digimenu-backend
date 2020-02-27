
$("#searchbox1").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#menulist li").filter(function() {
        $(this).toggle($(this).children("span").text().toLowerCase().indexOf(value) > -1)
    });
});