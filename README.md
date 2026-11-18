
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

Three mutations were introduced temporarily into `DataFlowTargets.java`. After each experiment, the original source code was restored using `git restore`.

| Mutation | Modification | Tests run | Failed | Result |
|---|---|---:|---:|---|
| M1 | Remove `streak = streak + 1` | 11 | 2 | Detected |
| M2 | Replace `best = streak` with `best = present` | 11 | 1 | Detected |
| M3 | Add `int total = present + best;` after the loop | 11 | 0 | Survived |

### M1 — Missing Update

Two tests failed:

- `bonus_bothConditionsMet_returnsEight`: expected 8, actual 5.
- `bonus_fiveConsecutive_returnsThree`: expected 3, actual 0.

Removing the update prevented `streak` from increasing. Consequently, the program failed to award the additional three points for five consecutive attendance days.

Evidence: `results/mvn-test-M1.txt`

### M2 — Wrong Variable

One test failed:

- `bonus_streakReset_returnsZero`: expected 0, actual 3.

The mutation incorrectly assigned `present` to `best`. Because `present` counts all attended days rather than consecutive days, the program incorrectly awarded three bonus points.

The existing test detected M2, so an additional M2-killing test was unnecessary.

Evidence: `results/mvn-test-M2.txt`

### M3 — Dead Definition

All 11 tests passed.

The mutation introduced an unused variable:

`int total = present + best;`

This assignment does not affect the returned value. Therefore, the existing JUnit tests could not detect the mutation.

Static analysis or an IDE inspection can identify the unused local variable. The actual inspection warning should be recorded separately after checking the IDE.

Evidence: `results/mvn-test-M3.txt`

### Final Verification

After restoring the original source code, Maven verification succeeded.

- Tests run: 11
- Failures: 0
- Errors: 0
- Skipped: 0
- Branches covered: 16
- Branches missed: 0
- Lines covered: 23
- Lines missed: 1

The single missed line corresponds to the implicit constructor.

Evidence:
- `results/mvn-verify-final.txt`
- `results/jacoco-final.csv`

These experiments demonstrate that data-flow faults can produce incorrect results, while dead definitions may remain undetected by dynamic testing.
