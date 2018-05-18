<?php

    require_once 'Database/DbConnect.php';
    require 'Database/DbController.php';
    
    require 'Models/Job.php';
    require 'Models/Uses.php';
    require 'Models/Technology.php';
    
    require 'Controllers/MainController.php';
    require 'Controllers/JobController.php';
    require 'Controllers/LocationController.php';
    require 'Controllers/TechnologyController.php';
    require 'Controllers/UsesController.php';
    
    $DbObj = new DbConnect();
    
    //This is the database connection
    $pdo = $DbObj->getDbConnection();
    
    $dbName = "HitDB";
    
    $mc = new MainController();
    
    #$mc->createDatabase($dbName, $pdo);
    
    echo "Dropping tables...\n";
    $mc->dropTables($pdo);
    
    echo "Creating tables...\n";
    $mc->createTables($pdo);
  
    echo "Populating Technology table...\n";
    $mc->populateTechnologyTable($pdo);
    
    echo "Populating Location table...\n";
    $mc->populateLocationTable($pdo);
    
    echo "Creating relations...\n";
    $mc->createRelations($pdo);
    
    echo "Complete!\n";

    