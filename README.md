⚙️ Approach

1.) Parse the JSON file using Gson.

2.) Decode each value from its given base using BigInteger.

3.) Apply Lagrange Interpolation to compute the polynomial’s constant term:
      p(0) = Σ [ y_i * Π ( -x_j / (x_i - x_j) ) ]  for all j ≠ i

4.) Print the constant C.

**Sample Output**
Processing file1.json:
The constant C is: 3
For file2.json: Uses points (1 to 7, corresponding large y) → p(0) = -6290016743746469796
