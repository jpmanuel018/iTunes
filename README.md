# iTunes
  This simple app can search music tracks available in Philippines from iTunes Music API.
  
# Overview
  This app works by searching music and it will display the result. You can also view the details of the music by clicking it.
  
## Key-Value Pairs
  term=value&country=ph&media=music&limit=60
  
## Architectural Pattern
  The Architectural Pattern used in this app is MVVM (Model-View-ViewModel) with Clean Architecture. I used this pattern to maintain the code easily.
  
## Persistence Mechanism
  None
  
## Data Persisted
  None
  
## Third-Party Libraries
  - Retrofit
    - For API Calls
  - RecyclerView
    - For displaying the results from API as list
  - Glide
    - For loading images
  - Hilt
    - For dependency injection
  - Coroutines
    - For concurrency design pattern
