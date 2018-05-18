<?php

class Job
{
    private $zipcode, $id;
    
    public function __construct($zipcode, $id)
    {
        $this->zipcode = $zipcode;
        $this->id = $id;
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

