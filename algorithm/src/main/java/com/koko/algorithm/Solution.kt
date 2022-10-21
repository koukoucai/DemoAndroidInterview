package com.koko.algorithm

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
 

提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案
进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？


 * Created by huanggang on 2022/10/21
 */
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var i:Int = 0
        var j:Int = 0;
        for((index1,num) in nums.withIndex()){
            for (index2 in index1+1..nums.size-1){
                if ((num + nums[index2]) == target){
                    i = index1
                    j = index2
                    return intArrayOf(i,j)
                }
            }
        }
        return intArrayOf(i,j)
    }
}

fun main(){

}