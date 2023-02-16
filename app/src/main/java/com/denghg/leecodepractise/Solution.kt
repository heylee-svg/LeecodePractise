package com.denghg.leecodepractise

class Solution {


    fun findNumberIn2DArray(target: Int): Boolean {
        var matrix = arrayOf(
            intArrayOf(1, 4, 7, 11, 15),
            intArrayOf(2, 5, 8, 12, 19),
            intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24),
            intArrayOf(18, 21, 23, 26, 30)
        )
        //i代表行 j代表列
        var i = matrix[0].size - 1
        var j = 0
        while (i > 0 && j < matrix[0].size) {
            if (matrix[i][j] > target) {
                i--
            } else if (matrix[i][j] < target) {
                j++
            } else {
                return true
            }
        }
        return false
    }

    /**
     * 输出任意一个重复的数字
     */
    fun findRepeatNumber(): Int {
        var nums = arrayOf(2, 3, 1, 0, 2, 5, 3)

        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] == nums[j]) {
                    return nums[i]
                }
            }
        }

        return 0
    }

    /**
     * 输出所有重复的数字
     */
    fun findAllRepeatNumber(nums: IntArray): Map<Int, Int> {
        return mapOf()
    }

    /**
     * 冒泡算法
     */
    fun bubleMath() {

    }

    /**
     * 二分查找算法
     */
    fun binraySearch() {

    }





}