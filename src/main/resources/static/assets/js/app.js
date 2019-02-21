// Counter Number
$('.count').each(function () {
    $(this).prop('Counter',0).animate({
        Counter: $(this).text()
    }, {
        duration: 3000,
        easing: 'swing',
        step: function (now) {
            $(this).text(Math.ceil(now));
        }
    });
});

function doAjax(url, method, callback, error) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let response = xhttp.responseText;
            if (!response || (response && response.indexOf('error') > 0)) {
                error(response);
            } else {
                callback(response);
            }
        }
    };
    xhttp.open(method, url, true);
    xhttp.send();
}