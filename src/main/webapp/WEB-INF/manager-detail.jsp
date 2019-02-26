<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Smart Parking - Manager</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="/vendors/selectFX/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="/vendors/jqvmap/dist/jqvmap.min.css">


    <link rel="stylesheet" href="/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>

<body>
<!-- Left Panel -->

<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">

        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu"
                    aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="../../resources/templates/home.html">
                <h2>Smart Parking</h2>
            </a>
            <a class="navbar-brand hidden" href="../../resources/templates/home.html">
                <h2>SP</h2>
            </a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="../../resources/templates/home.html"> <i class="menu-icon fa fa-home"></i>Home</a>
                </li>

                <h3 class="menu-title">Supervisors</h3><!-- /.menu-title -->
                <li>
                    <a href="../../resources/templates/create-supervisor.html">
                        <i class="menu-icon ti-plus"></i>Create Supervisor
                    </a>
                </li>
                <li>
                    <a href="../../resources/templates/list-supervisors.html">
                        <i class="menu-icon fa fa-table"></i>List Supervisors
                    </a>
                </li>

                <h3 class="menu-title">Parking Lots</h3><!-- /.menu-title -->
                <li>
                    <a href="../../resources/templates/list-parking-lots.html">
                        <i class="menu-icon fa fa-table"></i>List Parking Lots
                    </a>
                </li>
                <li class="nav-button active-pro">
                    <a href="../../resources/templates/login.html">
                        <p>Logout</p>
                    </a>
                </li>

                <!-- IMPORTANT This is showed only if "System Admin" -->
                <h3 class="menu-title">Managers</h3><!-- /.menu-title -->
                <li>
                    <a href="../../resources/templates/create-manager.html">
                        <i class="menu-icon ti-plus"></i>Create Manager
                    </a>
                </li>
                <li>
                    <a href="../../resources/templates/list-managers.html">
                        <i class="menu-icon fa fa-table"></i>List Managers
                    </a>
                </li>
                <!-- END IMPORTANT -->
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside><!-- /#left-panel -->

<!-- Left Panel -->

<!-- Right Panel -->

<div id="right-panel" class="right-panel">

    <!-- Header-->
    <header id="header" class="header">

        <div class="header-menu">

            <div class="col-sm-7">
                <div class="header-left">
                    <a href="#">
                        <h3>Manager - Minh Che</h3>
                    </a>
                </div>
            </div>

            <div class="col-sm-5">
                <div class="user-area dropdown float-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <p style="margin: 0;">Tera Mind</p>
                    </a>

                    <div class="user-menu dropdown-menu">
                        <a class="nav-link" href="../../resources/templates/edit-profile.html"><i
                                class="fa fa-user"></i> My Profile</a>
                        <a class="nav-link" href="../../resources/templates/change-password.html"><i
                                class="fa fa-key"></i> Change Password</a>
                        <hr/>
                        <a class="nav-link" href="../../resources/templates/login.html"><i class="fa fa-power-off"></i>
                            Logout</a>
                    </div>
                </div>
            </div>
        </div>

    </header><!-- /header -->
    <!-- Header-->

    <div class="breadcrumbs">
        <div class="col-sm-7">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Welcome, have a nice day!</h1>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li class="active">
                            <script>
                                let today = new Date();
                                let dd = today.getDate();
                                let mm = today.getMonth() + 1;
                                let yyyy = today.getFullYear();
                                if (dd < 10) {
                                    dd = '0' + dd;
                                }
                                if (mm < 10) {
                                    mm = '0' + mm;
                                }
                                document.write(mm + '/' + dd + '/' + yyyy);
                            </script>
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-header">
                <b>Manager</b>
                <button type="button" class="btn btn-danger float-right">
                    <i class="fa fa-trash-o"></i> Deactive
                </button>
            </div>
            <div class="card-body card-block">
                <div class="form-group">
                    <label for="email" class=" form-control-label">Email</label>
                    <input type="text" id="email" value="aa" class="form-control" disabled>
                </div>
                <div class="form-group col-lg-6">
                    <label for="firstname" class=" form-control-label">First name</label>
                    <input type="text" id="firstname" value="Minh" class="form-control" disabled>
                </div>
                <div class="form-group col-lg-6">
                    <label for="lastname" class=" form-control-label">Last name</label>
                    <input type="text" id="lastname" value="Che" class="form-control" disabled>
                </div>
                <div class="form-group">
                    <label for="phone" class=" form-control-label">Phone number</label>
                    <input type="text" id="phone" value="0702751588" class="form-control" disabled>
                </div>
            </div>
            <div class="card-footer">
                <button type="button" class="btn btn-danger float-left" disabled>
                    <i class="fa fa-ban"></i> Cancel
                </button>
                <button type="button" class="btn btn-primary float-right">
                    <i class="fa fa-pencil"></i> Edit
                </button>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Created Supervisors</strong>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Full Name</th>
                        <th scope="col">Created Date</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href="supervior-detail.html">Mark Otto</a></td>
                        <td>01/26/2019</td>
                        <td><a href="#"><i class="fa fa-trash-o"></i> Deactive</a></td>
                    </tr>
                    <tr>
                        <td><a href="supervior-detail.html">Mark Otto</a></td>
                        <td>01/26/2019</td>
                        <td><a href="#"><i class="fa fa-trash-o"></i> Deactive</a></td>
                    </tr>
                    <tr>
                        <td><a href="supervior-detail.html">Mark Otto</a></td>
                        <td>01/26/2019</td>
                        <td><a href="#"><i class="fa fa-trash-o"></i> Deactive</a></td>
                    </tr>
                    <tr>
                        <td><a href="supervior-detail.html">Mark Otto</a></td>
                        <td>01/26/2019</td>
                        <td><a href="#"><i class="fa fa-trash-o"></i> Deactive</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div><!-- /#right-panel -->

<!-- Right Panel -->

<script>

    function loadList() {
        $('#list-managers').load('/account/listmanagers/1');
    }

    $(function () {
        loadList();
        $(document).on('click', '#button-update-cart', function () {
            var quantity = $('.productQuantity');
            var prod = [];
            quantity.each(function (index, element) {
                prod.push({
                    'proId': element.getAttribute('prodId'),
                    'quantity': element.value
                });
            });
            doAjax(domain + "account/listmanagers/1", "GET", function (data) {
                let accounts = JSON.parse(data);
                $("#list-managers").empty();
                $.each(accounts, function (index, lot) {
                    let tr =
                        "<tr>\n" +
                        "<td><a href=\"../../resources/templates/manager-detail.html\">" + lot.firstName + " " + lot.lastName + " " + "</a></td>\n" +
                        "<td>" + lot.phoneNumber + "</td>\n" +
                        "<td><a href=\"#\"><i class=\"fa fa-trash-o\"></i> Delete</a></td>\n" +
                        "</tr>";
                    $("#list-managers").append(tr);
                });
                buildBootrapTable();
            }, function (error) {
                console.log(error);
            });
        });

</script>

<script src="/vendors/jquery/dist/jquery.min.js"></script>
<script src="/vendors/popper.js/dist/umd/popper.min.js"></script>
<script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/assets/js/main.js"></script>


<!-- <script src="vendors/chart.js/dist/Chart.bundle.min.js"></script>
<script src="assets/js/dashboard.js"></script>
<script src="assets/js/widgets.js"></script> -->
<script src="/vendors/jqvmap/dist/jquery.vmap.min.js"></script>
<script src="/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
<script src="/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>

</body>

</html>