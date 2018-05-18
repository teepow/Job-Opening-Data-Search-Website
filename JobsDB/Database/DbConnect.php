<?php

class DbConnect
{
    private $host = "cs336.cvvd0wxa95qe.us-east-2.rds.amazonaws.com";
    private $username = "tom";
    private $password = "CS336!Rutgers.#hit";
    private $database = "hitdb";
    
    public function getDbConnection()
    {
        try {
            $pdo = new PDO("mysql:host=$this->host;dbname=$this->database", $this->username, $this->password, 
                //Allow PDO to access local files
                array(PDO::MYSQL_ATTR_LOCAL_INFILE => true));
        } catch (PDOException $e) {
            print "Error!: " . $e->getMessage();
            die();
        }
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        return $pdo;
    }
}

