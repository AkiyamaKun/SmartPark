//Fetch API
var jwtTokenGlobalVariable;

function ready(fn) {
    if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading"){
        fn();
        activeMenu();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}

function GetAjaxWithFetch(url, success, fail = () => {}){
    fetch(url)
        .then((respond) => {
            if (respond.ok) {
                // noinspection JSAnnotator
                return respond.json();
            }
            throw Error(respond.statusText);
        }).then((result)=> {
        success(result);
    }).catch((err)=>{
        fail(err);
    });
}

function CallAjaxWithFetch(url, method, data, success, fail = () => {}){
    data = data || {};
    fetch(url,{
        method: method,
        body: JSON.stringify(data),
        headers: {
            'Accept' : 'application/json',
            'Content-Type': 'application/json',
            'Authorization': window.jwtTokenGlobalVariable
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
            'Authorization': window.jwtTokenGlobalVariable
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
        dataType: "json",
        contentType: "application/json"
    });
}