### Problem Understanding

The problem asks us to identify all individuals who will eventually learn a secret, given a set of meetings and two initial secret holders. We are given `n` people, indexed from 0 to `n-1`. Initially, person 0 and `firstPerson` both know the secret at time 0. Meetings are described by `[personA, personB, time]`, meaning personA and personB meet at the specified `time`. The rule for secret sharing is: if two people meet at time `t`, and *both* of them already know the secret *at or before* time `t`, then they will share the secret with each other. We need to return a list of all people who eventually come to know the secret.

The key constraints are:
1.  Initial secret holders: Person 0 and `firstPerson` at time 0.
2.  Time-dependent sharing: A person must know the secret *before or at* the meeting time to share it.
3.  Propagation: If one person knows the secret, and they meet someone who doesn't, and the time condition is met, the second person learns it.

### Approach / Intuition

This problem can be modeled as a graph problem where people are nodes and meetings are edges. The "secret propagation" over time, seeking the *earliest* time a person learns the secret, strongly suggests a variation of Dijkstra's algorithm.

Here's the intuition:

1.  **State Representation:** For each person, we need to track the earliest time they learn the secret. Let's call this `time[i]` for person `i`. Initially, `time[0]` and `time[firstPerson]` are 0, and all other `time[i]` are effectively infinity (meaning they don't know the secret yet).
2.  **Priority Queue for Exploration:** We use a min-priority queue to store `(person, secret_knowledge_time)` pairs. The priority queue will always give us the person who knows the secret at the earliest time among all candidates. This is crucial for Dijkstra's correctness.
3.  **Dijkstra's Adaptation:**
    *   When we extract a person `u` from the priority queue with an associated secret knowledge time `t_u`, it means we've found the earliest time `u` learns the secret.
    *   Now, we look at all meetings `(u, v, t_meeting)` involving `u`.
    *   For `v` to learn the secret from `u` during this meeting, two conditions must be met:
        *   `u` must know the secret *at or before* the meeting time: `t_u <= t_meeting`.
        *   Learning the secret through this meeting must be *earlier* than any previously known time for `v`: `t_meeting < time[v]`.
    *   If both conditions are true, `v` learns the secret at `t_meeting`. We update `time[v] = t_meeting` and add `(v, t_meeting)` to the priority queue.
4.  **Initial Connection:** To ensure person 0 and `firstPerson` can immediately share secrets with each other's "circles," we can model an "initial meeting" between them at time 0. This simplifies the initial state handling within the Dijkstra's loop.

By processing people in increasing order of their secret knowledge time, we guarantee that when we consider a meeting, the person sharing the secret (the one extracted from the PQ) has their earliest secret knowledge time correctly established. This allows for correct time-dependent propagation.

### Data Structures and Algorithms

1.  **Adjacency List (`ArrayList<int[]>[] adj`):** Used to represent the graph. `adj[i]` stores a list of `[neighbor_person_id, meeting_time]` pairs, indicating that person `i` met `neighbor_person_id` at `meeting_time`. This allows efficient retrieval of all meetings for a given person.
2.  **Earliest Secret Time Array (`int[] time`):** An array of size `n` where `time[i]` stores the earliest time person `i` is known to have learned the secret. It's initialized with `Integer.MAX_VALUE` for unknown times and 0 for initial secret holders.
3.  **Priority Queue (`PriorityQueue<int[]>`):** A min-priority queue is used to implement Dijkstra's algorithm. It stores `int[]` arrays of the form `[person_id, secret_knowledge_time]`, ordered by `secret_knowledge_time` (the second element).
4.  **Dijkstra's Algorithm:** The core algorithm used to find the earliest secret knowledge time for all reachable people, adapted to handle the time-dependent sharing condition.

### Code Walkthrough

```java
class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 1. Initialize Adjacency List
        // adj[i] will store a list of int[] where each int[] is {neighbor_id, meeting_time}
        ArrayList<int[]>[] adj = new ArrayList[n];
        // Initialize each inner ArrayList
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        // 2. Populate Ad