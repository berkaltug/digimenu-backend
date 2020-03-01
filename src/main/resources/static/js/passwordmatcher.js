$(document).ready(function(){
    $("#ConfirmPassword").keyup(function(event){
        if ($("#Password").val() != $("#ConfirmPassword").val()) {
            $("#msg").html("Parolalar eşleşmemektedir").css("color","red");
        }else{
            $("#msg").html("Parolalar eşleşti").css("color","green");
        }
    });

    $("#submitbtn").click(function (event) {
        if ($("#Password").val() != $("#ConfirmPassword").val()){
            event.preventDefault();
        }
    });
});