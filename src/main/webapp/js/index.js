async function checkUser(contextPath, email, password) {
    $.ajax({
        url: contextPath + "/user",
        type: "get",
        dataType: "json",
        data: {
            'email': email,
            'onlyPassword': 1
        },
        success: function (data) {
            if (data.status === 'success') {
                return data.value === password
            } else {
                return false
            }
        }
    })
}

function getUserId(contextPath, email, callback) {
    $.get(contextPath + "/user?email=" + email, function (data) {
        const json = JSON.parse(data)
        alert(json)
        if (json.status === 'success') {
            return json.value.id
        } else {
            return json.status
        }
    })
}