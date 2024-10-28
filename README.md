# Tag trace

REST API implemented in Spring Boot allowing clients to manage so-called "tags" which can be used to track personal
items. I created this project after reading [Get Your Hands Dirty on Clean Architecture](https://reflectoring.io/book/)
because I wanted to try out the suggestions in the book for myself. In fairness to the author, do not use this repo to
judge the ideas they present in the book. The more I have worked on it the more I have adapted
the author's suggestions to fit something I personally prefer.

## The idea

Tags are my idea for a way to track your belongings that sits somewhere between AirTags and writing your contact
information on good old-fashioned paper. Users can create "Tags" for something they don't want to lose. As of now the
tags
take the form of QR codes which users can print and attach to their items. If someone finds a lost item with a tag they
can scan the QR codes
which will alert the owner that the tag has been found. From there they can get in contact with the owner to help them
recover their item.

That is the idea but not everything is implemented as of now and I will likely change it in the future. 

