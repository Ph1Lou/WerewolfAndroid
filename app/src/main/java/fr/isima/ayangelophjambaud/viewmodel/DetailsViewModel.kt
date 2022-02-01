package fr.isima.ayangelophjambaud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.isima.ayangelophjambaud.models.Game
import fr.isima.ayangelophjambaud.models.PrettyEvent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

class DetailsViewModel(private val gameUUID: String) : ViewModel() {

    private val client = HttpClient(CIO) {
        install(JsonFeature){
            serializer = GsonSerializer {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
            install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
    }
    val items: MutableLiveData<List<PrettyEvent>> by lazy {
        MutableLiveData<List<PrettyEvent>>().also {
            loadItems()
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            val game: Game = client.get("https://api.ph1lou.fr/games/events/$gameUUID")
            items.postValue(game.prettyEvents)
        }
    }

}