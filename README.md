# Herblore

## Minimum Viable Product

### What are Reagents?

Each reagent has *x* effect "points". To get that effect into a flask, you need to use another two reagents that share that effect. Similar to Skyrim system, except that a single reagent can have the same effect multiple times. This would be counted towards the potency when brewing using a points system, potentially with diminishing returns.

When combining three reagents to make a flask, the resulting effect is determined by the intersections of the reagents used. The effect that is shared by all three reagents will be the effect of the flask. If there is more than one effect shared by all three reagents, then the effect with the highest sum of points between all three reagents is chosen. If there is no effect shared between all three reagents, then the flask is a "failure" (or perhaps... it creates a Frankenflask?)

Consider the following contextual example:

```
Reagent 1: Health Regen I, Jump Height I, Fortune I
Reagent 2: Health Regen II, Jump Height I, Fortune III
Reagent 3: Health Regen III, Jump Height II
```

This would create an aesthetically-named flask that provides the Health Regen effect with a potency of six.

### Gathering Reagents

Plant reagents can be found and gathered by traveling the world! Biome-typed Perennial Patches spawn in nearly every biome. Right-clicking the patch will harvest it, giving the harvester one or more biome-typed Grimy Herbs. [TENTATIVE IDEA: The picked patch will remain, and eventually, new herbs will grow back.] Grimy Herbs can be cleaned by right-clicking on a water source block with one in your hand, and will yield a weighted-random reagent from a set of biome-appropriate possibilities.

### Making flasks

* Use **Pestle and Mortar** with a reagent in the crafting grid (shapeless) to create the ground version of that reagent.
* Combine three ground reagents and a vial of water in the crafting grid (shapeless) to create an unfinished potion.
* Place the **Athanor** block down. Put the unfinished potion and a piece of fuel onto it. Light the fuel with a flint and steel and wait for the flask to begin bubbling to show that it's complete.
* Take the finished flask from the block.

### Some Test Effects

* Instant health with health regeneration
* Fortune when mining for a short duration
* Jump height increase

### Balancing

* Toxicity System (The Witcher III style)

