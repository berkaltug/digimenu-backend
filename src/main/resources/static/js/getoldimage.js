$(document).ready(function () {
    var imageId = $("#img-id").val();
 var url= "http://res.cloudinary.com/hkn1l1hym/image/upload/" + imageId;
 $("#pic").attr("src",url).width(300).height(300);
})