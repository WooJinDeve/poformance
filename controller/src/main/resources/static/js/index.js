function submitForm(event) {
    event.preventDefault(); // Prevent default form submission

    // Create Request object
    var request = {
        method: document.getElementById("method").value,
        uri: document.getElementById("uri").value,
        headers: getHeaders(),
        body: document.getElementById("body").value,
        requestParameters: getRequestParameters(),
        agentNum: parseInt(document.getElementById("agentNum").value),
        testTime: parseInt(document.getElementById("testTime").value)
    };

    // Convert Request object to JSON string
    var jsonRequest = JSON.stringify(request);

    // Send AJAX request
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/load-test");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("요청이 성공적으로 전송되었습니다.");
                window.location = "http://localhost:5601/app/kibana/discover"
            } else {
                console.error("요청 전송 중 오류가 발생하였습니다.");
            }
        }
    };
    xhr.send(jsonRequest);
}

function getHeaders() {
    // Get headers data and return as an array of objects
    var headerElements = document.getElementsByClassName("header");
    var headers = [];
    for (var i = 0; i < headerElements.length; i++) {
        var name = headerElements[i].querySelector("input[name='headersName[]']").value;
        var value = headerElements[i].querySelector("input[name='headersValue[]']").value;
        headers.push({ key: name, value: value });
    }
    return headers;
}

function getRequestParameters() {
    var parameterElements = document.getElementsByClassName("parameter");
    var parameters = [];
    for (var i = 0; i < parameterElements.length; i++) {
        var name = parameterElements[i].querySelector("input[name='requestParametersName[]']").value;
        var value = parameterElements[i].querySelector("input[name='requestParametersValue[]']").value;
        parameters.push({ key: name, value: value });
    }
    return parameters;
}

function addHeader(){
    var newHeader = document.createElement("div");
    newHeader.classList.add("header");

    var nameInput = document.createElement("input");
    nameInput.type = "text";
    nameInput.name = "headersName[]";
    nameInput.placeholder = "Name";

    var valueInput = document.createElement("input");
    valueInput.type = "text";
    valueInput.name = "headersValue[]";
    valueInput.placeholder = "Value";

    newHeader.appendChild(nameInput);
    newHeader.appendChild(valueInput);

    var headersContainer = document.getElementById("headers");
    headersContainer.appendChild(newHeader);
}

function addParameter() {
    var newParameter = document.createElement("div");
    newParameter.classList.add("parameter");

    var nameInput = document.createElement("input");
    nameInput.type = "text";
    nameInput.name = "requestParametersName[]";
    nameInput.placeholder = "Name";

    var valueInput = document.createElement("input");
    valueInput.type = "text";
    valueInput.name = "requestParametersValue[]";
    valueInput.placeholder = "Value";

    newParameter.appendChild(nameInput);
    newParameter.appendChild(valueInput);

    var parametersContainer = document.getElementById("requestParameters");
    parametersContainer.appendChild(newParameter);
}

document.getElementById("loadTestForm").addEventListener("submit", submitForm);