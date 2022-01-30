# BitcoinTickerWidget
Simple Bitcoin Price Widget that gets the bitcoin price using [https://blockchain.info/tobtc?currency=USD&value=1](https://www.blockchain.com/api) API.

## Technology and Stack:
  * Module by layer Architecture
  * MVVM as presenter layer design pattern
  * Kotlin Coroutines
  * Work Manager for periodic widget update

## Next Steps:
  * Adding resizing widget mechanism according to user prefrences
  * Adding other Crypto assets price
  * Adding widget configuration page for update intervals and crypto asset selection
  * Adding Android 12 features
  
## Known Issues:
  * `WidgetProvider` does not guarantee to call `onUpdate()` method for every `WidgetManaget.update` call. maybe it is better to create a custom broadcast receiver for critical updates.
