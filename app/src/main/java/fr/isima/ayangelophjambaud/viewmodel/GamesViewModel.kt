package fr.isima.ayangelophjambaud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.isima.ayangelophjambaud.models.Game
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {

    private val client = HttpClient(CIO) {
        install(JsonFeature){
            serializer = GsonSerializer(){
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
    }
    val items: MutableLiveData<List<Game>> by lazy {
        MutableLiveData<List<Game>>().also {
            loadItems()
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            val game:List<Game> = client.get("https://api.ph1lou.fr/getGames/0/30");
            items.postValue(game)
        }
    }


}