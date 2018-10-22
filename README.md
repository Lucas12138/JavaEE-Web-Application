# 17682 Java EE Web Application

Keywords: Tomcat, MVC, GenericDAO, DBBean, FormBean, Transaction, MySQL, JSTL, JSP, Bootstrap, SQL injection, HTML injection

## HW1 Reflection

The first homework is a very basic one. The goal is to build a calculator with only frontend.

A javascript file, a CSS file and a HTML file are used.

The HTML is validated by: https://validator.w3.org/


## HW2 Reflection

In HW2, only backend code is allowed. It has the same functions with HW1.

To achieve this, PrintWriter is wildly used. Although it's still very far from a sophisticated web application, some concepts have been introduced here:

* Make sure the results of the functions are reasonable in the use case (e.g. case of divide by 0)
* Take care of missing or invalid parameters
* Use a list to keep track of errors
* Handle GET and POST requests differently

Those ideas are also embedded in the following tasks.   

## HW3 Reflection

HW3-5 will be not related to calculator. It's a blog website, which allow users to register, login, post, visit and comment.

HW3 is all about the frontend of the blog web application.
* "<welcome-file-list>" is used to define the welcome page
* "<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\"> in each JSP is used for favicon
* A little gif is added to make the UI look better
* A Bootstrap navbar is used for the user's blog space on the left of the UI
* Some Bootstrap classes are applied to achieve reactive and user-friendly frontend    

## HW4 Reflection


![alt text](https://github.com/Lucas12138/JavaEE-Web-Application/blob/master/hw4/web-service-architecture.png "web-service-architecture")

Inspired by this graph, a diagram about the achitecture of HW 4 is as follows:

![alt text](https://github.com/Lucas12138/JavaEE-Web-Application/blob/master/hw4/hw4-architecture.png "hw4-architecture")

This version only has very basic login and register functions.

Besides, the Navbar is using an UserBean array. Vistor page uses the index of the item of the array to change behavior.
This may lead to concurrency issues. It needs to be aware of in the HW5.

Apart from the MVC included, some tools like GenericDAO, DBBean, FormBean are added.

* GenericDAO has basic CRUD methods and transaction support. It can reduce the chance of SQL injection, too
* DBBean is a great style to manage the interaction between java and database
* FormBean is very helpful for the interaction with UI and java 

## HW5 Reflection

![alt text](https://github.com/Lucas12138/JavaEE-Web-Application/blob/master/hw5/comment-visitor-page-demo.png "omment-visitor-page-demo")

Above is a demo UI of comment on visitor page use case.

![alt text](https://github.com/Lucas12138/JavaEE-Web-Application/blob/master/hw5/hw5-architecture.png "hw5-architecture")

The HW5 improved not only the functionality but also the architecture.

It's no longer handling different requests with different servlets directly. Instead, a central controller will forward all the "*.do" style requests(avoid letting user guess the real resources) to the Action class.

The Action class is abstract and uses a hashmap to manage the specific actions(e.g. LoginAction, LogoutAction etc.). When is action is being performed, the hashmap will be synchronized to avoid potential consistency issues. The hashmap's distinct key nature will avoid two actions with same name mistake.

When the number of DAOs increases, it's good to have a centralized model class as well. It applies a composition style holding 3 different kinds of DAOs, namely UserDAO, PostDAO and CommentDAO. Then, model will be put into the controller and concrete DAOs will be put into actions.

There're also some other small pieces of improvement:
* Apply JSTL, so that we can remove all the java code in JSP(for separate of concern and let the UI more structural)
* Use hidden fields to facilitate the interaction between frontend and backend. In the meanwhile, backend also needs to check if the values collected from them are valid
* Avoid HTML injection with JSTL "c:out" tag and sanitize text in the HTML
* Make good use of transaction and avoid user commenting on non-existing posts, stray comments(delete all the comments belong to a post when the post is deleted) etc.

For future optimization:
* AJAX can be included(e.g. fetch posts)
* Use tomcat filter and hide the values in the hidden fields with CSRF token, signature or encryption
* Add more functionalities, such as user profile management, photos and videos etc.
* Enhance security with tomcat SSL  