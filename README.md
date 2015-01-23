bookmark.new
============

Bookmark.new is a Bookmarking tool for managing digital resources.. 
###Table of Contents  
* [Technologies Used][]
* [Features][]
* [Functionality][]
* [How to build][]

##<a name="Technology"></a>Technologies Used

* Cloud: Amazon Web Services (AWS)
* Database: Amazon DynamoDB
* Server-side: Java
* Web Framework: Spring
* Frontend Framework: AngularJS
* RWD: Bootstrap


##<a name="Features"></a>Features
Bookmark.new is a tool which helps users to save the meta data of the Resources and keep a track of the same, this application helps users to bookmark the Resources; that the user may not be able to  refer immediately. The user will be able to bookmark the resource with priority is yet another feature. If the resource is very important for the user , he can bookmark the resource with a high priority and if the resource is not very important for the user but need to remember the same then he can bookmark the resource with a low priority. The user can add notes of a particular resource after refferring the same can be done using this tool.The user will be able to book mark anytype of resource here like if he want to bookmark a song he can do that or he want to bookmark an important document of angular js he will be able to do that too.Moreover bookmark.new is a cloud enabled tool which saves the data into Dynamodb( The nosql service provided by amazon) which enhances fast and huge data saving .

User can sign up to this tool for the firts time and using the username and password provided he will be able to login after that. He can add ResourceGroups like github,stackoverflow,soundcloud etc in the ResourceGroup Screen and while adding the resource he can refer from which resourceGroup he got that particular resource, he can mention the path which may be a github location or w3schools location etc.A user can also  add anote after reading or going through a partcular resource  and can save that too.

##<a name="Functionality"></a>Functionality
* Registering a user
* Login for a user
* ResourceGroup: The locations where the Resources reside.
* Adding a ResourceGroup
* Editing an existing ResourceGroup
* Deleting an existing ResourceGroup
* Resource: The meta data of the digital resource is reffered as Resource here. A user can manage all type of resource according to his priority.
* Adding a resource
* Editing the details of an existing Resource
* Deleting an existing Resource
* Adding Notes: Notes can be added for a resource

##Screen Shots
* User Sign Up Screen
![Sign Up](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark1.PNG)<br/>

* User Sign In 
![Sign In](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark4.PNG)<br/>

* Resource Home Screen
Resource Home Screen will display all the resources saved by the logged  User in Descending Order of the Priority.
![Home](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark17.PNG)<br/>

* Resource Group Screen
![Resource Group](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark11.PNG)<br/>

* Resource Group Edit Screen
![Resource Group Edit](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark10.PNG)<br/>

* Resource Screen
![Resource](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark12.PNG)<br/>

*Resource Edit Screen 


* Notes Screen
![Notes](https://github.com/AccelNA/aws-coe/blob/master/contents/images/Bookmark/bookmark16.PNG)<br/>



##<a name="Build"></a>How to build
To build this application You need to have some configurations done in your Eclipse IDE
**Prerequisites.<br>**
* Eclipse version should be kepler or LUna, The most preffered version is Kepler
* It should haved Aws Toolkit for eclipse configured .Please refer this link for more details of installing the aws toolkit for eclipse  [http://aws.amazon.com/eclipse/](http://aws.amazon.com/eclipse/).
**Building the Application.<br>*
* Create a AWS JAVA Web Project 


[Technologies Used]: #Technology
[Features]: #Features
[Functionality]: #Functionality
[How to build]: #Build
