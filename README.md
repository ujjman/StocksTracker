<h1 align='center'> <img src='media/appicon.jpg' width='75' align='center'> Stocks Tracker</h1>

**Stocks Tracker** lets you to find details of stock on a particular date  
## Features
- Search for a Stock details
- Stores Recently Searched Stocks
- Tap on one of the Recently Searched Stock to get Details of that Stock on that particular day
- Get More Details (Low Price, High Price, Volume, etc.) of the Stock

## Installation

1. **Clone the Project:**
    1. Run ` git clone https://github.com/ujjman/StocksTracker ` in terminal
    1. Android Studio -> File -> Open
    

## Polygon API

Stocks Tracker uses [Polygon API](https://polygon.io/docs/stocks/getting-started) to get the details of the stock searched.
## Libraries

- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - For managing background threads
- [Volley](https://google.github.io/volley) - An HTTP library that makes networking for Android apps easier
- Jetpack
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store and manage UI-related data in a lifecycle conscious way
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder class
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
