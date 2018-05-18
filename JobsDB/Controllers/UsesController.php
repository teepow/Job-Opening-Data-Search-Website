<?php

class UsesController
{
    function insertUses($uses, $pdo)
    {
        $dbc = new DbController();
        $dbc->insertUsesTuple($uses, $pdo);
    }
    
    function jobFrameworkAndLanguageHandler($zips, $zipsAndPops, $pdo)
    {
        $jobId = 1;
        while(($zip = fgets($zips)) !== FALSE) {
            $pop = $this->getPopForZip($zip, $zipsAndPops);
            
            $numJobs = $this->getNumberOfJobsByPopulationSize($pop);
            
            for($i = 0; $i < $numJobs; $i++) {
                $this->insertNewJob($jobId, $zip, $pdo);
                
                $this->createLanguageAndFrameworkForJob($jobId, $pdo);
                
                $jobId++;
            }  
        }  
    }
    
    private function getPopForZip($zip, $zipsAndPops) {
        $zipString = (string) $zip;
        $zipString = str_replace("\n", '', $zipString);
        
        //PHP gives a warning if there is no population for a zipcode
        //This handles the warning
        set_error_handler(array($this, 'warningHandler'));
        $pop = $zipsAndPops[$zipString];
        restore_error_handler();
        
        return $pop;
    }
    
    private function warningHandler() {
        return;
    }
    
    private function getNumberOfJobsByPopulationSize($pop) {
        $numJobs = $pop / 10000;
        $numJobs = floor($numJobs);
        
        return $numJobs;
    }
    
    private function insertNewJob($jobId, $zip, $pdo) {
        $jc = new JobController();
        
        $job = new Job($zip, $jobId);
        $jc->insertJob($job, $pdo);
    }
    
    private function createLanguageAndFrameworkForJob($jobId, $pdo) {
        $tc = new TechnologyController();
        
        $language = $tc->getRandomLanguage();
        
        $this->insertNewUsesRelationForJobAndLanguage($jobId, $language, $pdo);
        //SQL and Scratch do not have frameworks associated with them
        if($language != "SQL" && $language != "Scratch") {
            $this->insertNewUsesRelationForJobAndFramework($jobId, $pdo, $language);
        }
    }
    
    private function insertNewUsesRelationForJobAndLanguage($jobId, $language, $pdo) {
        $usesTuple = $this->getUsesTupleForLanguage($language, $jobId, $pdo);
        $this->insertUses($usesTuple, $pdo);
    }
    
    private function getUsesTupleForLanguage($language, $jobId, $pdo) {
        $tc = new TechnologyController();
        
        $techId = $tc->queryTechnologyId($language, $pdo);
        $usesTuple = new Uses($techId, $jobId);
        $usesTuple->language = $language;
        
        return $usesTuple;
    }
    
    private function insertNewUsesRelationForJobAndFramework($jobId, $pdo, $language) {
        $tc = new TechnologyController();
        
        $framework = $tc->getRandomFrameworkForLanguage($language);
        $usesTuple = $this->getUsesTupleForFramework($framework, $jobId, $pdo);
        $this->insertUses($usesTuple, $pdo);
    }
    
    private function getUsesTupleForFramework($framework, $jobId, $pdo) {
        $tc = new TechnologyController();
        
        $techId = $tc->queryTechnologyId($framework, $pdo);
        $usesTuple = new Uses($techId, $jobId);
        
        return $usesTuple;
    }
}

