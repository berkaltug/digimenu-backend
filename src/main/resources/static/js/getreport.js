$(document).ready(function () {
    $.datetimepicker.setLocale('tr');
    $('#datetimepicker1').datetimepicker({
        format:'d-m-Y H:i'
    });
    $('#datetimepicker2').datetimepicker({
        format:'d-m-Y H:i'
    });
});

function getReport(startDate,endDate){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        data:JSON.stringify({
            startDate:startDate,
            endDate:endDate
        }),
        url:"/restaurant/report",
        success:function(data){
            if(data.reportList.length > 0){
                $('#reportTable>tbody>tr').remove()
                $.each(data.reportList,function(index,obj){
                    $('#reportTable').append("<tr  class='listItem'><td>" + obj.name + "</td><td>" + obj.count + "</td><td class='price'>" + obj.totalPrice + " ₺</td></tr>")
                })
                $('#reportTable').append("<tr><td>Hasılat :</td><td></td><td id='hasılat'></td></tr>");
                calculateHasılat();
            }else{
                $('#reportTable>tbody>tr').remove()
                $('#reportTable').append("<tr><td> Belirtilen aralıkta satış bulunmamaktadır. </td><td></td><td></td></tr>")
            }
        },
        error:function (xhr, status, error) {
            window.alert("Bir problem oluştu,daha sonra tekrar deneyiniz.")
        }
    })

}

function calculateHasılat() {
    var totalPrice2 = 0.0;
    $('#reportTable tbody .listItem').each(function (index, item) {
        totalPrice2 += parseFloat($(item).children(".price").html());
    });
    $('#reportTable tbody .listItem').each(function (index, item) {
        var x=parseFloat($(item).children(".price").html()).toFixed(2);
        console.log(x + "value")
        $(item).children(".price").html(x)
    });
    $('#hasılat').html(parseFloat(totalPrice2).toFixed(2) + " ₺");
}

$("#raporBtn").on('click', function(){
    getReport($('#datetimepicker1').val(),$('#datetimepicker2').val());

})
