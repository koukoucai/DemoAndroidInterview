package com.koko.algorithm

/**
 * 206 反转链表
 * 输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000

进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * Created by huanggang on 2023/5/6
 */
class ReverseList {
    //迭代
    fun reverseList(head: ListNode?): ListNode? {
        var pre:ListNode? = null
        var next :ListNode? = null
        var curr :ListNode ?= head
        //每次循环，当前节点curr指向前面的节点pre
        //把curr 和pre 往后移
        while (null != curr){
            next = curr.next
            curr.next = pre
            pre = curr
            curr = next
        }
        return pre
    }

    //递归
    fun reverseListII(head: ListNode?): ListNode?{
        if (head == null ||head.next == null) return head
        //递归
        val newHead = reverseListII(head.next)
        //归，反的过程
        head.next?.next = head
        head.next = null
        return newHead
    }
}

fun main(){

}