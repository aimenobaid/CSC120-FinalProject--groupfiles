Architecure Diagram: https://docs.google.com/drawings/d/1Kdz_9xdI5osGZPDPGFbb5GNC8VAh5xtsRH_oBl-vIxs/edit?usp=sharing 

Mtg Notes 4/18

General thoughts / questions
1) any method that will be useful in more than one location class should be implemented in Island
    *may require having exceptions in the child classes if certain things do not apply in one location

2) will the movement set up work? should we be creating new instances of the loc classes with each movement?
    **ask prof

3) for the purposes of getting graded we are prob gonna need a lot more comments explaining what everything does... prob javaDocs too

To-Do For Week 2 pt 2:
1) Finish action methods in Island and all child locations
    - all shared methods in Island (Aimen)
        - write generic help method here, change Main so that it gets called
        - do shore overwrites
    - finish child classes (NS, SS, M/C/W, DF, LF) (Anna, Sharmila)
        - override help to give correct options
        - add cases in Main so all place specific methods can be called
2) start working on game play stuff
    - action / movement tracking for logging days
    - luck points and game endings (resuce, volcano, death, etc)
    - adding cases to accept all necessary input and to call all possible methods that have been implemented in the locations
3) eventually learn how to make pop up graphic for mtn peak map!
4) when game starts hard code new location objects, change all move methods to return these exisitng locations not new instances