<?php

class DbController
{
    
    function createDatabase($dbName, $pdo)
    {
        $sql = "CREATE DATABASE " . $dbName;
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        }
        
    }
    
    function dropTable($tableName, $pdo)
    {
        $sql =  "DROP TABLE IF EXISTS $tableName";
        
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        } 
    }
    
    function createUserTable($pdo)
    {
        $sql =  "CREATE TABLE User (id INT AUTO_INCREMENT, username VARCHAR(15), password VARCHAR(15), 
                PRIMARY KEY (id))";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        }         
    }
    
    function createLocationTable($pdo)
    {
        $sql = "CREATE TABLE Location (zipcode INT(5), city VARCHAR(50), state VARCHAR(2), 
                PRIMARY KEY(zipcode))";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        }         
    }
    
    function createJobTable($pdo)
    {
        $sql = "CREATE TABLE Job (id INT AUTO_INCREMENT, created_on DATETIME DEFAULT CURRENT_TIMESTAMP, u_id INT(11),zipcode INT(5), 
                FOREIGN KEY(u_id) REFERENCES User(id)  ON DELETE CASCADE, 
                FOREIGN KEY(zipcode) REFERENCES Location (zipcode), 
                PRIMARY KEY(id))";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        }  
    }
    
    function createTechnologyTable($pdo)
    {
        $sql = "CREATE TABLE Technology (id INT AUTO_INCREMENT, name VARCHAR(35), type VARCHAR(2), 
                PRIMARY KEY(id))";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        } 
    }
    
    function createUsesTable($pdo)
    {
        $sql = "CREATE TABLE Uses (j_id INT, t_id INT,
                PRIMARY KEY(j_id, t_id), 
                FOREIGN KEY(j_id) REFERENCES Job(id) ON DELETE CASCADE, 
                FOREIGN KEY(t_id) REFERENCES Technology(id))";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        }         
    }
    
    function populateLocationTableWithFile($file, $pdo)
    {
        $sql = "LOAD DATA LOCAL INFILE '$file' INTO TABLE Location";
        try {
            $pdo->exec($sql);            
        } catch (Exception $e) {
            echo ("Error $e");
        }
    }
    
    function insertJobTuple($job, $pdo)
    {
        $sql = "INSERT INTO Job (id, zipcode) VALUES ('" . $job->id . "','" . $job->zipcode . "')";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        } 
    }
    
    function insertUsesTuple($uses, $pdo)
    {
        $sql = "INSERT INTO Uses (j_id, t_id) VALUES ('" . $uses->jobId . "','" . $uses->techId . "')";
        try {
            $pdo->exec($sql);            
        } catch (Exception $e) {
        } 
    }
    
    function insertTechnologyTuple($technology, $pdo)
    { 
        $sql = "INSERT INTO Technology (id, name, type) 
                VALUES(" . $technology->id . ", '" . $technology->name . "', '" . $technology->type . "')";
        try {
            $pdo->exec($sql);
        } catch (Exception $e) {
        } 
    }
    
    function queryTechnologyId($technology, $pdo)
    {
        $sql = "SELECT id FROM Technology WHERE name= :technology";
        $query = $pdo->prepare($sql);
        $query->execute(array(':technology' => $technology));
        $techId = $query->fetchColumn();
        
        return $techId;
    }
    
}

