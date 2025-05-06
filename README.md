## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

 # Reflection:

        We approached this project as a very broad idea that could be creatively refined during the process. The concept of a deserted island is a very common one, and we felt like we could do a lot with the idea, and therefore have the player have a similar kind of gratifying experience depending on the choices they made.

        One new thing we tackled was having the map image pop up when you reached the peak of the mountain. This required us to learn about JFrames and JDialogues, which were ultimately very useful skills and felt like it added an extra element to our game.

        We felt satisfied with our implementation. We could have used a more narrative approach and have the player go through a set series of tasks, but we felt like our freedom of choice implementation was more fun and more challenging for us as coders to consider how flexible our classes had to be. If we had unlimited time, we would love to make the fights more exciting (probably by adding more scanners and making them more interactives), as well as having more minigames and sidequests as part of the build-your-own-adventure style, so players have some structure. 

        One piece of feedback we received was during an early code review, which was to include more of the broader methods in the broad Island class, which we ended up implementing. This was helpful in the help method specifically, where we made a broader list before overwriting it in the child classes. It also might've been helpful to figure out how we were going to do movements first, since we ran into a little bit of confusion whether or not we should use a HashMap or not, which could've gone more efficiently.

        If there is one piece of advice we could give our past selves it would have to be writing javadoc comments along the way. Writing majority of the properly formatted comments near the end was tedious. The correctly formatted comments would have kept the classes better orgnized. 

        Our team dynamic worked very well, as we all worked on our own parts of the island before implementing it together. We met weekly and were very organized, which helped us complete the project and feel satisfied with our work.


\Below are notes from our various meetings:

# New TO DO for finilazing the project:

1) Fix inventory bug, Aimen: **YESs** //I thought it was working before, now its acc working

2) Starting to write game ending events/Death:
        --> Rescue depending on number of days survived.**YES** //hopefully should be working
        --> Volcanic eruption based on luck and trying to build a shelter in the wrong place **YES**
        --> In Player: **YES** // also hopefully should be working
            -Health below 10 kills you
            -Hunger above 100 kills you
            -Thirst above 100 kills you
            -Losing a fight decreases Health by 70 ish. 
        --> Fixing the advance day and stats logic to be consistent. **YES** 
            -Advancing a day should decrease your hunger and thirst but increase your health.
            -South shore supply collection should reset every day, should not be able to collect infinite supplies on one day.
        --> TigerMonkeyHut fun treasure (flare that lets you be rescued) **YES** 


3) Play game and make sure we meet all rubric requirements (see 'rubric.md' and below) ALSO address all question comments and add javadocs and other comments for the purpose of being graded 

        From the official final project repo:
        ## Deliverables:
        - Your final codebase -done
        - Your revised annotated architecture diagram -done
        - Design justification (including a brief discussion of at least one alternative you considered) -done
        - A map of your game's layout (if applicable) -done
        - `cheatsheet.md` -done
        - Completed `rubric.md` -done


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

3) make pop up graphic for map at the mountain peak - **YES** but could be better? **FIXED!**
