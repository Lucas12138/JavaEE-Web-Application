## HW4 Reflection


![alt text](https://github.com/CMU-J2EE/zizhel/blob/master/hw4/web-service-architecture.png "web-service-architecture")

Inspired by this graph, a diagram about the achitecture of HW 4 is as follows:

![alt text](https://github.com/CMU-J2EE/zizhel/blob/master/hw4/hw4-architecture.png "hw4-architecture")

This version only has very basic login and register functions.

Besides, the Navbar is using an UserBean array. Vistor page uses the index of the item of the array to change behavior.
This may lead to concurrency issues. It needs to be aware of in the HW5.
