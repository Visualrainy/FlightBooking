
var mockserver = require('mockserver-node');
mockserver
    .start_mockserver({serverPort: 1080, verbose: true})
    .then(
        function () {
            console.log("started MockServer");
            refundTicket();
            refundTicketWithInvalidOrderId();
            refundTicketWithInvalidTicketId();
            refundTicketWithRefundMultiTimes();
            noConsumptions();
            singleConsumptions();
            multiConsumptions();
        },
        function (error) {
            console.log(JSON.stringify(error, null, "  "));
        }
    );


function refundTicket() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "POST",
                "path": "/booking-orders/123456/tickets/654321/refund"
            },
            "httpResponse": {
                "body": {
                    "code": 200,
                    "message": "success"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function refundTicketWithInvalidOrderId() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "POST",
                "path": "/booking-orders/abc/tickets/654321/refund"
            },
            "httpResponse": {
                "body": {
                    "code": 10000,
                    "message": "fail"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function refundTicketWithInvalidTicketId() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "POST",
                "path": "/booking-orders/123456/tickets/abc/refund"
            },
            "httpResponse": {
                "body": {
                    "code": 10001,
                    "message": "fail"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function refundTicketWithRefundMultiTimes() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "POST",
                "path": "/booking-orders/12345/tickets/54321/refund"
            },
            "httpResponse": {
                "body": {
                    "code": 10002,
                    "message": "fail"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function noConsumptions() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "GET",
                "path": "/precharge-contracts/1234/consumptions"
            },
            "httpResponse": {
                "body": {
                    "code": 200,
                    "data": [],
                    "message": "success"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function singleConsumptions() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "GET",
                "path": "/precharge-contracts/12345/consumptions"
            },
            "httpResponse": {
                "body": {
                    "code": 200,
                    "data": [
                        {
                            "cid": "111",
                            "amount": 1000,
                            "payTime": "2022-03-12",
                            "flight": {
                                "number": "MU-666",
                                "departure": "成都",
                                "arrival": "上海",
                                "departureTime": "2022-03-13",
                            }
                        }
                    ],
                    "message": "success"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function multiConsumptions() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .mockAnyResponse({
            "httpRequest": {
                "method": "GET",
                "path": "/precharge-contracts/123456/consumptions"
            },
            "httpResponse": {
                "body": {
                    "code": 200,
                    "data": [
                        {
                            "cid": "111",
                            "amount": 1000,
                            "payTime": "2022-03-12",
                            "flight": {
                                "number": "MU-666",
                                "departure": "成都",
                                "arrival": "上海",
                                "departureTime": "2022-03-13",
                            }
                        },{
                            "cid": "222",
                            "amount": 1200,
                            "payTime": "2022-03-12",
                            "flight": {
                                "number": "MU-228",
                                "departure": "上海",
                                "arrival": "成都",
                                "departureTime": "2022-03-16",
                            }
                        },{
                            "cid": "5555",
                            "amount": 1500,
                            "payTime": "2022-03-12",
                            "flight": {
                                "number": "MU-866",
                                "departure": "上海",
                                "arrival": "北京",
                                "departureTime": "2022-03-17",
                            }
                        }
                    ],
                    "message": "success"
                }
            }
        })
        .then(
            function () {
                console.log("refundTicket expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function createExpectationOverTLS() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080, undefined, true)
        .mockAnyResponse({
            "httpRequest": {
                "method": "GET",
                "path": "/view/cart",
                "queryStringParameters": {
                    "cartId": ["055CA455-1DF7-45BB-8535-4F83E7266092"]
                },
                "cookies": {
                    "session": "4930456C-C718-476F-971F-CB8E047AB349"
                }
            },
            "httpResponse": {
                "body": "some_response_body"
            }
        })
        .then(
            function () {
                console.log("expectation created");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsExact() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'path': '/some/path'
            }, 2, 2)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveAtLeastTwice() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'path': '/some/path'
            }, 2)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveAtMostTwice() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'path': '/some/path'
            }, 0, 2)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveExactlyTwice() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'path': '/some/path'
            }, 2, 2)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveAtLeastTwiceByOpenAPI() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'specUrlOrPayload': 'https://raw.githubusercontent.com/mock-server/mockserver/master/mockserver-integration-testing/src/main/resources/org/mockserver/openapi/openapi_petstore_example.json'
            }, 2)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveExactlyOnceByOpenAPIWithOperation() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verify(
            {
                'specUrlOrPayload': 'org/mockserver/openapi/openapi_petstore_example.json',
                'operationId': 'showPetById'
            }, 1, 1)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestsReceiveExactlyOnceByExpectationIds() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verifyById(
            {
                'id': '31e4ca35-66c6-4645-afeb-6e66c4ca0559'
            }, 1, 1)
        .then(
            function () {
                console.log("request found exactly 2 times");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestSequence() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verifySequence(
            {
                'path': '/some/path/one'
            },
            {
                'path': '/some/path/two'
            },
            {
                'path': '/some/path/three'
            }
        )
        .then(
            function () {
                console.log("request sequence found in the order specified");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestSequenceUsingOpenAPI() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verifySequence(
            {
                'path': '/status'
            },
            {
                'specUrlOrPayload': 'org/mockserver/openapi/openapi_petstore_example.json',
                'operationId': 'listPets'
            },
            {
                'specUrlOrPayload': 'org/mockserver/openapi/openapi_petstore_example.json',
                'operationId': 'showPetById'
            }
        )
        .then(
            function () {
                console.log("request sequence found in the order specified");
            },
            function (error) {
                console.log(error);
            }
        );
}

function verifyRequestSequenceUsingExpectationIds() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .verifySequenceById(
            {
                'id': '31e4ca35-66c6-4645-afeb-6e66c4ca0559'
            },
            {
                'id': '66c6ca35-ca35-66f5-8feb-5e6ac7ca0559'
            },
            {
                'id': 'ca3531e4-23c8-ff45-88f5-4ca0c7ca0559'
            }
        )
        .then(
            function () {
                console.log("request sequence found in the order specified");
            },
            function (error) {
                console.log(error);
            }
        );
}

function retrieveRecordedRequests() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .retrieveRecordedRequests({
            "path": "/some/path",
            "method": "POST"
        })
        .then(
            function (recordedRequests) {
                console.log(JSON.stringify(recordedRequests, null, "  "));
            },
            function (error) {
                console.log(error);
            }
        );
}

function retrieveRecordedLogMessages() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .retrieveLogMessages({
            "path": "/some/path",
            "method": "POST"
        })
        .then(
            function (logMessages) {
                // logMessages is a String[]
                console.log(logMessages);
            },
            function (error) {
                console.log(error);
            }
        );
}

function clearWithRequestPropertiesMatcher() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .clear({
            'path': '/some/path'
        })
        .then(
            function () {
                console.log("cleared state that matches request matcher");
            },
            function (error) {
                console.log(error);
            }
        );
}

function clearWithOpenAPIRequestMatcher() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .clear({
            "specUrlOrPayload": "https://raw.githubusercontent.com/mock-server/mockserver/master/mockserver-integration-testing/src/main/resources/org/mockserver/openapi/openapi_petstore_example.json",
            "operationId": "showPetById"
        })
        .then(
            function () {
                console.log("cleared state that matches request matcher");
            },
            function (error) {
                console.log(error);
            }
        );
}

function clearWithExpectationId() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .clearById("31e4ca35-66c6-4645-afeb-6e66c4ca0559")
        .then(
            function () {
                console.log("cleared state that matches expectation id");
            },
            function (error) {
                console.log(error);
            }
        );
}

function clearRequestsAndLogsWithRequestPropertiesMatcher() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .clear({
            'path': '/some/path'
        }, 'LOG')
        .then(
            function () {
                console.log("cleared state that matches request matcher");
            },
            function (error) {
                console.log(error);
            }
        );
}

function clearRequestAndLogsWithOpenAPIRequestMatcher() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .clear({
            "specUrlOrPayload": "https://raw.githubusercontent.com/mock-server/mockserver/master/mockserver-integration-testing/src/main/resources/org/mockserver/openapi/openapi_petstore_example.json",
            "operationId": "showPetById"
        }, 'LOG')
        .then(
            function () {
                console.log("cleared state that matches request matcher");
            },
            function (error) {
                console.log(error);
            }
        );
}

function reset() {
    var mockServerClient = require('mockserver-client').mockServerClient;
    mockServerClient("localhost", 1080)
        .reset()
        .then(
            function () {
                console.log("reset all state");
            },
            function (error) {
                console.log(error);
            }
        );
}