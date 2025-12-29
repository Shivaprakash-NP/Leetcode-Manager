### Problem Understanding

The problem "Pyramid Transition Matrix" asks us to determine if a pyramid of blocks can be constructed given a starting `bottom` row of blocks and a set of `allowed` transitions. Each block is represented by a single character. An `allowed` transition is a three-character string, say "ABC". This means if block 'A' is directly to the left of block 'B' in a lower row, then the block directly above them in the next higher row *can* be 'C'. We need to build the pyramid level by level until only a single block remains at the top. If it's possible to build such a pyramid following the rules, we return `true`; otherwise, `false`.

For example, if `bottom = "BCD"`