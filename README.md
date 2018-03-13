# KwetterBackendAPI
Kwetter backend made in Java EE. Kwetter is the fictional program based on Twitter

To use this backend
- Install latest release version
- MySQL installed with Payara 5.0
- Create local database

Api calls are made with the /api annotation from localhost:8080/KwetterBackend_Maxime/api/ if run locally
When these 'input' tags are available. Write the input given from the description and the data can be accessed.

# Users and user information
To find all users and their written tweets
- api/users

To create a new user
- api/users/create
- (JSON format)

To create a tweet from an existing user
- api/users/addtweet/'user-id-data'

To find a specific user by it's ID
- api/users/finduser/'id-of-user'

To remove a specific user by it's ID
- api/users/removeuser/'id-of-user'

To find a specific tweet by it's ID, written by a certain user.
- api/users/findtweet/'id-of-tweet'

To remove a specific tweet by it's ID, written by a certain user.
- api/users/removetweet/'id-of-tweet'

To find a specific user by it's username.
- api/users/finduserbyusername/'username'
  
To follow a specific user by it's username
- api/users/followuser/'user-data'/'usertofollow-data'

To unfollow a specific user by it's username
- api/users/unfollowuser/'user-data'/'usertounfollow-data'

To get following users from a single user
- api/users/getfollowing/'username-data'

To get followers from a single user
- api/users/getfollowers/'username-data'

To promote a user with a new user role
- api/users/promote

To demote a user with a new suer role
- api/users/demote

to update a user with new user information
- api/users/updateuser

# Tweets and tweet information
To find all tweets
- api/tweets

To find a specific tweet by it's ID
- api/tweets/findtweet/'id'
  
To find a specific tweet by it's content (has wildcard pattern in it)
- api/tweets/findtweetbycontent/'content-key'

To add a mention to an existing tweet
- api/tweets/addmention/'tweet-id-data'/'username-data'

To find all written tweets from a specific user by it's username
- api/users/gettweetsfromuser/'username-data'
- api/users/liketweet/'username-data'/'tweet-id-data'

To add a new tweet from a single user with their own context, tags and mentions
- api/users/addtweet/'username-data'/'text-to-write-data'/'tags-data'/'username-data'
- api/users/addtweet/'username-data'/'text-to-write-data'/'tags-data'/'noname-data' (no mentions in the tweet)

To find a tweet with liked users by it's tweet id
- api/tweets/gettweetlikes/'tweet-id-data'

To find the mentions (users) on a tweet's id
- api/tweets/gettweetmentions/'tweet-id-data'

To find the tweets of users that a single users follows. Given the username of the user that follows other users
- api/tweets/gettweetsfollowing/'username-data'
