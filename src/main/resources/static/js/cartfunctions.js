$(document).ready(function () {
    calculateTotalPrize()
});

function calculateTotalPrize() {
    var totalPrice2 = 0.0;
    $('#hesapTable tbody .siparisItem').each(function (index, item) {
        totalPrice2 += parseFloat($(item).children(".price").html());
    });
    $('#toplamUcret').html(parseFloat(totalPrice2).toFixed(2) + " ₺");
}

function deleteItem(id, obj) {
    var $btn = obj;
    $btn.attr('disabled', true);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        url: "/restaurant/flushitem/" + id,
        success: function (data) {
            $btn.closest("tr").remove();
            calculateTotalPrize();
        },
        error: function (xhr, status, error) {
            alert("Sunucuda bir problem oluştu ve ürün silinemedi.Daha sonra tekrar deneyiniz.");
            $btn.attr('disabled', false);
        }
    });
}

function deleteWrongOrder(name, masaNo, cartId, obj) {
    var $btn = obj;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        url: "/restaurant/delete-wrong-order/" + name + "/" + masaNo + "/" + cartId,
        success: function (data) {
            $btn.closest("tr").remove();
            calculateTotalPrize();
        },
        error: function (xhr, status, error) {
            alert("Sunucuda bir problem oluştu ve ürün silinemedi.Daha sonra tekrar deneyiniz.");
            $btn.attr('disabled', false);
        }
    });
}

function setDeliveryOption(id, value, obj) {
    var $switch = obj;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        data: JSON.stringify({
            value: value,
            id: id
        }),
        url: "/restaurant/delivery",
        success: function (data) {
            console.log("successfully delivered");
        },
        error: function (xhr, status, error) {
            alert("Sunucuda bir problem oluştu.Daha sonra tekrar deneyiniz.");

        }
    });
}

$('#hesapTable').on('click', '.btn-warning', function () {
    deleteItem($(this).attr('id'), $(this));
});

$('#hesapTable').on('click', '.btn-danger', function () {
    var name = $(this).closest("tr").find("td:eq(0)").text();
    var masaNo = $("#masaNo").text();
    var cartId = $(this).attr("id");
    deleteWrongOrder(name, masaNo, cartId, $(this));
});

$('#hesapTable').on('click', '.custom-control-input', function () {
    var value = $(this).is(':checked');
    var id = $(this).closest("tr").find("button.btn-danger").prop("id");
    setDeliveryOption(id, value, $(this));
})

