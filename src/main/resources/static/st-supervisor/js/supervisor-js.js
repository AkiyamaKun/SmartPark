var AUTHORIZATION_TOKEN = localStorage.getItem("authorizationToken");

function ready(fn) {
    if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading"){
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}

function CallAjaxWithFetch(url, method, data, success, fail = () => {}){
    data = data || {};
    fetch(url,{
        method: method,
        body: JSON.stringify(data),
        headers: {
            'Accept' : 'application/json',
            'Content-Type': 'application/json',
            'Authorization': AUTHORIZATION_TOKEN
        }
    }).then((response)=>{
        if(response.ok){
            // noinspection JSAnnotator
            return response.json();
        }
        throw Error(response.statusText);
    }).then((result)=>{
        success(result);
    }) .catch((err)=>{
        fail(err);
    })
}

function doAjax(url, method, data, callback, onError) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(data),
        success: function (response) {
            if (response.status) {
                callback(response);
            } else {
                alert(response.message);
            }
        },
        error: function (xhr, status, error) {
            alert(xhr.responseJSON.message);
        },
        //Headers: {'Authorization': 'authorizationToken'},
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", AUTHORIZATION_TOKEN);
        },
        dataType: "json",
        contentType: "application/json",
    });
}

// function showAlert(success, message) {
//     let alertNoti = (success ? "<div id='alert' class='alert alert-success'>" : "<div id='alert' class='alert alert-danger'>") +
//         "<span data-notify='message'>" + message +
//         "</span></div>";
//     $("body").append(alertNoti);
//     if ($("#alert").length) {
//         $("#alert").css("opacity", "1");
//     }
//     setTimeout(function () {
//         if ($("#alert").length) {
//             $("#alert").remove();
//         }
//     }, 5000);
// }