# Darwin World
Simulation of evolution in the world inhabited by animals. <br>
Every day of simulation consists of:
- removing dead animals
- animals movements
- grass consumption by animals
- reproduction
- new grass growth <br>
Animals movement is defined by its genotype. Newborn animal inherits genotype after parents proportionally to their energy with mutations that can occur during reproduction. <br>
Position for grass to growth on is random mainteining the Pareto principle. There is 80% chance that grass will grow on the 20% of the map near the equator. <br>
User can choose two types of map and two types of genotype. <br>
- Earth - right and left borders of the map are connected. If animal goes beyond the right border, it appears on the left side etc. Upper and lower borders are like walls. Animal bounces from them.
- Hell Portal - if animal goes beyond the border it is transfered to random generated position, but it loses energy (same amount as reproduction)
- Normal genotype - gens are active in order from 1.
- Back ad forward - gens are active from 1 to n (where n is a number of gens) and then in reverse order from n-1 back to 1.<br>
User can define various attributes of the simulation such as:
- map size
- initial number of animals
- number of gens
- initial animal energy
- energy needed for reproduction
- cost of reproduction
- initial number of grass
- number of grass generated every day
- energy gained by consuming grass
- minimum and maximum number of mutations
- types of map and geotype
- simulation speed
Additionally user can save simulation statistics to csv file and load configuration from JSON file.<br>
If many animals stands on the same field, conflicts over consumption and reproduction are solved by following aspects:
- energy 
- age 
- number of children. If it is draw animal is choosen randomly.<br>
During simulation various stats are tracking. User can also choose one of animals present on the map and track it with its stats or track every animal with most popular genotype.<br><br>
*Darwin World* is created by **Szymon Janik** and **Łukasz Kluza** as a student project for an *Object oriented programming* course at *AGH University of Kraków*

