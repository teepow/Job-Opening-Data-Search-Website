<?php

class MainController
{
    
    function dropTables($pdo) {
        $dbc = new DbController();
        
        $dbc->dropTable("Uses", $pdo);
        $dbc->dropTable("Job", $pdo);
        $dbc->dropTable("Technology", $pdo);
        $dbc->dropTable("Location", $pdo);
        $dbc->dropTable("User", $pdo);
    }
    
    function createDatabase($dbName, $pdo) {
        $dbc = new DbController();
        
        $dbc->createDatabase($dbName, $pdo);
    }
    
    function createTables($pdo) {
        $dbc = new DbController();
        
        $dbc->createUserTable($pdo);
        $dbc->createLocationTable($pdo);
        $dbc->createJobTable($pdo);
        $dbc->createTechnologyTable($pdo);
        $dbc->createUsesTable($pdo);
    }
    
    function populateTechnologyTable($pdo) {
        $tc = new TechnologyController();
        
        $tc->populateTechnologyTable($pdo);
    }
    
    function populateLocationTable($pdo) {        
        $lc = new LocationController();
        
        $lc->populateLocationTable($pdo);
    }
    
    function createRelations($pdo) {
        //CSV file containing zipcodes and their respective population
        $file = "zip_pop.csv";
        
        //Associative array with zipcode as index and pop as value
        $zipsAndPops = $this->getZipsAndPopsArrayFromFile($file);
        
        //file containing the zipcodes
        $zips = fopen("zips.txt", "r") or die("Unable to open file");
        
        $uc = new UsesController();        
        $uc->jobFrameworkAndLanguageHandler($zips, $zipsAndPops, $pdo);
        
        fclose($zips);
    }
    
    private function getZipsAndPopsArrayFromFile($file) {
        $pop_zip = fopen("$file", "r") or die("Unable to open file");
        
        //An array of arrays. Each array has the zipcode at index 0 and the population for that zipcode at index 1
        $rows = array_map('str_getcsv', file('zip_pop.csv'));
        
        //Puts the arrays into a single associative array with the zipcode as the index and its pop as the value
        foreach ($rows as $zip_pop_array) {
            $zipsAndPops[$zip_pop_array[0]] = $zip_pop_array[1];
        }
        
        return $zipsAndPops;        
    }
}

