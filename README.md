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

***anna 4/23 8pm: I'm confused about how the case thing works in main. do we need to have overwritten methods for collecting stuff in every location class?



from the official final project repo

# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.