# HTTP Methods

## Goals

- Learn about web browser developer tools
- Use developer tools to inspect network traffic
- Learn about HTTP requests and responses


## Enabling Developer Tools

When you click around the ToDone UI you'll see that nothing works. The reason for this is that the backend is not fully implemented. 

We can use the *developer tools* built into every web browser to help diagnose this problem. To access the developer tools:

- in Firefox, right click on the ToDone web page and choose Inspect Element" from the dropdown menu;
- in Chrome, right click on the ToDone web page and choose "Inspect" from the dropdown menu; and
- in Safari, follow [Apple's instructions][safari-dev-tools] to enable the "Develop" menu.


## Inspecting Network Requests

Using the web developer tools we can inspect requests sent over the network. Open the "Network" tab in the web developer tools, and then click on the button to complete a task in the ToDone user interface. In the network tab you should see a request sent to `http://localhost:3000/` for a file or name (depending on the web browser) `close`. If you look at the response you'll see the backend responds with a `404 Not Found`.


## HTTP Requests and Responses

To understand what's going on we need to know a bit about the *Hypertext Transfer Protocol (HTTP)*, which is the protocol the web browser and the web server use to talk.

The full details of HTTP are defined in [*Request for Comment 7230*][rfc-7230], which is a fairly dense read. You can find many other descriptions online.

Right now we need to know a request consists of:

- a [*URL*][url] such as `http://localhost:3000/api/task/1/close`;
- a *method* such as `GET` or `POST`; and
- an optional *body* which contains extra information for the request.

We can think of the URL as the address of a web page. The method tells the backend what what the frontend wants to do. A `GET` method means the backend should send that page to the frontend. A `POST` requests means the frontend is sending some information to the backend. Our example is a `POST` request to `http://localhost:3000/api/task/1/close`, which is telling the backend to close the task with ID 1.

A response has three main parts:

- a status code such as 200 or 404;
- informative text that describes the status code; and
- an optional body that gives more information.

The 200 status code means the server has accepted the request and everything is ok. The 404 status code means that the backend couldn't find anything at the given URL. In our example the backend responds with a 404, meaning the backend doesn't (yet) understand how to handle a request to `/api/tasks/1/close`. The other common status code is 500, which indicates something went wrong on the backend while handling a request.

[safari-dev-tools]: https://support.apple.com/en-gb/guide/safari/sfri20948/mac
[rfc-7230]: https://tools.ietf.org/html/rfc7230
[url]: https://en.wikipedia.org/wiki/URL
