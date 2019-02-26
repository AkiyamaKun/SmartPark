//Fetch API
function GetAjaxWithFetch(url, success, fail = () => {}){
    fetch(url)
        .then((respond) => {
            if (respond.ok) {
                // noinspection JSAnnotator
        return response.json();
            }
            throw Error(response.statusText);
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
            'Content-Type': 'application/json'
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