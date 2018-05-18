<?php

class JobController
{
    function insertJob($job, $pdo)
    {
        $dbc = new DbController();
        $dbc->insertJobTuple($job, $pdo);
        
    }
}

