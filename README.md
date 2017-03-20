# Assignment 2 - *NY Times Search (Android)*

**Build a New York Times News Search article app which allows a user to find old articles.

Submitted by: **RPrasad**

Time spent: **24** hours spent in total


##User Stories:

The following user stories must be completed:

* [X] User can enter a search query that will display a grid of news articles using the thumbnail and headline from the New York Times Search API. (3 points)
* [X] User can click on "filter" icon which allows selection of advanced search options to filter articles. (3 points)
* [X]An example of a query with filters (begin_date, sort, and news_desk) applied can be found here. Full details of the API can be found on this article
* [X] Subsequent searches will have any filters applied to the search results. (1 point)
* [X] User can tap on any article in results to view the contents in an embedded browser. (2 points)
* [X] User can scroll down "infinitely" to continue loading more news articles. The maximum number of articles is limited by the API search. (1 point)

#The following advanced user stories are optional but recommended:

* [X] Robust error handling, check if internet is available, handle error cases, network failures. (1 point)
* [X] Use the ActionBar SearchView or custom layout as the query box instead of an EditText. (1 point)
* [X] User can share a link to their friends or email it to themselves. (1 point)
* [X] Replace Filter Settings Activity with a lightweight modal overlay. (2 points)
* [X] Improve the user interface and experiment with image assets and/or styling and coloring (1 to 3 points depending on the difficulty of UI improvements)
* [X] Stretch: Use the RecyclerView with the StaggeredGridLayoutManager to display improve the grid of image results (see Picasso guide too). (2 points)
* [] Stretch: For different news articles that only have text or have text with thumbnails, use Heterogenous Layouts with RecyclerView. (2 points)
* [X] Stretch: Use Parcelable instead of Serializable using the popular Parceler library. (1 point)
* [] Stretch: Replace all icon drawables and other static image assets with vector drawables where appropriate. (1 point)
* [X] Stretch: Leverage the data binding support module to bind data into one or more activity layout templates. (1 point)
* [X] Stretch: Replace Picasso with Glide for more efficient image rendering. (1 point)
* [] Stretch: Switch to using retrolambda expressions to cleanup event handling blocks. (1 point)
* [X] Stretch: Leverage the popular GSON library to streamline the parsing of JSON data. (1 point)
* [X] Stretch: Consume the New York Times API using the popular Retrofit networking library instead of Android Async HTTP. (3 points)
* [X] Stretch: Replace the embedded WebView for viewing news articles with a Chrome Custom Tab using a custom action icon for sharing. (2 po






## Video Walkthrough 

Here's a walkthrough of implemented user stories:

![Video Walkthrough](nytimes.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [2017] [RPRASAD]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
