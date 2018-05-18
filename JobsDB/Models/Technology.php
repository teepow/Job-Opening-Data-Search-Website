<?php

class Technology
{
    private $type;
    
    private $name;
    
    private $id;
    
    public function __construct($type, $name, $id)
    {
        $this->type = $type;
        $this->name = $name;
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

