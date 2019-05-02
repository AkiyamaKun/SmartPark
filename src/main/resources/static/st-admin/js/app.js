//Fetch API
var AUTHORIZATION_TOKEN = localStorage.getItem("authorizationToken");

function ready(fn) {
    if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading"){
        fn();
        activeMenu();
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

function CallAjaxWithFetchWithFormData(url, method, data, success, fail = () => {}){
    data = data || {};
    fetch(url,{
        method: method,
        body: data,
        headers: {
            'Accept' : 'application/json, application/x-www-form-urlencoded',
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
                showAlert(false, response.message);
            }
        },
        error: function (xhr, status, error) {
            debugger;
            showAlert(false, xhr.responseJSON.message);
        },
        //Headers: {'Authorization': 'authorizationToken'},
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", AUTHORIZATION_TOKEN);
        },
        dataType: "json",
        contentType: "application/json",
    });
}