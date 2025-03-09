
struct Solution;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        s.len() as i32
    }
}

fn main() {
    println!("{}", Solution::length_of_longest_substring("".to_string()));
}
