/**
 * @author Victor Oliveira
 */

fun main(args: Array<String>) {
	// @formatter:off

	val arrayOfInts: IntArray = intArrayOf(100, 200, 322, 444, 555, 6213, 712432, 81424, 914, 101)
	val arrayOfIntsOrdered: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	val unorderedArrayOfInts: IntArray = intArrayOf(1000, 2, 322, 4, 555, 6213, 712, 181424, 914, 1)
	val reallySimpleArray: IntArray = intArrayOf(2, 2, 2, 1, 1, 2, 1, 1, 2, 1)
	val simpleArray: IntArray = intArrayOf(4, 1, 5, 0, 1, 6, 5, 1, 5, 3)

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
	System.out.println("mergeSort ${getArrayAsString(mergeSort(unorderedArrayOfInts.copyOf(), 0, unorderedArrayOfInts.size - 1))}")
	System.out.println("quickSort ${getArrayAsString(quickSort(unorderedArrayOfInts.copyOf(), 0, unorderedArrayOfInts.size - 1))}")

	System.out.println("reallySimpleSort ${getArrayAsString(reallySimpleSort(reallySimpleArray.copyOf()))}")

	val countKeysEqual = countKeysEqual(simpleArray.copyOf(), 6);
	System.out.println("countKeysEqual ${getArrayAsString(countKeysEqual)}")

	val countKeysLess = countKeysLess(countKeysEqual, 6)
	System.out.println("countKeysLess ${getArrayAsString(countKeysLess)}")

	System.out.println("rearrange ${getArrayAsString(rearrange(simpleArray.copyOf(), countKeysLess, 6))}")
	System.out.println("countingSort ${getArrayAsString(countingSort(unorderedArrayOfInts.copyOf(), 181424))}")

	// @formatter:on
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

		while ((j > 0) && (array[j - 1] > item)) {
			array[j] = array[j - 1]
			j--
		}

		array[j] = item
	}

	return array
}

/**
 * Θ(n.lg n) for best case
 * Θ(n.lg n) for worst case
 * Θ(n.lg n) for all cases
 * This algorithm has a high constant value
 * This algorithm has to make copies. (More memory and more processing)
 */
fun mergeSort(array: IntArray, start: Int, end: Int): IntArray {

	if (start >= end) {
		return array

	} else {
		val mid = (start + end) / 2

		mergeSort(array, start, mid)
		mergeSort(array, mid + 1, end)
		mergeSentinel(array, start, mid, end)
	}

	return array
}

/**
 * Θ(n) for best case
 * Θ(n) for worst case
 * Θ(n) for all cases
 */
fun mergeSentinel(array: IntArray, start: Int, mid: Int, end: Int): IntArray {
	/* The first +1 of (mid + 1 + 1) and (end + 1 + 1)
	* is because from java docs:
	* @param from the initial index of the range to be copied, inclusive
	* @param to the final index of the range to be copied, exclusive. (EXCLUSIVE)
	*
	* The last +1 of (mid + 1 + 1) and (end + 1 + 1)
	* is the space reserved to the Sentinel Int.MAX_VALUE */
	val left = array.copyOfRange(start, mid + 1 + 1)
	val right = array.copyOfRange(mid + 1, end + 1 + 1)

	left[left.size - 1] = Int.MAX_VALUE
	right[right.size - 1] = Int.MAX_VALUE

	var i = 0
	var j = 0

	for (k in start..end) {

		if (left[i] <= right[j]) {
			array[k] = left[i]
			i++
		} else {
			array[k] = right[j]
			j++
		}
	}

	return array
}

/**
 * Θ(n.lg n) for best case
 * Θ(n²) for worst case
 * Good choice considering the medium case
 */
fun quickSort(array: IntArray, start: Int, end: Int): IntArray {

	if (start >= end) {
		return array

	} else {
		val pivot = partition(array, start, end)

		quickSort(array, start, pivot - 1)
		quickSort(array, pivot + 1, end)
	}

	return array
}

/**
 * Θ(n) for all cases
 */
fun partition(array: IntArray, start: Int, end: Int): Int {
	val pivot = array[end]
	var separator = start

	/*
	 * In kotlin until is equal to end - 1
	 */
	for (i in start until end) {

		if (array[i] <= pivot) {
			swap(array, separator, i)
			separator++
		}
	}
	/*
	 *	The last place requires no test cause it's pivot
	 */
	swap(array, separator, end)
	return separator
}

private fun swap(array: IntArray, i: Int, j: Int) {
	val temp = array[i]
	array[i] = array[j]
	array[j] = temp
}

/**
 * Θ(n) for all cases
 */
fun reallySimpleSort(array: IntArray): IntArray {

	var k = 0
	for (i in 0 until array.size) {
		if (array[i] == 1) {
			k++
		}
	}
	/*
	 * Code above could be:
	 * val k = (0 until array.size).count { array[it] == 1 }
	 */
	for (i in 0..k) {
		array[i] = 1
	}
	for (i in k + 1 until array.size) {
		array[i] = 2
	}

	return array
}

/**
 * Θ(n) for all cases
 */
fun countKeysEqual(array: IntArray, arrayLimit: Int): IntArray {
	val equal = IntArray(arrayLimit + 1, { 0 })

	for (i in 0 until equal.size) {
		equal[i] = 0
	}

	for (i in 0 until array.size) {
		val key = array[i]
		equal[key] += 1
	}
	/*
	 * Code above can be:
	 * (0 until array.size)
	 * 	.map { array[it] }
	 *	.forEach { equal[it] += 1 }
	 */

	return equal
}

/**
 * Θ(n) for all cases
 */
fun countKeysLess(equal: IntArray, arrayLimit: Int): IntArray {
	val less = IntArray(arrayLimit + 1)
	less[0] = 0

	for (j in 1..arrayLimit) {
		less[j] = less[j - 1] + equal[j - 1]
	}

	return less
}

/**
 * Θ(n) for all cases
 */
fun rearrange(array: IntArray, less: IntArray, arrayLimit: Int): IntArray {
	val newArray = IntArray(array.size)
	val next = IntArray(arrayLimit + 1)

	for (j in 0..arrayLimit) {
		next[j] = less[j] + 1
	}

	for (i in 0 until array.size) {
		val key = array[i]
		val index = next[key]
		newArray[index-1] = array[i]
		next[key] += 1
	}

	return newArray
}

/**
 * Θ(n) for all cases when arrayLimit is constant
 *
 * Counting Sort wins the lower limit Ω(n.lg n)
 * We can't use countingSort with float numbers or Strings
 */
fun countingSort(array: IntArray, arrayLimit: Int): IntArray  {
	var equal = countKeysEqual(array, arrayLimit)
	val less = countKeysLess(equal, arrayLimit)

	return rearrange(array, less, arrayLimit)
}