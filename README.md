# CS1632-Deliverable3
Reddit.com black box testing for Prof. Laboon's 1632 class

Tests the following feature files: 

1.	Feature: Logging In
a.	Scenario: Successful Log In
i.	Given a correct username and password
ii.	When the user tries to log in with these credentials
iii.	Then the user is logged in and can view post history
b.	Scenario: Invalid Username
i.	Given an invalid username and any password
ii.	When the user tries to log in with these credentials
iii.	Then the user is alerted they have a wrong password
c.	Scenario: Invalid Password
i.	Given a valid username and an invalid password
ii.	When the user tries to log in with these credentials
iii.	Then the user is alerted they have a wrong password
d.	Scenario: No User Account
i.	Given the user has not created an account
ii.	When the user tries to log in
iii.	Then the user has the option to create an account
2.	Feature: Posting Image Links
a.	Scenario: Successful Image Post
i.	Given a user has a link to an image
1.	And has typed a title
2.	And has correctly answered the CAPTCHA
ii.	When the user submits the image link
iii.	Then the user is directed to their posted image link
b.	Scenario: Invalid Post Title
i.	Given a user has a link to an image
1.	And has typed an invalid title
2.	And has correctly answered the CAPTCHA
ii.	When the user submits the image link
iii.	Then the user is alerted to fix the “title” field
c.	Scenario: Invalid CAPTCHA
i.	Given a user has a link to an image
1.	And has typed a title
2.	And has INCORRECTLY answered the CAPTCHA
ii.	When the user submits the image link
iii.	Then the user is alerted to attempt the CAPTCHA test again
d.	Scenario: Duplicate Link
i.	Given a user has a link to an image
1.	And has typed a title
2.	And has correctly answered the CAPTCHA
3.	And the link is already present in the subreddit selected
ii.	When the user submits the image link
iii.	Then the user is brought to the page of the existing link, alerting them it has already been submitted
e.	Scenario: Suggest Title
i.	Given a user has a link to an image
1.	And has NOT typed a title
ii.	When the user clicks the “suggest title” button
iii.	Then the post is given a title based on the link
3.	Feature: Comment Management
a.	Scenario: Post Comment
i.	Given a user is on a post’s page
1.	And the user has typed any characters into the comment box
ii.	When the user clicks the “save” button
iii.	Then their comment is added to the post’s thread
b.	Scenario: Delete Comment
i.	Given a user is on a post’s page 
1.	And the user has already commented on the post
ii.	When the user clicks the “delete” button
iii.	Then their comment is removed is removed from the post’s thread

c.	Scenario: Edit Comment
i.	Given a user is on a post’s page
1.	And the user has already commented on the post
ii.	When the user clicks the “edit” button
iii.	Then their comment is re-opened for editing
1.	And is saved upon clicking the “save” button
d.	Scenario: Reply Comment
i.	Given a user is on a post’s page
1.	And there exists comments on the post
ii.	When a user clicks the “reply” button
iii.	Then a dialogue appears for them to reply to a comment
1.	And is saved upon clicking the “save” button
e.	Scenario: Save Comment
i.	Given a user is on a post’s page
1.	And there exists comments on the post
ii.	When a user clicks the “save” button
iii.	Then the comment is added to their “saved” list
4.	Feature: Manage Subreddit Contributors
a.	Scenario: Add Contributor
i.	Given a moderator is on the “contributors” page
ii.	When a moderator adds a username to the approved submitters list
iii.	Then that user is now an approved contributor
b.	Scenario: Remove Contributor
i.	Given a moderator is on the “contributors” page
1.	And contributors exist for the subreddit
ii.	When a moderator clicks remove for any username
iii.	Then that user is no longer an approved contributor



c.	Scenario: Message Contributor
i.	Given a moderator is on the “contributors” page
1.	And contributors exist for the subreddit
ii.	When a moderator clicks “send message” for any username
iii.	Then the moderator is directed to a page where they can send a private message to that user
d.	Scenario: Search Contributors
i.	Given a moderator is on the “contributors” page
1.	And contributors exist for the subreddit
ii.	When a moderator searches for a username in the “jump to” box
iii.	Then the moderator will be given a list of approved contributors that match their search
5.	Feature: Manage Posts
a.	Scenario: Sticky Post
i.	Given a moderator is on a post’s page
ii.	When a moderator clicks the “sticky this post” button
iii.	Then the post will appear at the top of the subreddit automatically.
b.	Scenario: Unsticky Post
i.	Given a moderator is on a post’s page
1.	And that post is currently stickied
ii.	When a moderator clicks the “unsticky this post” button
iii.	Then the post will no longer appear at the top of the subreddit automatically.
c.	Scenario: Remove Post
i.	Given a moderator is on a post’s page
ii.	When a moderator clicks the “remove” button
iii.	Then the post will be removed from the subreddit
d.	Scenario: Approve Post
i.	Given a moderator is on a post’s page
ii.	When a moderator clicks the “approve” button
iii.	Then the post will be set to “approved” status
e.	Scenario: Approve Removed Post
i.	Given a moderator is on a post’s page
1.	And that post was previously removed
ii.	When a moderator clicks the “approve” button
iii.	Then the post will be made available again
1.	And the post will be set to “approved” status
