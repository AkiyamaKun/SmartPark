
// Counter Number
$('.count').each(function () {
    $(this).prop('Counter', 0).animate({
        Counter: $(this).text()
    }, {
        duration: 3000,
        easing: 'swing',
        step: function (now) {
            $(this).text(Math.ceil(now));
        }
    });
});

function doAjax(url, method, data, callback, onError) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(data),
        success: function(response) {
            if (response.status) {
                callback(response);
            } else {
                showAlert(false, response.message);
            }
        },
        error: function(xhr, status, error) {
            debugger;
            showAlert(false, xhr.responseJSON.message);
        },
        dataType: "json",
        contentType: "application/json"
    });

}



function buildBootrapTable(tableId) {
    $(`#${tableId}`).DataTable({
        lengthMenu: [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
    });

    $('#row-select').DataTable({
        initComplete: function () {
            this.api().columns().every(function () {
                var column = this;
                var select = $('<select class="form-control"><option value=""></option></select>')
                    .appendTo($(column.footer()).empty())
                    .on('change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw();
                    });
                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });
            });
        }
    });
}

function showAlert(success, message) {
    let alertDiv = (success ? "<div id='alert' class='alert alert-success'>" : "<div id='alert' class='alert alert-danger'>") +
                        "<i class='fa fa-info-circle'></i>" + "&nbsp;&nbsp;" + message +
                    "</div>";
    $("body").append(alertDiv);
    if ($("#alert").length) {
        $("#alert").css("opacity", "1");
    }
    setTimeout(function () {
        if ($("#alert").length) {
            $("#alert").remove();
        }
    }, 5000);
}