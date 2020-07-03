$(document).ready(function () {

  var webSocket;
  var tempDataPoints = [];
  var humDataPoints = []
  var updateInterval = 1000;
  var dataLength = 20;
  
  var chart = new CanvasJS.Chart("temperaturaContainer", {
    zoomEnabled: true,

    axisX:{
      title: "Muestras",
      interval: 20,
      intervalType: "second"

    },
    axisY: {
      title: "Temperatura",
      includeZero: false
    },
    data: [{
      type: "line",
      dataPoints: tempDataPoints
    }]
  });

  var chart2 = new CanvasJS.Chart("humedadContainer", {
    zoomEnabled: true,
    axisX:{
      title: "Muestras",
      interval: 20,
      intervalType: "second"

    },
    axisY: {
      title: "Humedad",
      includeZero: false
    },
    data: [{
      type: "line",
      dataPoints: humDataPoints
    }]
  });


  var updateChart = function (dataPoints) {

    var dp = JSON.parse(dataPoints);
    console.log(dp);
    tempDataPoints.push({
      label: dp.fechaGeneracion,
      y: dp.temperatura
    });

    humDataPoints.push({
      label: dp.fechaGeneracion,
      y: dp.humedad
    });

    chart.render();
    chart2.render();
  };


  function socketConnect() {
    webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/lecturaSocket");
    webSocket.onmessage = function (datos) {
      updateChart(datos.data);
    };
  }

  function connect() {
    if (!webSocket || webSocket.readyState === 3) {
      socketConnect();
    }
  }
  updateChart(dataLength);
  setInterval(connect, updateInterval);

})