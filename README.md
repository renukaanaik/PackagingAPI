<pre>
<b> pack-items </b>

API to determine items to be kept in a package based on package constraints.

<ins>Input to API</ins> : a filePath where a file with input items list is kept. Format of file looks like below.

81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)

Each line contains the weight that the package can take (before the colon) and the list of items you need
to choose. Each item is enclosed in parentheses where the 1st number is a item’s index number, the 2nd
is its weight and the 3rd is its cost.

<ins>Output from API</ins> : For each set of items that you put into a package provide a new row in the output string (items’ index
numbers are separated by comma). E.g.
4
-
2,7
8,9

<ins>Package Constraints</ins> :
The total weight is less than or equal to the package limit and the total cost is as large as possible.

1. Max weight that a package can take is ≤ 100
2. Max number of items in a package is 15
3. Max weight and cost of an item is ≤ 100

<b> Architecture </b>

This Api is build on Chain of responsibility pattern where the package constrains are implementented
in the form of rules. Each implementation class of interface PackageRules depicts one rule and is executed in particular order.

<b> Technology Stack </b>
* Java 8
* JUnit
* Mockito

<b> How to package </b>
mvn clean install


<b> How to run </b>

You can include this api as a dependency in you project as below.
&lt;dependencies&gt;
 &lt;dependency&gt;
  &lt;groupId&gt;com.mobiquity&lt;/groupId&gt;
  &lt;artifactId&gt;pack-items&lt;/artifactId&gt;
  &lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;
 &lt;/dependency&gt;
&lt;/dependencies&gt;

<b> Improvements Possible </b>

* More validations to the input can be added for example if in the item list we get two items
with same index then the logic will not work as expected

* More tests could be added

</pre>


	
	