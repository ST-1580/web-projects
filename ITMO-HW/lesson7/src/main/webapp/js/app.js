window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

myAjax = function (action, data, $error) {
    data.action = action;
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    });
}