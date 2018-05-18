<?php

class DataStructures
{
    
    /*
     * A Two-dimensional associative array. Each language is
     * associated with a set of frameworks. SQL and Scratch
     * are not associated with any frameworks.
     */
    private $languagesAndFrameworks = array(
        "Java"          => array("Spring", "Play", "Spring Boot"),
        "JavaScript"    => array("Angular", "React", "JQuery"),
        "Python"        => array("Django", "Flask", "Pyramid"),
        "C#"            => array(".NET"),
        "C++"           => array("STL", "Boost"),
        "C"             => array("APR", "C Algorithms", "CPL"),
        "PHP"           => array("Laravel", "Cake PHP", "Symfony"),
        "Ruby"          => array("Rails", "Sinatra"),
        "Go"            => array("Beego", "Buffalo"),
        "Perl"          => array("Catalyst"),
        "SQL"           => array(),
        "Scala"         => array("Scalatra", "Finatra"),
        "Objective-C"   => array("CloudKit", "Mojolicious"),
        "Apex"          => array("Apex Up", "Trigger Framework"),
        "R"             => array("Coala", "Shiny"),
        "Swift"         => array("Cocoa Touch", "AVFoundation"),
        "SAS"           => array("Fraud Framework"),
        "MATLAB"        => array("Simulink"),
        "Crystal"       => array("Kemal"),
        "Scratch"       => array()
    );
    
    /*
     * Associative array that uses the percentages from
     * the second infographic here: https://stackify.com/popular-programming-languages-2018
     * to distribute languages based on demand according to Indeed.com.
     */
    private $percentages = array(
        "Java"          => 21.24,
        "JavaScript"    => 40.85,
        "C#"            => 51.79,
        "Python"        => 61.30,
        "C++"           => 68.24,
        "C"             => 74.80,
        "PHP"           => 78.82,
        "Ruby"          => 82.39,
        "Go"            => 85.66,
        "Perl"          => 88.28,
        "SQL"           => 90.68,
        "Scala"         => 92.15,
        "Objective-C"   => 93.55,
        "Apex"          => 94.85,
        "R"             => 96.15,
        "Swift"         => 97.37,
        "SAS"           => 98.16,
        "MATLAB"        => 98.80,
        "Crystal"       => 99.43,
        "Scratch"       => 100
    );
    
    public function getLanguagesAndFrameworks()
    {
        return $this->languagesAndFrameworks;
    }
    
    public function getPercentages()
    {
        return $this->percentages;
    }
}

