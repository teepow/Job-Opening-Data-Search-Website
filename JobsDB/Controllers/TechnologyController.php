<?php

include 'Data Structures/DataStructures.php';

class TechnologyController
{
    
    function populateTechnologyTable($pdo)
    {
        $ds = new DataStructures();
        
        $languagesAndFrameworks = $ds->getLanguagesAndFrameworks();
        
        $id = 1;
        foreach($languagesAndFrameworks as $language => $frameworks) {
            $this->insertNewTechnology("pl", $language, $id++, $pdo);
            
            foreach($frameworks as $framework) {
                $this->insertNewTechnology("fw", $framework, $id++, $pdo);
            }
        }
    }
    
    function getRandomLanguage()
    {
        $ds = new DataStructures();
        $percentages = $ds->getPercentages();
        
        $randomNum = mt_rand(1, 10000) / 100;
        $language = "";
        
        switch ($randomNum) {
            case($randomNum <= $percentages["Java"]):
                $language = "Java";
                break;
                
            case($randomNum <= $percentages["JavaScript"]):
                $language = "JavaScript";
                break;
                
            case($randomNum <= $percentages["C#"]):
                $language = "C#";
                break;
                
            case($randomNum <= $percentages["Python"]):
                $language = "Python";
                break;
                
            case($randomNum <= $percentages["C++"]):
                $language = "C++";
                break;
                
            case($randomNum <= $percentages["C"]):
                $language = "C";
                break;
                
            case($randomNum <= $percentages["PHP"]):
                $language = "PHP";
                break;
                
            case($randomNum <= $percentages["Ruby"]):
                $language = "Ruby";
                break;
                
            case($randomNum <= $percentages["Go"]):
                $language = "Go";
                break;
                
            case($randomNum <= $percentages["Perl"]):
                $language = "Perl";
                break;
                
            case($randomNum <= $percentages["SQL"]):
                $language = "SQL";
                break;
                
            case($randomNum <= $percentages["Scala"]):
                $language = "Scala";
                break;
                
            case($randomNum <= $percentages["Objective-C"]):
                $language = "Objective-C";
                break;
                
            case($randomNum <= $percentages["Apex"]):
                $language = "Apex";
                break;
                
            case($randomNum <= $percentages["R"]):
                $language = "R";
                break;
                
            case($randomNum <= $percentages["Swift"]):
                $language = "Swift";
                break;
                
            case($randomNum <= $percentages["SAS"]):
                $language = "SAS";
                break;
                
            case($randomNum <= $percentages["MATLAB"]):
                $language = "MATLAB";
                break;
                
            case($randomNum <= $percentages["Crystal"]):
                $language = "Crystal";
                break;
                
            case($randomNum <= $percentages["Scratch"]):
                $language = "Scratch";
                break;
        }
        return $language;
    }
    
    function getRandomFrameworkForLanguage($language)
    {
        $ds = new DataStructures();
        $languagesAndFrameworks = $ds->getLanguagesAndFrameworks();
        
        $lastIndexOfFrameworksArray = count($languagesAndFrameworks[$language]) - 1;
        
        $randomIndex = rand(0, $lastIndexOfFrameworksArray);
        
        $framework = $languagesAndFrameworks[$language][$randomIndex];
        
        return $framework;
    }
    
    function createTechnologyTable($pdo)
    {
        $dbc = new DbController();
        $dbc->createTechnologyTable($pdo);
    }
    
    function queryTechnologyId($technology, $pdo)
    {
        $dbc = new DbController();
        return $dbc->queryTechnologyId($technology, $pdo);
    }
    
    private function insertNewTechnology($type, $technology, $id, $pdo) {
        $dbc = new DbController();
        
        $technology = new Technology($type, $technology, $id);
        $dbc->insertTechnologyTuple($technology, $pdo);    
    }
}

