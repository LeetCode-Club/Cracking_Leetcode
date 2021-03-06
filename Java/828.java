//https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)

// index[26][2] record last two occurrence index for every upper characters.
// Initialise all values in index to -1.
// Loop on string S, for every character c, update its last two occurrence index to index[c].
class Solution {
    public int uniqueLetterString(String S) {
        int[][] index = new int[26][2];
        for (int i = 0; i < 26; ++i) Arrays.fill(index[i], -1);
        int res = 0, N = S.length();
        for (int i = 0; i < N; ++i) {
            int c = S.charAt(i) - 'A';
            res += (i - index[c][1]) * (index[c][1] - index[c][0]);
            index[c] = new int[] {index[c][1], i};
        }
        for (int c = 0; c < 26; ++c)
            res += (N - index[c][1]) * (index[c][1] - index[c][0]);
        return res;
    }
}



class Solution {
    public int uniqueLetterString(String S) {     
        int res = 0;
        if (S == null || S.length() == 0)
            return res;    
        int[] showLastPosition = new int[128];
        int[] contribution = new int[128];
        int cur = 0;
        for (int i = 0; i < S.length(); i++) {
            char x = S.charAt(i);
            cur -= contribution[x]; 
            contribution[x] = (i - (showLastPosition[x] - 1));
            cur += contribution[x]; 
            showLastPosition[x] = i + 1;
            res += cur;
        }   
        return res;
    }
}