let map;
let marker;

function initMap() {
    // Default Long and Lat
    let latitude = 10.762622;
    let longitude = 106.660172;

    let myLatLng = {
        lat: latitude,
        lng: longitude
    };

    map = new google.maps.Map(document.getElementById("maps"), {
        center: myLatLng,
        disableDefaultUI: true,
        zoom: 14,
        zoomControl: false,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDoubleClickZoom: true, // disable the default map zoom on double click
    });
    google.maps.event.addListener(map, 'click', function (event) {
        if (marker) {
            marker.setMap(null);
        }
        $('#lat-input').val(event.latLng.lat());
        $('#long-input').val(event.latLng.lng());
        marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });
    });
}