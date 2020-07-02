
<!doctype html>
<html lang="en" dir="ltr" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en" />
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <!-- Generated: 2018-04-16 09:29:05 +0200 -->
    <title>Sensores</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">


    <!-- Dashboard Core -->
    <link href="/dashboard.css" rel="stylesheet" />

    <script src="/jquery.min.js"></script>
    <script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
<#--    <script>-->
<#--        $(document).ready(function () {-->

<#--            var webSocket;-->
<#--            var dps = [];-->
<#--            var temp = 0;-->
<#--            var hum = 0;-->
<#--            var dps2 = [];// dataPoints-->
<#--            var chart = new CanvasJS.Chart("chartContainer", {-->
<#--                zoomEnabled: true,-->
<#--                title :{-->
<#--                    text: "Sensor Temperatura"-->
<#--                },-->
<#--                axisX:{-->
<#--                    title: "Fecha",-->
<#--                    interval: 30,-->
<#--                    intervalType: "second"-->

<#--                },-->
<#--                axisY: {-->
<#--                    title: "Temperatura",-->
<#--                    includeZero: false-->
<#--                },-->
<#--                data: [{-->
<#--                    type: "line",-->
<#--                    dataPoints: dps-->
<#--                }]-->
<#--            });-->

<#--            var chart2 = new CanvasJS.Chart("chartContainer2", {-->
<#--                zoomEnabled: true,-->
<#--                title :{-->
<#--                    text: "Sensor Humedad"-->
<#--                },-->
<#--                axisX:{-->
<#--                    title: "Fecha",-->
<#--                    interval: 30,-->
<#--                    intervalType: "second"-->

<#--                },-->
<#--                axisY: {-->
<#--                    title: "Humedad",-->
<#--                    includeZero: false-->
<#--                },-->
<#--                data: [{-->
<#--                    type: "line",-->
<#--                    dataPoints: dps2-->
<#--                }]-->
<#--            });-->

<#--            var updateInterval = 1000;-->
<#--            var dataLength = 20; // number of dataPoints visible at any point-->

<#--            var updateChart = function (dataPoints) {-->

<#--                var dp = JSON.parse(dataPoints);-->

<#--                dps.push({-->
<#--                    label: dp.fechaGeneracion,-->
<#--                    y: dp.temperatura-->
<#--                });-->

<#--                dps2.push({-->
<#--                    label: dp.fechaGeneracion,-->
<#--                    y: dp.humedad-->
<#--                });-->


<#--                temp = parseInt(document.getElementById("temp").innerText) + 1;-->
<#--                document.getElementById("temp").innerText = temp.toString();-->

<#--                hum = parseInt(document.getElementById("hum").innerText) + 1;-->
<#--                document.getElementById("hum").innerText = temp.toString();-->

<#--                chart.render();-->
<#--                chart2.render();-->
<#--            };-->



<#--            function socketConnect() {-->
<#--                webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/dataSocket");-->
<#--                webSocket.onmessage = function (datos) {-->
<#--                    updateChart(datos.data);-->
<#--                };-->
<#--            }-->

<#--            function connect() {-->
<#--                if (!webSocket || webSocket.readyState === 3) {-->
<#--                    socketConnect();-->
<#--                }-->
<#--            }-->
<#--            updateChart(dataLength);-->
<#--            setInterval(connect, updateInterval);-->

<#--        })-->
<#--    </script>-->
</head>
<body class="">
<div class="page">
    <div class="page-main">
        <div class="header py-4">
            <div class="container">
                <div class="d-flex">
                    <a class="header-brand" href="./index.html">
                        <h3 class="card-title">Retriever de temperatura y humedad</h3>
                    </a>
                </div>
            </div>
        </div>
        <div class="my-3 my-md-5">
            <div class="container">
                <div class="page-header">
                    <h1 class="page-title">
                        Sensores
                    </h1>
                </div>
                <div class="row row-cards">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Temperatura</h3>
                            </div>
<#--                            <div id="chartContainer" style="height: 300px; width: 100%;"></div>-->

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Humedad</h3>
                            </div>
                            <#if dispositivo??>
                                <h4 class="card-title">${dispositivo.fecha}</h4>
                            </#if>
<#--                            <div id="chartContainer2" style="height: 300px; width: 100%;"></div>-->

                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                        <span class="stamp stamp-md bg-red mr-3">
                          <i class="fas fa-thermometer-full"></i>
                        </span>
                                <div>
                                    <h4 class="m-0"><a href="javascript:void(0)">Readings Temperatura <small id="temp">-1</small></a></h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                        <span class="stamp stamp-md bg-blue mr-3">

                            <i class="fas fa-tint"></i>
                        </span>
                                <div>
                                    <h4 class="m-0"><a href="javascript:void(0)">Readings Humedad <small id="hum">-1</small></a></h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>