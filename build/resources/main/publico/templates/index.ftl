<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Monitoreo en Vivo</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <link href="/css/sb-admin.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<#include "navbar.ftl">
<#-- Inicio Cuerpo -->
<div id="content-wrapper">

    <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Charts</li>
        </ol>


        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                Sensor de Temperatura</div>
            <div class="card-body">
                <div id="temperaturaContainer" style="height: 300px; width: 100%;"></div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                Sensor de Humedad</div>
            <div class="card-body">
                <div id="humedadContainer" style="height: 300px; width: 100%;"></div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>


        <p class="small text-center text-muted my-5">
            <em>More chart examples coming soon...</em>
        </p>

    </div>
    <!-- /.container-fluid -->

</div>
<!-- /.content-wrapper -->

<#-- Fin Cuerpo -->
<script src="/jquery.min.js"></script>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
<script src="/js/home.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="/js/demo/chart-area-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="/js/demo/datatables-demo.js"></script>

</body>
</html>