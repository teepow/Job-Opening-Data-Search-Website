<?php

class LocationController
{
    function populateLocationTable($pdo)
    {
        $file = 'zip_city_state.txt';
        
        $dbc = new \DbController();
        $dbc->populateLocationTableWithFile($file, $pdo);
    }
}

