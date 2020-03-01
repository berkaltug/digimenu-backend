$('#price').keypress(function (e) {
    var character = String.fromCharCode(e.keyCode)
    var newValue = this.value + character;
    if (isNaN(newValue) || parseFloat(newValue) * 100 % 1 > 0) {
        e.preventDefault();
        return false;
    }
});