Android testing project for Netflix's reactive programming RxJava library. 
RxJava is a port from Microsoft's RxExtensions library.

RxJava: https://github.com/Netflix/RxJava

How to use / Implementation:
- 3 Fragments on a ViewPager. All of them are forced to be always on memory. We dont detach them ever.
- There is a button on the first one to send a query to Foursquare API for getting near locations to a city inserted by the user himself.
- The query is done through retrofit for getting an Observable in return.
- The other two fragments subscribe to the returned observable so they can react to changes on it.
