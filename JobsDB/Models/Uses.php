<?php

class Uses
{
    private $techId, $jobId, $language, $framework;
    
    public function __construct($techId, $jobId)
    {
        $this->techId = $techId;
        $this->jobId = $jobId;
    }
    
    public function __get($property)
    {
        if (property_exists($this, $property)) {
            return $this->$property;
        }
    }
    
    public function __set($property, $value)
    {
        if(property_exists($this, $property)) {
            $this->$property = $value;
        }
    }
}

