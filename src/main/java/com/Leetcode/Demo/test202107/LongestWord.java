package com.Leetcode.Demo.test202107;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 杨胜
 * @date: 2021/7/22 20:14
 * @Description: 词典最长单词
 */
public class LongestWord {


    /**
     * @param words
     * @return 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
     * <p>
     * 若无答案，则返回空字符串。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * words = ["w","wo","wor","worl", "world"]
     * 输出："world"
     * 解释：
     * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
     * 示例 2：
     * <p>
     * 输入：
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出："apple"
     * 解释：
     * "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
     */
    public static String genLongestWord(String[] words) {

        String[] tempString = new String[50];
        for (int i = 0; i < words.length; i++) {
            if (tempString[words[i].length()] != null) {
                tempString[words[i].length()] = tempString[words[i].length()] + " " + words[i];
            } else {
                tempString[words[i].length()] = words[i];
            }
        }

        List<String> longestWordList = new ArrayList<>();
        for (int j = tempString.length - 1; j >= 1; j--) {
            if (tempString[j] == null) {
                continue;
            }

            for (String t : tempString[j].split(" ")) {
                if (isHaveTrueWord(tempString, t, j)) {
                    longestWordList.add(t);
                }
            }
            if (longestWordList.size() > 0) {
                break;
            }
        }
        String[] strings = longestWordList.toArray(new String[longestWordList.size()]);
        String longestWord ="";
        if (strings.length > 0) {
            longestWord = strings[0];
            for (int i = 0; i < strings.length; i++) {
                if (longestWord.compareTo(strings[i]) > 0 && strings[i].length() >= longestWord.length()) {
                    longestWord = strings[i];
                }
            }
        }
        return longestWord;
    }

    public static boolean isHaveTrueWord(String[] stepString, String longestWord, int longSize) {
        if (longSize == 1) {
            return true;
        }
        longestWord = longestWord.substring(0, longestWord.length() - 1);
        if (stepString[longSize - 1] != null && stepString[longSize - 1].contains(longestWord) && isHaveTrueWord(stepString, longestWord, longSize - 1)) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {

        String[] wordsA = {"w", "wo", "wor", "worl", "world"};
        String[] wordsB = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        String[] wordsC = {"l", "le", "lel", "lelelele"};
        System.out.println(genLongestWord(wordsA));
        System.out.println(genLongestWord(wordsB));
        System.out.println(genLongestWord(wordsC));
    }

}
