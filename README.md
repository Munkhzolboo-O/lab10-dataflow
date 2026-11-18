
# Lab 10 — Data Flow Analysis (DU Pairs)

**Student:** Oyunsuren Munkhzolboo  
**Student ID:** B210910878

## Project

- Java 17+
- Maven
- JUnit 5
- JaCoCo

## Verification

- Tests run: 11
- Failures: 0
- Errors: 0
- Branches covered: 16
- Branches missed: 0

## GCD DU-Pair Analysis


The gcd() method contains 11 DU pairs.

| Variable | Def | Use | Type | Def-clear path |
|---|---|---|---|---|
| x | 13 | 16 | c-use | 13 → 15 → 16 |
| x | 13 | 20 | c-use | 13 → 15 → 20 |
| x | 17 | 16 | c-use | 17 → 18 → 15 → 16 |
| x | 17 | 20 | c-use | 17 → 18 → 15 → 20 |
| y | 13 | 15 | p-use | 13 → 15 |
| y | 13 | 16 | c-use | 13 → 15 → 16 |
| y | 13 | 17 | c-use | 13 → 15 → 16 → 17 |
| y | 18 | 15 | p-use | 18 → 15 |
| y | 18 | 16 | c-use | 18 → 15 → 16 |
| y | 18 | 17 | c-use | 18 → 15 → 16 → 17 |
| tmp | 16 | 18 | c-use | 16 → 17 → 18 |


## Bonus DU-Pair Analysis

## Bonus DU-Pair Analysis

D = definition, U = use.
c-use = computation use, p-use = predicate use.
A kill occurs when a variable is redefined.

| Variable | Definition lines | Use lines |
|---|---|---|
| attendance | 31 | 32 (p), 38 (c) |
| threshold | 31 | 32 (p), 50 (p) |
| a | 38 | 39 (p) |
| present | 35, 40 | 40 (c), 50 (p) |
| streak | 36, 41, 46 | 41 (c), 42 (p), 43 (c) |
| best | 37, 43 | 42 (p), 53 (p) |
| score | 49, 51, 54 | 54 (c), 56 (c) |

### Kill examples

- Line 40 redefines `present`, killing its previous definition.
- Lines 41 and 46 redefine `streak`.
- Line 43 redefines `best`.
- Lines 51 and 54 redefine `score`.

### Tested scenarios

- Empty attendance array
- Threshold not met
- Threshold met
- Five consecutive positive attendance values
- Both bonus conditions met
- Streak reset
- Null attendance
- Negative threshold


## Mutation Testing

To be completed.
