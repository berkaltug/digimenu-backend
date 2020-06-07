$(".pic-button").click(function(event){
    //resim butonundan public idyi çek
    var publicId=$(this).find("img").attr("alt");
    console.log(publicId)
    var url='http://res.cloudinary.com/hkn1l1hym/image/upload/w_301/' + publicId;
    //url'i modaldaki resmin kaynağına yapıştır
    $(".modal-image-container>img").attr("src",url);
    //ismi divden çek modal başlığına yapıştır
    var isim=$(this).closest(".item-container").find(".item-header").text();
    console.log(isim);
    $("#modalFoodName").text(isim);
    //içindekiler metnini çek null değilse modalda yerleştir
    $("#modalFoodInfo").text(""); // modaldaki içindekiler boşsa öncekini yazmasın diye boşalt
    var icerik=$(this).closest(".item-container").find(".item-ingredients").text();
    if(icerik!=null && icerik != ""){
        $("#modalFoodInfo").html(icerik);
    }
})