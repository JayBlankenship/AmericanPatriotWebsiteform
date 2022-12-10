<?php
    $email = $_POST['email'];

    //database connection
    $conn = new sqli('localhost','root','','0_test');
    if($conn->connection_error){
        die('Connection Failed : '.$conn->connection_error);
        echo "registration failed";
    }else{
    $stmt = $conn->prepare("insert into registration(email)
            values(?)");
        $stmt->bind_param("s",$email);
        $stmt->execute();
        echo "registration Successfully...";
        $stmt->close();
        $conn->close();
    }
?>