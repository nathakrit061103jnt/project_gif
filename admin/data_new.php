<?php
session_start();
if (isset($_SESSION["aid"])) {
    ?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ข่าว</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <?php include_once "./sidebar.php";?>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <?php include_once "./navbar.php";?>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">ข้อมูลการเเจ้งข่าว</h1>
                    <p class="mb-4">รายละเอียดห้องพัก</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">ข่าว</h6>
                        </div>
                        <div class="card-body">
                            <a href="./insert_new.php" type="button" class="btn btn-primary mb-3">เพิ่มข่าว</a>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ไอดีข่าว</th>
                                            <th>หัวข้อข่าว</th>
                                            <th>วันที่เเจ้งข่าว</th>
                                            <th>จัดการ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php

    include "./configs/connectDB.php";

    $sql = "SELECT * FROM  news WHERE aid='" . $_SESSION["aid"] . "';";

    $result = mysqli_query($conn, $sql);

    while ($data = mysqli_fetch_assoc($result)) {
        ?>
                                        <tr>
                                            <td><?=$data["news_id"]?></td>
                                            <td><?=$data["news_title"]?></td>

                                            <td class="">
                                                <?php
$date = date_create($data["news_date"]);
        $y = date_format($date, "Y");
        $y = intval($y) + 543;
        echo date_format($date, "d-m") . "-$y";

        ?>
                                            </td>
                                            <td class="py-0 align-middle">
                                                <div class="btn-group btn-group-sm">
                                                    <a href="./show_new.php?news_id=<?=$data["news_id"]?>"
                                                        class="btn btn-info"><i class="fas fa-eye"></i></a>
                                                    <a href="edit_new.php?news_id=<?=$data["news_id"]?>"
                                                        class="btn btn-warning"><i class="fas fa-edit"></i></a>
                                                    <button onclick="deleteNewFunc(<?=$data['news_id']?>)"
                                                        class="btn btn-danger"><i class="fas fa-trash"></i></button>
                                                </div>
                                            </td>
                                        </tr>

                                        <?php
}

    ?>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <?php
include_once "./footer.php";
    ?>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <script>
    function deleteNewFunc(news_id) {
        var r = confirm("ต้องการลบข่าวหรือไม่!");
        if (r == true) {
            location = `data_new.php?deleteNew=req&news_id=${news_id}`;
        } else {
            location = "data_new.php";
        }
    }
    </script>

    <?php
if (isset($_GET["deleteNew"]) && isset($_GET["news_id"])) {
        $news_id = $_GET["news_id"];
        $sqlD = "DELETE FROM news WHERE news_id='$news_id'";

        if (mysqli_query($conn, $sqlD)) {
            // echo "<script>";
            // echo "alert('ลบข่าวเรียบร้อย');";
            // echo "</script>";

            echo "<script>";
            echo "location = 'data_new.php';";
            echo "</script>";

        } else {

            echo "<script>";
            echo "alert('ไม่สามารถลบข่าวได้');";
            echo "</script>";

            echo "<script>";
            echo "location = 'data_new.php';";
            echo "</script>";

        }

    }
    ?>



    <script>
    $('#dataTable').dataTable({
        "order": [
            [0, 'desc']
        ]
    });
    </script>



    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

</body>

</html>
<?php
} else {
    echo "<script>";
    echo "location = './login.php';";
    echo "</script>";
}
?>