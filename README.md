Architecure Diagram: https://docs.google.com/drawings/d/1Kdz_9xdI5osGZPDPGFbb5GNC8VAh5xtsRH_oBl-vIxs/edit?usp=sharing 


# Combined/Updated TO DO (as of 4/26 9am)
1) all child location classes should be fully built out and cases added to main so that their functionality can be used
ALSO go through and make sure methods like fight(), buildShelter(), buildFire(), etc, are overwritten in places where they should NOT be used (or should be used differently)

    - ANNA: classes built **YES** 
            added to Main **YES**
            help overwritten **YES** 
            ----- DOUBLE CHECK THAT ALL CONDITIONALS ARE IN THERE
            other Island methods overwritten **PART**
    - SHARMILA: classes built ___ 
                added to main ___ 
                help overwritten ___
                other Island methods overwritten ___
        - also architecture diagram update

    - **help is abstract in Island rn, is that good or?
    - AIMEN: 
        -Consistent help() in every child class **YES**
        -New strategy for moving around, consistent for all child classes completed **YES**
                --> note to Sharmila and Anna: we should double check that all the moving methods 
                    //are taking you where you need to go based on the map. 
        -Fix the shelter building logic in island and player, consistent in all child classes. **YES**
                --> NorthShore and LightForest simply allow shelters. 
                --> Mountain and Dark Forest allow shelters too but success depends on luck. 
                --> Stream, Waterfall, Cave and SouthShore do not allow building a shelter by default. 
        -Fix rest in Player **YES**
                --> Works consistently with buildshelter logic and gives specific messages for different locations
        -Fix eat and drink in Player **YES** 
                --> Now it increments actions and deducts from inventory.
                --> Can eat all types of food not just fish.
        -Starting to write game ending events based on luck **In progress**


3) write game endings and luck-related events- resuce, volcanic eruption, other deaths, etc

4) make pop up graphic for map at the mountain peak

5) play game and make sure we meet all rubric requirements (see 'rubric.md' and below) ALSO address all question comments and add javadocs and other comments for the purpose of being graded




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