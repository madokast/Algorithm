#include <iostream>
using namespace std;

class Solution {
    public:
        int lengthOfLongestSubstring(string s) {
            int s_len = s.length();
            if (s_len == 0) {
                return 0;
            }
            bool map[256] = {false};
            int i = 0, longest = 1;
            map[s[0]] = true;

            for (int j=1; j<s_len; j++) {
                char c = s[j];
                while (map[c] == true) {
                    map[s[i]] = false;
                    i++;
                }
                map[c] = true;
                int cur_length = j-i+1;
                if (cur_length > longest) {
                    longest = cur_length;
                }
            }

            return longest;
        }
};

int main() {
    std::cout << Solution().lengthOfLongestSubstring("ab"s) << std::endl;
    std::cout << Solution().lengthOfLongestSubstring("abcabcbb"s) << std::endl;
    std::cout << Solution().lengthOfLongestSubstring("bbbbb"s) << std::endl;
    std::cout << Solution().lengthOfLongestSubstring("pwwkew"s) << std::endl;
}