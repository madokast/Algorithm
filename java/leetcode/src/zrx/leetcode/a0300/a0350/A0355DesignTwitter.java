package zrx.leetcode.a0300.a0350;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * <p>
 * 实现 Twitter 类：
 * <p>
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用次函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近  10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * 输出
 * [null, null, [5], null, null, [6, 5], null, [5]]
 * <p>
 * 解释
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
 * twitter.follow(1, 2);    // 用户 1 关注了用户 2
 * twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
 * twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
 * twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 104
 * 所有推特的 ID 都互不相同
 * postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/18 22:33
 */
public class A0355DesignTwitter {
    class Twitter {

        Map<Integer, Set<Integer>> userFollowMap = new HashMap<>();

        List<Integer> posts = new ArrayList<>(30000);
        List<Integer> users = new ArrayList<>(30000);

        int total = 0;

        public Twitter() {

        }

        public void postTweet(int userId, int tweetId) {
            posts.add(tweetId);
            users.add(userId);
            total++;
        }

        public List<Integer> getNewsFeed(int userId) {
            LinkedList<Integer> ret = new LinkedList<>();
            Set<Integer> friends = userFollowMap.get(userId);
            int size = 0;
            if (friends == null) {
                for (int i = total-1; i >= 0; i--) {
                    if(userId==users.get(i)){
                        ret.addLast(posts.get(i));
                        size++;
                        if (size == 10) break;
                    }
                }
            } else {
                for (int i = total-1; i >= 0; i--) {
                    if(friends.contains(users.get(i))){
                        ret.addLast(posts.get(i));
                        size++;
                        if (size == 10) break;
                    }
                }
            }
            return ret;
        }

        public void follow(int followerId, int followeeId) {
            userFollowMap.putIfAbsent(followerId, new HashSet<>());
            userFollowMap.get(followerId).add(followeeId);
            userFollowMap.get(followerId).add(followerId); // self
        }

        public void unfollow(int followerId, int followeeId) {
            userFollowMap.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
        }


    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
}
