function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#pic').attr('src', e.target.result).height(300).width(300);
        }

        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}

$("#file").change(function() {
    readURL(this);
});