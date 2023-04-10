# ChangeableRecycler

Video of created application:

https://user-images.githubusercontent.com/69115244/230915394-75186cc6-c808-4931-bf10-e3afa8a0ac94.mp4

<br />

The [task](https://github.com/avito-tech/bx-android-trainee-assigment):

We have a screen with RecyclerView, it has a list of two columns, initially 15 items.

Requirements:

1. The item has its number and a delete button that removes it, like a tile with the number and the button.

2. There is something asynchronous in the system which adds a new item every 5 seconds to a random position.

3. The item number keeps increasing.

4. Adds and deletes are done with animation (can be standard).

5. This whole system supports screen rotation and keeps working afterwards.

More complicated requirements:

1. Make a pool of numbers of deleted items and add new ones from the pool, and if it is empty just increase the number.

2. Make two columns in vertical position, four columns in horizontal position.
