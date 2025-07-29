# LeetCode: Course Schedule - Detailed Solution Explanation

## 1. Problem Understanding

The "Course Schedule" problem asks whether it's possible to finish all `numCourses` courses given a set of prerequisites.  Each prerequisite is represented as a pair `[course_a, course_b]`, meaning course `course_a` must be taken before course `course_b`.  The goal is to determine if a valid course schedule exists without any circular dependencies.

## 2. Approach / Intuition

This solution uses topological sort via Kahn's algorithm.  Topological sort arranges nodes (courses) in a directed acyclic graph (DAG) such that for every directed edge from node A to node B, node A appears before node B in the ordering.  If a cycle exists (a circular dependency), topological sorting is impossible.

The algorithm works by:

1. **Creating an adjacency list:**  Representing the course dependencies using an adjacency list for efficient access to the courses that depend on each course.
2. **Calculating in-degrees:** Determining the in-degree (number of incoming edges) for each course, representing the number of prerequisites for each course.
3. **Initializing a queue:** Adding all courses with an in-degree of 0 (no prerequisites) to a queue.
4. **Processing the queue:** Iteratively removing courses from the queue, decreasing the in-degree of their dependent courses. If a dependent course's in-degree becomes 0, it's added to the queue.
5. **Checking for completion:** After the queue is empty, if the number of processed courses (`c`) equals the total number of courses, a topological sort was successful, implying no cycles and a valid schedule exists. Otherwise, a cycle exists.


This approach is chosen because it's efficient for detecting cycles in a directed graph and constructing a valid course schedule if one exists.  Other approaches like Depth-First Search (DFS) can also solve this, but Kahn's algorithm is often preferred for its clarity and efficiency in this specific problem.

## 3. Data Structures and Algorithms

* **Data Structures:**
    * `adj`: An adjacency list (ArrayList of ArrayLists) to represent the directed graph of course dependencies. `adj[i]` contains a list of courses that require course `i` as a prerequisite.
    * `ind`: An array to store the in-degree of each course.
    * `q`: A queue (LinkedList) to store courses ready to be processed (courses with in-degree 0).
    * `vis`: (While present in the code, this array isn't actually used in this particular implementation. It's likely a remnant from a different approach or a debugging tool.)

* **Algorithms:**
    * **Kahn's algorithm:** A topological sorting algorithm based on breadth-first search (BFS).


## 4. Code Walkthrough

* **Initialization:**
    * `vis[] = new boolean[numCourses];`:  (Unused in this implementation)
    * `ArrayList<Integer> adj[] = new ArrayList[numCourses];`: Creates an adjacency list to store course dependencies.
    * `int[] ind = new int[numCourses];`: Creates an array to store the in-degrees of each course, initialized to 0.
    * `int c = 0;`: A counter to track the number of processed courses.
    * The loop `for(int i = 0 ; i<numCourses ; i++) adj[i] = new ArrayList<>();` initializes each element of the adjacency list as an empty ArrayList.

* **Building the graph:**
    * `for(int[] e : prerequisites)`: Iterates through the prerequisites. `e[1]` is the prerequisite course, and `e[0]` is the course that depends on it.
    * `adj[e[1]].add(e[0]);`: Adds the dependent course (`e[0]`) to the adjacency list of the prerequisite course (`e[1]`).
    * `ind[e[0]]++;`: Increments the in-degree of the dependent course (`e[0]`).

* **Initializing the queue:**
    * `for(int i = 0; i<numCourses; i++) { if(ind[i] == 0) q.offer(i); }`: Adds all courses with an in-degree of 0 (no prerequisites) to the queue.

* **Topological sort (BFS):**
    * `while(!q.isEmpty())`: Continues until all courses with in-degree 0 are processed.
    * `int node = q.poll();`: Removes a course from the queue.
    * `for(int v : adj[node])`: Iterates through the courses that depend on `node`.
    * `ind[v]--;`: Decrements the in-degree of each dependent course.
    * `if(ind[v] == 0) q.offer(v);`: If the in-degree becomes 0, the dependent course is added to the queue.
    * `c++;`: Increments the count of processed courses.


* **Result:**
    * `return numCourses==c;`: Returns `true` if all courses were processed (no cycles), otherwise `false`.


## 5. Time and Space Complexity

* **Time Complexity:** O(V + E), where V is the number of vertices (courses) and E is the number of edges (prerequisites).  This is because we visit each vertex and edge once.

* **Space Complexity:** O(V + E). The adjacency list `adj` and the in-degree array `ind` take O(V + E) space. The queue `q` can hold at most V elements in the worst case.  The space used by `vis` (though unused) would also add O(V).


The provided solution efficiently solves the Course Schedule problem using a well-established algorithm for topological sorting. The code is clear, well-structured, and adheres to best practices.  The only minor improvement would be to remove the unused `vis` array.
