/**
 * @author Victor Oliveira
 */

fun main(args: Array<String>) {
	val arrayOfInts: IntArray = intArrayOf(100, 200, 322, 444, 555, 6213, 712432, 81424, 914, 101)
	val arrayOfIntsOrdered: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	val unorderedArrayOfInts: IntArray = intArrayOf(1000, 2, 322, 4, 555, 6213, 712, 181424, 914, 1)

	System.out.println("isPrime ${isPrime(7)}")
	System.out.println("linearSearch ${linearSearch(arrayOfInts, 712432)}")
	System.out.println("betterLinearSearch ${betterLinearSearch(arrayOfInts, 101)}")
	System.out.println("sentinelLinearSearch ${sentinelLinearSearch(arrayOfInts, 914)}")
	System.out.println("factorial ${factorial(3)}")
	System.out.println("recursiveLinearSearch ${recursiveLinearSearch(arrayOfInts, 444, 0)}")
	System.out.println("binarySearch ${binarySearch(arrayOfIntsOrdered, 6)}")
	System.out.println("recursiveBinarySearch ${recursiveBinarySearch(arrayOfIntsOrdered, 6, 0, arrayOfIntsOrdered.size)}")
	System.out.println("selectionSort ${getArrayAsString(selectionSort(unorderedArrayOfInts.copyOf()))}")
	System.out.println("insertionSort ${getArrayAsString(insertionSort(unorderedArrayOfInts.copyOf()))}")
}

fun getArrayAsString(array: IntArray): String {
	val sb = StringBuilder()
	sb.append("[")
	for (i in array.indices) {
		sb.append(array[i])

		if (i < array.size - 1) {
			sb.append(',')
		}
	}
	sb.append("]")

	return sb.toString()
}

fun isPrime(n: Int): Boolean {
	var isPrime = true

	if (n > 2) {

		if (n.rem(2) == 0) return false

		for (item in 3..n) {
			if (n.rem(item) == 0 && item != n) {
				isPrime = false
				break
			}
		}
	}

	return isPrime
}

/**
 * Ω(n) for best case
 * Θ(n) for worst case
 * O(n) for all cases
 */
fun linearSearch(array: IntArray, item: Int): Int {
	var indexFound = -1

	for (i in array.indices) {
		when (array[i]) {
			item -> indexFound = i
		}
	}

	return indexFound
}

/**
 * Ω(1) for best case
 * Θ(n) for worst case
 * O(n) for all cases
 */
fun betterLinearSearch(array: IntArray, item: Int): Int {
	for (i in array.indices) {
		when (array[i]) {
			item -> return i
		}
	}

	return -1
}

/**
 * Ω(1) for best case
 * Θ(n) for worst case
 * O(n) for all cases
 * Note: Sentinel avoids array size checking for each interaction,
 * despite the fact to be O(n) like in betterLinearSearch in practice
 * sentinelLinear can be faster than betterLinearSearch
 */
fun sentinelLinearSearch(array: IntArray, item: Int): Int {
	var i = 0
	val n = array.size - 1
	val last = array[n]
	array[n] = item

	while (array[i] != item) {
		++i
	}

	array[n] = last
	if (i < n || array[n] == item) {
		return i
	}

	return -1
}

/**
 * O(n!)
 */
fun factorial(n: Int): Int {
	if (n == 0) {
		return 1
	}

	return n * factorial(n - 1)
}

/**
 * Ω(1) for best case
 * Θ(n) for worst case
 * O(n) for all cases
 */
fun recursiveLinearSearch(array: IntArray, item: Int, index: Int): Int {

	if (index == array.size) {
		return -1
	}

	return if (array[index] == item) {
		index
	} else {
		recursiveLinearSearch(array, item, index + 1)
	}
}

/**
 * Θ(1) for best case
 * Θ(lg n) for worst case
 * O(lg n) for all cases
 */
fun binarySearch(array: IntArray, item: Int): Int {

	var begin = 0
	var end = array.size - 1

	while (begin <= end) {
		val mid = (end + begin) / 2

		if (array[mid] == item) return mid

		if (item > array[mid]) {
			begin = mid + 1
		} else if (item < array[mid]) {
			end = mid - 1
		}
	}

	return -1
}

/**
 * Θ(1) for best case
 * Θ(lg n) for worst case
 * O(lg n) for all cases
 */
fun recursiveBinarySearch(array: IntArray, item: Int, begin: Int,
	end: Int): Int {

	if (begin > end) {
		return -1
	}

	var newBegin = begin
	var newEnd = end

	val mid = (end + begin) / 2

	if (array[mid] == item) return mid

	if (item > array[mid]) {
		newBegin = mid + 1
	} else if (item < array[mid]) {
		newEnd = mid - 1
	}

	return recursiveBinarySearch(array, item, newBegin, newEnd)
}

/**
 * Ω(n²) for best case
 * Θ(n²) for worst case
 * O(n²) for all cases
 */
fun selectionSort(array: IntArray): IntArray {

	for (i in array.indices) {

		var indexOfSmallest = i

		for (j in (i + 1)..(array.size - 1)) {
			if (array[j] < array[indexOfSmallest]) {
				indexOfSmallest = j
			}
		}

		/*

		 Above interaction can also be changed for:

		 ((i+1)..(orderedArray.size - 1))
			.asSequence()
			.filter { orderedArray[it] < orderedArray[indexOfSmallest] }
			.forEach { indexOfSmallest = it }

		 */

		val smallestNumber = array[indexOfSmallest]
		array[indexOfSmallest] = array[i]
		array[i] = smallestNumber
	}

	return array
}

/**
 * Θ(n) for best case
 * Θ(n²) for worst case
 * Better than selectionSort if the array is almost ordered, but
 * if move array items is a long costly operation, selectionSort is preferable
 * because insertionSort can move array items Θ(n²) times while selectionSort only
 * moves it Θ(n) times
 */
fun insertionSort(array: IntArray): IntArray {

	for (i in 1 until array.size) {
		val item = array[i]
		var j = i

		while ((j > 0) && (array[j-1] > item)) {
			array[j] = array[j - 1]
			j -= 1
		}

		array[j] = item
	}

	return array
}
